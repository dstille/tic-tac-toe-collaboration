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

