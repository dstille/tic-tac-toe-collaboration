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
