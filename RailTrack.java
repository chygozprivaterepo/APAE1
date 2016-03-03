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
			while(segments.get(0).getTrains().size() == segment.getCapacity()){
				segmentFull.await();
			}
			t.setPosition(segment);
			trains.add(t);
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
	
	public String toString(){
		String s = "";
		for(TrackPortion seg: segments){
			List<Train> segTrains = seg.getTrains();
			String trainNos = "";
			for(Train tr: segTrains){
				trainNos += tr + ",";
			}
			s += "|----"+seg+"--"+trainNos+"----|";
		}
		return s;
	}
}
