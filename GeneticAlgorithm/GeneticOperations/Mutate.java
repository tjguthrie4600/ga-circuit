package GeneticAlgorithm.GeneticOperations;

import GeneticAlgorithm.Population.Chromosome;
import GeneticAlgorithm.Cell.Cell;

import java.util.Random;

// Mutates a gene in the chromosome
// Randomly changes a gene in the cell to any other valid gene 
public class Mutate
{
    public static void mutate(Chromosome c1)
    {
	Random rand = new Random();
	Cell cell;
	String input;

	int randGene = rand.nextInt(3);
	int randCol = rand.nextInt(c1.getNumCols());
	int randRow = rand.nextInt(c1.getNumRows());
	int randRowConnect = rand.nextInt(c1.getNumRows());
	int randInput = rand.nextInt(c1.getNumInputs());

	int currentGate = c1.getCellAt(randRow,randCol).getGateFunction();
	int randGate = rand.nextInt(8);
	
	cell = c1.getCellAt(randRow,randCol);
	
	// Mutate Gate
	if (randGene == 0)
	{
	    while (currentGate == randGate)
		randGate = rand.nextInt(8);
	
	    cell.setGateFunction(randGate);
	}

	// Mutate Input01
	else if (randGene == 1)
	{
	    input = cell.getInput01();
	    // If the input is ABCDE
	    if (input.length() == 1)
	    {
		while (input.equals(cell.getInput01()))
		{
		    switch(randInput)
		    {
		    case 0: 
			cell.setInput01("A"); 
			break;
		    case 1: 
			cell.setInput01("B"); 
			break;
		    case 2: 
			cell.setInput01("C"); 
			break;
		    case 3: 
			cell.setInput01("D"); 
			break;
		    default: 
			cell.setInput01("E"); break;
		    }
		    randInput = rand.nextInt(c1.getNumInputs());
		}
	    }
	    // The input is row col combination
	    else
	    {
		while ((Integer.toString(randRowConnect) + Integer.toString(randCol - 1)).equals(cell.getInput01()))
		    randRowConnect = rand.nextInt(c1.getNumRows());
		cell.setInput01(Integer.toString(randRowConnect) + Integer.toString(randCol - 1));
	    }
	}
	
	// Mutate Input02 
	else 
	{
	    input = cell.getInput02();
	    // If the input is ABCDE
	    if (input.length() == 1)
	    {
		while (input.equals(cell.getInput02()))
		{
		    switch(randInput)
		    {
		    case 0: 
			cell.setInput02("A"); 
			break;
		    case 1: 
			cell.setInput02("B"); 
			break;
		    case 2: 
			cell.setInput02("C"); 
			break;
		    case 3: 
			cell.setInput02("D"); 
			break;
		    default: 
			cell.setInput02("E"); break;
		    }
		    randInput = rand.nextInt(c1.getNumInputs());
		}
	    }
	    // The input is row col combination
	    else
	    {
		while ((Integer.toString(randRowConnect) + Integer.toString(randCol - 1)).equals(cell.getInput02()))
		    randRowConnect = rand.nextInt(c1.getNumRows());
		cell.setInput02(Integer.toString(randRowConnect) + Integer.toString(randCol - 1));
	    }
	}
    }
}
