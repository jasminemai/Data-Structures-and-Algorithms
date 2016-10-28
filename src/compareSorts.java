import java.util.Random;
import java.util.Scanner;
/***********************************
 * Author: Jasmine Mai
 * Date: April 26, 2016
 * Class: CSCI 230:02
 * Assignment: Homework Assignment #5
 * Task: A collection of various sorting algorithms performing through a given array or randomized array. 
 * Input: Integer input needed is through the keyboard followed by the enter key.
 * Output: Program outputs the results of the selected method.
 * 
 * Certification of Authenticity: I certify that this code is my own work,
 * but I received some assistance from: Professor Manaris and Paul Kyser
 *
 ***********************************/
public class compareSorts 
{
	/**
	 * Takes in an array and uses the insertion sort.
	 * @param a
	 */
	public static void insertionSort(int [] a)
	{
		int valueToInsert;
		for(int separator = 1; separator < a.length; separator++)
		{
			valueToInsert = a[separator]; 		//value to enter into sorted section
			int j = separator - 1;
			while(j >= 0 && valueToInsert < a[j])
			{
				a[j+1] = a[j];		//shift item to right
				j--;
			}
			a[j+1] = valueToInsert;		//points at insertion
		}
	}
	
	/**
	 * The helper method that calls in mergesort, takes in an array and uses the merge sort.
	 * @param a
	 */
	public static void mergesort(int [] a)
	{
		int[] temp = new int[a.length];
		if(a.length <= 160)
		{
			insertionSort(a);
		}
		else
		{
			mergesort(a, temp, 0, a.length-1);
		}
	}
	
	/**
	 * Performs mergesort
	 * @param a Given array
	 * @param temp Temporary array
	 * @param left partition
	 * @param right partition 
	 */
	private static void mergesort(int [] a, int [] temp, int left, int right)
	{
			if (left < right)		//checks if pointers are overlapping
			{
				int center = (left + right) / 2;		//center
				mergesort(a, temp, left, center);		//sorts left half
				mergesort(a, temp, center + 1, right);		//sorts right half
				for (int i = left; i <= right ; i++)
				{
					temp[i] = a[i];		//copies temporary sorted list into array
				}
				int i1 = left;		//left sorted index
				int i2 = center + 1;		//right sorted index
				for (int current = left; current <= right; current++)
				{
					if (i1 == center + 1)		//checks if empty
					{
						a[current] = temp [i2];
						i2++;
					}
					else if (i2 > right)		//checks if right is empty
					{
						a[current] = temp[i1];
						i1++;
					}
					else if (temp[i1] < temp [i2])		//checks for smaller item and copies
					{
						a[current] = temp[i1];
						i1++;
					}
					else
					{
						a[current] = temp[i2];
						i2++;
					}
				}
			}
	}
	public static void quicksort(int[] a)
	{
		int[] temp = new int[a.length];
		if (a.length <= 70)
		{
			insertionSort(a);
		}
		else
		{
			quicksort(a, 0, a.length-1);
		}
	}
    private static void quicksort(int [] a, int left, int right) 
    {
        if (left < right)    // more remaining to sort?
        {
           // pick value at middle index as pivot
           int pivot = a[ (left+right)/2 ];
           
           // get partitions
           int i = left;
           int j = right;

           // create partitions by moving larger items to right,
           // and smaller items to left partition
           while (i < j) 
           {
               // keep moving i until we find an item that belongs
               // on the other side
               while (a[i] < pivot)
                  i++;
               // now, i points to first item >= pivot value
            
               // keep moving j until we find an item that belongs
               // on the other side
               while (a[j] > pivot)
                  j--;
               // now, j points to first item <= pivot value
            
               if (i <= j)  // haven't crossed yet
               {
                  swap(a, i, j);
                  i++;
                  j--;
               }
           }
           // now, the two partitions have been created
           
           // sort them
           quicksort(a, left, j);
           quicksort(a, i, right);
           
        }
    }

