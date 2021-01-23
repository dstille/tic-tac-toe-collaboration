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

