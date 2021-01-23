import java.util.*;
import java.io.*;
public class Board {

  char[] grid = new char[] {'1','2','3','4','5','6','7','8','9'};
  Stack <Integer> allMoves = new Stack <Integer>();
  Board() {}

  boolean move(int spot, char mark) {
    if (spot == -1) return false;
    grid[spot] = mark;
    allMoves.push(spot);
    System.out.println("All Moves = " + allMoves);
    return true;
  }
  
  int lastMove() {
    return allMoves.peek();
  }
  
  boolean isX(char mark) {
    return mark == 'X';
  }
  
  boolean isO(char mark) {
    return mark == 'O';
  }      

  void show() {
    System.out.println("  -------------");
    for (int i = 0; i < grid.length; i++) {
      System.out.println("  | " + grid[i++] + " | " + grid[i++] + " | " + grid[i] + " | ");
      System.out.println("  -------------");
    }
  }

  void restore(int spot) {
    grid[spot] = ((spot+1) + "").charAt(0);
  }

  void swapSides() {
    for (int i = 0; i < grid.length; i++)
      if (taken(i))
        grid[i] = opposite(grid[i]);
  }

  boolean won() {
    for (int row = 0; row < 3; row++) {
      if ((grid[3*row] == grid[3*row+1]) && (grid[3*row+1] == grid[3*row+2]))
        return true;
    }
    for (int col = 0; col < 3; col++) {
      if ((grid[col] == grid[col+3]) && (grid[col+3] == grid[col+6]))
        return true;
    }
    if ((grid[0] == grid[4]) && (grid[4] == grid[8]))
        return true;
    if ((grid[2] == grid[4]) && (grid[4] == grid[6]))
        return true;
    return false;
  }

  boolean taken(int spot) {
    return isMark(grid[spot]);
  }
  
  boolean openSpotsRemaining() {
    for(int spot = 0; spot <= 8; spot++)
      if(!taken(spot))
        return true;
    return false;
  }      
    

  boolean makeWinningMove(char mark) {
    return move(findWinningMove(mark), mark);
  }

  boolean blockWinningMove(char mark) {
    return move(findWinningMove(opposite(mark)), mark);
  }

  boolean setUp2Way(char mark) {
    return move(find2Way(mark), mark);
  }

  boolean block2Way(char mark) {
    return move(find2Way(opposite(mark)), mark);
  }

  boolean makeRandomMove(char mark) {
    int spot;
    do {
      spot = (int) (Math.random()*9);
    } while (taken(spot));
    return move(spot, mark);
  }

  boolean makeInitialMove(char mark, int turn) {
   int spot = -1;
   if(turn == 0)
     spot = 2 * (int) (Math.random()*5);
   else if (turn == 1)
     if (allMoves.elementAt(0).equals(4))
       spot = 0;
     else
       spot = 8 - allMoves.elementAt(0);
   return move(spot, mark);
  }

  private int findWinningMove(char mark) {
    int winningSpot = -1;
    for (int hypothetical = 0; hypothetical < 9; hypothetical++)
      if (!taken(hypothetical)) {
        grid[hypothetical] = mark;
        if (won())
          winningSpot = hypothetical;
        restore(hypothetical);
      }
    return winningSpot;
  }

  private int find2Way(char mark) {
    int twoWaySpot = -1;
    for (int hypothetical = 0; hypothetical < 9; hypothetical++) {
      int winningCombo = 0;
      if (!taken(hypothetical)) {
        grid[hypothetical] = mark;
        for (int continuation = 0; continuation < 9; continuation++) {
          if (!taken(continuation)) {
            grid[continuation] = mark;
            if (won())
              winningCombo++;
            restore(continuation);
          }
          if (winningCombo == 2)
            twoWaySpot = hypothetical;
        }
        restore(hypothetical);
      }
    }
    return twoWaySpot;
  }

  private char opposite(char mark) {
    if (mark == 'X')
      return 'O';
    else
      return 'X';
  }

  private boolean isMark(char mark) {
    return (mark == 'X' || mark =='O');
  }

}
