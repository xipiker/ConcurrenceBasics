package pers.xipiker.v19;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 实现功能:写一个固定容器同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及十个消费者线程的阻塞调用。
 */
public class MyContainer1<T> {
    final private LinkedList<T> linkedList = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;
    public synchronized void put(T t){
        //这里if和while的区别，如果用if可能会发生这样的问题，
        //容器满了，线程a在等待，然后容器空了一个位置，突然来了一个线程b插了进去，
        //然后容器满了，线程a收到通知会继续执行下去，因为容器满了产生报错。
        //而while不会出现这个问题，因为每次线程消费时，都会判断线程是否已满，然后进行等待。
        //if会继续执行这个，while会进行判断。
        //wait一般都是和while一起使用。
        while (linkedList.size() == MAX){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
    }
    public synchronized T get(){
        T t = null;
        while (linkedList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = linkedList.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
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
        //启动生产者
        for(int i = 0; i < 2; i++){
            new Thread(()->{
                for(int j = 0; j < 25; j++)
                    c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
