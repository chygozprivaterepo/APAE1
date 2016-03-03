package AE1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class TrackPortion {
	
	//instance variables
	protected Integer length;
	protected Integer capacity;
	protected Integer position;
	private ReentrantLock lock = new ReentrantLock();
	private Condition segmentFull = lock.newCondition();
	
	public TrackPortion(Integer l, Integer c, Integer pos){
		this.length = l;
		this.capacity = c;
		this.position =  pos;
	}
	
	public Integer getLength() {
		return length;
	}

	public Integer getCapacity() {
		return capacity;
	}
	
	public Integer getPosition(){
		return position;
	}
	
	
	public ReentrantLock getLock(){
		return lock;
	}
	
	public Condition getCondition(){
		return segmentFull;
	}
	
	public abstract String toString();
}
