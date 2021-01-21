package Pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestPool.java
 * @Description 线程池
 * @createTime 2021年01月20日 19:37:00
 */
public class TestPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
    }
}
