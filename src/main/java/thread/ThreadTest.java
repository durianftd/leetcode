package thread;

import java.util.Objects;

public class ThreadTest {

    private static final Integer lock = 0;

    static class MyThread implements Runnable {

        private String name;
        MyThread(){}
        MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
//            try {
//                Thread.sleep(1111);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            synchronized (lock) {
                System.out.println("获取到锁:" + name);
                try {
                    if (Objects.equals(name, "A")) {
                        lock.wait();
                    } else {
                        Thread.sleep(1000);
                        lock.notify();
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("MyThread:" + name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThreadA = new MyThread("A");
        MyThread myThreadB = new MyThread("B");
        Thread t1 = new Thread(myThreadA);
        Thread t2 = new Thread(myThreadB);
        t1.start();
        t2.start();
        Thread.sleep(5555L);
//        lock.notify();

    }
}
