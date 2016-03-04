package AE1;

/**
 * class that represents an express train
 * @author Chigozie Ekwonu
 */
public class ExpressTrain extends Train {

	/**
	 * constructor to create an express train object
	 * @param n the train's number
	 * @param s the train's speed
	 * @param t the rail track that the train will run on
	 */
	public ExpressTrain(int n, double s, RailTrack t) {
		super(n, s, t);
	}

	/**
	 * method to return a string representation of the train when printed out
	 */
	public String toString(){
		return number + "E"; //the "E" indicates that it is an express train
	}
}
