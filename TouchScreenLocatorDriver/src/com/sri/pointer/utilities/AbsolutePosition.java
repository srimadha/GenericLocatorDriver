package com.sri.pointer.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class AbsolutePosition {
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static double MAX_X = screenSize.getWidth();
	private static double MAX_Y = screenSize.getHeight();
	private static double MIN_X = 0;
	private static double MIN_Y = 0;
	
	private int x;
	private int y;

	public AbsolutePosition(){
		 this.x = (int) (MAX_X/2);
		 this.y = (int) (MAX_Y/2);
	}
	public AbsolutePosition(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void add(RelativePosition incr){
		double newXPos = this.x + incr.getX();
		double newYPos = this.y + incr.getY();
		
		this.x = (int) ((newXPos)>MAX_X?MAX_X:newXPos<0?MIN_X:newXPos);
		this.y = (int) ((newYPos)>MAX_Y?MAX_Y:newYPos<0?MIN_Y:newYPos);
		
	}
	
}
