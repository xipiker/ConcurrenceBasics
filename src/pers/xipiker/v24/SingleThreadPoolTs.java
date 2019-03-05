package pers.xipiker.v24;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程池，它的作用就是不管你创建几个线程，其实他们都是同一个线程。
 */
public class SingleThreadPoolTs {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++){
            final int j = i;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
    }
}
