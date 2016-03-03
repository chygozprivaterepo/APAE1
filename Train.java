package AE1;

public abstract class Train extends Thread{
	
	//instance variables
	protected int number;
	protected double speed;
	protected RailTrack track;
	protected TrackPortion position;
	
	//constructor to initialize a train object
	public Train(int n, double s, RailTrack t){
		this.number = n;
		this.speed = s;
		this.track = t;
	}

	public TrackPortion getPosition(){
		return this.position;
	}
	
	public void setPosition(TrackPortion p){
		this.position = p;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public void run(){
		try{
			while(position != null){
				int length = position.getLength();
				int time =(int)(((double)(length) / speed)*1000);
				Thread.sleep(time);
				track.advanceTrain(this);
			}
		}catch(InterruptedException e){
			System.out.println("Sleep interrupted");
		}
	}
	
}
