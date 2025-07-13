package thread;

public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
                System.out.println("enter thread");
                ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
                threadLocal.set(111);

            Integer i = threadLocal.get();
            System.out.println("f" + i);
            ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
            threadLocal1.set(222);
            i = threadLocal.get();

            // 这里才可以， 要把threadLocalMap里对应ThreadLocal 这个key的数据清除，否则会内存泄漏
            threadLocal.remove();
            System.out.println("s" + i);
        }
        );
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1111);
        System.out.println(threadLocal.get());
        t.start();
        Thread.sleep(1000L);
        System.out.println("end");
    }
}
