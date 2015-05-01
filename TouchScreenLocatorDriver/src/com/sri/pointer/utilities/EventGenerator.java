package com.sri.pointer.utilities;

import java.util.ArrayList;

public class EventGenerator {
	
	static ArrayList<PointerChangeListener> listenerList = new ArrayList<PointerChangeListener>();
	
	public static void addActionListener(PointerChangeListener listener) {
		listenerList.add(listener);
	}

	public static void inform() {
		for(PointerChangeListener listener : listenerList)
			listener.delegateToCommands();
	}
}
