package AE1;

public class Station extends TrackPortion{
	
	private String name;
	
	//constructor
	public Station(Integer length, Integer capacity, Integer pos, String name) {
		super(length, capacity, pos);
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
}
