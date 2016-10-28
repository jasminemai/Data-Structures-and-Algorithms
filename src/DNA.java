import java.security.InvalidParameterException;
import java.util.Scanner;
/***********************************
 * Author: Jasmine Mai
 * Date: March 1, 2016
 * Class: CSCI 230:02
 * Assignment: Homework Assignment #3
 * Task: An abstract data type for a DNA sequence being able to implement basic ADT methods.
 * Input: Integer input needed is through the keyboard followed by the enter key in order to determine
 * 		how many lines of methods to follow and which methods.
 * Output: Program outputs the results of the selected method.
 * 
 * Certification of Authenticity: I certify that this code is my own work.
 *
 ***********************************/

public class DNA 
{
	Nucleotide leftHelix;
	Nucleotide rightHelix;
	int numElements;

	private class Nucleotide
	{
		Character base;
		Nucleotide next;
		Nucleotide across;
	}
	/**
	 * Constructor method for class DNA
	 */
	public DNA()
	{
		Nucleotide leftHelix = new Nucleotide();
		Nucleotide rightHelix = new Nucleotide();
		leftHelix.next = null;
		rightHelix.next = null;
		numElements = 0;
	}

	/**
	 * Inserts a string that is placed at a specific index.
	 * @param index location of the inserted string
	 * @param basePair string that needs to be inserted
	 * @throws IndexOutOfBoundsException making sure the index is within bounds
	 */
	public void insert(int index, String basePair) throws IndexOutOfBoundsException, InvalidParameterException
	{
		if (index < 0 || index > numElements)		//Checks if the index is within bounds
		{
			throw new IndexOutOfBoundsException();
		}
		else if (basePair.length() % 2 != 0)
		{
			throw new InvalidParameterException();
		}
		else
		{
			Nucleotide newLeft = new Nucleotide();
			Nucleotide newRight = new Nucleotide();
			//Splits the basePair into two characters
			newLeft.base = basePair.charAt(0);
			newRight.base = basePair.charAt(1);

			if (index == 0 && isEmpty())  //Special case for the beginning 
			{
				newLeft.next = this.leftHelix;
				newRight.next = this.rightHelix;
//				newLeft.across = newRight;
//				newRight.across = newLeft;
				this.leftHelix = newLeft;
				this.rightHelix = newRight;

			}
			else		//General case for insertion
			{
				Nucleotide left = this.leftHelix;
				Nucleotide right = this.rightHelix;
				for (int i = 0; i < index - 1; i++)
				{
					left = left.next;
					right = right.next;
				}
				newLeft.next = left.next;
				newRight.next = right.next;
				left.next = newLeft;
				right.next = newRight;
			}
			numElements += 1;		//Increments the logical length
		}
	}
	/**
	 * Removes the basePair at the the specified index.
	 * @param index location of the removed basePair
	 * @return the removed basePair
	 * @throws IndexOutOfBoundsException
	 */
	public String remove(int index) throws IndexOutOfBoundsException
	{
		Nucleotide left = this.leftHelix;
		Nucleotide right = this.rightHelix;
		String newString = "";
		if (index < 0 || index > numElements)	//Check if index is in bounds
		{
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0)	//General case
		{
			left = this.leftHelix;
			right = this.rightHelix;
			Character leftChar = left.base;
			Character rightChar = right.base;
			//Splits the base to two separate characters and appends them
			newString = String.valueOf(leftChar) + String.valueOf(rightChar);
			this.leftHelix = left.next;
			this.rightHelix= right.next;
			left = null;
			right = null;
		}
		else
		{
			for(int i = 0; i < index - 1; i++)
			{
				left = left.next;
				right = right.next;
			}
			Nucleotide tempLeft = left.next;	//temporary place holder to save data
			Nucleotide tempRight = right.next;
			Character leftChar = tempLeft.base;
			Character rightChar = tempRight.base;
			//Splits the base to two separate characters and appends them
			newString = String.valueOf(leftChar) + String.valueOf(rightChar);
			tempLeft.next = null;
			tempRight.next = null;
		}
		numElements --;	//decrements numElements
		return newString;
	}

