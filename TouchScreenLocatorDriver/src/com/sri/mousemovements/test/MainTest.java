package com.sri.mousemovements.test;

import java.util.Random;

import com.sri.mousepointer.actions.MouseXY;
import com.sri.pointer.utilities.ActionCode;
import com.sri.pointer.utilities.EventGenerator;
import com.sri.pointer.utilities.IncrementXY;
import com.sri.pointer.utilities.PositionQueue;


public class MainTest{
	
	public static final int INTERVAL_IN_MS = 5;	 

	public static void main(String[] args) throws Exception{
		Random random = new Random();
		new MouseXY();
		for(int i=0;i<800;i++){
			int x = random.nextInt(5)-2;
			int y = random.nextInt(5)-2;
			
			IncrementXY newIncr = new IncrementXY();
			newIncr.setX(x);
			newIncr.setY(y);
			newIncr.setActionCode(ActionCode.MOVE_PTR);
			newIncr.setPriority(0);
			PositionQueue.q.add(newIncr);
			
			EventGenerator.inform();
			Thread.sleep(INTERVAL_IN_MS);
		}
		IncrementXY newIncr = new IncrementXY();
		newIncr.setX(1);
		newIncr.setY(1);
		newIncr.setActionCode(ActionCode.RIGHT_CLICK);
		newIncr.setPriority(0);
		PositionQueue.q.add(newIncr);
		EventGenerator.inform();
	}
}
