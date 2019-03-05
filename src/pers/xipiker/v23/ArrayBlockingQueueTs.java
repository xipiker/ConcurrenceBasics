package pers.xipiker.v23;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界队列
 */
public class ArrayBlockingQueueTs {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    //static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            strs.put("a" + i);
        }
        //会一直等待，等待strs空一位然后添加进去，满了会阻塞
        //strs.put("aaa");
        //会报异常
        //strs.add("aaa");
        //不会报异常，因为它会有一个boolean返回值
        //strs.offer("aaa");
        //这里意思是一秒钟后，没有添加成功就不往里面继续加了
        //strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
    }
}
