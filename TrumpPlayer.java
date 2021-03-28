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