	/**
	 * Prints the basePairs from one specific start to an end.
	 * @param startIndex where printing begins
	 * @param endIndex where printing ends
	 */
	public void print(int startIndex, int endIndex) throws IndexOutOfBoundsException
	{
		int start;
		int end = endIndex;
		Nucleotide leftPoint;
		Nucleotide rightPoint;
		leftPoint = this.leftHelix;
		rightPoint = this.rightHelix;
		if (startIndex > 0 || endIndex > numElements)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			for (int i = 0; i < startIndex; i++)		//Loops through helix until it reaches the start
			{
				leftPoint = leftPoint.next;
				rightPoint = rightPoint.next;
			}
			for (int i = startIndex; i < endIndex; i++)		//Loops through helix from start to end
			{
				System.out.print(leftPoint.base);
				System.out.print(rightPoint.base);
				leftPoint = leftPoint.next;
				rightPoint = rightPoint.next;
			}
		}
	}
	
	/**
	 * Clears the Nucleotide
	 */
	public void clear()
	{
		//Setting both helices to null and numElements to 0
		this.leftHelix = null;
		this.rightHelix = null;
		this.numElements = 0;
	}

	/**
	 * Determines if it is empty
	 * @return boolean determine if it is empty
	 */
	public boolean isEmpty()
	{
		//numElements is 0, or empty
		return numElements == 0;
	}

	/**
	 * Determines the logical length
	 * @return the logical length
	 */
	public int getLength()
	{
		return numElements;
	}

	/**
	 * Loops through the helix looking for the desired pair
	 * @param basePair desired basePair
	 * @return index location of basePair
	 */
	public int find(String basePair) throws InvalidParameterException
	{
		if (basePair.length() % 2 != 0)		//Checks if valid pair
		{
			throw new InvalidParameterException();
		}
		int result = -1;
		Nucleotide leftHelixResult;
		Nucleotide rightHelixResult;
		leftHelixResult = this.leftHelix;
		rightHelixResult = this.rightHelix;
		boolean found;
		found = false;
		while (!found)
		{
			if (leftHelixResult.base.equals(basePair.charAt(0)) && rightHelixResult.base.equals(basePair.charAt(1)))	//Checks if the basePairs are equal
			{
				result += 1;
				found = true;
			}
			else
			{
				if(leftHelixResult.next == null && rightHelixResult.next == null)		//basePairs do not exist
				{
					result = -1;	//returns -1 since no match
					found = true;	//although not found, break out of loop
				}
				else
				{
					//walks through the helix and incrementing each
					leftHelixResult = leftHelixResult.next;
					rightHelixResult = rightHelixResult.next;
					result += 1;
				}
			}
		}
		return result;
	}
	/**
	 * Prints the leftHelix.
	 */
	public void printLeft()
	{
		Nucleotide left;
		left = this.leftHelix;
		while (left != null)
		{
			System.out.print(left.base);
			left = left.next;
		}
	}

	/**
	 * Prints the rightHelix.
	 */
	public void printRight()
	{
		Nucleotide right;
		right = this.rightHelix;
		while (right != null)
		{
			System.out.print(right.base);
			right = right.next;
		}
	}

	/**
	 * Prints the base and its corresponding pair from the opposite helix.
	 * @param index location of the base
	 * @param helix determine which helix, left or right
	 * @throws IndexOutOfBoundsException
	 */
	public void printBasePair (int index, int helix) throws IndexOutOfBoundsException
	{
		Nucleotide left = this.leftHelix;
		Nucleotide right = this.rightHelix;
		if (helix != 0 && helix != 1)		//Checks if it is valid
		{
			throw new IndexOutOfBoundsException();
		}
		if (index < 0 || index > numElements)		//Checks if it is within bounds
		{
			throw new IndexOutOfBoundsException();
		}
		if (helix == 0)		//leftHelix's pair
		{
			for (int i = 0; i < index; i++)
			{
				left = left.next;
				right = right.next;
			}
			System.out.println(Character.valueOf(left.base) + "" + Character.valueOf(right.base));
		}
		if (helix == 1)		//rightHelix's pair
		{
			for (int i = 0; i < index; i++)
			{
				left = left.next;
				right = right.next;
			}
			System.out.println(Character.valueOf(right.base) + "" + Character.valueOf(left.base));
		}
	}
	
	/**
	 * Inserts a string of pairs into specified index
	 * @param index specified insertion location
	 * @param sequence string that needs to be inserted
	 * @throws IndexOutOfBoundsException
	 * @throws InvalidParameterException
	 */
	
	public void insertSequence(int index, String sequence) throws IndexOutOfBoundsException, InvalidParameterException
	{
		if (index < 0 || index > numElements)		//Checks if the index is within bounds
		{
			throw new IndexOutOfBoundsException();
		}
		else if (sequence.length() % 2 != 0)		//Checks if it is a pair
		{
			throw new InvalidParameterException();
		}
		else
		{
			Nucleotide newLeft = new Nucleotide();
			Nucleotide newRight = new Nucleotide();
			String sequencePair = "";
			//Splits the string into pairs and places them through insert
			for(int i = 0; i < sequence.length(); i+=2)
			{
				newLeft.base = sequence.charAt(i);
				newRight.base = sequence.charAt(i+1);
				sequencePair = String.valueOf(newLeft.base) + "" + String.valueOf(newRight.base);
				System.out.println(sequencePair);
				insert(index, sequencePair);
				index++;
			}
		}
	}
	/**
	 * Main compiler that takes input and gives and output
	 */
	public static void main (String[] args)
	{
		DNA dna = new DNA();	//New instance of class
		Scanner scanner = new Scanner(System.in);		//New scanner
		int lineInput = scanner.nextInt();		//Number of line inputs
		int[] option = new int[lineInput];		//Array of options
		
		int index = 0;
		int indexRemove = 0;
		int startIndexPrint = 0;
		int endIndexPrint = 0;
		int indexPrintBasePair = 0;
		int helixPrintBasePair = 0;
		int startIndex = 0;
		int endIndex = 0;
		int endIndexSequence = 0;
		String basePair = "";
		String basePairFind = "";
		String sequence = "";
		boolean finished = false;
		
		while (!finished)
		{
			for (int i = 0; i < option.length; i++)		//Awaits for input
			{
				option[i] = scanner.nextInt();
				if (option[i] == 1)
				{
					index = scanner.nextInt();
					basePair = scanner.next();
					dna.insert(index, basePair);
				}
				else if (option[i] == 2)
				{
					indexRemove = scanner.nextInt();
				}
				else if (option[i] == 3)
				{
					startIndexPrint = scanner.nextInt();
					endIndexPrint = scanner.nextInt();
				}
				else if (option[i] == 4)
				{
					dna.clear();
				}
				else if (option[i] == 5)
				{
					//dna.isEmpty();
				}
				else if (option[i] == 6)
				{
					//dna.getLength();
				}
				else if (option[i] == 7)
				{
					basePairFind = scanner.next();
				}
				else if (option[i] == 8)
				{
					//dna.printLeft();
				}
				else if (option[i] == 9)
				{
					//dna.printRight();
				}
				else if (option[i] == 10)
				{
					indexPrintBasePair = scanner.nextInt();
					helixPrintBasePair = scanner.nextInt();
				}
				else if (option[i] == 11)
				{
					endIndexSequence = scanner.nextInt();
					sequence = scanner.next();
				}
				else
				{
					System.out.println("You've either tried to enter a bonus question or invalid input! Try again! :)");
				}
			}	
			for (int i = 0; i < option.length; i++)		//Prints everything after it has collected information
			{
				if (option[i] == 2)
				{
					System.out.println(dna.remove(indexRemove));
				}
				else if (option[i] == 3)
				{
					dna.print(startIndexPrint, endIndexPrint);
					System.out.println("");
				}
				else if (option [i] == 5)
				{
					System.out.println(dna.isEmpty());
				}
				else if (option[i] == 6)
				{
					System.out.println(dna.getLength());
				}
				else if (option[i] == 7)
				{
					System.out.println(dna.find(basePairFind));
				}
				else if (option[i] == 8)
				{
					dna.printLeft();
					System.out.println("");
				}
				else if (option[i] == 9)
				{
					dna.printRight();
					System.out.println("");
				}
				else if (option[i] == 10)
				{
					dna.printBasePair(indexPrintBasePair, helixPrintBasePair);
					System.out.println("");
				}
				else if (option[i] == 11)
				{
					dna.insertSequence(endIndex, sequence);
				}
			}
			finished = true;
		}
	}
}