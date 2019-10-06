import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

	/*

      THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING

      CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas.

	*/

public class GuessingGame {

	private static Scanner scanner = new Scanner( System.in );
	//int numMatches;
	private ArrayList<Integer> numbers; //array of numbers from 1000-9999
	private int numGuesses;//number of guesses made
	private int myGuess;//the guess
	private int randomIndex;//random generated index to get random number in numbers left
	private int dig1;//left-most digit in random number
	private int dig2;//middle digit
	private int dig3;//middle digit
	private int dig4;//left-most digit in random number
	private int digArray1;//left-most digit in current number in array being checked
	private int digArray2;//middle digit
	private int digArray3;//middle digit
	private int digArray4;//right-most digit in current number in array being checked

	public GuessingGame () {
		numbers = new ArrayList<Integer>();
		for (int i = 1000; i <= 9999; i++){
			numbers.add(i);
		}
		//initialization to 0 of all variables
		randomIndex = 0;
		myGuess = 0;
		dig1 = 0;
		dig2 = 0;
		dig3 = 0;
		dig4 = 0;
		digArray1 = 0;
		digArray2 = 0;
	 	digArray3 = 0;
		digArray4 = 0;
		numGuesses = 0;

	}

	public int myGuessIs() {//generates random guess
		if(numbers.size() == 0){
			return -1;
		}else{
			randomIndex = (int)(Math.random() * numbers.size());//find random index within elements remaining in ArrayList
			myGuess = numbers.get(randomIndex);//get the integer at that random index value
			numGuesses++;//increase number of guesses taken.
			return myGuess;
		} 
	}

	public int getGuess(){
		return myGuess;
	}
	
	public int totalNumGuesses() {
		// this should return the total number of guesses taken
		return numGuesses;
	}

	public void getDigits(int num){ //this method sets the dig variable to the individual digits of the random number.
		dig4 = num % 10;//last digit
		num /= 10;
		dig3 = num % 10;//3rd digit
		num /= 10;
		dig2 = num % 10;//2nd digit
		dig1 = num / 10;//1st digit
	}

	public void getDigitsArray(int num){//this method sets the digArray variable to the individual digits of the number in the ArrayList.
		digArray4 = num % 10;//last digit
		num /= 10;
		digArray3 = num % 10;//3rd digit
		num /= 10;
		digArray2 = num % 10;//2nd digit
		digArray1 = num / 10;//1st digit
	}
 
	public void updateMyGuess(int nmatches) {
		int curGuess = getGuess();
		numbers.remove(numbers.indexOf(curGuess));//remove the element in the ArrayList that was guessed so we don't guess again.
		// update the guess based on the number of matching digits claimed by the user
		int i = 0;
		

		while(i < numbers.size()){//while loop to account for varying size and index of ArrayList as it shrinks
			int same = 0;//counter to see how many digits are the same
			getDigits(curGuess);
			getDigitsArray(numbers.get(i));
			if(dig1 == digArray1){//compare 1st digits
				same++;
			}if(dig2 == digArray2){//compare 2nd digits
				same++;
			}if(dig3 == digArray3){//comapre 3rd digits
				same++;
			}if(dig4 == digArray4){//compare 4th digits
				same++;
			}if(same != nmatches){
				numbers.remove(i);//only remove number if number of same digits is not the same as the number entered by user. 
			}
			i++;//increase variable that keeps while loop going
		}
	}
		

	
	// you shouldn't need to change the main function
	public static void main(String[] args) {

		GuessingGame gamer = new GuessingGame( );
  
		System.out.println("Let's play a game. Think of a number between 1000 and 9999");
        
        System.out.println("Press \"ENTER\" when you are ready");
        
        scanner.nextLine();
        
		int numMatches = 0;
		int myguess = 0;
		
		do {
			myguess = gamer.myGuessIs();
			if (myguess == -1) {
				System.out.println("I don't think your number exists!");
				System.exit(0);
			}
			System.out.println("I guess your number is " + myguess + ". How many digits did I guess correctly?");
			String userInput = scanner.nextLine();
			
			// quit if the user input nothing (such as pressed ESC)
			if (userInput == null)
				System.exit(0);
			// parse user input, pop up a warning message if the input is invalid
			try {
				numMatches = Integer.parseInt(userInput.trim());
			}
			catch(Exception exception) {
				System.out.println("Your input is invalid. Please enter a number between 0 and 4");
				continue;
			}
			// the number of matches must be between 0 and 4
			if (numMatches < 0 || numMatches > 4) {
				System.out.println("Your input is invalid. Please enter a number between 0 and 4");
				continue;
			}
			if (numMatches == 4)
				break;
			// update based on user input
			gamer.updateMyGuess(numMatches);
			
		} while (true);
		
		// the game ends when the user says all 4 digits are correct
		System.out.println("Aha, I got it, your number is " + myguess + ".");
		System.out.println("I did it in " + gamer.totalNumGuesses() + " turns.");
	}
}
