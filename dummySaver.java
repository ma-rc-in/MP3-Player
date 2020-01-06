import java.util.*;
/**
 * Dummy saver class for use in KF6034 assignment for teams of two.
 *
 * @author Dan Hodgson
 * @version 1.0
 */

public class dummySaver implements danSaver
{
    // instance variables - replace the example below with your own
    private double  a_volume = 1.0;
    private boolean a_mute = false;

    //settings functionality - band B requirements
    public void saveSettings()
    {
        System.out.println("saveSettings() invoked in dummySaver");
    }

    public void loadSettings()
    {
        System.out.println("loadSettings() invoked in dummySaver");
    }
    
    public boolean getMute()
    {
        System.out.println("getMute() invoked in dummySaver: "+ a_mute);
        return a_mute;
    }
    
    public void setMute(boolean _mute)
    {
        System.out.println("saveSettings() invoked in dummySaver: " + _mute);
        a_mute = _mute;
    }
    
    public double getVolume()
    {
        System.out.println("getVolume() invoked in dummySaver: "+ a_volume);
        return a_volume;
    }
    
    public void setVolume(double _volume)
    {
        System.out.println("setVolume() invoked in dummySaver: "+ _volume);
        a_volume = _volume;
    }    
    
    //playlist functionality - band C requirements
    public void savePlayList(ArrayList<String> _filepaths, String _filepath)
    {
        System.out.println("savePlayList() invoked in dummySaver with the following parameters: ");
        System.out.println("    Playlist filepath: " + _filepath);
        for (String t_file : _filepaths)
        {
            System.out.println("      " + t_file);
        }
        
    };
    
    public ArrayList<String> loadPlayList(String _filepath)
    {
        System.out.println("loadPlayList() invoked in dummySaver with the following parameters: ");
        System.out.println("    Playlist filepath: " + _filepath);
        System.out.println("-This will invoke getHardCodedPlaylist()");
        return getHardCodedPlayList();
    }
    
    public ArrayList<String> getHardCodedPlayList()
    {
        System.out.println("getHardCodedPlaylist() invoked in dummySaver");
        ArrayList<String> l_playList = new ArrayList<String>();
        l_playList.add("1 Munaar.mp3");
        l_playList.add("2 Ketchup.mp3");
        l_playList.add("3 JPC.mp3");
        l_playList.add("4 Pandon.mp3");
        return l_playList;
    }
}
