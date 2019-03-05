package pers.xipiker.v23;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列
 */
public class ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++){
            strs.offer("a" + i);
        }
        //size  = 10
        System.out.println(strs);
        System.out.println(strs.size());

        //size = 9,poll是拿出来删了
        System.out.println(strs.poll());
        System.out.println(strs.size());

        //size = 9,peek是拿出来不删
        System.out.println(strs.peek());
        System.out.println(strs.size());

        //还有双向队列
    }
}
