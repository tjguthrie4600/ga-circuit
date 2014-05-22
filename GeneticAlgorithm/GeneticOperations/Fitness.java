package GeneticAlgorithm.GeneticOperations;

import GeneticAlgorithm.Population.Chromosome;
import GeneticAlgorithm.Cell.Cell;
import GeneticAlgorithm.GateFunctions.Gates;

public class Fitness
{
    // Returns the fitness of the chromosome given the minterms and the output cell
    // Where fitness is defined as: (# of correct_outputs) / (# of total outputs)
    public static float calcFitness(Chromosome c1, boolean minterms[], int outputPos, int debug)
    {
	// Number of colums in truth table
	int cols = c1.getNumInputs();
	// Number of rows in truth table
	int rows = (int) Math.pow(2,cols);
	// The truth table
	boolean[][] inputs = generateTruthTable(cols);
	
	// Dimentions of circuit
	int circuitRows = c1.getNumRows();
	int circuitCols = c1.getNumCols();

	Cell cell;
	String input01;
	String input02;
        int function;
	boolean in01;
	boolean in02;
	boolean out;
	int in01x;
	int in01y;
	int in02x;
	int in02y;
	
	float fitness = 0;
	boolean A = false;
	boolean B = false;
	boolean C = false;
	boolean D = false;
	boolean E = false;
	boolean[] fit = new boolean[32];
	
	// Go through rows in truth table
	for (int i=0; i<rows; i++)
	{
	    // Set values for inputs
	    A = inputs[i][0];
	    B = inputs[i][1];
	    C = inputs[i][2];
	    if (cols == 4)
		D = inputs[i][3];
	    if (cols == 5 )
		E = inputs[i][4];
	    
	    // Evaluate first column of circuit
	    for (int j = 0; j < circuitRows; j++)
	    {
		cell = c1.getCellAt(j,0);
		input01 = cell.getInput01();
		input02 = cell.getInput02();
		function = cell.getGateFunction();
		
		// Get value of input01
		if (input01.equals("A"))
		    in01 = A;
		else if (input01.equals("B"))
		    in01 = B;
		else if (input01.equals("C"))
		    in01 = C;
		else if (input01.equals("D"))
		    in01 = D;
		else
		    in01 = E;

		// Get value of input02
		if (input02.equals("A"))
		    in02 = A;
		else if (input02.equals("B"))
		    in02 = B;
		else if (input02.equals("C"))
		    in02 = C;
		else if (input02.equals("D"))
		    in02 = D;
		else
		    in02 = E;

		// Perform the gate function
		out = getOutput(in01, in02, function);
	        
		// Set the output array element
	        c1.setOutputAt(j,0,out);	
	    }

	    // Evaluate the rest of the circuit
	    for (int k = 1; k < circuitCols; k++)
	    {
	    	for (int l = 0; l < circuitRows; l++)
	    	{
		    // Get all the information from the cell
	    	    cell = c1.getCellAt(l,k);
	    	    input01 = cell.getInput01();
	    	    input02 = cell.getInput02();
	    	    function = cell.getGateFunction();
	    
	    	    in01x = Integer.parseInt(input01.substring(0,1));
	    	    in01y = Integer.parseInt(input01.substring(1,2));
	    	    in02x = Integer.parseInt(input02.substring(0,1));
	    	    in02y = Integer.parseInt(input02.substring(1,2));
	    
	    	    in01 = c1.getOutputAt(in01x, in01y);
	    	    in02 = c1.getOutputAt(in02x, in02y);
	    
	    	    // Perform the gate function
	    	    out = getOutput(in01, in02, function);
	        
	    	    // Set the output array element
	    	     c1.setOutputAt(l,k,out);
	    	}
	    }
	    
	    // Prints the truth table if the method was called with debug set to one
	    if (debug == 1)
		{
		    System.out.println("For inputs: " + A + " " + B + " " + C);
		    for (int d = 0; d<c1.getNumRows(); d++)
		    {
			for (int e = 0; e<c1.getNumCols(); e++)
		        {
			    System.out.print(c1.getOutputAt(d,e) + " ");
			}
			System.out.println();
		    }
		
		    System.out.println();
		}

	    // Set the current output array
	    fit[i] = c1.getOutputAt(outputPos,circuitCols - 1);
	}

	// Calculate Fitness
	for (int m = 0; m < rows; m++)
	{
	    if (fit[m] == minterms[m])
		fitness = fitness + 1;
	}
	
	return fitness/rows;
    }

    // Given the number of inputs, this method generates a truth table
    private static boolean[][] generateTruthTable(int n)
    {
        int rows = (int) Math.pow(2,n);
        int intValue;
        boolean[][] inputs = new boolean[rows][n];


        for (int i=0; i<rows; i++)
	    {
		for (int j=0; j<n; j++)
		    {
			intValue = ((i/(int) Math.pow(2, j))%2);
			if (intValue == 0)
			    inputs[i][n - j - 1] = false;
			else
			    inputs[i][n - j - 1] = true;
		    }
	    }

        return inputs;
    }

    // Perform the gate function and return the result
    private static boolean getOutput(boolean in01, boolean in02, int function)
    {
	boolean out;
	switch(function)
	{
	    case 0:
		out = Gates.wire(in01,in02);
		break;
	    case 1:
		out = Gates.not(in01,in02);
		break;
	    case 2:
		out = Gates.and(in01,in02);
		break;
	    case 3:
		out = Gates.or(in01,in02);
		break;
	    case 4:
		out = Gates.xor(in01,in02);
		break;
	    case 5:
		out = Gates.nand(in01,in02);
		break;
	    case 6:
		out = Gates.nor(in01,in02);
		break;
	    default:
		out = Gates.xnor(in01,in02);
		break;
	}	
	return out;
    }
}
