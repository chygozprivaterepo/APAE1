package AE1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class TrackPortion {
	
	//instance variables
	protected Integer length;
	protected Integer capacity;
	protected Integer position;
	//protected int currentNumber;
	protected List<Train> currentTrains;
	private ReentrantLock lock = new ReentrantLock();
	private Condition segmentFull = lock.newCondition();
	
	public TrackPortion(Integer l, Integer c, Integer pos){
		this.length = l;
		this.capacity = c;
		this.position =  pos;
		//currentNumber = 0;
		currentTrains = new ArrayList<Train>();
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
	
	public void enter(Train t, TrackPortion previous){
		lock.lock();
		try
		{
			System.out.println("Train "+t.getNumber()+" wants to enter "+this.getClass()+" "+position);
			while(currentTrains.size() == capacity){
				System.out.println(this.getClass() + " " + position + " is full. Train "+t.getNumber()+" could not enter");
				//System.out.println("Segment " + position + " has "+ currentNumber + " trains in it");
				segmentFull.await();
				
			}
			if(previous != null){
				previous.leave(t);
			}
			t.setPosition(this);
			currentTrains.add(t);
			System.out.println("Train "+t.getNumber()+" has entered "+this.getClass()+" "+position);
			//currentNumber++;
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void leave(Train t){
		lock.lock();
		try{
			//t.setPosition(null);
			//currentNumber--;
			currentTrains.remove(t);
			segmentFull.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public ReentrantLock getLock(){
		return lock;
	}
	
	public Condition getCondition(){
		return segmentFull;
	}
	
	public List<Train> getTrains(){
		return currentTrains;
	}
	public abstract String toString();
}
