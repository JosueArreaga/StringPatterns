package main;

import util.*;
import java.util.*;

public class PatternFinder {

	/**
	 *	CLASS: COP3337 Programming 2
	 *	Professor: Kianoush
	 *	By: Josue Arreaga, ID: 6278623
	 * 	Description: This program allows the user to input a value between 100,000 to a Billion and generates a
	 * 	random string of such length. We then look for substrings in the string that are: singletons, ArithmeticPatterns,
	 * 	NegativeArithmeticPatterns, bipartites, tripartites, or palindromes. We then return the longest and most
	 * 	interesting string.This program heavily relays in exception handling. We create our own exceptions and catch
	 * 	clauses. I also made heavy use of StringBuilder to avoid the issues associated with string concatenation.
	 */



    public static void main(String[] args) {
	Scanner keyboard = new Scanner(System.in);
	System.out.println("Enter the length of random string: ");
	int patternMaxLength = 10;//you need to update this part so that the value is given by the user via keyboard!
	int randomStringLength = keyboard.nextInt();

	while (true) {
	    try {
		if (randomStringLength < 100000 || randomStringLength > 1000000000)
		    throw new NumberFormatException();
	    } catch (NumberFormatException e) {
		System.out.println("Try again!");
		randomStringLength = keyboard.nextInt();
		continue;
	    }
	    break;
	}
	//Step 2: generating random string...
	String randomString = "thisisarandomstringbobbobbobendofthestring";//randomStringGenerator(randomStringLength);
	//Step 3: finding the interesting patterns
    	try {
    	    for (int length = patternMaxLength; length > 0; length--){
				singletonMiner(randomString, length);
				ArithmeticStringPositiveMiner(randomString, length);
				ArithmeticStringNegativeMiner(randomString, length);
				TripartiteMiner(randomString, length);
				BipartiteMiner(randomString, length);
				palindromeMiner(randomString, length);
			}
    	} catch (Exception exp) {
    	    System.out.println(exp.getMessage());
    	}
//		try{
//			for (int length = patternMaxLength; length > 0; length--)
//				ArithmeticStringPositiveMiner(randomString, length);
//		}catch (Exception exp){
//			System.out.println(exp.getMessage());
//		}
//		try{
//			for (int length = patternMaxLength; length > 0; length--)
//				ArithmeticStringNegativeMiner(randomString, length);
//		}catch (Exception exp){
//			System.out.println(exp.getMessage());
//		}
//		try{
//			for (int length = patternMaxLength; length > 0; length--)
//				TripartiteMiner(randomString, length);
//		}catch (Exception exp){
//			System.out.println(exp.getMessage());
//		}
//		try{
//			for (int length = patternMaxLength; length > 0; length--)
//				BipartiteMiner(randomString, length);
//		}catch (Exception exp){
//			System.out.println(exp.getMessage());
//		}
//		try{
//			for (int length = patternMaxLength; length > 0; length--)
//				palindromeMiner(randomString, length);
//
//		}catch (Exception exp){
//			System.out.println(exp.getMessage());
//		}
    }



	private static String randomStringGenerator(int length) {// generates a string made of randomly generated lowercase
		// letters.
		Random random = new Random(System.nanoTime());
		char[] array = new char[length];
		for (int i = 0; i < length; i++)
			array[i] = (char) ('a' + random.nextInt(26));
		return new String(array);
	}

	private static void singletonMiner(String mine, int length) throws SingletonException{
		for (int start = 0; start < mine.length() - length; start++) {
			int i;
			for (i = start + 1; i < start + length; i++)
				if (mine.charAt(i) != mine.charAt(i - 1))
					break;
			if (i == start + length){
				SingletonException e = new SingletonException(mine.substring(start, start + length), start);
				throw e;
			}
		}
	}

	private static void ArithmeticStringPositiveMiner(String mine, int length) throws ArithmeticExeption {

		StringBuilder result = new StringBuilder();

		char[] num = new char[length];
		char previous = ' ';

		for(int i = 0; i <= mine.length() - length; i++){
			for(int j = 0; j < length; j++){
				num[j] = mine.charAt(i + j);
			}

			for(int j = 0; j < length; j++){
				if(j == 0){
					previous = num[j];
					result.setLength(0);
					result.append(previous);
					continue;
				}
				if(previous + 1 != num[j]){
					//System.out.println("previous " + previous +  " current " + num[j]);
					break;
				}
				previous = num[j];
				result.append(previous);
				if(j + 1 >= length)
					throw new ArithmeticExeption(result.toString(), i);
			}
		}
	}

