import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
	public static void main(String[] args)
	{
		//initialization
		Scanner in = new Scanner(System.in);
		int size;
		int opNum;
		
		//Creating lists of arrays of different arities
		ArrayList<Integer> ar0 = new ArrayList<Integer>();
		ArrayList<Integer[]> ar1 = new ArrayList<Integer[]>();
		ArrayList<Integer[][]> ar2 = new ArrayList<Integer[][]>();
		ArrayList<Integer[][][]> ar3 = new ArrayList<Integer[][][]>();
//		ArrayList<Integer[][][][]> ar4 = new ArrayList<Integer[][][][]>();
//		ArrayList<Integer[][][][][]> ar5 = new ArrayList<Integer[][][][][]>();
		
		
		
		//Input the size, number of operations, and the arity of each operation
		System.out.println("Please enter an integer size of your algebra: ");
		size = in.nextInt();
		
		if(size < 2)
		{
			System.out.println("You must enter an integer size larger than 1");
			System.out.println("Please enter an integer size of your algebra: ");
			size = in.nextInt();
		}
		
		System.out.println("Please enter the number of operations for your algebra: ");
		opNum = in.nextInt();
		
		int[] arity = new int[opNum];
		
		for(int i = 0; i < opNum; i++)
		{
			System.out.println("What is the arity of operation " + (i) + ": ");
			arity[i] = in.nextInt();
		}
		
		int input;
		//Creating lists to hold the array results of the different arities
		for(int i = 0; i < arity.length; i++)
		{
			if(arity[i] == 0)
			{
				System.out.println("Please input the result of operation " + (i) + " with arity 0: ");
				input = in.nextInt(); 
				if(input >= size)
				{
					System.out.println("Invalid result, please try again");
					i--;
				}
				else
				{
					ar0.add(input);
				}
			}
			
			else if(arity[i] == 1)
			{
				Integer[] temp = new Integer[size];
				for(int j = 0; j < size; j++)
				{
					System.out.println("Please input the result of operation " + (i) + " at element " + (j) + ": ");
					input = in.nextInt();
					if(input >= size)
					{
						System.out.println("Invalid result, please try again");
						j--;
					}
					else
					{
						temp[j] = input;
					}
					
				}
				ar1.add(temp);
			}
			
			else if(arity[i] == 2)
			{
				Integer[][] temp = new Integer[size][size];
				for(int o1 = 0; o1 < size; o1++)
				{
					for(int o2 = 0; o2 < size; o2++)
					{
						System.out.println("Please input the result of the operation " + (i) + " at element [" + (o1) + " , " + (o2) + "]: ");
						input = in.nextInt();
						if(input >= size)
						{
							System.out.println("Invalid result, please try again");
							o2--;
						}
						else
						{
							temp[o1][o2] = input;
						}
					}
				}
				ar2.add(temp);
			}
			else if(arity[i] == 3)
			{
				Integer [][][] temp = new Integer[size][size][size];
				for(int o1 = 0; o1 < size; o1++)
				{
					for(int o2 = 0; o2 < size; o2++)
					{
						for(int o3=0; o3 < size; o3++)
						{
							System.out.println("Please input the result of the operation " + (i) + " at element [" + o1 + " , " + (o2) + " , " + (o3) + "]: ");
							input = in.nextInt();
							if(input >= size)
							{
								System.out.println("Invalid result, please try again");
								o3--;
							}
							else
							{
								temp[o1][o2][o3] = input;
							}
						}
					}
				}
				ar3.add(temp);
			}
		}
		
		//Print out the lists
		System.out.println();
		System.out.println();
		if(ar0.size() != 0 )
		{
			for(int zero = 0; zero < ar0.size(); zero++)
			{
				System.out.println("Arity zero result for " + (zero) + ": ");
				System.out.println(ar0.get(zero).toString());
			}
		}

		System.out.println();
		if(ar1.size() != 0)
		{
			for(int one = 0; one < ar1.size(); one++)
			{
				System.out.println("Arity One result for " + (one) +": ");
				System.out.println("Element                  Result");
				for(int i = 0; i< ar1.get(one).length; i++)
				{
					System.out.println((i) + "                        " + ar1.get(one)[i]);
				}
			}
		}
		
		System.out.println();
		if(ar2.size() != 0)
		{
			for(int two = 0; two < ar2.size(); two++)
			{
				System.out.println("Arity Two result for " + (two) +": ");
				System.out.println("Element                  Result");
				for(int i = 0; i< ar2.get(two).length; i++)
				{
					for(int j = 0; j< ar2.get(two)[0].length; j++)
					{
						System.out.println("[" + (i) + " , "+ (j) + "]" + "                  " + ar2.get(two)[i][j]);
					}
				}
			}
		}
		
		System.out.println();
		if(ar3.size() != 0)
		{
			for(int three = 0; three < ar3.size(); three++)
			{
				System.out.println("Arity Three result for " + (three) +": ");
				System.out.println("Element                  Result");
				for(int i = 0; i< ar3.get(three).length; i++)
				{
					for(int j = 0; j< ar3.get(three).length; j++)
					{
						for(int k = 0; k < ar3.get(three).length; k++)
						{
							System.out.println("[" + (i) + " , "+ (j) + " , " + (k) + "]" + "               " + ar3.get(three)[i][j][k]);
						}
					}
				}
			}
		}
		in.close();
		
	}
	
	//Sort subsets in ascending order, maybe using ArrayLists rather than arrays
	//Graphical interface
	//Extend Arity, maybe recursively?
	//Create Arity class
	//Next, Create Product Algebra AXA = component result -> Ask if we should store the product algebra in a list and what they were products of?
		// n elements means that we have n^2 number of products, we should store the pair (x,y)
	
	
	
}
