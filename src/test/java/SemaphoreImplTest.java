import org.junit.Test;
import semaphore.Semaphore;
import semaphore.SemaphoreImpl;

public class SemaphoreImplTest {

    public Semaphore semaphore = new SemaphoreImpl(2);

    @Test
    public void testSemaphoreImpl() throws Exception {

        new Thread(new SendThread(semaphore)).start();
        new Thread(new ReceiveThread(semaphore)).start();

    }

    public class SendThread implements Runnable {

        Semaphore semaphore = null;

        public SendThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println("Thread Sender" + Thread.currentThread().getName() + " number " + i++);
                try {
                    this.semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class ReceiveThread implements Runnable {

        Semaphore semaphore = null;

        public ReceiveThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
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