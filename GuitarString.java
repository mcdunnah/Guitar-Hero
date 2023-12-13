/** * Mia McDunnah CS145 20258
   * GuitarString class models a vibrating guitar 
   * string of a given frequency
*/

import java.util.*;


/**
   * Constructs a guitar string of the given frequency.
   * @param frequency The frequency of the guitar string must be greater than 0
   * @throws IllegalArgumentException If the frequency is less than or equal to 0,
   * or if the resulting size of the ring buffer is less than 2
*/
public class GuitarString {
   // initialize ringbuffer
   private Queue<Double> ringbuffer;
   private int size;
   
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   
   public GuitarString (double frequency) {
      if (frequency <= 0) {
            throw new IllegalArgumentException();
      }
      size = Math.round(StdAudio.SAMPLE_RATE/(float)frequency);
      if (size < 2) {
            throw new IllegalArgumentException();
      }
      ringbuffer = new LinkedList<>(); 
      for (int i=0; i<size; i++) {
         ringbuffer.add(0.0);
      }
   }
   
   /**
      * for TESTING and DEBUGGING only
      * Constructs a guitar string and initializes the contents of the ring buffer to the values in the array
      * @param init The array of initial values for the ring buffer
      * @throws IllegalArgumentException If the array has fewer than two elements.
   */
   public GuitarString (double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringbuffer = new LinkedList<>();
      for (double value : init) {
         ringbuffer.add(value);
      }
      size = init.length;
   }
   
   /**
      * Replaces N elements in ring buffer with N random values between [-0.5, 0.5)
   */
   public void pluck() {
      Random random = new Random();
      for (int i = 0; i < size; i++) {
         ringbuffer.remove(); 
         ringbuffer.add(random.nextDouble() - 0.5);
      }
   }
   
   /**
      * Applies the Karplus-Strong update once 
      * Deletes the sample at the front of the ring buffer and adds to
      * the end of the ring buffer
   */
   public void tic() {
      if (ringbuffer.isEmpty()) {
         throw new IllegalStateException("Ring buffer is empty.");
      }
      double frontSample = ringbuffer.poll();
      double average = (frontSample + ringbuffer.peek()) / 2.0;
      ringbuffer.add(ENERGY_DECAY_FACTOR * average);
   }

   /**
      * Returns the current sample
      * @return The current sample of the guitar string
      * @throws NoSuchElementException If the ring buffer is empty
   */
   public double sample() {
      if (ringbuffer.isEmpty()) {
         throw new NoSuchElementException("Ring buffer is empty.");
      }
      return ringbuffer.peek();
   }
}