

public class PostThreadDemo extends Thread {

	private Thread t;
	private String threadName;

	PostThreadDemo(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);

		try {
			//for (int i = 4; i > 0; i--) {
				//System.out.println("Thead: " + threadName + "," + i);
				NetClientPost.testPost();
				Thread.sleep(50);
			//}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
			e.printStackTrace();
		}
		System.out.println("Thread " + threadName + " exiting.");

	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new PostThreadDemo("Thread-" + i).start();
		}

	}

}
