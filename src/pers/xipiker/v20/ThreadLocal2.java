package pers.xipiker.v20;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在Hibernate中session就存在与ThreadLocal中，避免synchronized的使用。
 * 实验结果:System.out.println(tl.get());ThreadLocal对象的意思就是我自己的线程我自己用。
 */
public class ThreadLocal2 {
    static ThreadLocal<PersonRs> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new PersonRs());
        }).start();
    }
}

class PersonRs{
    String name = "zhangshan";
}
