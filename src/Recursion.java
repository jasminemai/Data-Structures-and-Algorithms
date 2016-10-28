import java.util.Arrays;
import java.util.Scanner;
/***********************************
 * Author: Jasmine Mai
 * Date: January 21, 2016
 * Class: CSCI 230:02
 * Assignment: Homework Assignment #1
 * Task: A collection of recursive methods that perform specific tasks and each method is called
 * 		by user input.
 * Input: Integer input needed is through the keyboard followed by the enter key.
 * Output: Program outputs the results of the selected method.
 * 
 * Certification of Authenticity: I certify that this code is my own work,
 * but I received some assistance from: Professor Manaris and Paul Kyser
 *
 ***********************************/
public class Recursion 
{
	/**
	 * This method determines whether the item integer value is within the given list
	 * from a certain point in the array, indicated by the startIndex
	 * @param list	The array that is used to determine whether or not the values or inside.
	 * @param item	The item that the method is searching for.
	 * @param startIndex Where the method begins to search for the item.
	 * @return A boolean indicating if the item is within the array
	 */
	
	public boolean isMember(int[] list, int item, int startIndex)
	{
		boolean placeHolder;
		if (startIndex == list.length)	//Base case with no match.
		{
			placeHolder = false;
		}
		else if (list[startIndex] == item)	//Determines if the startIndex is equal to item.
		{
			placeHolder = true;
		}
		else	//Increments the startIndex to proceed into the array, searching for item.
		{
			startIndex++;
			placeHolder = isMember(list, item, startIndex);
		}
		return placeHolder;
	}
	
	/**
	 * Counts the number of objects within the array from a specific starting point
	 * @param list	The array that is used to determine what to count.
	 * @param startIndex Where the method begins to start counting.
	 * @return The total number of objects in the array from the startIndex.
	 */
	public int numberItems(int[] list, int startIndex)
	{
		int result;
		if (startIndex == list.length) //Base case indicating it has reached the end
		{
			result = 0;
		} 
		else	//If it is not at the end, it keeps incrementing by one
		{
			result = numberItems(list, startIndex + 1) + 1;
		}
		return result;
	}
	
	/**
	 * Counts the number of items within an array from the startIndex to the beginning.
	 * @param list The array that is used to determine what to count.
	 * @param startIndex Where the method begins to start counting.
	 * @return The number of items within the array in reverse.
	 */
	public int numberItemsReverse(int[] list, int startIndex)
	{
		int result;
		if (startIndex == list.length)
		{
			result = 1;
		} 
		else
		{
			result = numberItems(list, startIndex - 1) + 1;
		}
		return result;
	}
	
	/**
	 * Counts the number of times item shows up within an array from a specified starting point.
	 * @param list The array that is used to determine whether or not items are within it.
	 * @param item	The object that is being looked for in the array.
	 * @param startIndex Where the method begins to start searching.
	 * @return The number of times item shows up in list from startIndex.
	 */
	public int countItem(int[] list, int item, int startIndex)
	{
		int result;
		if (startIndex == list.length)		//Base Case
		{
			result = 0;
		}
		else if (list[startIndex] == item)
		{	
			startIndex++;
			result = 1 + countItem(list, item, startIndex);
		}
		else
		{
			startIndex++;
			result = countItem(list, item, startIndex);
		}
		return result;
	}

	/*
	 * I was not able to get this method to work.
	 */
		public static int[] reverseList(int[] list)
		{
			int[] result = null;
			int [] copy = Arrays.copyOf(list, list.length-1);
			int temp = copy[list.length-1];
			if (copy.length == 0)
			{
				result = copy;
			}
			else
			{
				//SOMETHING GOES HERE
				result = reverseList(list);
			}
			System.out.println(Arrays.toString(result));
			return result;
		}

	/**
	 * The main driver of the class, needs to accept integers to determine which method needs to be accessed.
	 */
	public static void main(String[] args)
	{
		int itemValue = 0;
		int startValue = 0;
		Scanner scanner = new Scanner(System.in); //Initializes the scanner/user input.
		int option = scanner.nextInt();
		Recursion recursion = new Recursion(); //Instantiates the class.

		if (option == 1) //Calls isMember() and the user input.
		{
			int[] array = toArray();
			itemValue = scanner.nextInt();
			startValue = scanner.nextInt();
			System.out.println(recursion.isMember(array, itemValue, startValue));
		}
		else if (option == 2)	//Calls numberItems() and the user input.
		{
			int[] array = toArray();
			startValue = scanner.nextInt();
			System.out.println(recursion.numberItems(array, startValue));
		}
		else if (option == 3)	//Calls numberItemsReverse() and the user input.
		{
			int[] array = toArray();
			startValue = scanner.nextInt();
			System.out.println(recursion.numberItemsReverse(array, startValue));
		}
		else if (option == 4) //Calls countItem() and the user input.
		{
			int[] array = toArray();
			itemValue = scanner.nextInt();
			startValue = scanner.nextInt();
			System.out.println(recursion.countItem(array, itemValue, startValue));
		} 
		else if (option == 5)	//Calls reverseList() and the user input.
		{
			System.out.println("I was unable to get this method to work.");
		}
		else 	//User did not enter a valid input
		{
			System.out.println("Hey you didn't enter a valid number!");
		}
	}
	
	/**
	 * A helper method that takes user input and converts their integers into an array.
	 * @return User's array
	 */
	public static int[] toArray()
	{
		Scanner arrayScan = new Scanner(System.in);
		int arrayLength = arrayScan.nextInt();
		int[] givenArray = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++)
		{
			givenArray[i] = arrayScan.nextInt();
		}
		return givenArray;
	}
}
