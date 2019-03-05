package pers.xipiker.v18;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock实现公平锁
 */
public class ReentrantLock5 extends Thread{
    //参数为true表示公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r5 = new ReentrantLock5();
        Thread th1 = new Thread(r5);
        Thread th2 = new Thread(r5);
        th1.start();
        th2.start();
    }
}
