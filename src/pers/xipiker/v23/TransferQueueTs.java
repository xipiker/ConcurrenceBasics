package pers.xipiker.v23;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue意思是消费者先启动，然后生产者启动看消费者有没有启动有的话直接跳过队列跟消费者交互。
 */
public class TransferQueueTs {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //如果找不到消费者的情况下会阻塞
        strs.transfer("aaa");
        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
