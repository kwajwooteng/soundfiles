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
 * @author K Oteng-Amoako
 * @dated 28 June 2016
 * 
 */

class music_line_object extends Thread 
{
                // provide the line object a name
                // line object parameters
                String name_of_line; 
                String credits;
                String file_name;
                AudioStream audioStream;
                AudioData musicData;
                AudioPlayer musicPlayer;
                ContinuousAudioDataStream loops;
                AudioDataStream single;
                InputStream in;
                byte bytedata;

                music_line_object() 
                    { 
                        this.name_of_line = "no_name";
                        this.loops = null;
                        this.single = null;
                        this.in = null;
                        this.musicPlayer = AudioPlayer.player;
                    };

                music_line_object(String input_file_name) throws java.io.IOException {

                            this.in = new FileInputStream(input_file_name);
                            this.audioStream = new AudioStream(in);
                            musicData = this.audioStream.getData();
                            loops = null;
                            loops = new ContinuousAudioDataStream(musicData);
                            single = new AudioDataStream(musicData);

                            if (this.loop) 
                            {
                                musicPlayer.start(loops);  
                            } 
                                else 
                            { 
                                musicPlayer.start(single);      
                            }         

                };



                void start_play()
                  {

                    loops = new ContinuousAudioDataStream(musicData);
                    single = new AudioDataStream(musicData);

                    if (this.loop) 
                        {
                            musicPlayer.start(loops);  
                        } 
                    else 
                        { 
                            musicPlayer.start(single);     
                        }


                    musicPlayer.start(this.audioStream);

                  };

                void load_track(String input_file_name) throws java.io.IOException
                  {
                    this.in = new FileInputStream(input_file_name);
                    this.audioStream = new AudioStream(in);
                    musicData = this.audioStream.getData();
                  };

                void set_loop(Boolean loop_status) throws java.io.IOException
                  {
                    this.loop = loop_status;
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
{}

class audio_player_class 
    {           
                
                // constructors
                audio_player_class()  
                {
                    loop_sample = null;
                    single_play_sample = null;
                    musicPlayer = null;
                    //this.setPlayerParameters();
                };
                
                audio_player_class(music_line_object object_file)  
                { 
                    try 
                        {
                            this.set_insert(object_file);
                            this.play();
                        }
                            catch (IOException io)
                        {
                    
                        }
                };
                
                
                void set_insert(music_line_object object_file)
                {
                    // set the sound object
                    this.casette = object_file;
                    this.loop_track_status = casette.loop;
                };
                
                // method plays line based on current settings
                void play() throws java.io.IOException
                {         
                    // generate sound samples
                    this.loop_sample = new ContinuousAudioDataStream(this.casette.musicData);
                    this.single_play_sample = new AudioDataStream(this.casette.musicData);
                    this.musicPlayer = AudioPlayer.player;
                    
                    // loop or simply play
                    if (this.loop_track_status) 
                        {
                            this.musicPlayer.start(this.loop_sample);  
                        } 
                    else 
                        { 
                            this.musicPlayer.start(this.single_play_sample);     
                        }  
 
                };
     
    
                // volume range 0 to 1
                private double gain;
    
                // length of the clip in microseconds. i.e. 1,000,000 = 1 second
                private double length;
    
                // range 1 (left) to -1 (right)
                private  double pitch;
    
                // range 1 (left) to -1 (right)
                private double pan;
    
                // start time and end time
                private double start_time;
                private double end_time;  
    
                // AudioDataStream and AudioPlayer class declarations
                private ContinuousAudioDataStream loop_sample;
                private AudioDataStream single_play_sample;
                private AudioPlayer musicPlayer;
                private Boolean loop_track_status;
                private music_line_object casette;
       
    };

public class MusicMixer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    
                // play the audio clip with the audioplayer class
                // create a device to play sound
                // defining own object will allow additional features like loop
                audio_player_class device_a;

                // create sound sample 1
                music_line_object track_1 = new music_line_object();
                track_1.loop = true;
                track_1.file_name = "/Users/Usered/Desktop/gwh1.wav";
                track_1.name_of_line = "drum";
                track_1.credits = "http://www.wavlist.com/soundfx/027/";
                track_1.load_track("/Users/Usered/Desktop/gwh1.wav");
                //sound_3.start_play();

                // create sound sample 2
                music_line_object track_2 = new music_line_object();
                track_2.loop = true;
                track_2.file_name = "/Users/Usered/Desktop/fanfare.wav";
                track_2.credits = "http://www.wavlist.com/soundfx/027/";  
                track_2.load_track("/Users/Usered/Desktop/fanfare.wav");
                //sound_4.start_play();

                // play music
                device_a = new audio_player_class(track_1);
                device_a = new audio_player_class(track_2);
       
    }
    
}
