package AE1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * abstract class to represent a part of the railway
 * @author Chigozie Ekwonu
 *
 */
public abstract class TrackPortion {
	
	//instance variables
	protected int length; //represents the length of the portion
	protected int capacity; //represents the capacity of the portion
	protected List<Train> currentTrains; //stores the current number of trains in the portion
	private ReentrantLock lock = new ReentrantLock(); //lock for the portion
	private Condition segmentFull = lock.newCondition(); //condition for the lock
	
	/**
	 * constructor to create a track portion 
	 * @param l the length of the portion
	 * @param c the capacity of the portion
	 */
	public TrackPortion(int l, int c){
		this.length = l;
		this.capacity = c;
		currentTrains = new ArrayList<Train>(); //initializes the currentTrains list
	}
	
	/**
	 * accessor method to return the length of the portion
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * method to add a train to the track portion and set the train's position
	 * @param t the train to be added
	 * @param previous the train's previous position
	 */
	public void enter(Train t, TrackPortion previous){
		lock.lock(); //lock the portion
		try
		{
			System.out.println("Train "+t+" wants to enter "+this.getClass()+" "+(t.getTrack().getPortions().indexOf(this)+1));
			while(currentTrains.size() == capacity){ //if the portion is currently full,
				System.out.println(this.getClass() + " " + (t.getTrack().getPortions().indexOf(this)+1) + " is full. Train "+t+" could not enter");
				System.out.println("Segment " + (t.getTrack().getPortions().indexOf(this)+1) + " has "+ currentTrains.size() + " trains in it");
				segmentFull.await(); //wait for a train to leave the portion
			}
			//at this point, the portion's capacity is not full
			if(previous != null){ //if the train had a previous position (that is it is not on the first portion of the track)
				previous.leave(t); //remove the train from that portion. At this point, since the train has a lock on the portion
								//that it wants to enter, no other train can enter that position. The train at this point may not 
								//appear on the railway status as printed by the railway printer but the train will very likely enter
								//the next portion unless something else goes wrong that is not related to deadlocks.
			}
			Thread.sleep(2000);
			
			currentTrains.add(t); //add the train to this portion's currentTrain list	
			t.setPosition(this); //set the train's position to this portion	
			System.out.println("Train "+t+" has entered "+this.getClass()+" "+(t.getTrack().getPortions().indexOf(this)+1));
		}
		catch(InterruptedException e){
			System.out.println("Wait condition was interrupted unexpectedly");
		}
		finally{
			lock.unlock(); //unlock the portion
		}
	}
	
	/**
	 * method to remove a train from a portion
	 * @param t the train to be removed
	 */
	public void leave(Train t){
		lock.lock(); //lock the portion
		try{
			currentTrains.remove(t); //remove the train
			segmentFull.signalAll(); //signal any threads waiting on the segmentFull condition of the portion to re-check 
		}finally{
			lock.unlock(); //unlock the portion
		}
	}
	
	public void isFull(){
		lock.lock();
		try{
			while(currentTrains.size() == capacity){
				segmentFull.await();
			}
		}catch(InterruptedException e){
		}finally{
			lock.unlock();
		}
	}
	
	public abstract String toString();
}
