// Author: Michal Pasternak
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Referee {
    static SocketCommunicator sc = new SocketCommunicator();
    static double RoverX = 0;
    static double RoverZ = 0;
    static double TargetX = 0;
    static double TargetZ = 0;
    static double innerDiameter = 2;
    static double outerDiameter = 5;
    static int tooClose = 0;
    static int tooFar = 0;
    static int inZone = 0;
    static int rate = 1; // time in ms between data captures
    static String IP;
    static String pname = "Rover";
    static String tname = "Target";
    static int Port;
    static int serverPort;
    static int counter=0;
    static int runTime = 60000; // in ms

    public static void main(String[] args) throws Exception {

        //------ Load config
        List<String> conf = readFile("config.txt");
        Iterator<String> conIt = conf.iterator();

        while (conIt.hasNext()) {
            String a = conIt.next();
            if (a.contains("=") == true) {
                String b = a.substring(0, a.indexOf('='));
                if (b.equals("name"))
                    tname = a.substring(a.indexOf('=')+1);
                if (b.equals("simulationIP"))
                    IP = a.substring(a.indexOf('=')+1);
                if (b.equals("innerDiameter"))
                    innerDiameter = Integer.parseInt(a.substring(a.indexOf('=')+1));
                if (b.equals("outerDiameter"))
                    outerDiameter = Integer.parseInt(a.substring(a.indexOf('=')+1));
                if (b.equals("control3Port"))
                    Port = Integer.parseInt(a.substring(a.indexOf('=')+1));
                if (b.equals("playerName"))
                    pname = a.substring(a.indexOf('=')+1);
                if (b.equals("rate"))
                    rate = Integer.parseInt(a.substring(a.indexOf('=')+1));
                if (b.equals("refereePort"))
                    serverPort = Integer.parseInt(a.substring(a.indexOf('=')+1));
            }
        }

        //------Connect to simulation
        try {
            sc.connectToServer(IP, Port);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        //------initialize shared mem
        Synch.mutex = new Semaphore(1,true);
        Synch.tarx = new Semaphore(1,true);
        Synch.tarz = new Semaphore(1,true);
        Synch.dist = new Semaphore(1,true);
        Synch.wait = new Semaphore(0,true);
        Synch.available = false;


        //------start the referee server

        SocketServer ss = new SocketServer(serverPort);
        ss.start();
        targetControl target = new targetControl();
        target.load();

        //------- wait for connection of rover controller
        Synch.wait.acquire();

        //------- start target controller

        target.start();

        //------- start Referee

        long startTime = System.currentTimeMillis();


        while((System.currentTimeMillis()-startTime)<= runTime){
            // -- check the simulation for positions
            counter++;
            long time = (counter*rate)-(System.currentTimeMillis()-startTime);
            // -- wait if we are faster then rate of recording
            if (time>0){
            try {
                Thread.sleep(time);
                } catch (InterruptedException ex) {
                System.out.println(ex);
            }
          }
            getPositions();
            double dist = calcDist();
            System.out.println("Current distance: "+dist);

            //-- record the values in shared mem
            Synch.tarx.acquire();
            Synch.mutex.acquire();
            if (TargetX!=Synch.targetx) {
                Synch.targetx = TargetX;
                Synch.available = true;
            }
            Synch.mutex.release();
            Synch.tarx.release();

            Synch.tarz.acquire();
            Synch.mutex.acquire();
            if (TargetZ!=Synch.targetz) {
                Synch.targetz = TargetZ;
                Synch.available = true;
            }
            Synch.mutex.release();
            Synch.tarz.release();

            Synch.dist.acquire();
            Synch.mutex.acquire();
            if (dist!=Synch.distance) {
                Synch.distance = dist;
                Synch.available = true;
            }
            Synch.mutex.release();
            Synch.dist.release();
            // count scores for time step
            if(dist<innerDiameter){
                // too close
                tooClose++;
            }
            else if(dist>outerDiameter){
                //too far
                tooFar++;
            }
            else {
                // in the zone
                inZone++;
            }
        }
        // --- score logging
        System.out.println("Round finished");
        System.out.println("--------------------");
        System.out.println("percent of time in zone: "+calcPercent(inZone,tooClose,tooFar)+"%");
        System.out.println("percent of time too close: "+calcPercent(tooClose,inZone,tooFar)+"%");
        System.out.println("percent of time too far: "+calcPercent(tooFar,inZone,tooClose)+"%");
        System.out.println("--------------------");

    }

    public static void getPositions() { // update x and z positions of rover and target
        TargetX = extractDouble(sc.send(tname+",GPSx()"));
        TargetZ = extractDouble(sc.send(tname+",GPSz()"));
        RoverX = extractDouble(sc.send(pname+",GPSx()"));
        RoverZ = extractDouble(sc.send(pname+",GPSz()"));
    }

    public static double calcDist(){ // calculate the distance on the x-z plane
        double difX = (TargetX-RoverX);
         double difZ = (TargetZ-RoverZ);
         double dist = Math.sqrt((difX*difX)+(difZ*difZ));
        return dist;
    }
    public static String calcPercent(double num1,double num2,double num3){ // calculate percent to 4 decimal places
      double val = (100*(num1/(num1+num2+num3)));
      DecimalFormat df = new DecimalFormat("#.###");
      String percent = df.format(val);
        return percent;
    }
    public static double extractDouble(String s){ // extract double from unity reply message
        s = s.substring(s.indexOf(',')+1,s.indexOf(';'));
        return Double.parseDouble(s);
    }

    // file read
    public static List<String> readFile(String fileName) {
        int count = 1;
        File file = new File(fileName);
        // this gives you a 2-dimensional array of strings
        List<String> data = new ArrayList<>();
        Scanner inputStream;
        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                data.add(inputStream.next());
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
