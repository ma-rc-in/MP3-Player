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

public class GUITester extends JFrame implements ActionListener//, danPlayer
{
    private JButton a_button = new JButton("Play");
    private JButton a_button2 = new JButton("Pause");
    private JButton a_button3 = new JButton("Stop");
    private JButton a_button4 = new JButton("Mute");
    private JButton a_button5 = new JButton("Set0");
    private ControllerTest a_control = new ControllerTest();  
    private JPanel a_panel = new JPanel();
    private MediaPlayer a_mediaPlayer;
    private Saver saver = new Saver(); //saver class

    JFileChooser chooseFile;
    JButton openFileButton = new JButton("Open File");
    JLabel fileNameLabel = new JLabel();    
    public String fileName;
    public String pathFile;
    File myFile = null;
    private double _volume;
    private double getVolumeValue;
    private boolean mute;
    private boolean isMuted;
    Media songPlay;

    public void GUITester()
    {
        add(a_panel);
        a_panel.add(a_button);
        a_panel.add(a_button2);
        a_panel.add(a_button3);     
        a_panel.add(openFileButton);
        a_panel.add(fileNameLabel);
        a_panel.add(a_button4);   
        a_panel.add(a_button5); 
        a_button.addActionListener(this);
        a_button2.addActionListener(this);
        a_button3.addActionListener(this);
        openFileButton.addActionListener(this);
        a_button4.addActionListener(this);
        a_button5.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);       

        initialiseFiles();
    }    
     
    public void initialiseFiles(){
        JFXPanel fxPanel = new JFXPanel();
        String bip = "file1.mp3";  
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
    }

    public void actionPerformed(ActionEvent _ae)
    {
        if (_ae.getSource() == a_button)play();
        if (_ae.getSource() == a_button2)pause();
        if (_ae.getSource() == a_button3)stop();  
        if (_ae.getSource() == openFileButton)openFile();
        if (_ae.getSource() == a_button4)setMute(mute);
        if (_ae.getSource() == a_button5)setVolume(_volume);

    }

    public void saveSettings()
    {
        saver.saveSettings();
    }

    public void loadSettings()
    {
        saver.loadSettings();
    }

    public void play()
    {
        a_mediaPlayer.play();        
        getTotalTime();
        getMute();
    }

    public void pause()
    {
        a_mediaPlayer.pause();
    }

    public void stop()
    {
        a_mediaPlayer.stop();
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
            pathFile = chooseFile.getSelectedFile().getPath();}

        pathFile = pathFile.replace("\\", "/"); 
        songPlay = new Media(new File(pathFile).toURI().toString());
        //a_mediaPlayer.stop();       
        a_mediaPlayer = new MediaPlayer(songPlay);
        a_mediaPlayer.setAutoPlay(true);     
        getCurrentTrackName();
        getVolume();             
    }

    public String getCurrentTrackName(){

        if (fileName.indexOf(".") > 0){
            fileName = fileName.substring(0, fileName.lastIndexOf("."));}
        return fileName;
    }

    public void setMute(boolean _setMute){
        if (! mute){
            a_button4.setText("Unmute");
            a_mediaPlayer.muteProperty();      
            a_mediaPlayer.setMute(true); 

        }      
        else {
            a_button4.setText("Mute");
            a_mediaPlayer.setMute(false);         
        }
       
        mute = ! mute;
        saver.setMute(mute); //sets saver to mute value
    }

    public double setVolume(double _volume){                        
        //songPlay = new Media(new File(pathFile).toURI().toString());        
        a_mediaPlayer.setVolume(_volume);
        saver.setVolume(_volume);
        return _volume;
    }

    public Duration getTime(){
        Duration duration = a_mediaPlayer.getCurrentTime();
        return duration;
    }

    public boolean getMute(){
        //isMuted = a_mediaPlayer.isMute(); //REMOVE THE COMMENTS LATER <<<<<<<<<<<<<<<<<<<THIS WAS FOR A TEST
        //isMuted = a_mediaPlayer.isMute();   
        //boolean muted = a_mediaPlayer.isMute();
        boolean isMuted = saver.getMute(); //gets volume from saver
        //return muted;
        return isMuted;
        
        //fileNameLabel.setText("22" + isMuted);
        //return isMuted;  
        //boolean muted = a_mediaPlayer.isMute();
        //boolean muted = saver.getMute(); //gets volume from saver
        //return muted;  

    }

    public double getVolume(){
        //getVolumeValue = a_mediaPlayer.getVolume();
        getVolumeValue = saver.getVolume();
        return getVolumeValue;
    }

    //public void setTime(Duration _time){}

    public Duration getTotalTime(){
        Duration duration = a_mediaPlayer.getTotalDuration();
        return duration;
    }

    //public void openPlayList(){}

    //public ArrayList<String> getPlayList(){}

    //public void playTrack(int _trackNo){}

    public void restart(){
        a_mediaPlayer.seek(a_mediaPlayer.getStartTime());    
    } 

}

