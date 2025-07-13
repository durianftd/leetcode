package thread;

public class WaitNotifyExample {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t1 getlock");
                    Thread.sleep(2000);
                    System.out.println("t1 sleep over");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.notify();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t2 wait");
                    lock.wait(2000);
                    System.out.println("t2 do");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        t1.start();
        t2.start();

//        t1.start();
    }
}
