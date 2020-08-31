/*
 * The Music class plays the music for BlackJack by opening the audio input stream
 * and playing the same audio clip infinitely.
 */

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Music extends JFrame {
	
	/**
	 * Constructor
	 * pre: none
	 * post: Audio is played the moment the main method runs.
	 */
	public Music() {
		try {
			// Song declaration and opening the audio input stream.
			URL song = this.getClass().getClassLoader().getResource("jazz.wav");
    		AudioInputStream audio = AudioSystem.getAudioInputStream(song); // throws UnsupportedAudioFileException, IOException
            
    		// Get, open, and play the audio clip.
    		Clip clip = AudioSystem.getClip(); // throws LineUnavailableException
            clip.open(audio); // throws IOException, LineUnavailableException
        	clip.start();
        	
        	// Repeat the song infinitely.
        	clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (LineUnavailableException e) {
    		e.printStackTrace();
    	}
	}
}
