package pers.xipiker.v17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束。
 * 解决方法一:给lists添加volatile之后，t2能够接收通知，但是t2线程的死循环很浪费cpu，如果不用死循环，该怎么做?
 * 解决方法二:可以使用wait和notify做到，wait会释放锁，而notify不会释放锁，需要注意的是，运行这种方法，必须要保证
 * t2先执行，也就是首先先让t2监听才可以，notify之后，t1必须释放锁，t2退出后也必须notify，通知t1继续执行。
 * 解决方法三:使用Latch(门闩)代替wait notify来进行通知，
 * 好处是通信方式简单，同时也可以指定等待时间，使用await和countdown方法代替wait和notify，
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify就显得太重了，
 * 这是应该考虑countdownlatch/cyclicbarrier/semaphore
 */
public class MyContainer {
    //添加volatile，使t2能够得到通知
    volatile List list = new ArrayList();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer c = new MyContainer();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("t2 start ...");
            if(c.size() != 5){
                try {
                    //也可以指定等待时间
                    //latch.await(5000, TimeUnit.MILLISECONDS);
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 over ...");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 start...");
            for(int i = 0; i < 10; i++){
                c.add(new Object());
                System.out.println("add" + i);
                if(c.size() == 5){
                    //打开门闩，让t2得以执行
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}
