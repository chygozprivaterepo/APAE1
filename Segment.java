package AE1;

/**
 * class to represent a segment of the rail track
 * @author Chigozie Ekwonu
 *
 */
public class Segment extends TrackPortion{

	/**
	 * constructor to create a Segment object
	 * @param length the length of the segment
	 */
	public Segment(Integer length) {
		super(length, 1); //capacity of segment is 1
	
	}
	
	/**
	 * method to return a string representation of the segment with the trains in it when it is to be printed out
	 */
	public String toString(){
		String trainNos = "";
		for(Train t: currentTrains){
			trainNos += t + ",";
		}
		return "|----track--"+trainNos+"----|";
	}
}
