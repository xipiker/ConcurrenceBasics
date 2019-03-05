package pers.xipiker.v12;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能代替synchronized。
 * 实验结果:多个线程间比如A线程执行中的count为100，B线程计算的A的count在加一为101，然后轮到A执行时他的count值时应该count结果为102，
 * 但是执行了为101，所以原本count的102值又被清回101。
 *
 * 对比以上我们可以将volatile修饰改为synchronized，它可以保证可见性和原子性，volatile只能保证可见性。
 */
public class T {
    volatile int count = 0;
    void m(){
        for(int i = 0; i < 1000; i++)
            count++;
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++){
            threads.add(new Thread(t::m, "Thread-" + i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
