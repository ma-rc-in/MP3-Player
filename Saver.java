import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class Saver //implements danSaver
{
    private double  a_volume;
    private boolean a_mute;
    
    ////***B Requirements***
    
    public boolean getMute()
    {
        return a_mute;
    }
    
    public void setMute(boolean _mute)
    {
        a_mute = _mute;
    }
    
    public double getVolume()
    {
        return a_volume;
    }
    
    public void setVolume(double _volume)
    {
        a_volume = _volume;
    }
    
    public void saveSettings()
    {
       try 
       {
           FileOutputStream t_outputStream = new FileOutputStream("settings"); //saves to file settings, or creates it if it can't
           XMLEncoder t_encoder = new XMLEncoder(t_outputStream);
           t_encoder.writeObject(this);
           t_encoder.close();
           t_outputStream.close();
       } 
       
       catch (Exception _ex) 
       {
           System.out.println(_ex.getMessage());
       }     
    }
    
    public void loadSettings()
    {
        try
        {
            FileInputStream t_input = new FileInputStream("settings"); //load from file settings
            XMLDecoder t_decode = new XMLDecoder(t_input);
            Object t_object = t_decode.readObject();
            Saver t_temp = (Saver)t_object;
            
            //sets values for the mute and volume
            setMute(t_temp.getMute());
            setVolume(t_temp.getVolume());
            
            t_decode.close();
            t_input.close();
        }
        catch (Exception _ex) 
        {
            System.out.println(_ex.getMessage());
        }  
    }
      
    //***C Requirements***
    //public void savePlayList(ArrayList<String> _filepaths, String _filepath){}
    //public ArrayList<String> loadPlayList(String _filepath){}    
    //public ArrayList<String> getHardCodedPlayList(){}
}