	private static void ArithmeticStringNegativeMiner(String mine, int length) throws NegativeArithmeticExeption {

		StringBuilder result = new StringBuilder();

		char[] num = new char[length];
		char previous = ' ';

		//2312kjdasksddcba
		for(int i = mine.length(); i >= length; i--){
			for(int j = 0; j < length; j++){
				num[j] = mine.charAt(i - 1 - j);
			}
			for(int j = 0; j < length; j++){
				if(j == 0){
					previous = num[j];
					result.setLength(0); // result = new StringBuilder();
					result.append(previous);
					continue;
				}
				if(previous + 1 != num[j]){
					//System.out.println("previous " + previous +  " current " + num[j]);
					break;
				}
				previous = num[j];
				result.append(previous);
				if(j + 1 >= length){

					String backwardsResult = result.toString();
					result.setLength(0);

					for(int l = backwardsResult.length() - 1; l >= 0; l--)
						result.append(backwardsResult.charAt(l));
					throw new NegativeArithmeticExeption(result.toString(), i - length);
				}
			}
		}
	}

	private static void TripartiteMiner(String mine, int length) throws TripartiteException {

		if(length % 3 != 0)
			return;

		int distance = length/3;

		StringBuilder first = new StringBuilder();
		StringBuilder second = new StringBuilder();
		StringBuilder third = new StringBuilder();


		for(int i = 0; i <= mine.length() - length; i++){
			first.setLength(0);
			first.append(mine.substring(i, i + distance));
			second.setLength(0);
			second.append(mine.substring(i + distance, i + (distance * 2)));
			third.setLength(0);
			third.append(mine.substring(i + (distance * 2) , i + (distance * 3)));

			//System.out.println(first + " " + second + " " + third);
			if(second.toString().equals(first.toString()) && first.toString().equals(third.toString()))
				throw new TripartiteException(first.toString() + second + third, i);
		}






		/*

		Alternate Working code:

			This implementation different from out current one because multiple N,
		the length of the string by 3. In other words, given an odd string of length 7 we try to find if there is
		a bipartite string of length 21. This code tries to force finding a tripartite string regardless of length. It
		also takes far longer as we are supposed to find string lengths of 10 or less. Meanwhile this code will look for
		substrings of length 30, 27, 24, ... 0;


		StringBuilder first = new StringBuilder();
		StringBuilder second = new StringBuilder();
		StringBuilder third = new StringBuilder();


		for(int i = 0; i < mine.length() - (length * 3) - 2; i++){

			first.setLength(0);
			first.append(mine.substring(i, i + length + 1));
			second.setLength(0);
			second.append(mine.substring(i + length + 1, i + (length * 2) + 2));
			third.setLength(0);
			third.append(mine.substring(i + (length * 2) + 2, i + (length * 3) + 3));

			if(second.toString().equals(first.toString()) && first.toString().equals(third.toString()))
				throw new TripartiteException(first.toString() + second + third, i);
		}
		 */
	}


	private static void BipartiteMiner(String mine, int length) throws BipartiteException {

		if(length % 2 != 0)
			return;

		int distance = length/2;

		StringBuilder first = new StringBuilder();
		StringBuilder second = new StringBuilder();


		for(int i = 0; i <= mine.length() - length; i++){
			first.setLength(0);
			first.append(mine.substring(i, i + distance));
			second.setLength(0);
			second.append(mine.substring(i + distance, i + (distance * 2)));

			if(second.toString().equals(first.toString()))
				throw new BipartiteException(first.toString() + second, i);
		}



		/*										Alternate working code.

		This implementation different from out current one because multiple N,
		the length of the string by 2. In other words, given an odd string of length 7 we try to find if there is
		a bipartite string of length 14. This code tries to force finding a bipartite string regardless of length.

		StringBuilder current;
		StringBuilder next;


		for(int i = 0; i < mine.length() - (length * 2) - 1; i++){

			current = new StringBuilder(mine.substring(i, i + length + 1));
			next = new StringBuilder(mine.substring(i + length + 1, i + (length * 2) + 2));
			//System.out.println(current + " " + next);
			if(next.toString().equals(current.toString()))
				throw new BipartiteException(current + next.toString(), i);
		}
		 */
	}


	private static void palindromeMiner(String mine, int length) throws PalindromeException {

		StringBuilder front = new StringBuilder();
		StringBuilder back = new StringBuilder();

		for(int i = 0; i <= mine.length() - length; i++){
			front.setLength(0);
			front.append(mine.substring(i, i + length));
			back.setLength(0);
			for(int j = front.length() - 1; j >= 0; j--){
				back.append(front.charAt(j));
			}

			if(front.toString().equals(back.toString()))
				throw new PalindromeException(front.toString(), i);

			//throw new SingletonException(mine.substring(start, start + length), start);
		}
	}
}
