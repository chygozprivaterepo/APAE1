package AE1;

public class Runme {

	public static void main(String[] args) {
		
		RailTrack rw = new RailTrack();
		
		TrackPortion station1 = new Station(3, 3, 1, "Glasgow");
		TrackPortion station2 = new Station(3, 2, 3, "Stirling");
		TrackPortion station3 = new Station(3, 3, 5, "Perth");
		TrackPortion station4 = new Station(3, 2, 7, "Inverness");
		
		rw.addTrackPortion(station1);
		rw.addTrackPortion(new Track(2, 2));
		rw.addTrackPortion(station2);
		rw.addTrackPortion(new Track(2, 4));
		rw.addTrackPortion(station3);
		rw.addTrackPortion(new Track(2, 6));
		rw.addTrackPortion(station4);
		
		TrainMaker trainMaker = new TrainMaker(rw);
		trainMaker.start();
		
		RailwayPrinter printer = new RailwayPrinter(rw);
		printer.start();
		try{
			trainMaker.join();
			//train2.join();
			printer.join();
		}catch(InterruptedException e){}
	}

}
