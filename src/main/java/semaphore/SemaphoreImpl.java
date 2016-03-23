package semaphore;

import semaphore.Semaphore;

public class SemaphoreImpl implements Semaphore {

    private volatile int availablePermits;
    private final int MAX_AVAILABLE;


    public SemaphoreImpl(int permits) {
        this.availablePermits = permits;
        this.MAX_AVAILABLE = permits;
    }

    @Override
    public synchronized void acquire() throws InterruptedException{
        if (availablePermits == 0) {
            this.wait();
        }
        availablePermits--;

    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException {
        if (availablePermits <= permits) {
            this.wait();
        }
        availablePermits -= permits;
    }

    @Override
    public synchronized void release() throws InterruptedException {
        if (availablePermits < MAX_AVAILABLE) {
            availablePermits++;
            this.notify();
        }
    }

    @Override
    public synchronized void release(int permits) throws InterruptedException {
        if (availablePermits + permits <= MAX_AVAILABLE) {
           availablePermits += permits;
           this.notify();
        } else {
            System.out.println("You can only release "+ (MAX_AVAILABLE - availablePermits) + " permits");
        }
    }

    @Override
    public int getAvailablePermits() throws InterruptedException {
        return this.availablePermits;
    }


}
