package com.sri.connectivity.wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;


public class DataClientTest {

    BufferedReader in;
    PrintWriter out;

    public DataClientTest() {

       
      
    }

    private String getServerAddress() {
        return "NW-8677LM";
    }

    
	public static final int INTERVAL_IN_MS = 50;	 
    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        Random random = new Random();
        for(int i=0;i<800;i++){
			int x = random.nextInt(5)-2;
			int y = random.nextInt(5)-2;
        	out.println(x+","+y+",0,0");   
        	try {
				Thread.sleep(INTERVAL_IN_MS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        DataClientTest client = new DataClientTest();
        client.run();
    }
}