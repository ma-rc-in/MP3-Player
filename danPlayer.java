import javafx.util.Duration;
import java.util.*;

public interface danPlayer
{
    //the very basics, band A requirements
    public void play();
    public void stop();
    public void pause();
    public void openFile();
    
    //intermediate, band B requirements
    public String getCurrentTrackName();  // need to strip down to filename and remove ".mp3"
    public void setMute(boolean _mute);
    public void setVolume(double _volume); //0-1
    public Duration getTime();
    public boolean getMute();
    public double getVolume(); //0-1
    
    //advanced, band C requirements
    public void setTime(Duration _time);       
    public Duration getTotalTime();
    public void openPlayList();
    ArrayList<String> getPlayList(); //need to strip down filenames
    public void playTrack(int _trackNo); // choose which track in playlist to play.
    public void restart(); //go to start of current track
    
    
}
