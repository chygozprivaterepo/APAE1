package AE1;

/**
 * class to represent a station
 * @author Chigozie Ekwonu
 *
 */
public class Station extends TrackPortion{
	
	private String name; //name of station
	
	/**
	 * constructor to initialize a Station object
	 * @param length the length of the station
	 * @param capacity the capacity of the station
	 * @param name the name of the station
	 */
	public Station(Integer length, Integer capacity,  String name) {
		super(length, capacity);
		this.name = name;
	}
	
	/**
	 * method to return a string representation of the station with the trains in it when it is to be printed
	 */
	public String toString(){
		String trainNos = "";
		for(Train t: currentTrains){
			trainNos += t + ",";
		}
		return "|----"+name+"--"+trainNos+"----|";
	}
}
