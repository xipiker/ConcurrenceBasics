package pers.xipiker.v4;

/**
 * synchronized关键字
 * 对某个对象加锁
 */
public class T {
    private static int count = 10;
    //这里等同于synchronized(xxx.T.class)
    public synchronized static void m(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
    public static void ms(){
        synchronized (T.class){
            count--;
        }
    }
}
