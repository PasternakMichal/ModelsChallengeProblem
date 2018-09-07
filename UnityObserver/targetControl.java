// Author: Michal Pasternak
import java.io.IOException;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.System;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class targetControl extends Thread {
    public static String name = "Target";
    public static String IP = "127.0.0.1";
    public static String filename = "test.csv";
    public static int Port = 8887;
    public static int runTime = 60000; // in ms
    public static int waitMin = 200; //0-inf
    public static int waitMax = 500; //0-inf
    public static int speedMin = 20; //0-100
    public static int speedMax = 75; //0-100
    public static int straightPercent = 75; // 0-100
    public static List<String> moves = new ArrayList<>();
    public static boolean preRec = true;

    private static SocketCommunicator rover = new SocketCommunicator();
    private static Random rand = new Random();

    public targetControl() {
    }

    public static void load() {

        // --- read the config file
        List<String> conf = readFile("Settings/config.txt");
        Iterator<String> conIt = conf.iterator();

        while (conIt.hasNext()) {
            String a = conIt.next();
            if (a.contains("=") == true) {
                String b = a.substring(0, a.indexOf('='));
                //if (b.equals("name"))
                //    name = a.substring(a.indexOf('=') + 1);
                if (b.equals("simulationIP"))
                    IP = a.substring(a.indexOf('=') + 1);
                if (b.equals("fileName"))
                    filename = a.substring(a.indexOf('=') + 1);
                if (b.equals("leaderPort"))
                    Port = Integer.parseInt(a.substring(a.indexOf('=') + 1));
                if (b.equals("runTime"))
                    runTime = Integer.parseInt(a.substring(a.indexOf('=') + 1));
                if (b.equals("waitMin"))
                    waitMin = Integer.parseInt(a.substring(a.indexOf('=') + 1));
                if (b.equals("waitMax"))
                    waitMax = Integer.parseInt(a.substring(a.indexOf('=') + 1));
                if (b.equals("speedMin"))
                    speedMin = Integer.parseInt(a.substring(a.indexOf('=') + 1));
                if (b.equals("speedMax"))
                    speedMax = Integer.parseInt(a.substring(a.indexOf("=") + 1));
                if (b.equals("straightPercent"))
                    straightPercent = Integer.parseInt(a.substring(a.indexOf("=") + 1));
                if (b.equals("preRecorded"))
                    preRec = Boolean.parseBoolean(a.substring(a.indexOf("=") + 1));
            }
        }

        //-----connect to rover
        try {
            System.out.println("connecting to: " + IP + " on port: " + Port);
            rover.connectToServer(IP, Port);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {

        //--- get start time
        long startTime = System.currentTimeMillis();

        if (preRec == true) {

            // -------- if pre recorded --------

            // load the array
            Iterator<String> commands = readFile(filename).iterator();

            while (commands.hasNext()) {
                String line = commands.next();
                if (line.contains("wait") == true) {
                    try {
                        Thread.sleep(Integer.parseInt(line.substring(line.indexOf(",") + 1)));
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                } else {
                    rover.noReply(name + line);
                    System.out.println(name + line);
                }
            }
        } else {

            // -------- if random --------

            speedMax = speedMax - speedMin + 1;
            int adder = speedMin;
            //run for set time
            while ((System.currentTimeMillis() - startTime) <= runTime) {
                // ---set drive
                int x = rand.nextInt(speedMax) + adder;
                int z = 0;
                if (rand.nextInt(100) <= straightPercent) {
                    z = x;
                } else {
                    z = rand.nextInt(speedMax) + adder;
                }
                //record

                //execute
                setLR(x, z);

                // ---wait

                int w = rand.nextInt(waitMax - waitMin + 1) + waitMin;
                // record

                //execute
                try {
                    Thread.sleep(w);
                    moves.add("wait," + w);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            saveFile(filename, moves);
        }
        //--wrap up
        setLR(0, 0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        // stop rover
        Brake(100);
        System.out.println("End of leader movement.");

    }


    //---- command format
    public static void setLR(int x, int y) {
        rover.noReply(name + ",setLRPower(" + x + "," + y + ")");
        moves.add(",setLRPower(" + x + "," + y + ")");
    }

    public static void Brake(int x) {
        rover.noReply(name + ",brake(" + x + ")");
        System.out.println(name + ",brake(" + x + ")");
    }

    public static void unBrake() {
        rover.noReply(name + ",brake(0)");
        System.out.println(name + ",brake(0)");
    }

    //--- utils
    // file save
    public static void saveFile(String name, List<String> outdata) {
        try {
            PrintWriter out = new PrintWriter(name);
            Iterator<String> it = outdata.iterator();
            while (it.hasNext()) {
                out.println(it.next());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // file read
    public static List<String> readFile(String fileName) {
        int count = 1;
        File file = new File(fileName);
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
