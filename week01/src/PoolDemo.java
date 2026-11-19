import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolDemo {
    public static void main(String[] args) throws Exception {
        int taskCount = 2000;
        // 1. 纯 new Thread 测试
        long start1 = System.currentTimeMillis();
        CountDownLatch latch1 = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++) {
            new Thread(() -> {
                try { Thread.sleep(1); } catch (InterruptedException e) {}
                latch1.countDown();
            }).start();
        }
        latch1.await();
        long time1 = System.currentTimeMillis() - start1;
        System.out.println("2000 个 new Thread 耗时: " + time1 + " ms");

        // 2. 4核心线程池测试
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4, 4, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> new Thread(r, "pool-4")
        );

        long start2 = System.currentTimeMillis();
        CountDownLatch latch2 = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++) {
            pool.execute(() -> {
                try { Thread.sleep(1); } catch (InterruptedException e) {}
                latch2.countDown();
            });
        }
        latch2.await();
        long time2 = System.currentTimeMillis() - start2;
        System.out.println("4 线程池 耗时: " + time2 + " ms");

        pool.shutdown();


    }
}


