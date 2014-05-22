import GeneticAlgorithm.GateFunctions.Gates;
import GeneticAlgorithm.Cell.Cell;
import GeneticAlgorithm.Population.Population;
import GeneticAlgorithm.GeneticOperations.Fitness;
import GeneticAlgorithm.Population.Chromosome;
import GeneticAlgorithm.GeneticOperations.Selection;
import GeneticAlgorithm.GeneticOperations.Crossover;
import GeneticAlgorithm.GeneticOperations.Mutate;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

// Class to perform the genetic algorithm and solve the given function
public class designCircuit
{
    public static void main (String [] args)
    {
	// GA parameters
	int TournamentSize = 5;
	int populationSize = 150;
	double crossoverRate = 0.7;
	double mutationRate = 0.4;
	int circuitRow = 3;
	int circuitCol = 3;

	Scanner scan = new Scanner(System.in);
	String min = "";
	List<String> items;
	int generation = 0;
	boolean found = false;
	Chromosome c1;
	Chromosome c2;
	Chromosome c3;
	Chromosome c4;
	int newChildren = 0;
	
	// User parameters
	int numOutputs;
	int numInputs;

	// Gets number of inputs from user
	System.out.print("Enter number of inputs (valid range 3-5): ");
	numInputs = scan.nextInt();
	System.out.println();

	// Gets number of outputs from user
	System.out.print("Enter number of outputs (current max is " + circuitCol + "): ");
	numOutputs = scan.nextInt();
	System.out.println();

	// Array to hold users minterms from the user
	boolean[][] minterms = new boolean[numOutputs][(int) Math.pow(2,numInputs)];

	// Initiaze minterm array to flase
	for (int x = 0; x<numOutputs; x++)
	    for (int y = 0; y < (int) Math.pow(2,numInputs); y++)
		minterms[x][y] = false;

	// Get minterms from user and insert into array
	for (int f = 0; f<= numOutputs; f++)
	{
	    if (f != 0)
		System.out.print("Enter the minterms for function " + f +" separated by comas:");
	    min = scan.nextLine();
	    if (f != 0)
		System.out.println();
	    if (f != 0)
	    {
		items = Arrays.asList(min.split("\\s*,\\s*"));
		for (int e = 0; e < items.size(); e++)
		{
		    minterms[f-1][Integer.parseInt(items.get(e))] = true;
		}
	    }
	}

	/*
	// Prints out minterms
	for (int r=0;r<numOutputs;r++)
	    for (int t=0;t<(int) Math.pow(2,numInputs);t++)
		System.out.println("Minterms[" + r + "]" + "[" + t + "]" +  " = "+  minterms[r][t]);
	*/

	// Initialize the population
	Population p1 = new Population(populationSize, circuitRow, circuitCol, numInputs, numOutputs);
	p1.initPop();

	// Calculate and set the fitness for each chromosome
	for (int i = 0; i<populationSize; i++)
	{
	    c1 = p1.getChromosome(i);
	    setFit(c1,numOutputs,numInputs,minterms,0);
	}

	// Keep going until we have a fully functional circuit
	while (!found)
	{   
	    // For the entire population
	    for (int j = 0; j < populationSize; j++)
	    {	
		// Probablity, if selected for crossover
		if (Math.random() <= crossoverRate)
		{
		    newChildren++;
		    
		    // Select chromosmes for mateing
		    c1 = Selection.select(p1, TournamentSize);
		    c2 = Selection.select(p1, TournamentSize);
		
		    // Make two children
		    c3 = Crossover.crossover(c1,c2);
		    c4 = Crossover.crossover(c2,c1);
	
		    // Evaluate the fitness of the children
		    setFit(c3,numOutputs,numInputs,minterms,0);
		    setFit(c4,numOutputs,numInputs,minterms,0);

		    // Add the most fit to the population, disgaurd the other
		    if (c3.getFitness() >= c4.getFitness())
		    {
			// Mutate child
			if (Math.random() <= mutationRate)
			{
			    Mutate.mutate(c3);
			    setFit(c3,numOutputs,numInputs,minterms,0);
			}
			// Insert child
			p1.insertChromosome(c3);
		    }
		    else
		    {
			// Mutate child
			if (Math.random() <= mutationRate)
			{
			    Mutate.mutate(c4);
			    setFit(c4,numOutputs,numInputs,minterms,0);
			}
			// Insert child
			p1.insertChromosome(c4);
		    }
		}
	    }
			
	    // Remove the weakest from the population, maintain original population size
		for (int k = 0; k<newChildren; k++)
		    p1.deleteUnfit();
		newChildren = 0;

		generation++;
	    
	    // Get the fittest out of the population
	    c1 = p1.getFittest();
	    
	    // Print the most fit chromosome for each generation
	    System.out.println();
	    System.out.println("Generation: " + generation);
	    c1.printChromosome();

	    // If the fittest is a fully functional circuit
	    if (c1.getFitness() == (float)numOutputs)
	    {
		setFit(c1,numOutputs,numInputs,minterms,0);
		found = true;
	    }   
	}
	System.out.println();
    }

    // Sets the chromosomes fitness for each output
    // Note: Can be made more efficient by putting this function in Fitness.java
    private static void setFit(Chromosome c1, int numOutputs, int numInputs, boolean[][] minterms, int debug)
    {
	float fitness = 0;
	float totalFitness = 0;
	int numCols = (int) Math.pow(2,numInputs);
	// Holds one function
	boolean minterm[] = new boolean[numCols];

	// For every output
	for (int k = 0; k < numOutputs; k++)
	{
	    // Set the current function for the output
	    for (int i = 0; i < numCols; i++)
		minterm[i] = minterms[k][i];

	    // Calculate the fitness of the chromosome at that output
	    fitness = Fitness.calcFitness(c1, minterm, k, debug);
	    totalFitness = totalFitness + fitness;
	}

	// Set the total fitness
	// Total fitness = numOutputs*(fitness of each output)	
	c1.setFitness(totalFitness);
    }
}
