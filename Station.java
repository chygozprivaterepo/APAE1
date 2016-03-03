package AE1;

public class Station extends TrackPortion{
	
	private String name;
	
	//constructor
	public Station(Integer length, Integer capacity,  String name) {
		super(length, capacity);
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
}