    /**
     * Swap method that switches the integers.
     * @param a
     * @param pivot
     * @param right
     */
	private static void swap(int[] a, int pivot, int right) 
	{
		int temp = a[pivot];
		a[pivot] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		compareSorts compare = new compareSorts();		//Initialize class
		Scanner scanner = new Scanner(System.in);		//Create scanner
		int lineInput = scanner.nextInt();
		boolean finished = false;
		while (!finished)
		{
			if (lineInput == 1)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];		//Creates array
					for (int i =0; i < length; i++)		//Prints array
					{
						array[i] = scanner.nextInt();
					}
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.insertionSort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
					
					for (int i = 0; i < array.length; i++)	//Prints sorted array
					{
						System.out.print(array[i]+ " ");
					}
					System.out.println("\nSorting by insertionSort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;
			}
			else if (lineInput == -1)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];	//Initializes array
					Random rand = new Random();		//Initializes random
					for (int i =0; i < length; i++)		//Creates random array of intergers
					{
						array[i] = rand.nextInt(1000);
					}
					for (int i = 0; i < array.length; i++)		//Prints random array
					{
						System.out.print(array[i]+ " ");
					}
					System.out.print("\n");
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.insertionSort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
	
					for (int i = 0; i < array.length; i++)		//Prints sorted array
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.println("\nSorting by insertionSort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;
			}
			else if (lineInput == 2)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];		//Creates array
					for (int i =0; i < length; i++)
					{
						array[i] = scanner.nextInt();
					}
					
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.mergesort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
					for (int i = 0; i < array.length; i++)		//Prints sorted array
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.println("\nSorting by mergesort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;
				
			}
			else if (lineInput == -2)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];
					Random rand = new Random();
					for (int i =0; i < length; i++)
					{
						array[i] = rand.nextInt(1000);
					}
					for (int i = 0; i < array.length; i++)
					{
						System.out.print(array[i]+ " ");
					}
					System.out.print("\n");
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.mergesort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
					
					for (int i = 0; i < array.length; i++)
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.println("\nSorting by mergesort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;	
			}
			else if (lineInput == 3)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];
					for (int i =0; i < length; i++)
					{
						array[i] = scanner.nextInt();
					}
					
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.quicksort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
					
					for (int i = 0; i < array.length; i++)
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.println("\nSorting by quicksort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;
			}
			else if (lineInput == -3)
			{
				int length = scanner.nextInt();
				if (length < 0)
				{
					System.out.println("Hey! You didn't enter a valid length!");
				}
				else
				{
					int[] array = new int[length];
					Random rand = new Random();
					for (int i =0; i < length; i++)
					{
						array[i] = rand.nextInt(1000);
					}
					for (int i = 0; i < array.length; i++)
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.print("\n");
					long startTime = System.nanoTime();   // start timing
	
					compareSorts.quicksort(array);
	
					long endTime = System.nanoTime();    // end timing
					double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
	
					for (int i = 0; i < array.length; i++)
					{
						System.out.print(array[i]+ " ");
					}
					
					System.out.println("\nSorting by quicksort took " + runtimeInMilliseconds + " milliseconds...");
				}
				finished = true;
			}
			else
			{
				System.out.println("Hey you didn't enter a valid number!");
				finished = true;
			}
		}

	}
	
	public static void average(int size)
	{
		int[] array = new int[size];
		Random rand = new Random();
		for (int i =0; i < size; i++)
		{
			array[i] = rand.nextInt(1000);
		}

		long startTime = System.nanoTime();   // start timing

		compareSorts.mergesort(array);

		long endTime = System.nanoTime();    // end timing
		double runtimeInMilliseconds = (double)(endTime - startTime) / 1000000.0; 
		
		long startTime2 = System.nanoTime();   // start timing

		compareSorts.mergesort(array);

		long endTime2 = System.nanoTime();    // end timing
		double runtimeInMilliseconds2 = (double)(endTime2 - startTime2) / 1000000.0; 
		
		long startTime3 = System.nanoTime();   // start timing

		compareSorts.mergesort(array);

		long endTime3 = System.nanoTime();    // end timing
		double runtimeInMilliseconds3 = (double)(endTime3 - startTime3) / 1000000.0; 
		
		double average = (runtimeInMilliseconds+ runtimeInMilliseconds2 + runtimeInMilliseconds3)/3;
		System.out.println("\nSorting by mergesort took " + average + " milliseconds...");
	}

}
