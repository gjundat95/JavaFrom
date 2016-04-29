package Thread1;

public class Demo1 {

	public static void main(String[] args) {
		Lop1 l1 = new Lop1();
		Lop2 l3 = new Lop2();
		l1.start();
		l3.start();
	}

}
class Lop1 extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			try {
				System.out.println("-------------Lop1");
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Lop2 extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			try {
				System.out.println("-----------------Lop2");
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
