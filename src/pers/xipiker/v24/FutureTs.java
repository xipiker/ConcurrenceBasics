package pers.xipiker.v24;

import java.util.concurrent.*;

public class FutureTs {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Callable() {Integer call();}
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
        new Thread(task).start();
        System.out.println(task.get());//阻塞

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        //阻塞完以后f.get后，在f.isDone()，会返回true，否则false
        //System.out.println(f.get());
        System.out.println(f.isDone());
    }
}
