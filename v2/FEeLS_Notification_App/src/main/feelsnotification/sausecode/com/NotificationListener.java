package main.feelsnotification.sausecode.com;

public class NotificationListener implements Runnable{
	private int interval;
	public NotificationListener(int inter) {
		interval=inter;
	}
	
	@Override
	public void run() {
		while(true){
			
			
			try {Thread.sleep(interval);} catch (InterruptedException e){e.printStackTrace();} //interval
		}
	}
	
}
