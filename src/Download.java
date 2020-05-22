import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class Download extends Observable implements Runnable {

    String link;
    String ofilePath;
    File ofile;
    int stat;
    private int status;
    public static final String STATUSES[] = {"Downloading", "Paused", "Complete",};

    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;

    public void pause() {
        status = PAUSED;
        stateChanged();
    }

    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }


    public void stateChanged() {
        setChanged();
        notifyObservers();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int stat) {
        this.status = status;
    }


//    Download(String link, String ofilePath) {
//        this.link = link;
//        this.ofilePath = ofilePath;
//        ofile = new File(ofilePath);
////        if (status == DOWNLOADING) {
////            download();
////        }
//    }

    Download() {
        // ?
    }

    public void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOfilePath(String ofilePath) {
        this.ofilePath = ofilePath;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);

            System.out.println(link + "   1");
            System.out.println(ofilePath + "   1");

            ofile = new File(ofilePath);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            double filesize = (double) http.getContentLength();
            BufferedInputStream bis = new BufferedInputStream(http.getInputStream());

            FileOutputStream fos = new FileOutputStream(ofile);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDL = 0.00;
            System.out.println(link + "2");
            while (status == DOWNLOADING) {
                System.out.println( "status ready");
                while ((read = bis.read(buffer, 0, 1024)) >= 0) {
                    bos.write(buffer, 0, read);
                    downloaded += read;
                    percentDL = downloaded * 100 / filesize;
                    String percent = String.format("%.1f", percentDL);
                    System.out.println("downloaded " + percent + "% of file ");
                }

                bos.close();
                bis.close();

                System.out.println("DL done!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
