package com.sri.mousepointer.actions;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.sri.pointer.utilities.ActionCode;
import com.sri.pointer.utilities.PointerEventObserver;
import com.sri.pointer.utilities.RelativePosition;
import com.sri.pointer.utilities.PointerEventListener;
import com.sri.pointer.utilities.AbsolutePosition;
import com.sri.pointer.utilities.PositionQueue;
public class Locator implements PointerEventListener{
	
	private static int LEFT =  InputEvent.BUTTON1_DOWN_MASK;
	private static int RIGHT = InputEvent.BUTTON3_DOWN_MASK;
	
	private static Robot robot;
	private static AbsolutePosition pointer = new AbsolutePosition();
	
	public Locator(){
		PointerEventObserver.addActionListener(this);
		pointerMove();
	}
	static{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}  
		
	}
	@Override
	public void delegateToCommands() {
		try{
			RelativePosition incr = PositionQueue.q.remove();
			switch(incr.getActionCode()){
				case ActionCode.MOVE_PTR : pointerMove(incr); break;
				case ActionCode.SINGLE_CLICK : leftClick(); break;
				case ActionCode.DBL_CLICK : doubleClick(); break;
				case ActionCode.RIGHT_CLICK : rightClick(); break;
				case ActionCode.SCROLL : scroll(incr); break;
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private void scroll(RelativePosition incr){
		robot.mouseWheel(incr.getY());
	}
	
	private void pointerMove(){
		robot.mouseMove(pointer.getX(), pointer.getY());
	}
	private void pointerMove(RelativePosition incr){
		getCurrentPosition().add(incr);
		pointerMove();
	}

	private void rightClick(){
		robot.mousePress(RIGHT);
		robot.mouseRelease(RIGHT);
		
	}
	
	private void doubleClick() {
		leftClick();
		leftClick();
		
	}
	private void leftClick() {
		robot.mousePress(LEFT);
		robot.mouseRelease(LEFT);
	}
	

	/**
	 * 
	 */
	private AbsolutePosition getCurrentPosition() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		pointer.setX(p.x);
		pointer.setY(p.y);
		return pointer;
	}
	
}
