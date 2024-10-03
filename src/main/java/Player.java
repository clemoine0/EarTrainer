import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Player {

    Clip note1;
    Clip note2;

    public void play(int firstNote, int secondNote) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        File file1 = new File("audio/"+firstNote+".wav");
        File file2 = new File("audio/"+secondNote+".wav");
        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
        note1 = AudioSystem.getClip();
        note2 = AudioSystem.getClip();
        note1.open(audioStream1);
        note2.open(audioStream2);

        if (note1.isRunning()||note2.isRunning()) {
            note1.close();
            note2.close();
            }

        note1.start();
        Thread.sleep(1000);
        note2.start();
    }
}
