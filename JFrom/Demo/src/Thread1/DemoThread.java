package Thread1;

public class DemoThread {

	public static void main(String[] args) {
		Thread1 l1 = new Thread1();
		l1.start();
	}
	

	

}

class Thread1 extends Thread{

	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			try {
				System.out.println("Thread 1 "+i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class Thread2 extends Thread{

	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			try {
				System.out.println("Thread 2 "+i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
