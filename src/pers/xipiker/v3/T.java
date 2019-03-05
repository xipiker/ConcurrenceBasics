package pers.xipiker.v3;

/**
 * synchronized关键字
 * 对某个对象加锁
 */
public class T {
    private int count = 10;
    //同等于在方法的代码执行要synchronized(this)
    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
