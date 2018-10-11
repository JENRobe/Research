package algebragen;

import java.util.ArrayList;

public class SubAlgebra 
{
	public ArrayList<Integer> gen;
	public ArrayList<Integer> subAlg;
	
	public SubAlgebra(ArrayList<Integer> generators, ArrayList<Integer> subAlgebra)
	{
		gen = new ArrayList<Integer>(generators);
		subAlg = new ArrayList<Integer>(subAlgebra);
	}

	public int getSubAlgVal(int index)  {return subAlg.get(index);}
	public int getGenVal(int index)  {return gen.get(index);}
	public ArrayList<Integer> getGenerators() {return gen;}
	public ArrayList<Integer> getSubAlgebra() {return subAlg;}
	public int getGeneratorSize(){return gen.size();}
	public int getSubAlgebraSize(){return subAlg.size();}
	
}
