package pers.xipiker.v18;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁(重点)，使用syn锁定的话如果遇到异常，
 * jvm会自动释放，但是lock必须手动释放锁，因此经常在finally中进行锁释放。
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();
    void m1(){
        try {
            lock.lock();
            for (int i = 0; i < 10; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    void m2(){
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 r2 = new ReentrantLock2();
        new Thread(r2::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r2::m2).start();
    }
}
