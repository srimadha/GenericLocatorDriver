package com.sri.mousepointer.actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.sri.pointer.utilities.ActionCode;
import com.sri.pointer.utilities.EventGenerator;
import com.sri.pointer.utilities.IncrementXY;
import com.sri.pointer.utilities.PointerChangeListener;
import com.sri.pointer.utilities.PointerPosition;
import com.sri.pointer.utilities.PositionQueue;
public class MouseXY implements PointerChangeListener{
	
	private static int LEFT =  InputEvent.BUTTON1_DOWN_MASK;
	private static int RIGHT = InputEvent.BUTTON3_DOWN_MASK;
	
	private static Robot robot;
	private static PointerPosition pointer = new PointerPosition();
	
	public MouseXY(){
		EventGenerator.addActionListener(this);
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
			IncrementXY incr = PositionQueue.q.remove();
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
	
	private void scroll(IncrementXY incr){
		robot.mouseWheel(incr.getY());
	}
	private void pointerMove(IncrementXY incr){
		pointer.add(incr);
		robot.mouseMove(pointer.getX(), pointer.getY());
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
}
