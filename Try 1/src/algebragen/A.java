//Potential problem: Cannot Close Scanners without throwing an error



package algebragen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class A 
{
	public int 				uSize;			//This is the size of the Algebra
	public int[]			univ;			//This is the array of results
	public int 				numOp;			//This is the number of operations
	public Operation[]		operations;		//This is the array of operations
	public int 				origUnSize;


	//This is the constructor for creating an algebra
	public A(String file) //Takes in the name of the input file
	{
		try
		{
			Scanner input = new Scanner(new File(file));
			//In the first line of the file it should have uSize numOp
			uSize = input.nextInt();
			origUnSize = uSize;
			numOp = input.nextInt();
			operations = new Operation[numOp]; //This creates an array of operations of the size given

			int arity;
			//This loop creates the operations by taking the arity and then the respective solutions
			for(int i = 0; i < numOp; i++)
			{
				arity = input.nextInt();
				if(arity == 0)
				{
					int result = input.nextInt();
					if(result >= uSize)
					{
						System.out.println("Invalid result");
						System.exit(0);
					}
					operations[i] = new Operation_0(result, 0);	
				}
				else if(arity == 1)
				{
					int result1[] = new int[uSize];
					for(int j = 0; j < uSize; j++)
					{
						result1[j] = input.nextInt();
						if(result1[j] >= uSize)
						{
							System.out.println("Invalid result");
							System.exit(0);
						}
					}
					operations[i] = new Operation_1(result1, 0);
				}
				else if(arity == 2)
				{
					int result2[][] = new int[uSize][uSize];
					for(int k = 0; k < uSize; k++)
					{
						for(int j = 0; j < uSize; j++)
						{
							result2[k][j] = input.nextInt();
							if(result2[k][j] >= uSize)
							{
								System.out.println("Invalid result");
								System.exit(0);
							}
						}
					}
					operations[i] = new Operation_2(result2,0);
				}
				else if(arity == 3)
				{
					int result3[][][]= new int[uSize][uSize][uSize];

					for(int l = 0; l < uSize; l++)
					{
						for(int j = 0; j < uSize; j++)
						{
							for(int k = 0; k < uSize; k++)
							{
								result3[l][j][k] = input.nextInt();
								if(result3[l][j][k] >= uSize)
								{
									System.out.println("Invalid result");
									System.exit(0);
								}
							}
						}
					}
					operations[i] = new Operation_3(result3,0);
				}
			}

			input.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}

	}

	//This creates an object called operation that has the following methods
	public interface Operation
	{
		void createOperation();
		Operation createProductOperation();
		String toString();
	}

	//This is a type of operation, specifically an operation with arity 0
	private class Operation_0 implements Operation
	{
		int result; //An arity 0 operation has 1 solution for all elements in the algebra

		//This method is needed as it is used in 
		public Operation_0(int res, int a)
		{
			createOperation(res);
		}

		//This is for the product algebra
		public Operation_0(int i)
		{
			//A pair (a,b) can be expressed as an integer as follows
			//If n is the size of the algebra
			//We can write the result as (n*a)+b
			//In an operation of arity 0 the pair is (a,a)
			result = (i * uSize) + i;
		}

		public void createOperation(int res) 
		{
			result = res;
		}

		public String toString() 
		{
			StringBuilder one = new StringBuilder();
			one.append("Element                  Result \n");
			one.append("All                      " + result +"\n");
			return one.toString();
		}

		public Operation createProductOperation() 
		{
			Operation ret = new Operation_0(result);
			return ret;
		}

		public int getResult()
		{
			return result;
		}

		public void createOperation() {}
	}

	private class Operation_1 implements Operation
	{
		int[] result;

		public Operation_1(int[] res, int a)
		{
			result = new int[uSize];
			createOperation(res);
		}

		//This is for the product algebra
		public Operation_1(int[] a)
		{
			this.result = new int[a.length*a.length]; //We have to take the product so we need n^2 values

			for(int i = 0; i < a.length; i++)
			{
				for(int j = 0; j <a.length; j++)
				{
					//We want a pair (res[i], res[j])
					//This means we will do (res[i] *uSize) + res[j]
					this.result[(i * a.length) + j] = (a[i]*uSize) + a[j];
				}
			}

		}

		public void createOperation(int[] res) 
		{
			result = res;
		}

		public String toString()
		{
			StringBuilder one = new StringBuilder();
			one.append("Element                  Result \n");
			for(int i = 0; i < result.length; i++)
			{
				one.append(i + "                        " + result[i] + "\n");
			}
			return one.toString();
		}

		public Operation createProductOperation() 
		{
			Operation ret = new Operation_1(result);
			return ret;
		}

		public int getResult(int index)
		{
			return result[index];
		}

		//Just need this to appease java
		public void createOperation() {}
	}

	private class Operation_2 implements Operation
	{
		int[][] result = new int[uSize][uSize];

		public Operation_2(int res[][], int a)
		{
			createOperation(res);
		}

		public Operation_2(int[][] a)
		{
			this.result = new int[a.length*a.length][a.length*a.length];
			for(int i = 0; i < a.length; ++i)
			{
				for(int j = 0; j < a.length; ++j)
				{
					for(int k = 0; k < a.length; ++k)
					{
						for(int l = 0; l < a.length; ++l)
						{
							this.result[(i * a.length) + j][(k * a.length) + l] = (uSize* a[i][k]) + a[j][l];
						}
					}
				}
			}
		}

		public void createOperation(int res[][]) 
		{
			result = res;

		}

		public String toString()
		{
			StringBuilder one = new StringBuilder();
			one.append("Element                  Result \n");

			for(int i = 0; i < result.length; i++)
			{
				for(int j = 0; j < result.length; j++)
				{
					one.append("(" + (i) + " , "+ (j) + ")" + "                  " + result[i][j] + "\n");
				}
			}

			return one.toString();
		}

		public Operation createProductOperation() 
		{
			Operation ret = new Operation_2(result);
			return ret;
		}

		public int getResult(int in1, int in2)
		{
			return result[in1][in2];
		}

		public void createOperation() {}

	}

	private class Operation_3 implements Operation
	{
		int[][][] result = new int[uSize][uSize][uSize];

		public Operation_3(int[][][] res, int a)
		{
			createOperation(res);
		}

		public Operation_3(int[][][] a)
		{
			this.result = new int[a.length * a.length][a.length * a.length][a.length * a.length];

			for(int i = 0; i < a.length; ++i)
			{
				for(int j = 0; j < a.length; ++j)
				{
					for(int k = 0; k < a.length; ++k)
					{
						for(int l = 0; l < a.length; ++l)
						{
							for(int m = 0; m < a.length; ++m)
							{
								for(int n = 0; n < a.length; ++n)
								{
									this.result[(i * a.length) + j][(k * a.length) + l][(m * a.length) + n] = (uSize* a[i][j][k])+a[l][m][n];                            
								}
							}   
						}
					}
				}
			}
		}

		public void createOperation(int[][][] res) 
		{
			result = res;

		}

		public String toString()
		{
			StringBuilder one = new StringBuilder();
			one.append("Element                  Result \n");
			for(int i = 0; i < result.length; i++)
			{
				for(int j = 0; j < result.length; j++)
				{
					for(int k = 0; k < result.length; k++)
					{
						one.append("[" + (i) + " , "+ (j) + " , " + (k) +"]" + "                  " + result[i][j][k] + "\n");
					}
				}
			}
			return one.toString();
		}

		public Operation createProductOperation()
		{
			Operation ret = new Operation_3(result);
			return ret;
		}

		public int getResult(int in1, int in2, int in3)
		{
			return result[in1][in2][in3];
		}

		public void createOperation() {}

	}

	//This is the Product Algebra
	public A(A a)
	{
		this.origUnSize = a.uSize;
		this.uSize = a.uSize * a.uSize;
		this.univ = new int[this.uSize];

		for(int i = 0; i < this.uSize; i++)
		{
			//Rather than doing pairs we are going to use integers
			//This is how you would make the pairs
			//universe[i] = (T) new Pair((int) (i / Math.sqrt(universeSize)), (int) (i % Math.sqrt(universeSize)));

			//How we will do this with integers
			univ[i] = i;
		}

		this.numOp = a.numOp;

		//Given that we are dealing with n^2 number of elements we have to change our operations
		//We have to create product operations
		this.operations = new Operation[numOp];
		for(int i = 0; i < numOp; i++)
		{
			this.operations[i] = a.operations[i].createProductOperation();
		}

	}

	//This is the method to create the subalgebras
	
	//this should return an ArrayList of SubAlgebras
	public ArrayList<SubAlgebra> createSubAlgebras()
	{
		ArrayList<SubAlgebra> principleSsts = new ArrayList<SubAlgebra>();
		ArrayList<SubAlgebra> result = new ArrayList<SubAlgebra>();
		int temp;

		System.out.println("The following is a list of Subalgebras: ");

		//We will generate all the subsets that contain the principle subsets
		for(int i = 0; i < this.uSize; i++)
		{	
			ArrayList<Integer> gen = new ArrayList<Integer>();
			ArrayList<Integer> subAlg = new ArrayList<Integer>();
			
			//We will start by adding all of the zero arity elements to the subset
			for(int j = 0; j < numOp; j++)
			{
				if(operations[j] instanceof Operation_0)
				{
					temp = ((Operation_0) operations[j]).getResult();
					if(!subAlg.contains(temp))
					{
						subAlg.add(temp);
						//System.out.println(subAlg.toString());
					}
				}
			}

			//then we add the principle element
			if(!subAlg.contains(i))
			{
				subAlg.add(i);
				gen.add(i);
				//System.out.println(subset.toString());
			}

			principleSsts.add(subAlgebraGen(new SubAlgebra(gen, subAlg)));
//			System.out.println(principleSsts.get(principleSsts.size()-1).toString());
		}

		//So far I have all my principle subsets now I want to put them in order and then dictionary order after that

		for(int i = 0; i < principleSsts.size(); i++)
		{
			principleSsts.set(i,order(principleSsts.get(i)));
		}

		//So now I have all the subsets ordered by their elements and now I need to put them in dictionary order
		//So quicksort needs to take in an arraylist of subalgebras
		QuickSort(principleSsts, 0, principleSsts.size()-1);

		//This loop removes any solutions that are repeated
		removeDuplicates(principleSsts);
		
		for(int i = 0; i < principleSsts.size(); i++)
		{
			
			//In the generator arraylist I will add all of the generators for A then loop through and add all generators for B that are not already included
			
			for(int l = 0; l < principleSsts.size(); l++)
			{
				ArrayList<Integer> generators = new ArrayList<Integer>(principleSsts.get(i).getGenerators());
				ArrayList<Integer> subalgebra = new ArrayList<Integer>(principleSsts.get(i).getSubAlgebra());
				
				int Gensize = principleSsts.get(l).getGeneratorSize();
				int SubAlgebra1Size = principleSsts.get(i).getSubAlgebraSize();
				int SubAlgebra2size = principleSsts.get(l).getSubAlgebraSize();

				//We want to see the number of elements
				int numElts = Math.max(SubAlgebra1Size, SubAlgebra2size);
				
				//First, I will create 2 ArrayList<Integer>  one for the generators, one for the subalgebra
//				System.out.println("Generator Set: " + i + " : " + principleSsts.get(i).getGenerators());
//				System.out.println("SubAlgebra: " + i + " : " + principleSsts.get(i).getSubAlgebra());
//				System.out.println("Generator Set: " + l + ": " + principleSsts.get(l).getGenerators());
//				System.out.println("SubAlgebra: " + l + " : " + principleSsts.get(l).getSubAlgebra());	
				//So if this number is the size of the universe we do not need to try a combination
				if(numElts != this.uSize)
				{
					//We will start with seeing if A contains B
					//So if A contains B, we do nothing
					if(containing(principleSsts.get(i).getGenerators(),principleSsts.get(l).getGenerators()))
					{
						//System.out.println("Equivalent SubAlgebras");
					}
					//Same if B contains A
					else if(containing(principleSsts.get(l).getGenerators(),principleSsts.get(i).getGenerators()))
					{
						//System.out.println("Equivalent SubAlgebras");
					}
					else if(principleSsts.get(i).gen.size()==0 ||principleSsts.get(l).gen.size()==0)
					{
						//System.out.println("Empty Generator");
					}
					//If not
					else
					{
						//If not, we want to create another subalgebra, where we combine all the generators from a and b and add all of those elements to the SubAlgebra 
						//Then we will run the SubAlgebraGen on that arrayList
					
						for(int j = 0; j < Gensize; j++)
						{
							if(!generators.contains(principleSsts.get(l).getGenVal(j)));
							{
								generators.add(principleSsts.get(l).getGenVal(j));
							}
						}
						for(int j = 0; j < SubAlgebra2size; j++)
						{
							if(!subalgebra.contains(principleSsts.get(l).getSubAlgebra().get(j)))
							{
								subalgebra.add(principleSsts.get(l).getSubAlgebra().get(j));
							}
						}
						//System.out.println("Generators before closure: " + generators.toString());
						//System.out.println("Subalgebra before closure: " + subalgebra.toString());
					}
					SubAlgebra temporary = order(subAlgebraGen(new SubAlgebra(generators, subalgebra)));
//					System.out.println(temporary.subAlg.toString());
					principleSsts.add(temporary);
					RemoveDupNoSort(principleSsts);
//					System.out.println("Test: ");
//					for(int f = 0; f < principleSsts.size(); f++)
//					{
//						System.out.println(principleSsts.get(f).getSubAlgebra().toString());
//						//System.out.println(principleSsts.get(f).getGenerators().toString());
//					}
				}
				else
				{
					//System.out.println("Entire Algebra \n");
				}
			}	
		}

		System.out.println("We have " + principleSsts.size() + " Subalgebras:");
		for(int j = 0; j < principleSsts.size(); j++)
		{
			result.add(principleSsts.get(j));
			System.out.println("Generators: " +principleSsts.get(j).getGenerators().toString());
			System.out.println("Subalgebra: " + principleSsts.get(j).getSubAlgebra().toString());
			System.out.println("-----------------------------------------------------------------");
		}
		System.out.println();
		System.out.println();
		
		ArrayList<ArrayList<Pair>> resInPairs = new ArrayList<ArrayList<Pair>>(); 
		for(int i = 0 ; i < result.size(); i++)
		{
			resInPairs.add(SubAlgToPairs(result.get(i).getSubAlgebra(), origUnSize));
		}
		for(int i = 0; i < resInPairs.size(); i++)
		{
				System.out.println(resInPairs.get(i).toString());
				System.out.println("This subalgebra is reflexive: " + isReflexive(resInPairs.get(i), origUnSize));
				System.out.println("This subalgebra is a partial function: " + isAPartialFunction(resInPairs.get(i)));
				System.out.println("This subalgebra is antisymmetric: "+ isAntiSymmetric(resInPairs.get(i)));
				System.out.println("This subalgebra is transitive: " + isTransitive(resInPairs.get(i)));
				System.out.println("This subalgebra is symmetric: " + isSymmetric(resInPairs.get(i)));
				System.out.println("This subalgebra is an equivalence relation: " + isEquivalence(resInPairs.get(i),origUnSize));
				System.out.println("This subalgebra is a partial order: " + isPartialOrder(resInPairs.get(i),origUnSize));
				System.out.println("This subalgebra is a function: " + isAFunction(resInPairs.get(i),origUnSize));
				System.out.println("-----------------------------------------------------------------");
		}
		
		return result;
	}

	public SubAlgebra subAlgebraGen(SubAlgebra subalg)
	{
		boolean keepGoing;
		int temp;
		do
		{
			keepGoing = false;
			for(int j = 0; j < numOp; j++)
			{
				if(operations[j] instanceof Operation_1)
				{
					for(int a = 0; a < subalg.getSubAlgebra().size(); a++)
					{
						temp = ((Operation_1)operations[j]).getResult(subalg.getSubAlgebra().get(a));
						if(!subalg.getSubAlgebra().contains(temp))
						{
							subalg.getSubAlgebra().add(temp);
							keepGoing = true;
						}
					}

				}

				if(operations[j] instanceof Operation_2)
				{
					for(int a = 0; a < subalg.getSubAlgebra().size(); a++)
					{
						for(int b = 0; b < subalg.getSubAlgebra().size(); b++)
						{
							temp = ((Operation_2)operations[j]).getResult(subalg.getSubAlgebra().get(a), subalg.getSubAlgebra().get(b));
							if(!subalg.getSubAlgebra().contains(temp))
							{
								subalg.getSubAlgebra().add(temp);
								keepGoing = true;
							}
						}
					}
				}

				if(operations[j] instanceof Operation_3)
				{
					for(int a = 0; a< subalg.getSubAlgebra().size(); a++)
					{
						for(int b = 0; b < subalg.getSubAlgebra().size(); b++)
						{
							for(int c = 0; c < subalg.getSubAlgebra().size(); c++)
							{
								temp = ((Operation_3)operations[j]).getResult(subalg.getSubAlgebra().get(a), subalg.getSubAlgebra().get(b), subalg.getSubAlgebra().get(c));
								if(!subalg.getSubAlgebra().contains(temp))
								{
									subalg.getSubAlgebra().add(temp);
									keepGoing = true;
								}
							}
						}
					}
				}
			}
		}
		while(keepGoing == true);
		return subalg;
	}

	//Takes in an ArrayList of SubAlgebras
	public void removeDuplicates(ArrayList<SubAlgebra> principleSsts)
	{
		for(int i = 1; i < principleSsts.size(); i++)
		{
			//Will need to compare subsets by taking 2 at a time and seeing if the smaller ones primary element is in the set
			SubAlgebra a = new SubAlgebra(principleSsts.get(i-1).getGenerators(), principleSsts.get(i-1).getSubAlgebra());
			SubAlgebra b = new SubAlgebra (principleSsts.get(i).getGenerators(), principleSsts.get(i).getSubAlgebra());

			//If the subsets are of the same size, we check to see if the subsets are duplicates of one another
			if(a.getSubAlgebra().size() == b.getSubAlgebra().size())
			{
				boolean same = true;
				for(int j = 0; j < a.getSubAlgebra().size();j++)
				{
					if(a.getSubAlgebra().get(j)!= b.getSubAlgebra().get(j))
					{
						same = false;
						break;
					}
				}
				if(same == true) 
				{
					for(int j=0; j< b.getGeneratorSize();j++)
					{
						if(!a.getGenerators().contains(b.getGenVal(j)))
						{
							principleSsts.get(i-1).getGenerators().add(b.getGenVal(j));
						}
					}
					principleSsts.remove(i);
					i--;
				}
			}
		}
	}
	
	public void RemoveDupNoSort(ArrayList<SubAlgebra> list)
	{
		for(int i = 0; i < list.size()-1; i++)
		{
			for(int j = i+1; j < list.size();j++)
			{
				//Will need to compare subsets by taking 2 at a time and seeing if the smaller ones primary element is in the set
				SubAlgebra a = new SubAlgebra(list.get(i).getGenerators(), list.get(i).getSubAlgebra());
				SubAlgebra b = new SubAlgebra (list.get(j).getGenerators(), list.get(j).getSubAlgebra());

				//If the subsets are of the same size, we check to see if the subsets are duplicates of one another
				if(a.getSubAlgebra().size() == b.getSubAlgebra().size())
				{
					boolean same = true;
					for(int k = 0; k < a.getSubAlgebra().size();k++)
					{
						if(a.getSubAlgebra().get(k)!= b.getSubAlgebra().get(k))
						{
							same = false;
							break;
						}
					}
					if(same == true) 
					{
						for(int k=0; k< b.getGeneratorSize();k++)
						{
							if(!a.getGenerators().contains(b.getGenVal(k)))
							{
								list.get(i).getGenerators().add(b.getGenVal(k));
							}
						}
						list.remove(j);
						i=0;
						j=0;
					}
				}
			}
			
		}
//		ArrayList<Integer> index = new ArrayList<Integer>();
//		for(int i = 0; i < list.size()-1; i++)
//		{
//			for(int j = i+1; j < list.size(); j++)
//			{
//				if(list.get(i).getSubAlgebraSize() == list.get(j).getSubAlgebraSize())
//				{
//					boolean isSame = true;
//					for(int k = 0; k < list.get(i).getSubAlgebraSize(); k++)
//					{
//						if(list.get(i).getSubAlgebra().get(k) != list.get(j).getSubAlgebra().get(k))
//						{
//							isSame = false;
//							break;
//						}
//					}
//					if(isSame == true)
//					{
//						index.add(j);
//					}
//				}
//			}
//		}
//		//Sort the indexes
//		int temp;
//		for(int i = 1; i < index.size(); i++)
//		{
//			temp = index.get(i);
//			int j = i -1;
//			while(j >=0 && index.get(j) > temp)
//			{
//				index.set((j+1), index.get(j));
//				j = j -1;
//			}
//			index.set(j+1, temp);
//		}
//		for(int j = index.size()-1; j >=0; j--)
//		{
//			list.remove(index.get(j));
//		}
	}

	//The following 2 methods are used for sorting an arraylist of SubAlgebras
	public void QuickSort(ArrayList<SubAlgebra> a, int low, int high)
	{
		//we want to sort them by the actual subAlgebra NOT the generators
		if(low < high)
		{
			/* pi is partitioning index, a[p] is now
	           at right place */
			int pi = partition(a, low, high);

			QuickSort(a, low, pi - 1);  // Before pi
			QuickSort(a, pi + 1, high); // After pi
		}
	}

	public int partition(ArrayList<SubAlgebra> a, int low, int high)
	{
		//We just want to order them by the subAlg
		ArrayList<Integer> pivot = new ArrayList<Integer>(a.get(high).getSubAlgebra());

		int i = low -1;	//Index of smaller element

		for(int j = low; j<= high - 1; j++)
		{
			//If current element is smaller than than the pivot
			//But our pivot element is an Array List so we have to loop through

			int min = Math.min(a.get(j).getSubAlgebra().size(), pivot.size());

			for(int k = 0; k < min; k++)
			{
				if(a.get(j).getSubAlgebra().get(k) < pivot.get(k))
				{
					i++; //increment index of smaller element
					//swap a(i) with a(j)
					//We can do this because our individual lists are already in dictionary order
					SubAlgebra temp = new SubAlgebra(a.get(i).getGenerators(), a.get(i).getSubAlgebra());
					
					//going to the ith SubAlgebra, to the jth SubAlgebra 
					a.set(i, a.get(j));
					a.set(j, temp);
					//We have to break out of this loop because we are looping through
					//Individual lists rather than the list of lists
					break;
				}
			}
		}

		SubAlgebra temp = new SubAlgebra(a.get(i+1).getGenerators(), a.get(i+1).getSubAlgebra());
		a.set(i+1, a.get(high));
		a.set(high, temp);
		return (i+1);
	}

	public SubAlgebra order(SubAlgebra a)
	{
		//This orders the generators
		for (int i=1; i< a.getGenerators().size(); i++)
		{
			int index = a.getGenerators().get(i);
			int j = i;
			while (j > 0 && a.getGenerators().get(j-1) > index)
			{
				a.getGenerators().set(j, a.getGenerators().get(j-1));
				j--;
			}
			a.getGenerators().set(j, index);
		}
		//This orders the SubAlg
		for (int i=1; i< a.getSubAlgebra().size(); i++)
		{
			int index = a.getSubAlgebra().get(i);
			int j = i;
			while (j > 0 && a.getSubAlgebra().get(j-1) > index)
			{
				a.getSubAlgebra().set(j, a.getSubAlgebra().get(j-1));
				j--;
			}
			a.getSubAlgebra().set(j, index);
		}
		return a;
	}

	//This method allows us to print the algebras in a readable format
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numOp; ++i)
		{
			sb.append("Operation " + i + "\n");
			sb.append("--------------------------------\n");
			sb.append(operations[i].toString() + "\n");
		}
		return sb.toString();
	}

	//This method sees if g1 contains g2
	public boolean containing(ArrayList<Integer> g1, ArrayList<Integer> g2) //see if g1 contains g2
	{
		int min = Math.min(g1.size(), g2.size());
		if(min != g2.size())
		{
			return false;
		}
		
		for(int i = 0; i < min; i++)
		{
			if(!g1.contains(g2.get(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	
	//These are the methods for pairs
	
	public static ArrayList<Pair> SubAlgToPairs(ArrayList<Integer> subAlgebra, int AlgebraSize)
	{
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for(int i = 0; i < subAlgebra.size(); i++)
		{
			pairs.add(new Pair(subAlgebra.get(i), AlgebraSize));
		}
		return pairs;
	}
	
	//Look at this function again, I may be mistaken on my definition of reflexive:
	// if I have xRy I then check to see if I have xRx not yRy. Is that incorrect?
	public static boolean isReflexive(ArrayList<Pair> list, int AlgebraSize)
	{
		for(int i = 0; i < AlgebraSize; i++)
		{
			boolean isTrue = false;
			int toCheck = i;
			for(int j = 0; j < list.size(); j++)
			{
				if(list.get(j).x == toCheck && list.get(j).y == toCheck){isTrue = true;}
			}
			if(isTrue == false)
			{
				return false;
			}
		}	
		return true;	
			
//			
//			if(xToCheck!=yToCheck)
//			{
//				//first check to see if x has a reflexive pair
//				boolean xReflexive = false;
//				for(int j = 0; j < list.size(); j++)
//				{
//					if(xToCheck == list.get(j).x && xToCheck ==list.get(j).y)
//					{
//						xReflexive = true;
//						break;
//					}
//				}
//				//check to see if y has a reflexive pair
//				boolean yReflexive = false;
//				for(int j = 0; j < list.size(); j++)
//				{
//					if(yToCheck == list.get(j).x && yToCheck ==list.get(j).y)
//					{
//						yReflexive = true;
//						break;
//					}
//				}
//				if(xReflexive != true || yReflexive != true)
//				{
//					return false;
//				}
//				
//			}
//		}
//		return true;
	}

	public static boolean isTransitive(ArrayList<Pair> list)
	{
//		//first see if the list has at least 3 elements
//		//can a function be transitive with less than 3 elements
//		if(list.size() < 3)
//		{
//			System.out.println("Not enough elements for tranitivity");
//			return false;
//		}
		// for all a,b,c if aRb and bRc then aRc
		for(int i= 0; i < list.size(); i++)
		{
			//aRb
			int a = list.get(i).x;
			int b = list.get(i).y;
			for(int j = 0; j<list.size(); j++)
			{
				//make sure they are NOT the same element
				if(i !=j && (b == list.get(j).x))
				{
					//bRc
					int c = list.get(j).y;
					boolean isTrans = false;
					for(int k = 0; k < list.size();k++)
					{
						if(a==list.get(k).x && c==list.get(k).y)
						{
							isTrans = true;
							break;
						}
					}
					if(isTrans == false)
					{
						System.out.println("This is not transitive aRb: ("+ a + ", " + b + ") and bRc: ("+ b + ", "+ c +")");
						return false;
					}
				}
			}
		}

		return true;
	}
	
	public static boolean isAntiSymmetric(ArrayList<Pair> list)
	{
		//Means you can not have xRy AND yRx
		for(int i = 0; i < list.size()-1; i++)
		{
			int xVal = list.get(i).x;
			int yVal = list.get(i).y;
			for(int j = i+1; j < list.size(); j++)
			{
				if(xVal == list.get(j).y && yVal == list.get(j).x)
				{
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isSymmetric(ArrayList<Pair> list)
	{
		//aRb the bRa
		for(int i = 0; i < list.size(); i++)
		{
			int a = list.get(i).x;
			int b = list.get(i).y;
			if(a!=b) //dont need to check if they are the same because obviously its in there
			{
				boolean isSym = false;
				for(int j = 0; j < list.size(); j++)
				{
					if(b == list.get(j).x && a == list.get(j).y)
					{
						isSym = true;
						break;
					}
				}
				if(isSym == false)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean isAPartialFunction(ArrayList<Pair> list)
	{
		//For it to be a function each x value must show up exactly once
		for(int i = 0; i < list.size()-1; i++)
		{
			int xVal = list.get(i).x;
			for(int j = i+1; j < list.size(); j++)
			{
				if(xVal == list.get(j).x)
				{
					return false;
				}
			}
		}
		return true;
	}
	public static boolean isAFunction(ArrayList<Pair> list, int AlgebraSize)
	{
		//For it to be a function each x value must show up exactly once
		for(int i = 0; i < AlgebraSize; i++)
		{
			boolean isHere = false;
			boolean moreThanOnce = false;
			int xVal = i;
			for(int j = 0; j < list.size(); j++)
			{
				if(list.get(j).x == xVal && isHere == false)
				{
					isHere = true;
				}
				else if(list.get(j).x == xVal && isHere == true)
				{
					moreThanOnce = true;
					return false;
				}
			}
			if(isHere == false || moreThanOnce == true)
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isEquivalence(ArrayList<Pair> list, int AlgebraSize)
	{
		boolean a = isReflexive(list,AlgebraSize);
		boolean b = isTransitive(list);
		boolean c = isSymmetric(list);
		if(a == true && b == true && c == true)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isPartialOrder(ArrayList<Pair> list, int AlgebraSize)
	{
		boolean a = isReflexive(list, AlgebraSize);
		boolean b = isTransitive(list);
		boolean c = isAntiSymmetric(list);
		if(a == true && b == true && c == true)
		{
			return true;
		}
		return false;
	}
	
}






