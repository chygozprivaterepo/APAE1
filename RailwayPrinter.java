package AE1;

public class RailwayPrinter extends Thread{
	
	private RailTrack track;
	
	public RailwayPrinter(RailTrack t){
		this.track = t;
	}
	
	public void run(){
		try{
			while(true){
				System.out.println(track);
				Thread.sleep(700);
			}
		}catch(InterruptedException e){
			
		}
	}
}
