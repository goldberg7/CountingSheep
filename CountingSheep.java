import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.io.*;

/*Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster. 
 * First, she picks a number N. Then she starts naming N, 2 × N, 3 × N, and so on.
 *  Whenever she names a number, she thinks about all of the digits in that number. 
 *  She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has 
 *  seen at least once so far as part of any number she has named. Once she has
 *   seen each of the ten digits at least once, she will fall asleep.*/


/*This tested correctly for both the large and small test files*/
public class CountingSheep {

	public static void main(String[] args) {
		File file = new File("large-test.txt");
		try{
			Scanner scnr = new Scanner(file);
			PrintWriter outFile = new PrintWriter("large-output.txt");
			
			int cases = scnr.nextInt(); 
			scnr.nextLine();
			
			
			for(int i = 1; i <= cases; i++){
				//Use this set to keep track of all 0...9 digits seen so far
				HashSet<Integer> set = new HashSet<>();
				
				//number read from file
				int n  = scnr.nextInt();
				//incremented count to multiply original number by 1 * N, 2 * N etc.
				int count = 1;
				
				if(n == 0)
					outFile.println("Case #" + i + ": INSOMNIA");
				
				else{
					while(true){
						int[] curr = extractDigits(n * count);
						//regardless, dups will not be added 
						for(int x : curr){
							set.add(x);
						}
						//check if we have all 0...9
						if(containsAll(set)){
							break;
						}
						
						count++;
					}
					//write to file with the correct output
					outFile.println("Case #" + i + ": " + n * count);
				}
				
				scnr.nextLine();
			}
			
			outFile.close();
			scnr.close();
		}
		catch(FileNotFoundException e){}
	}

	//given a number, return its digits in the form of an array
	public static int[] extractDigits(int number){
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		while(number > 0){
			int a = number / 10;
			a *= 10;
			int dig = number - a;
			digits.add(dig);
			
			number /= 10;	
		}
		
		int[] ret = new int[digits.size()];
		for(int i = 0; i < digits.size(); i++){
			ret[i] = digits.get(i);
		}
		
		return ret;
	}
	
	//check if the hash set contains all the integers 0...9
	public static boolean containsAll(HashSet<Integer> set){
		for(int i = 0; i < 10; i++)
			if(!set.contains(i))
				return false;
		
		return true;
	}
}
