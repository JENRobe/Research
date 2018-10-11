package algebragen;


public class Driver {

	public static void main(String[] args) 
	{

		//This will create the Algebra
		//        uSize, numOp
		String filename = "Stone Algebra.txt";
		System.out.println(filename + ": ");
		A b = new A(filename);

		//This will create the product algebra
		A bp = new A(b); 

		//This is for console output of the results
		System.out.println();
		System.out.println("The following is the algebra that you have created:");
		System.out.println(b);
		
		System.out.println();
		System.out.println("The following is the product algebra:");
		System.out.println(bp);
		System.out.println();

		//This creates the subalgebras
		b.createSubAlgebras();
		bp.createSubAlgebras();

		

		//Compute subsets of the product algebra
		//Throw away duplicate

		//Entail properly


	}
}