package pers.xipiker.v24;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 缓存线程池，它的意思是要多少线程它创建多少，当然也有范围限制，
 * int的最大值，当线程60秒没有执行，将关闭该线程。
 */
public class CachedPoolTs {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for(int i = 0; i < 2; i++){
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        TimeUnit.SECONDS.sleep(66);
        System.out.println(service);
    }
}
