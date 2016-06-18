/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.mixer;

import java.io.*;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
/**
 *
 * @author Usered
 * 
 * 
 */

class music_line_object extends Thread 
{
    // provide the line object a name
    // line object parameters
    String name_of_line; 
    String credits;
    AudioStream audioStream;
    AudioData musicData;
    AudioPlayer musicPlayer = AudioPlayer.player;
    ContinuousAudioDataStream loops = null;
    InputStream in;
    
    
            
    music_line_object() {
        this.loop = true;
        this.name_of_line = "no_name";
    };
    
    music_line_object(String input_file_name) {
        try 
            {
                this.in = new FileInputStream(input_file_name);
                this.audioStream = new AudioStream(in);
                musicData = this.audioStream.getData();
                loops = new ContinuousAudioDataStream(musicData);
                musicPlayer.start(loops);

                  
            } 
        catch (Exception e) {};
            
        
        
    };
    
    
    // volume range 0 to 1
    double gain;
    
    // length of the clip in microseconds. i.e. 1,000,000 = 1 second
    double length;
    
    // range 1 (left) to -1 (right)
    double pitch;
    
    // range 1 (left) to -1 (right)
    double pan;
    
    // whether to loop clip
    boolean loop;
    
    // start time and end time
    double start_time;
    double end_time;   
}

class music_clip_object extends music_line_object 
{

}

class audio_player_class 
    {
                AudioData musicData;
                ContinuousAudioDataStream loops = null;
                InputStream in;
                
                // constructors
                audio_player_class()  {};
                audio_player_class(music_line_object audio_link)  { AudioPlayer.player.start(audio_link.audioStream); };

                // method plays line based on current settings
                void play(music_line_object audio_link)
                    {   
                       
                        AudioPlayer.player.start(audio_link.audioStream);
                     
                    };
    };

public class MusicMixer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
       
    // play the audio clip with the audioplayer class
    
    
        // create a device to play sound
        // defining own object will allow additional features like loop
        audio_player_class device_a = new audio_player_class();
        
        // sound sample 1
        // TODO code application logic here
        music_line_object sound_1;
        sound_1 = new music_line_object("/Users/Usered/Desktop/a_team_intro.wav");
        sound_1.name_of_line = "Sheep blee";
        sound_1.credits = "http://download.wavetlan.com/SVV/Media/HTTP/WAV/Media-Convert/Media-Convert_test5_PCM_Stereo_VBR_8SS_44100Hz.wav";
        sound_1.loop = true;
        
        music_line_object sound_2;
        sound_2 = new music_line_object("/Users/Usered/Desktop/gwh1.wav");
        sound_2.name_of_line = "drum";
        sound_2.credits = "http://freewavesamples.com/files/High-Conga-1.wav";
        sound_2.loop = true;
        
        music_line_object sound_3;
        sound_3 = new music_line_object("/Users/Usered/Desktop/dt_16bars_102rap.wav");
        sound_3.name_of_line = "drum";
        sound_3.credits = "http://www.wavlist.com/soundfx/027/";
        sound_3.loop = true;
        
        music_line_object sound_4;
        sound_4 = new music_line_object("/Users/Usered/Desktop/a_team_intro.wav");
        sound_4.name_of_line = "drum";
        sound_4.credits = "http://www.wavlist.com/soundfx/027/";
        sound_4.loop = true;
        
        // play music
        device_a.play(sound_3);
       
    }
    
}
