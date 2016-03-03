package AE1;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RailTrack {

	//instance variables
	private List<TrackPortion> segments;
	private List<Train> trains;
	
	public RailTrack(){
		segments = new ArrayList<TrackPortion>();
		trains = new ArrayList<Train>();
	}
	
	public void addTrackPortion(TrackPortion s){
		segments.add(s);
	}
	
	public void addTrain(Train t){
		TrackPortion segment = segments.get(0);
		ReentrantLock lock = segment.getLock();
		Condition segmentFull = segment.getCondition();
		lock.lock();
		try{
			while(getTrainsAtTrackPortion(segments.get(0)).size() == segment.getCapacity()){
				segmentFull.await();
				System.out.println("Segment 1 is currently full and awaiting free track");
			}
			t.setPosition(segment);
			trains.add(t);
			System.out.println("Train "+t.getNumber()+ " has been added");
		}
		catch(InterruptedException e){}
		finally{
			lock.unlock();
		}
	}
	
	public void advanceTrain(Train t){
		try
		{
			TrackPortion currPosition = t.getPosition(); //get train's current position
			int currPosIndex = segments.indexOf(currPosition); //get index of the current position in the segment's list
			int nextPosIndex = ++currPosIndex;
			//ReentrantLock currentLock = currPosition.getLock();
			//currentLock.lock();
			
			if(nextPosIndex == segments.size()) //if train has passed end of railway, remove train from rail track
			{
				//currentLock.unlock();
				t.setPosition(null);
				removeTrain(t);
				return;
			}
			TrackPortion nextPosition = segments.get(nextPosIndex); //get train's next position
			Condition nextCondition = nextPosition.getCondition();
			ReentrantLock nextLock = nextPosition.getLock();
			nextLock.lock();	
			while(nextPosition.getCapacity() == getTrainsAtTrackPortion(nextPosition).size()){
				nextCondition.await();
				System.out.println("Train "+t.getNumber()+ " is checking again if segment "+nextPosition.getPosition()+" has space");
			}
			
			//Condition currCondition = currPosition.getCondition();
			t.setPosition(nextPosition);
			nextCondition.signalAll();
			nextLock.unlock();
			//currCondition.signalAll();
			//currentLock.unlock();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	/*
	public void enterNextSegment(Train t){
		
		TrackPortion currPosition = t.getPosition(); //get train's current position
		int currPosIndex = segments.indexOf(currPosition); //get index of the current position in the segment's list
		int nextPosIndex = ++currPosIndex;
		TrackPortion nextPosition = segments.get(nextPosIndex); //get train's next position
		ReentrantLock lock = nextPosition.getLock();
		lock.lock();	
		Condition currCondition = currPosition.getCondition();
		currCondition.signalAll();
		if(nextPosIndex == segments.size()) //if train has passed end of railway, remove train from rail track
		{
			t.setPosition(null);
		}
		else{
			t.setPosition(nextPosition);
		}
		
	}
	*/
	
	/*
	public void advanceTrain(Train t){
		TrackPortion trainPosition = t.getPosition();
		int positionIndex = segments.indexOf(trainPosition);
		
		if(positionIndex + 1 == segments.size()) //if train has passed end of railway, interrupt its thread
		{
			t.setPosition(null);
		}
		else
		{
			TrackPortion segment = segments.get(positionIndex+1);
			ReentrantLock lock = segment.getLock();
			Condition segmentFull = segment.getCondition();
			lock.lock();
			System.out.println("Train "+ t.getNumber() + " has locked segment " + (positionIndex+1));
			try{	
				while(getTrainsAtTrackPortion(segment).size() == segment.getCapacity()){
					System.out.println("Train "+ t.getNumber() + " is awaiting segment " + (positionIndex+1));
					segmentFull.await();
					//Thread.sleep(100);
				}
			}
			catch(InterruptedException e){}
			finally{
				t.setPosition(segment);
				segmentFull.signal();
				lock.unlock();
				System.out.println("Train "+ t.getNumber() + " has unlocked segment " + (positionIndex+1));
			}
		}
	}
	*/
	
	public void removeTrain(Train t){
		trains.remove(t);
	}
	
	public List<TrackPortion> getSegments(){
		return segments;
	}
	
	public List<Train> getTrainsAtTrackPortion(TrackPortion p){
		List<Train> temp = new ArrayList<Train>();
		for(Train t: trains){
			if(t.getPosition()== p){
				temp.add(t);
			}
		}
		return temp;
	}
	
	public String toString(){
		String s = "";
		for(TrackPortion seg: segments){
			List<Train> segTrains = getTrainsAtTrackPortion(seg);
			String trainNos = "";
			for(Train tr: segTrains){
				trainNos += tr.getNumber() + ",";
			}
			s += "|----"+seg+"--"+trainNos+"----|";
		}
		return s;
	}
}
