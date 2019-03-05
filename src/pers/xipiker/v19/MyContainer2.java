package pers.xipiker.v19;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现功能:写一个固定容器同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及十个消费者线程的阻塞调用。
 * 使用Lock和Condition来实现
 * 对比两种方法，Condition的方式可以更加精确的指定哪些线程被唤醒。
 */
public class MyContainer2<T> {
    final private LinkedList<T> linkedList = new LinkedList<>();
    //最多10个元素
    final private int MAX = 10;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    //生产者
    private Condition producer = lock.newCondition();
    //消费者
    private Condition consumer = lock.newCondition();
    public void put(T t){
        try {
            lock.lock();
            while (linkedList.size() == MAX){
                producer.await();
            }
            linkedList.add(t);
            ++count;
            //通知消费者线程进行消费
            consumer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public T get(){
        T t = null;
        try {
            lock.lock();
            while (linkedList.size() == 0){
                consumer.await();
            }
            t = linkedList.removeFirst();
            count--;
            //通知生产者进行生产
            producer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
        //启动消费者线程
        for(int i = 0; i < 10; i++){
            new Thread(()->{
                for (int j = 0; j < 5; j++)
                    System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i = 0; i < 2; i++){
            new Thread(()->{
                for (int j = 0; j < 25; j++)
                    c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
