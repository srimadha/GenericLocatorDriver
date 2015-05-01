package com.sri.pointer.utilities;
import java.util.Comparator;
import java.util.PriorityQueue;



public class PositionQueue {
	
	
	static class PQPrefComparator implements Comparator<RelativePosition> {
		 
		public int compare(RelativePosition one, RelativePosition two) {
			return two.getPriority() - one.getPriority();
		}
	}
	
	
	public static PriorityQueue<RelativePosition> q = new PriorityQueue<RelativePosition>(10, new PQPrefComparator());

}
