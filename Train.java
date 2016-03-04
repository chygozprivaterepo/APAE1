package AE1;

public abstract class Train extends Thread{
	
	//instance variables
	protected int number; //represents train number
	private double speed; //represents train speed
	private RailTrack track; // represents the entire rail track
	private TrackPortion position; //represents the train's current position on the track
	
	/**
	 * constructor to initialize a train object
	 * @param n the train number
	 * @param s the train speed
	 * @param t the rail track
	 */
	public Train(int n, double s, RailTrack t){
		this.number = n;
		this.speed = s;
		this.track = t;
	}
	
	/**
	 * method to set the position of the train
	 * @param p the position to be set to
	 */
	public void setPosition(TrackPortion p){
		this.position = p;
	}
	
	/**
	 * method that is called to start the train running
	 */
	public void run(){
		try{
			int index = 0; //when train starts initially, it is at the first station in the rail track
			TrackPortion previousPosition = null; //at start, there is no previous position
			while(true) //run continuously. Interrupted after train has passed the last station
			{
				track.getPortions().get(index).enter(this, previousPosition); //get the track segment at position specified
													//by index and try to enter that position
				int length = position.getLength(); //get the length of the track at current position
				int time =(int)(((double)(length) / speed)*1000); //compute the time that train will stay at current position
				Thread.sleep(time); //sleep for specified time
				index++; //index of the next position on the rail track to enter
				if (index == track.getPortions().size()) //if end of rail track is reached
				{ 
					position.leave(this); //leave current position
					return; //stop train's run by returning from method 
				}
				previousPosition = position; //set previous position to current position
			}
		}catch(InterruptedException e){
			System.out.println("Sleep interrupted"); //print statement if sleep is interrupted
		}
	}
	
	public abstract String toString();
}
