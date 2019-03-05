package pers.xipiker.v21;

import java.util.Arrays;

/**
 * 单例模式就是说系统中对于某类的只能有一个对象，不可能出来第二个。
 * 线程安全的单例模式
 * 更好的方式是采用下面的方法，即不用加锁，也能实现懒加载。
 */
public class Singleton {
    private Singleton(){
        System.out.println("sigletion start..");
    }

    private static class Inner{
        private static Singleton s = new Singleton();
    }

    private static Singleton getSingle(){
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++){
            ths[i] = new Thread(()->{
                Singleton.getSingle();
            });
        }

        Arrays.asList(ths).forEach(o->o.start());
    }
}
