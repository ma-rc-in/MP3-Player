//this is just to test the savers playlist functionality, delete this later

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class playerTest
{
    private MediaPlayer a_mediaPlayer;
    private Saver saver = new Saver();
    
    public playerTest()
    {
        //media player
        JFXPanel fxPanel = new JFXPanel();
        String bip = "File1.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
    }
    
    public void getHardCodedPlayList()
    {
        saver.getHardCodedPlayList();
        System.out.println(saver.getHardCodedPlayList());
    }
    
    public void playPlaylist(int l_number)
    {
        String l_song = saver.getHardCodedPlayList().get(l_number);
        System.out.println(saver.getHardCodedPlayList().get(l_number));
        
        JFXPanel fxPanel = new JFXPanel();
        String bip = l_song;
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
    }
    
    public void play()
    {
        a_mediaPlayer.play();
        System.out.println("play");
    }

}
