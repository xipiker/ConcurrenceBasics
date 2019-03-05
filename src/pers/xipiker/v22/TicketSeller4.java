package pers.xipiker.v22;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票。
 * 运行结果:效率高，这里下面判断与操作并不是原子性，但是为什么没有问题，因为这里判断以后没有对值做修改操作。
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    static {
        for (int i = 0; i < 1000; i++)
            tickets.add("票编号:" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if(s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
