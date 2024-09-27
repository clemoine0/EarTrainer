import java.util.Scanner;
import java.util.Random;

/* Notes and intervals in this file are numbered by semitone.
The number 1 will correspond to the lowest note in the range, 2 to the note a semitone above, etc...
I will be using a range of 49 notes, from C2 to C5, but this program will be designed to take
any range of notes, so long as there are at least 12 and their audio files are numbered properly. */

public class EarTrainer {
    public final static Scanner in = new Scanner(System.in);
    public final static Random rand = new Random();
    
    static void menu() {
        System.out.println("Ear Trainer");
        System.out.println("Type \"start\" to start or \"quit\" to quit.");

        while (true) {
            System.out.print(">> ");
            String choice = in.nextLine();
            if (choice.toLowerCase().equals("quit")) {
                return;
            } else if (choice.toLowerCase().equals("start")) {
                start();
                return;
            } else if (choice.toLowerCase().equals("help")) {
                help();
                return;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }       

    static void help() {
        // TODO: help message
    }
    
    static void start() {
        System.out.println("Started");
        // System.out.println("Interval: ")
    }

    static int[] randomNotes(int noteRange) {
        
        /* This function returns a random pair of notes,
        the interval between which may range from 0 (unison) to 12 (octave) */
        
        int[] notePair = {0, 0, 0}; // {Lower note, higher note, interval}
        int intervalSize = rand.nextInt(12)+1;
        
        notePair[0] = rand.nextInt(noteRange-intervalSize)+1;
        notePair[1] = rand.nextInt(notePair[0]+intervalSize);
        notePair[2] = intervalSize;
        
        return notePair;
    } 
    
    public static void main(String[] args) {
        menu();
    }
}