package pers.xipiker.v13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 确保变量的原子性，更加高效的方法可以使用AtomXXX类。
 * AtomXXX类本身方法都是原子性，但不能保证多个方法连续调用是原子性。
 */
public class T {
    AtomicInteger count = new AtomicInteger(0);
    void m(){
        for (int i = 0; i < 10000; i++)
            //if count.get() < 1000 ->这里如果执行那么就不在具有原子性了，
            // 因为可能会有线程执行到count.get() < 1000里面去，而又有的线程执行了下面的++操作。
            count.incrementAndGet(); //count++
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++){
            threadList.add(new Thread(t::m, "thread-" + i));
        }

        threadList.forEach((o)->o.start());

        threadList.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
