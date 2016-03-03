package AE1;

import java.util.Random;

public class TrainMaker extends Thread{
	
	private RailTrack railTrack;
	
	public TrainMaker(RailTrack r){
		railTrack = r;
	}
	
	public void run(){
		try{
			int trainNumber = 1;
			while(true){
				Random r = new Random();
				Double trainSpeed = r.nextDouble() * 5;
				Train train;
				if(trainSpeed > 2.5){
					train = new ExpressTrain(trainNumber, trainSpeed, railTrack);
				}
				else{
					train = new LocalTrain(trainNumber, trainSpeed, railTrack);
				}
				railTrack.addTrain(train);
				train.start();
				int delay = new Random().nextInt(1000);
				Thread.sleep(delay);
				trainNumber++;
			}
		}catch(InterruptedException e){
			
		}
	}
}
