package generate.generator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * volatile多线程安全测试类
 * 参考链接：https://blog.csdn.net/weixin_30342639/article/details/91356608
 * date:2019/11/19
 * */

public class TestVolatile {
	 
    private static CountDownLatch countDownLatch = new CountDownLatch(100);
    private volatile static int num1 = 0;
    private static AtomicInteger num2 = new AtomicInteger();
 
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i <  100; i++) {
            executor.execute(() -> {
                try {
                    num1++;
                    num2.getAndIncrement();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("num1=================="+num1);
        System.out.println("num2=================="+num2);
    }
}
