package com.sri.connectivity.wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.sri.pointer.utilities.PointerEventObserver;
import com.sri.pointer.utilities.RelativePosition;
import com.sri.pointer.utilities.PositionQueue;

public class LocatorClientEventsDelegator extends Thread {
    private Socket socket;
    private BufferedReader in;

    public LocatorClientEventsDelegator(Socket socket) {
        this.socket = socket;
    }

    /**
	 * @param input
	 */
	public static void renderPointer(String input) throws Exception{
		String xy[] = input.split(",");
		RelativePosition newIncr = new RelativePosition();
		newIncr.setX(Integer.parseInt(xy[0]));
		newIncr.setY(Integer.parseInt(xy[1]));
		newIncr.setActionCode(Integer.parseInt(xy[2]));
		newIncr.setPriority(Integer.parseInt(xy[3]));
		PositionQueue.q.add(newIncr);		
		PointerEventObserver.inform();
	}

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }    
                try{
                	renderPointer(input);
                }catch(Exception e){
                	System.out.println("Incorrect Format : " + input);
                	e.printStackTrace();
                }
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    }
}