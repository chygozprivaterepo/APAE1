package AE1;

public class Runme {

	/**
	 * main method to get the program running
	 */
	public static void main(String[] args) {
		
		RailTrack rw = new RailTrack(); //create a railway 
		
		//create some stations and segments and add to the railway
		rw.addTrackPortion(new Station(3, 3, "Glasgow"));
		rw.addTrackPortion(new Segment(2));
		rw.addTrackPortion(new Station(3, 2, "Stirling"));
		rw.addTrackPortion(new Segment(2));
		rw.addTrackPortion(new Station(3, 3, "Perth"));
		rw.addTrackPortion(new Segment(2));
		rw.addTrackPortion(new Station(3, 2, "Inverness"));
		
		//create and start a train maker thread
		TrainMaker trainMaker = new TrainMaker(rw);
		trainMaker.start();
		
		//create and start a railway printer thread
		RailwayPrinter printer = new RailwayPrinter(rw);
		printer.start();
		
		//pause main thread and wait for other threads to complete before it can finish. Will most likely wait forever though
		try{
			trainMaker.join();
			printer.join();
		}catch(InterruptedException e){
			System.out.println("Thread interrupted");
		}
	}

}
