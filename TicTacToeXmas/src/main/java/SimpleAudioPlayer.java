import java.io.*;
import javax.sound.sampled.*;

class SimpleAudioPlayer {

  void play(String filePath, String message) {
    try {
      InputStream in = new BufferedInputStream(getClass().getResourceAsStream("resources/" + filePath));
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(in);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      for (int i = 0; i < message.length(); i++) {
        clip.start();
        while (clip.getMicrosecondPosition() < (i + 1) * clip.getMicrosecondLength() / (message.length())) {}
        clip.stop();
        if (!message.equals(" ")) {
          System.out.print(message.charAt(i));
          clip.setMicrosecondPosition(clip.getMicrosecondPosition());
        }
      }
      clip.stop();
      clip.close();
    } catch (Exception ex) {
      System.out.println("Error with playing sound.");
      ex.printStackTrace();
    }
  }

  void play(String filePath) {
    play(filePath, " ");
  }

}



