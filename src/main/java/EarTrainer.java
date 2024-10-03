import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

/* Notes and intervals in this file are numbered by semitone.
The number 1 will correspond to the lowest note in the range, 2 to the note a semitone above, etc...
I will be using a range of 49 notes, from C2 to C6, but this program will be designed to take
any range of notes, so long as there are at least 12 and their audio files are numbered properly. */

public class EarTrainer {
    public final static Scanner in = new Scanner(System.in);
    public final static Random rand = new Random();
    public final static int range = 49;
    public final static String[] intervalNames = {"unison", "min2", "maj2", "min3",
                                    "maj3", "p4", "tritone", "p5",
                                    "min6", "maj6", "min7", "maj7", "octave"};
    public static int[] score = {0, 0}; // {correct, answered}; displayed when quit
    public static Player player = new Player();
    public static boolean playerQuit = false;

    static void menu() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        System.out.println("Ear Trainer");
        System.out.println("Type \"start\", \"quit\", or \"help\".");

        while (true) {
            System.out.print(">> ");
            String choice = in.nextLine();
            if (choice.equalsIgnoreCase("quit")) {
                return;
            } else if (choice.equalsIgnoreCase("start")) {
                start();
                return;
            } else if (choice.equalsIgnoreCase("help")) {
                help();
            } else {
                System.out.println("Invalid input!");
            }
        }
    }       

    static void help() {
        System.out.println("This program will help you practice your relative pitch.");
        System.out.println("When you start, two notes will be played\nand you will be prompted to enter the interval between them.");
        System.out.println("In ascending order, this program will expect intervals like so:");
        System.out.println("unison, min2, maj2, min3, maj3, p4, tritone,\np5, min6, maj6, min7, maj7, octave/p8");
        System.out.println("You may enter \"replay\" to hear an interval again");
    }
    
    static void start() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        // System.out.println("Started");
        while (!playerQuit) { // loops for each interval
            int[] interval = randomNotes();
            score[1]++;
            playNotes(interval);
            
            while (true) { // loops for each invalid guess or replay request
                // take input
                System.out.print(">> ");
                String guess = in.nextLine();

                // check input
                if (guess.equalsIgnoreCase("replay")) {
                    playNotes(interval);
                } else if (guess.equalsIgnoreCase("quit")) {
                    quit(score);
                    playerQuit = true;
                    break;
                }
                else if (Arrays.asList(intervalNames).contains(guess)) { // valid guess
                    if (intervalNames[interval[2]].equals(guess)) { // if guess is correct
                        System.out.println("Correct!");
                        score[0]++;
                    }
                    
                    break;
                } else {
                    System.out.println("Invalid guess");
                }
            } 
        }
    }

    static int[] randomNotes() {
        
        /* This function returns a random pair of notes,
        the interval between which may range from 0 (unison) to 12 (octave) */
        
        int[] interval = {0, 0, 0}; // {Lower note, higher note, interval}
        int intervalSize = rand.nextInt(13);
        
        interval[0] = rand.nextInt(EarTrainer.range-intervalSize)+1;
        interval[1] = interval[0]+intervalSize;
        interval[2] = intervalSize;
        
        return interval;
    } 

    static void quit(int[] scores) {
        System.out.println("You got " + scores[0] + " correct, out of " + scores[1] + "!");
    }
    
    static void playNotes(int[] interval) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        player.play(interval[0], interval[1]);
    }
    
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        menu();
    }
}