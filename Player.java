import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.*;

public abstract class Player {
  String name;
  char mark;
  static boolean cheated = false;

  public static Player create(String name) {
    if(name.equalsIgnoreCase("Trump"))
      return new TrumpPlayer(name);
    else if(name.equalsIgnoreCase("Pete"))
      return new PetePlayer(name);
    else if(name.equalsIgnoreCase("Uncle Dan"))
      return new UncleDanPlayer(name);
    else if(name.equalsIgnoreCase("Pickle Rick"))
      return new PickleRickPlayer(name);
    else if(name.equalsIgnoreCase("Minions"))
      return new MinionsPlayer(name);
    else
      return new HumanPlayer(name);
  }

  Player(String name) {
    this.name = name;
  }

  abstract void makeMove(Board board, int turn);

  void playIntroClips() {}
  
  void playNameClips() {}

  void playConcessionClips(Player opponent) {}

  void playVictoryClips(Player opponent) {}

  boolean isTrump() {
    return false;
  }
  boolean isPete() {
    return false;
  }
  boolean isUncleDan() {
    return false;
  }
  boolean isPickleRick() {
    return false;
  }
  boolean isMinions() {
    return false;
  }
  boolean isKid() {
    return false;
  }
  boolean isLeon() {
    return false;
  }
  boolean isJade() {
    return false;
  }
  boolean isSawyer() {
    return false;
  }
  boolean isMia() {
    return false; 
  }
  
  static boolean somebodyHasCheated() {
    return cheated;
  }        

  void setMark(char newMark) {
    mark = newMark; 
  }
    
  boolean chance(int percentage) {
    return (Math.random() * 100 < percentage);
  }

