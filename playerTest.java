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
    private JButton save = new JButton("Save");
    private JButton loadpl = new JButton("Load Playlist");
    private ControllerTest a_control = new ControllerTest();  

    JFileChooser chooseFile;  
    public String fileName;
    public String pathFile;
    
    private String bipNew;
    private ArrayList<String> test = new ArrayList();
    
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
        a_panel.add(save);
        a_panel.add(loadpl);
        openFileButton.addActionListener(this);
        loadpl.addActionListener(this);
        save.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);   
        
        //media player
        /*
        JFXPanel fxPanel = new JFXPanel();
        String bip = "File1.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
        */
    }
    
    public void actionPerformed(ActionEvent _ae)
    { 
        if (_ae.getSource() == openFileButton)openFile();
        if (_ae.getSource() == save)savePlaylistFile();
        if (_ae.getSource() == loadpl)loadPlaylist();
    }
    
    public void getHardCodedPlayList()
    {
        saver.getHardCodedPlayList();
        System.out.println(saver.getHardCodedPlayList());
    }
    
    public void savePlaylistFile() //this can be used to save the playList
    {
        //for testing purposes
        test.add("Test");
        test.add("Test2");
        test.add("Test3");
        System.out.println(test);
        
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.setCurrentDirectory(new File((System.getProperty("user.dir")))); //sets to automatically set at user directory
        if (chooseFile.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) 
        {
            File file = chooseFile.getSelectedFile();
            pathFile = chooseFile.getSelectedFile().getPath();
            savePlaylist(pathFile); //uses the pathFile to save the playlist
        }
    }
    
    public void loadPlaylist()
    {
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.setCurrentDirectory(new File((System.getProperty("user.dir")))); //sets to automatically set at user directory
        if (chooseFile.showSaveDialog(loadpl) == JFileChooser.APPROVE_OPTION) 
        {
            File file = chooseFile.getSelectedFile();
            pathFile = chooseFile.getSelectedFile().getPath();
            saver.loadPlayList(pathFile); //uses the pathFile to load the playlist
        }
    }
    
    public void savePlaylist(String fileSave)
    {
        saver.savePlayList(test, fileSave);
    }
     
    public void playPlaylist(int l_number)
    {        
        chooseFile = new JFileChooser();
        String l_song = saver.getHardCodedPlayList().get(l_number); //plays based on the index of the song chosen
        System.out.println(saver.getHardCodedPlayList().get(l_number)); //can delete later
        
        JFXPanel fxPanel = new JFXPanel();
        String bip = l_song; //used to get the song path from name
        bipNew = bip.replace("\\", "/");
        
        String name = bipNew.substring(bipNew.lastIndexOf("/") + 1); //this is used to get the name of the file
        System.out.println(name);
        
        Media hit = new Media(new File(bipNew).toURI().toString());
        a_mediaPlayer = new MediaPlayer(hit);
        a_mediaPlayer.setAutoPlay(true);
        
        //this is just to test
        //savePlaylist();
    }
    
    //these are just copied from the GUITester (player)
    
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
