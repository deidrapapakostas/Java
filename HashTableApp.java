/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import java.util.HashMap;
//import java.util.Map;

public class HashTableApp {

	//Builds and returns a hash table of frequencies from the given input file
	public static HashSeparateChaining getMap(Scanner in){
		HashSeparateChaining result = new HashSeparateChaining();
		String current;
		int countForCurrent; //integer to track number of matches for ascii values
		Integer value;
		while (in.hasNext()){
			current = in.next(); //saves String of next word
			countForCurrent = 0;
			current = current.toLowerCase(); //makes sure it's in lowercase
			//checks every character in String to ensure it's a letter or number
			for(int i = 0; i < current.length(); i++){
				if((current.charAt(i) > 96 && current.charAt(i) < 123) || (current.charAt(i) < 58 && current.charAt(i) > 47)){
					countForCurrent++; //counter to match it to length of String
				}
			}
			if(countForCurrent == current.length()){ //if counter matches the length, then word is valid
				if(result.get(current) == null){
					value = 1; //marks values as one if value had been null
				}else{ //otherwise updates it by 1
					value = result.get(current) + 1;
				}
				result.put(current, value); //puts value in table
			}
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length < 1){
			System.out.println("Pass the filename to be read as an argument.");
			System.exit(-1);
		}

		File input = new File(args[0]);
		Scanner fileReader = null;

		try{
			fileReader = new Scanner(input);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.  Check the file name and try again.");
			System.exit(-2);
		}

		HashSeparateChaining map = getMap(fileReader);
		System.out.println(map.toString());
		//map.toString();
		System.out.println("size of my map: " + map.size());

		//****************************************************************************
		//The following code implements Java's hashmap to compare my results to Java's. 
		/*Scanner fileReader2 = null;
		try{
			fileReader2 = new Scanner(input);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.  Check the file name and try again.");
			System.exit(-2);
		}
		String currentString;
		Integer value2;
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		while(fileReader2.hasNext()){
			currentString = fileReader2.next();
			int countForCurrent = 0;
			currentString = currentString.toLowerCase();
			for(int i = 0; i < currentString.length(); i++){
				if((currentString.charAt(i) > 96 && currentString.charAt(i) < 123) || (currentString.charAt(i) < 58 && currentString.charAt(i) > 47)){
					countForCurrent++;
				}
			}
			if(countForCurrent == currentString.length()){
				if(hmap.get(currentString) == null){
					value2 = 1;
				}else{
					value2 = hmap.get(currentString) + 1;
				}
				hmap.put(currentString, value2);
			}
		}
		System.out.println("size of hmap: " + hmap.size());*/
	}
}