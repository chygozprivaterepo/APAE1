package AE1;

/**
 * class that represents a local train
 * @author Chigozie Ekwonu
 */
public class LocalTrain extends Train {

	/**
	 * constructor to create a local train object
	 * @param n the train's number
	 * @param s the train's speed
	 * @param t the rail track that train runs on
	 */
	public LocalTrain(int n, double s, RailTrack t) {
		super(n, s, t);
	}
	
	/**
	 * method to return a string representation of the train when printed out
	 */
	public String toString(){ 
		return number + "L"; //the "L" indicates that the train is a local train
	}
}
