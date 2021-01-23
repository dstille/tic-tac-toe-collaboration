import java.util.*;
class Game {
  
  Board board = new Board();
  Player firstPlayer;
  Player secondPlayer;
  int turn = 0;
  Deque<Player> playerRotation  = new ArrayDeque <> ();
  
  Game(Player firstPlayer, Player secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
    playerRotation.add(firstPlayer);
    playerRotation.add(secondPlayer);
    firstPlayer.setMark('X');
    secondPlayer.setMark('O');
  }  

  void playGame(int gameNum) {
    introduceGame();
    System.out.println("Game # " + gameNum + ": " + firstPlayer.name + " v. " + secondPlayer.name + ". HERE WE GO!!!");
    board.show();

    do { 
      turn++;
      System.out.println("Turn # " + turn + ". It's time for " + currentPlayer().name + " to decide on a move.");
      Player currentPlayer = playerRotation.remove();
      currentPlayer.makeMove(board, turn);
      System.out.println(currentPlayer.name + " chooses square " + (board.lastMove() + 1));
      board.show();
      playerRotation.add(currentPlayer);
    } while (board.openSpotsRemaining() && !hasWinner());
    
    if(hasWinner())
      declareWinner();
    else
      declareTie();
  }

  Player winner() {
    if (hasWinner())
      return playerRotation.peekLast();  // currentPlayer
    return null;
  }
    
  Player loser() {
    return getOtherPlayer(winner());
  }     
  
  boolean hasWinner() {
    return board.won();
  }
  
  void declareWinner() {
    System.out.println(winner().name + " wins!!!");   
    if(loser().isTrump()) {
      winner().playVictoryClips(loser());
      loser().playConcessionClips(winner());
    }
    else {
      loser().playConcessionClips(winner());
      winner().playVictoryClips(loser());
    }
  }
  
  void declareTie() {
    System.out.println("No winner. That was a tie.");
    playClip("tie.wav");
  }

  private void introduceGame() {
    if(firstPlayer.isKid() && secondPlayer.isKid()) {
      firstPlayer.playNameClips();
      playClip("versus.wav");
      secondPlayer.playNameClips();
      playClip("get ready to rumble.wav");
    }  
    else {  
      firstPlayer.playIntroClips();
      secondPlayer.playIntroClips();
    }
  }
  
  private Player currentPlayer() {
    if (turn%2 == 1)
      return firstPlayer;
    return secondPlayer;
  }       
  
  private Player getOtherPlayer(Player player) {
    if(player==firstPlayer)
      return secondPlayer;
    return firstPlayer;
  }
  
  private void playClip(String filePath) {
	  new SimpleAudioPlayer().play(filePath);
	}

  private void playClip(String filePath, String message) {
	  new SimpleAudioPlayer().play(filePath, message);
	}

}           