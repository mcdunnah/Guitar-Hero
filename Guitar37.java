// skeleton version of the class

import java.util.*;

/**
 * This class represents a guitar with 37 strings
 * It implements the Guitar interface.
 */
public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    public GuitarString[] strings;  
    public int time; 

   /**
      * Constructs a Guitar37 object with 37 strings, each initialized to a specific frequency.
      * The frequencies are based on the provided keyboard layout.
   */
   public Guitar37() {
      strings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < strings.length; i++) {
         double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
         strings[i] = new GuitarString(frequency);
      }
      time = 0;
   }

   /**
      * Plays the specified note by plucking the corresponding string.
      * @param pitch The pitch of the note to play.
   */
   public void playNote(int pitch) {
      int index = pitch + 24;
      if (index >= 0 && index < strings.length) {
         strings[index].pluck();
      }
   }

   /**
      * Checks if the specified key has a corresponding string on this guitar
      * @param key The key to check.
      * @return True if the key has a corresponding string
   */
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) != -1;
   }

   /**
      * Plucks the string corresponding to the specified key.
      * @param key The key representing the string to pluck.
      * @throws IllegalArgumentException If the key is not one of the 37 keys it is designed to play.
   */
   public void pluck(char key) {
      int index = KEYBOARD.indexOf(key);
      if (index == -1) {
         throw new IllegalArgumentException("Invalid key: " + key);
      }
      strings[index].pluck();
   }

   /**
      * Returns the sum of the current samples from all strings.
      * @return The current sample of the guitar.
   */
   public double sample() {
      double sum = 0;
      for (GuitarString string : strings) {
         sum += string.sample();
      }
      return sum;
   }

   /**
      * Advances the time forward by one "tic" for all strings.
   */
   public void tic() {
      time++;
      for (GuitarString string : strings) {
         string.tic();
      }
   }

   /**
      * Returns the current time (the number of times tic has been called).
      * @return The current time.
   */
   public int time() {
      return time;
   }
}
