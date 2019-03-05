package pers.xipiker.v6;

/**
 * 实验结果:此案例说明加synchronized修饰后的线程对象，
 * 和不加synchronized修饰，两者同时运行并不冲突。
 */
public class T {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2...");
    }

    public static void main(String[] args) {
        T t = new T();
        //语法有点叼
        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();

        //和上面结果一样
        //new Thread(t::m1, "t1").start();
        //new Thread(t::m2, "t2").start();
    }
}
