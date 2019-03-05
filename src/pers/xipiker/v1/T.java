package pers.xipiker.v1;

/**
 * synchronized关键字
 * 对某个对象加锁
 */
public class T {
    private int count = 10;
    private Object o = new Object();
    public void m(){
        //任何线程要执行下面代码，必须先拿到o锁
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
