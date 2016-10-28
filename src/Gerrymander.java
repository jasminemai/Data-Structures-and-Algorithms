import java.util.Arrays;
import java.util.Scanner;
/***********************************
 * Author: Jasmine Mai
 * Date: February 8, 2016
 * Class: CSCI 230:02
 * Assignment: Homework Assignment #2
 * Task: Methods counting the number of  "neighbors" or "districts" or adjacent asterisks that the user gives.
 * Input: Integer input of the height, width, and location of asterisks.
 * Output: Program outputs the results of total number of asterisks.
 * 
 * I had a lot of problem when it came to counting recursively and I was just keep getting stuck in an infinite loop and a StackOverflow error,
 * which is why I had my count be iterative and to find whether or not they are adjacent recursive.
 * 
 * Certification of Authenticity: I certify that this code is my own work.
 *
 ***********************************/
public class Gerrymander {

	/**
	 * Although this method does not count the districts recursively, it uses a recursive method to do so.
	 * @param array The 2D array that is created from the user input of the size and asterisk location.
	 * @return Number of total districts within the array.
	 */
	public int countDistrictsRecursive(String[][] array)
	{
		int result = 0;		//Initializes the count to 0
		boolean remove[][] = new boolean[array.length][array[0].length];	//Initializes the array of booleans for visited asterisks
		for (int row = 0; row < array.length; ++row)		//Loops through the 2D array from row to columns
			for (int column = 0; column < array[0].length; ++column)
				if (array[row][column] == "*" && !remove[row][column]) {	//Determines if there is an asterisk in that location and if it is not visited.
					districtCheck(row, column, array, remove);
					result++;
				}
		return result;
	}
	
	/**
	 * This determines whether or not the asterisks are beside each other, whether or not it is above, below, right or left.
	 * @param row Height of the user's array.
	 * @param column Width of the user's array.
	 * @param array The 2D array that is created from the user input of the size and asterisk location.
	 * @param remove An array of booleans that determines if the asterisk has been checked, thus "removing" it from the untouched asterisk.
	 */
	public void districtCheck(int row, int column, String[][] array, boolean remove[][])
	{
		remove[row][column] = true;
		//Check if asterisks are to the left
		if (checkBound(row, column - 1, array, remove))
			districtCheck(row, column - 1, array, remove);
		//Check if asterisks are to the right
		if (checkBound(row + 1, column + 1, array, remove))
			districtCheck(row + 1, column + 1, array, remove);
		//Check if asterisks are above
		if (checkBound(row - 1, column, array, remove))
			districtCheck(row - 1, column, array, remove);
		//Check if asterisks are below
		if (checkBound(row + 1, column, array, remove))
			districtCheck(row + 1, column, array, remove);
	}

	/**
	 * This determines whether or not the check is within the bound of the array.
	 * @param row Height of the user's array.
	 * @param column Width of the user's array.
	 * @param array The 2D array that is created from the user input of the size and asterisk location.
	 * @param remove An array of booleans that determines if the asterisk has been checked, thus "removing" it from the untouched asterisk.
	 * @return A boolean of determining if the next check is within the bounds.
	 */
	public static boolean checkBound(int row, int column, String[][] array, boolean remove[][])
	{
		return row >= 0 && row < array.length && column >= 0 && column < array[0].length && array[row][column] == "*" && !remove[row][column];
	}

	/**
	 * Main driver class that accepts the user input to determine size and location for the array.
	 */
	public static void main(String[] args) {
		Gerrymander gerryMander = new Gerrymander();	//Instantiates the class.
		Scanner scanner = new Scanner(System.in);	//Initializes the scanner/user input.
		int option = scanner.nextInt();
		if (option == 1)
		{
			int row = scanner.nextInt();
			int column = scanner.nextInt();
			String[][] array = new String [row][column];
			int asteriskRow;
			int asteriskColumn;
			boolean finished = false;
			while (!finished)
			{
				int userInput = scanner.nextInt();
				if (userInput != -1)	//Scanner stops collecting user input once a -1 is entered.
				{
					asteriskRow = userInput/column;
					asteriskColumn = userInput % column;
					array[asteriskRow][asteriskColumn] = "*";		
				}
				else
				{
					finished = true;
				}
			}
	//		for(int i = 0; i < array.length; i++) {
	//			for(int j=0; j< array[i].length; j++)
	//				System.out.print(array[i][j] + " ");
	//			System.out.println();
	//		}
			System.out.println(gerryMander.countDistrictsRecursive(array));
		}
		else
		{
			System.out.println("Did not do bonus.");
		}
	}

}
