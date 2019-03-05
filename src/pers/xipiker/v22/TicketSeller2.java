package pers.xipiker.v22;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票。
 * 分析下面的程序可能会产生哪些问题?
 * 问题:判断与操作分离了。
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();
    static {
        for (int i = 0; i < 1000; i++)
            tickets.add("票编号:" + i);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(()->{
                while (tickets.size() > 0){
                    //这部分可能会会被打断，实际开发中也有可能出现，所以需谨慎
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
