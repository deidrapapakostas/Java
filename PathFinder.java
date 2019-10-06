/**
 * Starter code for the Maze path finder problem.
 */

/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;

/*
 * Recursive class to represent a position in a path
 */
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'
	
	// reference to the previous position (parent) that leads to this position on a path
	Position parent;
	
	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}
	
	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}
	
}

public class PathFinder {
	
	//main method given to us
	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}
		
		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}
	
	//method to check if an element in maze is equal to char 0 and can therefore be used to go through maze
	public static boolean isValid(int row, int col, char[][] maze){
		return maze[row][col] == '0'; //will return true if char is 0
	}

	//method to check if an element at a row and col in maze is actually in maze (within rows and cols of maze)
	public static boolean inBounds(int row, int col, char[][] maze){
		return col >= 0 && col < maze.length && row >= 0 && row < maze.length; //will return true if element is within maze boundaries
	}

	//method that using stack implementation of deque to find path through maze
	//pushes and pops valid elements (DURL order) onto stack implementation of deque and updates current Position at each iteration based on popped element 
	public static Position [] stackSearch(char [] [] maze){
		// path finding algorithm uses the stack to manage search list
		// algorithm marks the path in the maze, and returns array of Position 
		// objects coressponding to path, and null if no path is found
		//works LIFO order: last in first out

		Position[] positions; //array of Position elements to be returned
		ArrayDeque<Position> s = new ArrayDeque<Position>(); //using ArrayDeque as a stack in this method
		Position current; //position that keeps track of current position element being tested

		//Positions to keep track of elements above, below, right, and left
		Position down; 
		Position up;
		Position right;
		Position left;

		//Position first to hold the very first element in maze at [0][0]
		Position first;


		if(isValid(0, 0, maze)){ //will check if maze can even be entered
			first = new Position(0, 0, maze[0][0]); //holds first position in maze
			s.push(first); //uses stack implementation of deque to add (push) element to first place
			while(!s.isEmpty()){ //keeps loop running as long as deque is not empty
				current = s.pop(); //stack implementation to pop off first element
				if(current.i == maze.length - 1 && current.j == maze[0].length - 1){ //will end search if last element of maze is found
					positions = new Position[(maze.length) * (maze[0].length)]; //creates Position[] of max length
					positions[0] = current; //sets current position (last Position in maze) to first element in Positions[]
					int count = 1; //sets count to keep track of current element in positions array
					while(current.parent != null){ //continues to add positions as long as the parent != null (until the very first element)

						positions[count] = current.parent;
						current = current.parent;//updates current to parent element
						count++; //increases count
					}

					//every element visited has been marked with 'X' so this loop replaces them to '0'
					for(int r = 0; r < maze.length; r++){
						for(int c = 0; c < maze[r].length; c++){
							if(maze[r][c] == 'X'){
								maze[r][c] = '0';
							}
						}
					}
					maze[0][0] = 'X'; //we know that the first element was used in order to enter maze
					int track = 0; //counter to keep track of how many elements in positions array and keep while loop running
					while(positions[track] != null){ //will continue while there are Positions in positions array
						maze[positions[track].i][positions[track].j] = 'X'; //sets all elements in maze that were used to find path to 'X'
						track++; //increases counter
					}
					return positions; //returns array of valid positions to find path through maze

				//will check all valid positions down, up, right, and left of the current element and pop those valid onto deque (stack implementation)
				//updates current element to popped element 
				}else{ //will evaluate the following if not at the final element of maze
					//checks down
					if(inBounds(current.i + 1, current.j, maze) && isValid(current.i + 1, current.j, maze)){//checks in in bounds and valid position (== '0')
						down = new Position(current.i + 1, current.j, maze[current.i + 1][current.j], current);
						//maze[current.i + 1][current.j] = 'X'; //marks position as visited
						s.push(down);//adds position if valid to top element of stack implementation (push)
					//checks up
					}if(inBounds(current.i - 1, current.j, maze) && isValid(current.i - 1, current.j, maze)){//checks in in bounds and valid position (== '0')
						up = new Position(current.i - 1, current.j, maze[current.i - 1][current.j], current);
						//maze[current.i - 1][current.j] = 'X'; //marks position as visited
						s.push(up);//adds position if valid to top element of stack implementation (push)
					//checks right
					}if(inBounds(current.i, current.j + 1, maze) && isValid(current.i, current.j + 1, maze)){//checks in in bounds and valid position (== '0')
						right = new Position(current.i, current.j + 1, maze[current.i][current.j + 1], current);
						//maze[current.i][current.j + 1] = 'X'; //marks position as visited
						s.push(right);//adds position if valid to top element of stack implementation (push)
					//checks left
					}if(inBounds(current.i, current.j - 1, maze) && isValid(current.i, current.j - 1, maze)){//checks in in bounds and valid position (== '0')
						left = new Position(current.i, current.j - 1, maze[current.i][current.j - 1], current);
						//maze[current.i][current.j - 1] = 'X'; //marks position as visited
						s.push(left);//adds position if valid to top element of stack implementation (push)
					}
					maze[current.i][current.j] = 'X';
				}
				
			}
		}
		return null; //returns null if no path found
	}
	

	//method does same work as stack but uses queue implementation of deque
	public static Position [] queueSearch(char [] [] maze){
		// path finds algorithm using the queue to manage search list
		// algorithm marks the path in the maze, and returns array of Position 
		// objects coressponding to path, and null if no path found
		// works in FIFO order: first in first out

		Position[] positions; //positions array returns array of valid Position elements
		ArrayDeque<Position> queue = new ArrayDeque<Position>(); //uses queue implementation of deque
		Position current; //keeps track of current element being evaluated

		//Position elements to keep track of valid positions below, above, right, and left of current element
		Position down; 
		Position up;
		Position right;
		Position left;

		//keeps track of very first element of maze
		Position first;

		//allows us to enter maze only if first element is valid
		if(isValid(0, 0, maze)){
			first = new Position(0, 0, maze[0][0]);
			queue.addLast(first); //adds element to end of deque (queue implementation) (enqueue)
			while(!queue.isEmpty()){ //will run as long as queue implementation of deque not empty
				current = queue.removeFirst(); //removes first element of queue (dequeue)
				//will work on compiling elements for returned array
				if(current.i == maze.length - 1 && current.j == maze[0].length - 1){ 
					positions = new Position[(maze.length) * (maze[0].length)]; 
					positions[0] = current; //sets first element to current position
					int count = 1; //counter to keep track of element
					while(current.parent != null){//will iterate as long as parent position exists
						positions[count] = current.parent;
						current = current.parent;
						count++; //increases count
					}
					//sets X's to 0's since X's marked all visited positions and not just valid positions
					for(int r = 0; r < maze.length; r++){
						for(int c = 0; c < maze[r].length; c++){
							if(maze[r][c] == 'X'){
								maze[r][c] = '0';
							}
						}
					}
					//sets first element to X since will be valid
					maze[0][0] = 'X';
					int track = 0; //counter to keep track of current element
					while(positions[track] != null){ //will set only valid positions to X
						maze[positions[track].i][positions[track].j] = 'X';
						track++;
					}
					return positions; //returns array of positions

				//this part of method will function as long as final position not found and list not empty
				}else{
					//checks down
					if(inBounds(current.i + 1, current.j, maze) && isValid(current.i + 1, current.j, maze)){//checks in in bounds and valid position (== '0')
						down = new Position(current.i + 1, current.j, maze[current.i + 1][current.j], current);
						maze[current.i + 1][current.j] = 'X';//marks position as visited
						queue.addLast(down); //will add position if valid to end of queue implementation of deque (enqueue)
					//checks up
					}if(inBounds(current.i - 1, current.j, maze) && isValid(current.i - 1, current.j, maze)){//checks in in bounds and valid position (== '0')
						up = new Position(current.i - 1, current.j, maze[current.i - 1][current.j], current);
						maze[current.i - 1][current.j] = 'X';//marks position as visited
						queue.addLast(up);//will add position if valid to end of queue implementation of deque (enqueue)
					//checks right
					}if(inBounds(current.i, current.j + 1, maze) && isValid(current.i, current.j + 1, maze)){//checks in in bounds and valid position (== '0')
						right = new Position(current.i, current.j + 1, maze[current.i][current.j + 1], current);
						maze[current.i][current.j + 1] = 'X';//marks position as visited
						queue.addLast(right);//will add position if valid to end of queue implementation of deque (enqueue)
					//checks left
					}if(inBounds(current.i, current.j - 1, maze) && isValid(current.i, current.j - 1, maze)){//checks in in bounds and valid position (== '0')
						left = new Position(current.i, current.j - 1, maze[current.i][current.j - 1], current);
						maze[current.i][current.j - 1] = 'X';//marks position as visited
						queue.addLast(left);//will add position if valid to end of queue implementation of deque (enqueue)
					}
				}
				
			}
		}
		return null; //returns null if no path found
	}
	
	//method to print path of maze in coordinate-form starting from entrance point
	public static void printPath(Position [] path){
		int n = 0; //counter
		if(path == null){
			System.out.println("No path was found."); //if method returned null, error message printed
		}else{
			while(path[n] != null){//while array is not null (only uses valid elements)
				n++; //increase counter to know how many positions will be used
			}
			for(int k = n - 1; k >= 0; k--){ //counter in decreasing order since array returned starts from the end of the maze
				System.out.print("[" + path[k].i + "," + path[k].j + "] "); //prints path as list of [i][j] coordinates
			}
			System.out.println();//goes to the line
		}
		
	}
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}


}
