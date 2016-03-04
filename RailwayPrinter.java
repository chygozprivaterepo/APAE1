package AE1;

/**
 * class that prints out the status of the railway
 * @author Chigozie Ekwonu
 *
 */
public class RailwayPrinter extends Thread{
	
	private RailTrack track; //represents the railtrack
	
	/**
	 * constructor to create a RailwayPrinter object
	 * @param t the rail track object to be printed
	 */
	public RailwayPrinter(RailTrack t){
		this.track = t;
	}
	
	/**
	 * method to start the printing process. Prints out the status of the railway every 0.5 seconds
	 */
	public void run(){
		try{
			while(true) //loop forever
			{
				System.out.println(track);
				Thread.sleep(500);
			}
		}catch(InterruptedException e){
			System.out.println("Sleep interrupted");
		}
	}
}
