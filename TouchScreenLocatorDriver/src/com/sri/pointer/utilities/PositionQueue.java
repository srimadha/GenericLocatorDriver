package com.sri.pointer.utilities;
import java.util.Comparator;
import java.util.PriorityQueue;



public class PositionQueue {
	
	
	static class PQPrefComparator implements Comparator<IncrementXY> {
		 
		public int compare(IncrementXY one, IncrementXY two) {
			return two.getPriority() - one.getPriority();
		}
	}
	
	
	public static PriorityQueue<IncrementXY> q = new PriorityQueue<IncrementXY>(10, new PQPrefComparator());

}
