package thread;

public class ThreadStateTest {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("getlock");
                        lock.wait();
                        System.out.println("release");
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("over");


        synchronized (lock) {
            lock.notify();
        }
    }
}
