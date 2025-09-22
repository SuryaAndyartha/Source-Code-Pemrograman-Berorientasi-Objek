public class TimerThread extends Thread {
    private Clock clock;

    public TimerThread(Clock clock) {
        this.clock = clock;
    }

    public void run() {
        while (clock.isRunning()) {
            clock.step();
            pause();
        }
    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exc) {
            // ignore
        }
    }
}
