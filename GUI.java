import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener
{
    
    //need to impletement try catch and make sure that everything is protected
    
    private JFrame a_frame = new JFrame("Music Player"); //JFrame
    
    //control panel attributes
    private JButton a_playButton = new JButton("Play");
    private JButton a_pauseButton = new JButton("Pause");
    private JButton a_stopButton = new JButton("Stop");
    
    //information panel attrbriutes
    private JLabel a_songName = new JLabel("No Current Song");
    private JLabel a_currentTime = new JLabel("0:00");
    private JLabel a_totalTime = new JLabel("0:00"); 
    private JLabel a_leftB = new JLabel("(");
    private JLabel a_rightB = new JLabel(")");
    private JLabel a_slash = new JLabel("/");
    
    //slider
    private JSlider a_timeSlider = new JSlider();
    
    //pannels
    //DELETE LATER - can have multiple j_pannels, like a <div> in html
    private JPanel a_controlsPanel = new JPanel(); //used for play,stop and pause
    private JPanel a_informationPanel = new JPanel(); //used for track name and time

    //menu bar
    private JMenuBar a_menuBar = new JMenuBar();
    private JMenu a_file = new JMenu("File");
    private JMenuItem a_menuItemOpen = new JMenuItem("Open");
    
    //variables
    private double guiVolume;
    private boolean guiMute;
    
    GUITester player = new GUITester(); //constructor for the player class
    
    public GUI()
    {
        //creation
        add(a_controlsPanel);
        add(a_informationPanel);
        
        //menu bar
        a_menuBar.add(a_file); //menu bar
        a_file.add(a_menuItemOpen); //menu bar item
        
        //information panel
        a_informationPanel.add(a_songName); //name of the song label
        a_informationPanel.add(a_leftB);
        a_informationPanel.add(a_currentTime); //current time label (default 0:00)
        a_informationPanel.add(a_slash);
        a_informationPanel.add(a_totalTime); //total time label (default 0:00)
        a_informationPanel.add(a_rightB);
        a_informationPanel.add(a_timeSlider);
        
        
        //controls buttons
        a_controlsPanel.add(a_playButton);
        a_controlsPanel.add(a_pauseButton);
        a_controlsPanel.add(a_stopButton);
        
        //action listeners
        a_playButton.addActionListener(this);
        a_pauseButton.addActionListener(this);
        a_stopButton.addActionListener(this);
        a_menuItemOpen.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame layout
        a_frame.getContentPane().add(BorderLayout.NORTH, a_menuBar);
        a_frame.getContentPane().add(BorderLayout.CENTER, a_informationPanel);
        a_frame.getContentPane().add(BorderLayout.SOUTH, a_controlsPanel);
        
        //a frame layout
        a_frame.setSize(350,200);
        a_frame.setLocationRelativeTo(null);
        a_frame.setVisible(true);
        a_frame.setResizable(false);
        
        //need to call getMute and getVolume
        
        player.initialiseFiles();
    }
    
    public void actionPerformed(ActionEvent _actionevent)
    {
        if (_actionevent.getSource() == a_playButton)
        {
            try{songName();play();}
            catch(Exception e) //no media selected
            {
                JOptionPane.showMessageDialog(null, "There seems to be a problem."); //change
            }
        }
        if (_actionevent.getSource() == a_pauseButton)
        {
            pause();
        }
        if (_actionevent.getSource() == a_stopButton)
        {
            stop();
        }
        
        if (_actionevent.getSource() == a_menuItemOpen)
        {
            open();
            songName(); //updates song name label
        }

    }
    
    //C Requirements
    public void play()
    {
        //getCurrentTrackName(); //need to call from player
        player.play();     
    }

    public void stop()
    {
        player.stop();
    }
    
    public void pause()
    {
        player.pause();
    }
    
    public void open()
    {
        player.openFile();
    }
    
    //B Requirements 
    public void volume()
    {
        //need to set the volume
        //player.setVolume(); //double guiVolume
    }
    
    public void mute()
    {
        //need to setMute
        //player.setMute(); //boolean guiMute
    }
    
    public void restart()
    {
        
    }
    
    public void songName()
    {
        String l_name = player.getCurrentTrackName(); //get name of track
        a_songName.setText(l_name); //setName
    }
}
