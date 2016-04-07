package ua.kiev.semaphore;

/**
 * Created by lionliliya on 07.04.16.
 */
public class Bootstrap {
    public static void main(String[] args) {

        Semaphore semaphore = new SemaphoreImpl(2);
        new Thread(new SendThread(semaphore)).start();
        new Thread(new ReceiveThread(semaphore)).start();
    }

    public static class SendThread implements Runnable {

        Semaphore semaphore = null;

        public SendThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int i = 0;
            int count = 10;
            while (count-- > 0) {
                System.out.println("Thread Sender" + Thread.currentThread().getName() + " number " + i++);
                try {
                    this.semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ReceiveThread implements Runnable {

        Semaphore semaphore = null;

        public ReceiveThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int i = 0;
            int count = 10;
            while (count-- > 0) {
                try {
                    this.semaphore.release();
                    System.out.println("Thread Receiver" + Thread.currentThread().getName() + " number " + i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
