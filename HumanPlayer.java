import java.util.*;
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

  void makeMove(Board board, int turn) {
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
  
  void playNameClips() {
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
