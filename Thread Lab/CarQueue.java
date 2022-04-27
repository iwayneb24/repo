import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	private Random randomNum;
	private Queue<Integer> direction;
	
	public CarQueue() {
		direction = new ArrayDeque<Integer>();
		randomNum = new Random();
		for (int i = 0; i < 5; i++) {
			direction.add(randomNum.nextInt(4));
		}
	}
	
	public void addToQueue() {
		
		class RunQueue implements Runnable {

			@Override
			public void run() {
				while(true) {
					try {
						direction.add(randomNum.nextInt(4));
						Thread.sleep(100);
					} catch(InterruptedException e) {
						e.printStackTrace();	
					}				
				}
				
			}
			
		}
		
		RunQueue car = new RunQueue();
		Thread thread = new Thread(car);
		thread.start();
	}
	
	
	public int deleteQueue() {
		return direction.remove();
	}
	
	
	
	
	
	
}
