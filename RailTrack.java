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
			//while(getTrainsAtTrackPortion(segments.get(0)).size() == segment.getCapacity()){
			while(segments.get(0).getTrains().size() == segment.getCapacity()){
				segmentFull.await();
				//System.out.println("Segment 1 is currently full and awaiting free track");
			}
			t.setPosition(segment);
			trains.add(t);
			//System.out.println("Train "+t.getNumber()+ " has been added");
		}
		catch(InterruptedException e){}
		finally{
			lock.unlock();
		}
	}
	
	public void removeTrain(Train t){
		trains.remove(t);
	}
	
	public List<TrackPortion> getSegments(){
		return segments;
	}
	
	/*
	public List<Train> getTrainsAtTrackPortion(TrackPortion p){
		List<Train> temp = new ArrayList<Train>();
		for(Train t: trains){
			if(t.getPosition()== p){
				temp.add(t);
			}
		}
		return temp;
	}
	*/
	
	public String toString(){
		String s = "";
		for(TrackPortion seg: segments){
			//List<Train> segTrains = getTrainsAtTrackPortion(seg);
			List<Train> segTrains = seg.getTrains();
			String trainNos = "";
			for(Train tr: segTrains){
				trainNos += tr.getNumber() + ",";
			}
			s += "|----"+seg+"--"+trainNos+"----|";
		}
		return s;
	}
}
