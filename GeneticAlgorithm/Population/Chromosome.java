package GeneticAlgorithm.Population;

import GeneticAlgorithm.Cell.Cell;
import java.util.Random;

// Class to hold an array of cells
public class Chromosome
{
    // Class varablies
    private float fitness;
    private int rows;
    private int cols;
    private Cell[][] chromosome;
    private int numOutputs;
    private String inputs;
    private int numInputs;
    private boolean[][] outputs;

    // Constructor
    // Takes in number of rows, columns, inputs, and outputs
    public Chromosome (int row, int col, int in, int out)
    {
	rows = row;
	cols = col;
	chromosome = new Cell[rows][cols];
	outputs = new boolean[rows][cols];
	if (in == 3)
	    inputs = "ABC";
	if (in == 4)
	    inputs = "ABCD";
	if (in == 5)
	    inputs = "ABCDE";
	numInputs = in;
	numOutputs = out;
	fitness = 0;
    }

    // Fills the array of cells with random gates
    public void fillRandom ()
    {
	Random rand = new Random();
	int i;
	int j;
	String input = "";
	int index;

	for (i = 0; i < cols; i++)
	{
	    for (j = 0; j < rows; j++)
	    {
		// Initialize New Object
		chromosome[j][i] = new Cell("", "", -1);
		
		// Initialze Outputs
		outputs[j][i] = false;
		
		// Set Random Gate
		chromosome[j][i].setGateFunction(rand.nextInt(8));

		// Connect Circuit Randomly
		// If first column then inputs can be ABC(D)(E)
		if (i == 0)
		{ 
		    index = rand.nextInt(inputs.length());
		    input = Character.toString(inputs.charAt(index));
		    chromosome[j][i].setInput01(input);

		    index = rand.nextInt(inputs.length());
		    input = Character.toString(inputs.charAt(index));
		    chromosome[j][i].setInput02(input);
		}

		// If other colums then the inputs can be anywhere in the previous column 
		else
		{
		    index = rand.nextInt(rows);
		    input = Integer.toString(index);
		    chromosome[j][i].setInput01(index + Integer.toString(i - 1));

		    index = rand.nextInt(rows);
		    input = Integer.toString(index);
		    chromosome[j][i].setInput02(index + Integer.toString(i - 1));
		}
		    
	    }
	}
    }
    
    // Prints A Chromosome
    public void printChromosome()
    {
	System.out.println("===========================================");
	System.out.println("Fitness: " + fitness);
	for (int i = 0; i < rows; i++)
	{
	    for (int j = 0; j < cols; j++)
	    {
		System.out.print(chromosome[i][j].getInput01());
		System.out.print(chromosome[i][j].getInput02());
		System.out.print(chromosome[i][j].getGateFunction() + " ");
		if ( j == cols - 1)
		{
		    System.out.println();
		}
	    }
	}
	System.out.println("===========================================");
    }

    // Prints A Chromosomes Output Array
    public void printChromosomeOutput()
    {
	for (int i = 0; i < rows; i++)
	{
	    for (int j = 0; j < cols; j++)
	    {
		System.out.print(getOutputAt(i,j) + " ");
		if ( j == cols - 1)
		{
		    System.out.println();
		}
	    }
	}
    }

    // Gets the number of rows in the chromosome
    public int getNumRows()
    {
	return rows;
    }

    // Gets the number of columns in the chromosome
    public int getNumCols()
    {
	return cols;
    }

    // Gets the number of inputs in the chromosome
    public int getNumInputs()
    {
	return numInputs;
    }

    // Gets the number of outputs
    public int getNumOutputs()
    {
	return numOutputs;
    }

    // Gets the output of a cell
    public boolean getOutputAt(int i, int j)
    {
	return outputs[i][j];
    }

    // Sets the output at a cell
    public void setOutputAt(int i, int j, boolean value)
    {
	outputs[i][j] = value;
    }

    // Gets the cell at a location in the chromosome
    public Cell getCellAt(int i, int j)
    {
	return chromosome[i][j];
    }

    // Sets the cell at a location in the chromsome
    public void setCellAt(int i, int j, Cell c1)
    {
	chromosome[i][j] = c1;
    }
    
    // Sets the fitness of the chromosme
    public void setFitness(float fit)
    {
	fitness = fit;
    }

    // Gets the fitness of a chromosome
    public float getFitness()
    {
	return fitness;
    }
}