  void playClip(String filePath) {
		try {
      new SimpleAudioPlayer().play(filePath);
		}
    catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
  
  void playClip(String filePath, String message) {
    new SimpleAudioPlayer().play(filePath, message);
  }

}

/*
class TrumpPlayer extends Player {

  TrumpPlayer(String name) {
    super(name);
  }

  boolean isTrump() {
    return true;
  }

  void makeMove(Board board, int turn) {
    if (board.makeWinningMove(mark) || cheats(board, turn) || board.makeRandomMove(mark))
      playClip("trump gloat.wav");
  }

  boolean cheats(Board board, int turn) {
    if(!somebodyHasCheated() && chance(60) && board.block2Way(mark)) {
      playClip("trump always tell truth.wav");
      board.restore(board.allMoves.get(turn - 2));
      cheated = true;
      return true;
    }  
    return false;
  }

  void playIntroClips() {
    playClip("trump hail.wav");
    playClip("snl intro.wav");
    playClip("minions bello.wav");
    playClip("raspberry.wav");
  }

  void playVictoryClips(Player opponent) {
    playClip("trump gloat.wav");
  }    
  
  void playConcessionClips(Player opponent) {
    playClip("trump decisively won.wav");
    playClip("trump shenanigans.wav");
    playClip("trump lost.wav");        
  }
}


class PetePlayer extends Player {

  PetePlayer(String name) {
    super(name);
  }

  boolean isPete() {
    return true;
  }

  void makeMove(Board board, int turn) {
    if (board.makeWinningMove(mark) ||
        (chance(85) && board.blockWinningMove(mark)) ||
        (chance(90) && board.setUp2Way(mark)) ||
        (chance(75) && board.block2Way(mark)) ||
        board.makeRandomMove(mark))
      playClip("pete final.wav");
  }

  void playIntroClips() {
    playClip("pete final.wav");
  }

  void playVictoryClips(Player opponent) {
    playClip("queen champions.wav");
  }
}


class UncleDanPlayer extends Player {

  UncleDanPlayer(String name) {
    super(name);
  }

  boolean isUncleDan() {
    return true;
  }

  void makeMove(Board board, int turn) {
    if (chance(90) && board.makeWinningMove(mark))
      playClip("what to do.wav");
    else if (chance(95) && board.blockWinningMove(mark))
      if (Math.random() > 0.5)
        playClip("not this time.wav");
      else
        playClip("what to do.wav");
    else if (chance(90) && board.block2Way(mark))
      playClip("dan uhoh.wav");
    else if (chance(85) && board.setUp2Way(mark))
      playClip("watch out.wav");
    else {
      board.makeRandomMove(mark);
      playClip("what to do.wav");
    }
  }

  void playIntroClips() {
    playClip("good bad ugly.wav");
  }

  void playConcessionClips(Player opponent) {
    if(opponent.isKid())
      playClip("dan humbly impressed.wav");
  }

  void playVictoryClips(Player opponent) {
    if(opponent.isKid()) {
      playClip("don't worry.wav");
    }
    else {
      playClip("queen champions.wav");
    }
  }
}


class PickleRickPlayer extends Player {

  PickleRickPlayer(String name) {
    super(name);
  }

  boolean isPickleRick() {
    return true;
  }

  void makeMove(Board board, int turn) {
    if (board.makeInitialMove(mark, turn) ||
        board.makeWinningMove(mark) ||
        board.blockWinningMove(mark) ||
        board.block2Way(mark) ||
        board.setUp2Way(mark) ||
        board.makeRandomMove(mark))
      playMoveClips(turn);
  }

  void playIntroClips() {
    playClip("pickle rick.wav");
    playClip("rick theme.wav");
  }

  void playMoveClips(int turn) {
    if(turn == 1 || turn == 6 || turn == 7)
      playClip("snake jazz.wav");
    else if(Math.random() > 0.75)
      playClip("pickle rick.wav");
    else if(Math.random() > 0.4)
      playClip("rick algorithm.wav");
    else
      playClip("rick belches.wav");
  }

  void playVictoryClips(Player opponent) {
    playClip("rick dance.wav");
  }
}


class MinionsPlayer extends Player {

  MinionsPlayer(String name) {
    super(name);
  }

  boolean isMinions() {
    return true;
  }

  void makeMove(Board board, int turn) {
    // if (cheats(allMoves, grid, turn, 67) || board.makeRandomMove(mark))
    if (cheats(board, turn) || board.makeRandomMove(mark))
      playMoveClips();
  }

  void playMoveClips() {
    if(chance(67))
      playClip("king bob.wav");
    else if(chance(50))
      playClip("raspberry.wav");
    else {
      playClip("minions bello.wav");
      playClip("minions bello.wav");
    }
  }

  void playIntroClips() {
    playClip("minions bello.wav");
    playClip("minions bello.wav");
    playClip("king bob.wav");
    playClip("raspberry.wav");
  }

  void playVictoryClips(Player opponent) {
    playClip("king bob.wav");
  }

  boolean cheats(Board board, int turn) {
    if (!somebodyHasCheated() && chance(67) && turn > 3) {
      playClip("cheating laugh.wav");
      new Board().show();
      System.out.println("Technical difficulties! Under construction.");
      playClip("minions fight.wav");
      System.out.println("Look out!! Is that El Macho standing behind you??");
      board.swapSides();
      board.show();
      cheated = true;
    }
    return false;
  }

}


class HumanPlayer extends Player {

  HumanPlayer(String name) {
    super(name);
  }

  boolean isUser() {
    return true;
  }

  boolean isLeon() {
    return name.equalsIgnoreCase("Leon");
  }

  boolean isJade() {
    return name.equalsIgnoreCase("Jade");
  }

  boolean isSawyer() {
    return name.equalsIgnoreCase("Sawyer");
  }

  boolean isMia() {
    return name.equalsIgnoreCase("Mia");
  }

  boolean isKid() {
    return isLeon() || isJade() || isSawyer() || isMia ();
  }

  private void makeMove(Board board, int turn) {
    Scanner keyboard = new Scanner(System.in);
    int spot = -1;
    String input;
    do {
      do {
        System.out.print(name + ", you are \'" + mark + "\', what square would you like? ");
        input = keyboard.nextLine();
        if(input.length() == 1 && input.charAt(0) >= '0' && input.charAt(0) <= '9') {
          spot = Integer.parseInt(input) - 1;
          if(spot == -1)
            goBack(board, turn);
        }
        else
          playClip("enter number.wav");
      } while (spot == -1);
      if(board.taken(spot))
        System.out.println("That square is already taken. Try again!");
    } while(board.taken(spot));
    board.move(spot, mark);
  }

  void goBack(Board board, int turn) {
    Scanner kbd = new Scanner(System.in);
    int numBack;
    System.out.print("What turn number would you like to go back to? ");
    numBack = turn - kbd.nextInt();
    while(numBack-- > 0) {
      board.restore(board.allMoves.pop());
    }
    System.out.println(board.allMoves);
    board.show();
  }

  void playIntroClips() {
    if (isLeon())
      playClip("dan greet Leon.wav");
    else if (isJade())
      playClip("dan greet jade.wav");
    else if (isSawyer())
      playClip("dan greet sawyer.wav");
    else if (isMia())
      playClip("dan greet mia.wav");
  }
  
  void playIntroClips(boolean bothKids) {
    if(isLeon())
      playClip("leon rumble.wav");
    else if(isJade())
      playClip("jade rumble.wav");
    else if(isSawyer())
      playClip("sawyer rumble.wav");
    else if(isMia())
      playClip("mia rumble.wav");
   }         

  void playVictoryClips(Player opponent) {
    playClip("good job.wav");
    if(isLeon())
      playClip("goal.wav", "GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooOOOOOoooOOOOOOOOOOOOOOOOOOOOOOOOOOO" 
                       + "\nOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" 
                       + "\nOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOAAAAAAAAAAAAAAAAAAAAAaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL" 
                       + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");  
    else if(isKid())
      playClip("queen champions.wav");
  }

}
*/ 