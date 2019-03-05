package pers.xipiker.v5;

/**
 * 实验结果:在run方法如果不加上synchronized，会产生线程抢占，
 * 如果加上就确保了线程运行的原子性，先执行的执行，后执行的等待。
 */
public class T implements Runnable {

    private int count = 10;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++){
            new Thread(t, "Thread" + i).start();
        }
    }
}
