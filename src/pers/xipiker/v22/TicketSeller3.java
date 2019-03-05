package pers.xipiker.v22;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票。
 * 分析下面的程序可能会产生哪些问题?
 * 运行结果:运行结果没有问题，相当于把判断与操作加到一个锁里面，属于一个原子。但是效率不高。
 */
public class TicketSeller3 {
    static List<String> tickets = new LinkedList<>();
    static {
        for (int i = 0; i < 1000; i++)
            tickets.add("票编号:" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(()->{
                while (true){
                    synchronized (tickets){
                        if(tickets.size() <= 0) break;
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
