import java.net.URL;

public class ThreadManager implements Runnable {

    protected int mThreadID;
    public URL URL;
    public String outputFile;
    public int startByte;
    public int endByte;
    public boolean isFinished;
    public Thread thread;

    public ThreadManager(int threadID, URL url, String outputFile, int startByte, int endByte) {

        mThreadID = threadID;
        URL = url;
        outputFile = outputFile;
        startByte = startByte;
        endByte = endByte;
        isFinished = false;
        download();

    }

    public boolean isFinished() {
        return isFinished;
    }

    public void download() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // nothing for now
    }

    public void waitFinish() throws InterruptedException {
        thread.join();
    }

}
