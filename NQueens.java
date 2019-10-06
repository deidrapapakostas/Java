/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas. */

import java.util.Stack;

public class NQueens {
 
  //create stack to be used to keep track of valid spots
  public static Stack<Integer> s = new Stack<Integer>(); 
  public static int solutions = 0;

  //method that checks if a position is safe to place a queen
  public static boolean isSafe(int c, int r, int n){ 
    int checksOut = 0;//integer that keeps track of a count of how many items are safe
    if(s.size() == 0){ //checks to make sure stack isn't empty
      return true; //if it's empty, position is safe by default. 
    }
    if(c > (n - 1)){ //if column is out of bounds, return false
      return false;//not safe
    }
    for (int i = 0; i < s.size(); i++){ //loops through all elements in stack
      if(s.get(i) != c && ((r - c) != (i - s.get(i)) && ((r + c) != (i + s.get(i))))){ //checks diagonals and column
        checksOut++; //increases count
      } 
    }
    if(checksOut == s.size()){ //if the counter equals the number of elements in the stack then it's safe
      return true; //safe
    }else{
      return false; //not safe
    }
  }
 
  //helper method that recursively calls itself
  public static void helperSolve(int r, int c, int n){
      if(r >= 0 && r <= n){ //ensures that rows don't go out of bounds
        if(r == 0 && c > n - 1){//base case: when backtracks all the way to first row and no solution found
          return;
        }if(s.size() == n){ //if a solution is found
          solutions++;
          printSolution(s);
          helperSolve(--r, s.pop() + 1, n); //recursive call back a row to find next solution
        }else if(isSafe(c, r, n)){ //if a safe position is found
          s.push(c);
          helperSolve(++r, 0, n);//recursive call forward a row
        }else if(c >= n - 1){//if no safe position on that row
          helperSolve(--r, s.pop() + 1, n);//recursive call back a row
        }else{//move forward a column
          helperSolve(r, ++c, n);
        }
      }else{ //if out of bounds, return
        return;
      }
      
      
  }

  public static int solve(int n) {
    int col = 0;
    int row = 0;
    solutions = 0; //make sure it resets to 0
    helperSolve(row, col, n); //call helper method that uses recursion
    return solutions;
  }

  
  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();  
  }//printSolution()
  
  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {
  
  int n = 5;
  
  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if   
  }//if
  
  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()
  
}
