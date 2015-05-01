package com.sri.pointer.utilities;

import java.util.ArrayList;

public class PointerEventObserver {
	
	static ArrayList<PointerEventListener> listenerList = new ArrayList<PointerEventListener>();
	
	public static void addActionListener(PointerEventListener listener) {
		listenerList.add(listener);
	}

	public static void inform() {
		for(PointerEventListener listener : listenerList)
			listener.delegateToCommands();
	}
}
