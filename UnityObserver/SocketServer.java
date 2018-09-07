// Author: Michal Pasternak
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class SocketServer extends Thread {
// server thread for providing access to target rover position
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket = null;
    public int port;
    private double x;
    private double z;
    private double d;

    public SocketServer(int p) {
      System.out.println("Observation server open on port: "+p);
        port = p;
    }

    @Override
    public void run(){
        try {
            openServer(port);
        }
        catch (Exception e){}

    }

    public void interupt(){
        System.exit(0);
    }

    public void openServer(int portNum) throws IOException {

        try (
                ServerSocket serverSocket = new ServerSocket(portNum);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine, outputLine;

            // Initiate conversation with client
            while (true) {
                inputLine = in.readLine();
                if (inputLine != null) {
                    try {
                        out.println(translate(inputLine));
                    }
                    catch(Exception E) {}
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

 // -- read the x,z, and distance from the shared memory upon recieving message
    public String translate(String in) throws Exception{
        if (in.equals("ready")==true) {
            Synch.wait.release();
            return "Simulation Started";
        }
            if (in.equals("Leader,GPS()")==true) {
                Synch.mutex.acquire();
                if (Synch.available == true) {
                    Synch.mutex.release();
                    Synch.tarx.acquire();
                    x = Synch.targetx;
                    z = Synch.targetz;
                    Synch.tarx.release();
                } else {
                    Synch.mutex.release();
                  }
                  Synch.mutex.acquire();
                  if (Synch.available == true) {
                      Synch.mutex.release();
                      Synch.tarz.acquire();
                      z = Synch.targetz;
                      Synch.tarz.release();
                  } else
                      Synch.mutex.release();

                return "Leader,"+x+","+z+";";
            }
            else if(in.equals("Leader,Distance()")==true){
                Synch.mutex.acquire();
                if(Synch.available == true){
                    Synch.mutex.release();
                    Synch.dist.acquire();
                    d = Synch.distance;
                    Synch.dist.release();
                }
                else
                    Synch.mutex.release();
                return "Leader,"+d+";";
            }
            else
                return null;
        }


}
