import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.ObjectInputStream;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.util.*;

public class Saver implements danSaver
{
    private double  a_volume;
    private boolean a_mute;
    
    ArrayList<String> a_playListLoad = new ArrayList();

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
           //t_encoder.writeObject(this);
           t_encoder.writeObject(getMute()); //this has been changed from this to the methods
           t_encoder.writeObject(getVolume());
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
    public ArrayList<String> getHardCodedPlayList()
    {
        ArrayList<String> l_hardCodedPlayList = new ArrayList();
        
        //adding files to the arraylist
        //l_hardCodedPlayList.add("C://Users//cal24//Documents//University//Object Oriented Programming//Assignment 1//Assignment 1//Assignment 1//Callum Marcin Part A-copy//file1.mp3"); 
        //l_hardCodedPlayList.add("C://Users//cal24//Documents//University//Object Oriented Programming//Assignment 1//Assignment 1//Assignment 1//Callum Marcin Part A-copy//file2.mp3");
        
        //so files can be played here, regardless of the directory
        l_hardCodedPlayList.add(System.getProperty("user.dir") + "//file1.mp3");
        l_hardCodedPlayList.add(System.getProperty("user.dir") + "//file2.mp3");

        return l_hardCodedPlayList;
    }
    
    public void savePlayList(ArrayList<String> _filepaths, String _filepath)
    {
       try 
       {
           //this may be the filepath to the arraylist?
          //_filepaths.add(_filepath);//adds _filepath to the playlist -delete
          
          FileOutputStream t_outputStream = new FileOutputStream(_filepath); //saves to file settings, or creates it if it can't
          XMLEncoder t_encoder = new XMLEncoder(t_outputStream);
          t_encoder.writeObject(_filepaths);//might need to change
          t_encoder.close();
          t_outputStream.close();
       } 
       
       catch (Exception _ex) 
       {
          System.out.println(_ex.getMessage());
       }
    }
    
    public ArrayList<String> loadPlayList(String _filepath)
    {
        try
        {
            FileInputStream t_input = new FileInputStream(_filepath); //load from file "settings, was "settings" _filepath
            XMLDecoder t_decode = new XMLDecoder(t_input);
            a_playListLoad = (ArrayList) t_decode.readObject();

            t_decode.close();
            t_input.close(); 
        }
        catch (Exception _ex) 
        {
            System.out.println(_ex.getMessage());
        }  
        
       return a_playListLoad; //returns the playlist name
    }
}
