import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
      //  String link = "https://dls.music-fa.com/tagdl/99/Mehdi%20Ahmadvand%20-%20Tanham%20Nazar%20(320).mp3";
      // File file = new File("c:\\users\\AmirAli\\Desktop\\fileTest.mp3");

        try {
            DownloadManager downloadmanager = new DownloadManager();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
