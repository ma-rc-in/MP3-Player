//this is just to test the savers playlist functionality, delete this later
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultButtonModel;
import javafx.util.Duration;
import java.util.*;

public class playerTest extends JFrame implements ActionListener
{
    private MediaPlayer a_mediaPlayer;
    private Saver saver = new Saver();
    private JPanel a_panel = new JPanel();
    private JButton openFileButton = new JButton("Open File");
    private ControllerTest a_control = new ControllerTest();  

    JFileChooser chooseFile;  
    public String fileName;
    public String pathFile;
    File myFile = null;
    private double _volume;
    private double getVolumeValue;
    private boolean mute;
    private boolean isMuted;
    Media songPlay;
    
    public playerTest()
    {
        add(a_panel);
        a_panel.add(openFileButton);
        openFileButton.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);   
        
        //media player
        JFXPanel fxPanel = new JFXPanel();
        String bip = "File1.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
    }
    
    public void actionPerformed(ActionEvent _ae)
    { 
        if (_ae.getSource() == openFileButton)openFile();
    }
    
    public void getHardCodedPlayList()
    {
        saver.getHardCodedPlayList();
        System.out.println(saver.getHardCodedPlayList());
    }
    
    public void playPlaylist(int l_number)
    {        
        chooseFile = new JFileChooser();
        String l_song = saver.getHardCodedPlayList().get(l_number);
        System.out.println(saver.getHardCodedPlayList().get(l_number));
        
        JFXPanel fxPanel = new JFXPanel();
        String bip = l_song; //used to get the song path from name
        bip = bip.replace("\\", "/");
        
        String name = bip.substring(bip.lastIndexOf("/") + 1); //this is used to get the name of the file
        System.out.println(name);
        
        
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
        a_mediaPlayer.setAutoPlay(true);
    }
    
    public void openFile()
    {
        chooseFile = new JFileChooser();
        chooseFile.setCurrentDirectory(new File((System.getProperty("user.dir"))));
        chooseFile.setDialogTitle("Select MP3 File");
        chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooseFile.setAcceptAllFileFilterUsed(false);        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 files", "mp3");
        chooseFile.addChoosableFileFilter(filter);

        if(chooseFile.showOpenDialog(openFileButton)==JFileChooser.APPROVE_OPTION){
            myFile = chooseFile.getSelectedFile();
            fileName = chooseFile.getSelectedFile().getName();
            pathFile = chooseFile.getSelectedFile().getPath();
            System.out.println(myFile);
            System.out.println(fileName);
            System.out.println(pathFile);
        }

        pathFile = pathFile.replace("\\", "/"); 
        songPlay = new Media(new File(pathFile).toURI().toString());
        //a_mediaPlayer.stop();       
        a_mediaPlayer = new MediaPlayer(songPlay);
        a_mediaPlayer.setAutoPlay(true);                 
    }
    
    public void play()
    {
        a_mediaPlayer.play();
        System.out.println("play");
    }

}
