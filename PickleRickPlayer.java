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
