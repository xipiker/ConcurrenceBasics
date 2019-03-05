package pers.xipiker.v11;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见。
 * 例子: A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的赋值。
 *
 * 在下面代码中running是存在于堆内存的t对象中，当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，
 * 在运行过程中直接使用这个copy，并不会每次都去读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行。
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值。
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized。
 */
public class T {
    volatile boolean running = true;
    void m(){
        System.out.println("m start...");
        while (running){

        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
