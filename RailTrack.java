package AE1;

import java.util.*;

/**
 * class that represents the entire rail track
 * @author Chigozie Ekwonu
 */
public class RailTrack {

	//instance variables
	private List<TrackPortion> portions;  //list that stores all stations and segments that make up
											//the entire rail track 
	
	/**
	 * constructor that creates a rail track
	 */
	public RailTrack(){
		portions = new ArrayList<TrackPortion>(); //create a list to hold the stations and segments
	}
	
	/**
	 * method to add a segment or station to the rail track
	 * @param s
	 */
	public void addTrackPortion(TrackPortion s){
		portions.add(s);
	}
	
	/**
	 * method to return the stations and segments that make up the rail track
	 * @return the list of stations and segments
	 */
	public List<TrackPortion> getPortions(){
		return portions;
	}
	
	/**
	 * method that returns a string representation of the rail track when it is to be printed out
	 */
	public String toString(){
		String s = "";
		for(TrackPortion seg: portions) //loop through all the stations and segments in the rail track
		{
			s += seg;
		}
		return s; //return string representation of rail track
	}
}
