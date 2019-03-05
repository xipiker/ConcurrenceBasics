package pers.xipiker.v23;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 并发容器，效率对比
 */
public class ConcurrentMap {

    public static void main(String[] args) {
        //ConcurrentHashMap这里就是把锁分成16段，每段执行的时候加上那一段锁，所以效率相对比较高
        Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String> map = new ConcurrentSkipListMap<>();

        //Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        //门闩计数器
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for(int i = 0; i < ths.length; i++){
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++)
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t->t.start());
        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
