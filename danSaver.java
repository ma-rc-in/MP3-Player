import java.util.*;


public interface danSaver
{
    //settings functionality - band B requirements
    public void saveSettings();
    public void loadSettings();
    public boolean getMute();
    public void setMute(boolean _mute);
    public double getVolume();
    public void setVolume(double _volume);
    
    //playlist functionality - band C requirements
    public void savePlayList(ArrayList<String> _filepaths, String _filepath);
    public ArrayList<String> loadPlayList(String _filepath);
    public ArrayList<String> getHardCodedPlayList();
    
}
