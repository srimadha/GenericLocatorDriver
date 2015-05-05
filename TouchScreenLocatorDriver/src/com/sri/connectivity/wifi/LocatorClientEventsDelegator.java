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

    static int oldX = 0;
    static int oldY = 0;
    public LocatorClientEventsDelegator(Socket socket) {
        this.socket = socket;
    }

    /**
	 * @param input
	 */
	public static void renderPointer(String input) throws Exception{
		String xy[] = input.split(",");
		
		int newx = (int)(Float.parseFloat(xy[0]));
		int newy = (int)(Float.parseFloat(xy[1]));
		
		int incrX = newx - oldX;
		int incrY = newy - oldY;
		
		if(incrX>15) incrX = 1;
		if(incrY>15) incrY = 1;
		if(incrX<-15) incrX = -1;
		if(incrY<-15) incrY = -1;
		
		RelativePosition newIncr = new RelativePosition();
		newIncr.setX(incrX);
		newIncr.setY(incrY);
	
		System.out.println(incrX +","+incrY+","+Integer.parseInt(xy[2]));
		newIncr.setActionCode(Integer.parseInt(xy[2]));
		newIncr.setPriority(Integer.parseInt(xy[3]));
		PositionQueue.q.add(newIncr);		
		PointerEventObserver.inform();
		oldX = newx;
		oldY = newy;
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