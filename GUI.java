import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements ActionListener
{
    //need to impletement try catch and make sure that everything is protected
    private JFrame a_frame = new JFrame("Music Player"); //JFrame
    //private static String[] test = {"Test1", "Test2", "Test3"}; //delete later
    
    //control panel attributes
    private JButton a_playButton = new JButton("Play");
    private JButton a_pauseButton = new JButton("Pause");
    private JButton a_stopButton = new JButton("Stop");
    private JButton a_muteButton = new JButton("Mute");
    private JButton a_nextSongButton = new JButton("Next");
    private JButton a_previousSongButton = new JButton("Previous");
    
    //information panel attrbriutes
    private JLabel a_songName = new JLabel("No Current Song");
    private JLabel a_currentTime = new JLabel("(0:00)");
    private JList a_playlist = new JList();
    private JScrollPane a_playlistSP = new JScrollPane(a_playlist);
    
    //sliders
    private JSlider a_timeSlider = new JSlider();
    private JSlider a_volumeSlider = new JSlider();

    //pannels
    //DELETE LATER - can have multiple j_pannels, like a <div> in html
    private JPanel a_controlsPanel = new JPanel(); //used for play,stop and pause , delete gridbaglayout
    private JPanel a_informationPanel = new JPanel(); //used for track name and time

    //menu bar
    private JMenuBar a_menuBar = new JMenuBar();
    private JMenu a_file = new JMenu("File");
    private JMenuItem a_menuItemOpen = new JMenuItem("Open");

    //variables
    private double guiVolume;
    private boolean a_guiMute;
    private int a_playlistValue = 0;

    GUITester player = new GUITester(); //constructor for the player class

    public GUI()
    {
        //layout of the information panel (Layout Manager)
        a_informationPanel.setLayout(new BoxLayout(a_informationPanel, BoxLayout.Y_AXIS));
        a_controlsPanel.setLayout(new BoxLayout(a_controlsPanel, BoxLayout.X_AXIS));

        //creation
        add(a_controlsPanel);
        add(a_informationPanel);

        //menu bar
        a_menuBar.add(a_file); //menu bar
        a_file.add(a_menuItemOpen); //menu bar item
        
        //information panel (adding and alignment)
        a_playlist.setVisibleRowCount(5);
        a_playlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        a_playlistSP.setViewportView(a_playlist);
        a_informationPanel.add(a_playlistSP); //playlist
        a_playlist.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //used to change song on playlist
        a_playlist.addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event){
                    a_playlistValue = a_playlist.getSelectedIndex();
                    playlistPlay(a_playlistValue);
                }
            }
        );
        
        //information panel (other components)
        a_informationPanel.add(a_songName); //name of the song label
        a_songName.setAlignmentX(Component.CENTER_ALIGNMENT);
        a_informationPanel.add(a_timeSlider);
        a_timeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        a_timeSlider.setPreferredSize(new Dimension(325, 20));
        a_informationPanel.add(a_currentTime); //current time label (default 0:00)
        a_currentTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //controls buttons
        a_controlsPanel.add(a_playButton);
        a_controlsPanel.add(a_pauseButton);
        a_controlsPanel.add(a_stopButton);
        a_controlsPanel.add(a_nextSongButton);
        a_controlsPanel.add(a_previousSongButton);

        a_controlsPanel.add(a_muteButton);
        a_muteButton.setPreferredSize(new Dimension(50, 20)); //mute button
        a_muteButton.setFont(new Font("Arial", Font.PLAIN, 5)); //mute button
        a_controlsPanel.add(a_volumeSlider);
        a_volumeSlider.setPreferredSize(new Dimension(75, 10));
        
        //action listeners
        a_playButton.addActionListener(this);
        a_pauseButton.addActionListener(this);
        a_stopButton.addActionListener(this);
        a_muteButton.addActionListener(this);
        a_nextSongButton.addActionListener(this);
        a_previousSongButton.addActionListener(this);
        a_menuItemOpen.addActionListener(this);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame layout
        a_frame.getContentPane().add(BorderLayout.PAGE_START, a_menuBar); // was north
        a_frame.getContentPane().add(BorderLayout.CENTER, a_informationPanel); //was center
        a_frame.getContentPane().add(BorderLayout.PAGE_END, a_controlsPanel); //was south
             
        //a frame layout
        a_frame.setSize(600,250); //400 150
        a_frame.setLocationRelativeTo(null);
        a_frame.setVisible(true);
        a_frame.setResizable(false);

        //calls getMute and volume when starting
        if(getMute() == true){a_guiMute = true;}else{a_guiMute=false;} //check for the mute button icon
        getVolume(); //this is used to make sure that the volume is correct;
        
        //used to get the volumesliders poistion
        a_volumeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e)
        {setVolume();}});
        
        a_timeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e)
        {setTime();}});
        
        //timer, updates every 0.1 seconds
        ActionListener updateTime = new ActionListener()
        {
            public void actionPerformed(ActionEvent _actionevent)
            {getTime();}
        };
        
        Timer timer = new Timer(100, updateTime);
        timer.start();
        
        player.initialiseFiles();
    }

    public void actionPerformed(ActionEvent _actionevent) //actions performed by buttons
    {
        if (_actionevent.getSource() == a_playButton)
        {
            try
            {
                songName();
                play();
            }
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

        if (_actionevent.getSource() == a_muteButton) //mute functionality
        {
            setMute();
        }
        
        if (_actionevent.getSource() == a_menuItemOpen)
        {
            open();
            songName(); //updates song name label
        }
        
        if (_actionevent.getSource() == a_nextSongButton)
        {
            skip();
        }
        
        if (_actionevent.getSource() == a_previousSongButton)
        {
            back();
        }
        
    }

    //C Requirements
    //***C Requirements***
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
    
    //***B Requirements***    
    public void getVolume()
    {
        //double l_volume = player.getVolume();
        //a_volumeSlider.setValue(l_volume);
        //return l_volume;
    }
    
    public void setVolume()
    {
        //need to set the volume
        double l_volume = a_volumeSlider.getValue();
        l_volume = l_volume / 100;
        player.setVolume(l_volume); //double guiVolume
    }  
        
    public boolean getMute()
    {
        boolean l_isMute = player.getMute();
        return l_isMute;
    }

    public void setMute()
    {
        //need to setMute
        player.setMute(a_guiMute); //boolean guiMute
    }

    public void songName()
    {
        try
        {
            String l_name = player.getCurrentTrackName(); //get name of track
            a_songName.setText("Now Playing: " + l_name + "."); //setName
        }
        catch(Exception e)
        {
            a_songName.setText("No Current Song");
        }
    }
    
    public void getTime()
    {
        //Double l_time = player.getTime();
        //a_currentTime.setText("(" + l_time + ")");    
    }
    
    //***C Requirements***
    public void setTime()
    {
        
    }
    
    public void playlistDisplay() //need to call the method from somewhere
    {
        /*
        for(int l_sizeNumber = 0; l_sizeNumber < player.getPlaylist().size; l_sizeNumber++)
        {
            a_playlist.addElement(l_sizeNumber + ") " + player.getPlaylist());
        }
        */
    }
    
    public void playlistPlay(int a_playlistValue) //need to pass value
    {
        //player.playTrack(a_playlistValue);
    }
    
    public void skip()
    {
        /*
        a_playlistValue++;
        if(a_playlistValue > player.getPlaylist().size) //repeats if it gets to the last song
        {
            a_playlistValue = 0;
        }
        player.playTrack(a_playlistValue);
        */
    }
    
    public void back()
    {
        
    }
}
