package AE1;

public abstract class Train extends Thread{
	
	//instance variables
	protected int number;
	private double speed;
	private RailTrack track;
	private TrackPortion position;
	
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
			int index = 0;
			TrackPortion previousPosition = null; //at start, there is no previous position
			while(true) //while train is still on the track
			{
				track.getSegments().get(index).enter(this, previousPosition);
				//System.out.println("Train "+number+" has entered segment "+index);
				/*
				if(previousPosition != null){
					previousPosition.leave(this);
				}
				*/
				int length = position.getLength();
				int time =(int)(((double)(length) / speed)*1000);
				Thread.sleep(time);
				index++;
				if (index == track.getSegments().size()){
					position.leave(this);
					position = null;
					return;
				}
				previousPosition = position;
				position = track.getSegments().get(index);
			}
		}catch(InterruptedException e){
			System.out.println("Sleep interrupted");
		}
	}
	
	//public abstract String toString();
	
}
