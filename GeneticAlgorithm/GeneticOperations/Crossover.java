package GeneticAlgorithm.GeneticOperations;

import GeneticAlgorithm.Population.Chromosome;
import GeneticAlgorithm.Cell.Cell;

import java.util.Random;

// Performs single point crossover
// The single point can be any row,column combination where the row and colum selected are not on circuit boundries
// This prevents the child getting all of one parents gene
public class Crossover
{
    public static Chromosome crossover(Chromosome c1, Chromosome c2)
    {
	Random rand = new Random();
	int i;
	int j;
	Cell cell;

	Cell newCell;
	int newGate;
	String newInput01;
	String newInput02;

	Chromosome c3 = new Chromosome(c1.getNumRows(), c1.getNumCols(), c1.getNumInputs(), c1.getNumOutputs());
	int randCol = rand.nextInt(c1.getNumCols());
	int randRow = rand.nextInt(c1.getNumRows());

	// Take care of boundries, or child will get all of one parents genes
	if (randCol == 0)
	    randCol++;
	if (randCol == c1.getNumCols())
	    randCol--;
	if (randRow == 0)
	    randRow++;
	if (randRow == c1.getNumRows())
	    randRow--;

	// DEBUGING INFORMATION
	//System.out.println("Crossing over at point (" + randRow + "," + randCol + ")" );

	// Get and set the first part of the childs genes from the first parents genes
	breakpoint01:
	for (i = 0; i < c1.getNumRows(); i++)
	{    
	    for (j = 0; j < c1.getNumCols(); j++)
	    {

		if (i == randRow && j == randCol)
		    break breakpoint01;
		// DEBUGGING INFORMATION
		//System.out.println("Setting P1 " + i + " " + j);
		cell = c1.getCellAt(i,j);
		newInput01 = cell.getInput01();
		newInput02 = cell.getInput02();
		newGate = cell.getGateFunction();
		newCell = new Cell(newInput01,newInput02,newGate);
		c3.setCellAt(i,j,newCell);
	    }
	}

	// Get and set the second part of the childs genes from the second parents genes
	for (i = randRow; i < c1.getNumRows(); i++)
	{
	    for (j = 0; j < c1.getNumCols(); j++)
	    {
		if (i == randRow && j == 0)
		    j = randCol;
		// DEBUGING INFORMATION
		//System.out.println("Setting P2 " + i + " " + j);
		cell = c2.getCellAt(i,j);
		newInput01 = cell.getInput01();
		newInput02 = cell.getInput02();
		newGate = cell.getGateFunction();
		newCell = new Cell(newInput01,newInput02,newGate);
		c3.setCellAt(i,j,newCell);
	    }
	}
	return c3;			     
    }
}
