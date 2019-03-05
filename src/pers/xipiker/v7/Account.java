package pers.xipiker.v7;

import java.util.concurrent.TimeUnit;

/**
 * 脏读
 * 实验结果:实验结果为0.0。
 * 解决方法:确保读写都加上synchronized，保证原子性。
 */
public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name = name;
        //这里加上间隔时间的意义在于模拟线程被其他线程进行调用
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()->account.set("xipiker", 21.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("xipiker"));

    }
}
