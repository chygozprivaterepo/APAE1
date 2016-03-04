package AE1;

import java.util.Random;

/**
 * class that continuously makes trains and puts them on the first segment of the railway
 * @author Chigozie Ekwonu
 *
 */
public class TrainMaker extends Thread{
	
	private RailTrack railTrack; //represents the railway
	
	/**
	 * constructor that creates a TrainMaker object
	 * @param r the RailTrack object that the trains will be put on
	 */
	public TrainMaker(RailTrack r){
		railTrack = r;
	}
	
	/**
	 * method to start the train creation process
	 */
	public void run(){
		try{
			int trainNumber = 1; //start with a train number of one
			while(true){
				Random r = new Random();
				Double trainSpeed = r.nextDouble() * 5; //calculate a random speed
				Train train;
				if(trainSpeed > 2.5){ //if speed is more than 2.5, create an express train
					train = new ExpressTrain(trainNumber, trainSpeed, railTrack);
				}
				else{//else create a local train
					train = new LocalTrain(trainNumber, trainSpeed, railTrack);
				}
				railTrack.getPortions().get(0).isFull(); //checks if the first station in the rail track is full
								//if it is full, then the train maker class pauses while waiting for it to become free
				train.start(); //start the train
				int delay = new Random().nextInt(1000); //create a random time delay
				Thread.sleep(delay); //delay the creation of the next train by the calculated time delay
				trainNumber++; //increment the train number by 1
			}
		}catch(InterruptedException e){
			System.out.println("Train creator's sleep interrupted");
		}
	}
}
