import java.util.*;
import java.io.*;

public class Match {

  Scanner keyboard = new Scanner(System.in);
  Player firstPlayer;
  Player secondPlayer;
  int gameNum = 0;
  
  public void playMatch() {
    introduceMatch();
    createPlayers();

    do {
      Game game = new Game(firstPlayer, secondPlayer);
      game.playGame(++gameNum);
    } while(rematch());
    
    goodbye();
  }
  
  void introduceMatch() {
    System.out.println("Welcome to Uncle Dan's Tic-Tac-Toe game!" +
                      "\nYou can choose to play against eachother, against one of the profiled players," +
                      "\nOr have the profiled players play eachother." +
                      "\nJust enter your first name or the name of the players listed below:" +
                      "\n--------------------------------------------------------------------" +
                      "\nUncle Dan" +
                      "\nPete" +
                      "\nTrump" +
                      "\nMinions" +
                      "\nPickle Rick\n");
  }

  void createPlayers() {
    System.out.print("Enter player 1 name: ");
    firstPlayer = Player.create(keyboard.nextLine());
    System.out.print("Enter player 2 name: ");
    secondPlayer = Player.create(keyboard.nextLine());
    System.out.println();
  }
  
  boolean rematch() {
    System.out.print("Would you like another round with the same players? ");
    if (keyboard.nextLine().charAt(0) == 'y') {
      Player temp = firstPlayer;
      firstPlayer = secondPlayer;
      secondPlayer = temp;
      return true;
    }
    return false;
  }       
  
  private void goodbye() {
    if(firstPlayer.isTrump() || secondPlayer.isTrump()) {
      System.out.print("Do you know who likes to have the last word?...");
      playClip("trump nobody likes me.wav");
      System.out.print("...no, not him...");
      playClip("so long.wav", "...I do. Goodbye kids. GOODBYE MR TRUMP!!!");
    }
    else {
      System.out.print("Goodbye...");
      playClip("istanbuhl end.wav");
      System.out.print("...Are you still there?");
    }
  }
  
  private void playClip(String filePath) {
	  new SimpleAudioPlayer().play(filePath);
	}

  private void playClip(String filePath, String message) {
	  new SimpleAudioPlayer().play(filePath, message);
	}

  public static void main(String [] args) {
    Match match = new Match();
    match.playMatch();
  }
}  

