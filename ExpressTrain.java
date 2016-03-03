package AE1;

public class ExpressTrain extends Train {

	public ExpressTrain(int n, double s, RailTrack t) {
		super(n, s, t);
	}

	public String toString(){
		return number + "E";
	}
}
