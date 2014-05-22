package GeneticAlgorithm.Population;

import GeneticAlgorithm.Population.Chromosome;

import java.util.ArrayList;
import java.util.Collections;

// A collection of chromosomes
public class Population 
{
    // Class varaiables
    private int populationSize;
    private int rows;
    private int cols;
    private int numInputs;
    private int numOutputs;
    private ArrayList<Chromosome> chromosomes;

    // Constructor
    // Takes in the population size and the same paramters for the Chromosome object
    public Population(int popSize, int row, int col, int inputs, int outputs)
    {
	populationSize = popSize;
	rows = row;
	cols = col;
	numInputs = inputs;
	numOutputs = outputs;
	chromosomes = new ArrayList<Chromosome>();
    }

    // Initializes the population randomly
    public void initPop()
    {
        for (int i = 0; i < populationSize; i++)
        {
    	    Chromosome c1 = new Chromosome(rows,cols,numInputs,numOutputs);
    	    c1.fillRandom();
    	    chromosomes.add(c1);
      }
    }
    
    // Prints the current population
    public void printPop()
    {
	System.out.println("===============================================================");
    	for (int i = 0; i < populationSize; i++)
    	{
    	    chromosomes.get(i).printChromosome();
    	    System.out.println();
    	}
	System.out.println("===============================================================");
    }
    
    // Gets a chromosome from the population
    public Chromosome getChromosome(int i)
    {
	return chromosomes.get(i);
    }

    // Gets the most fit chromosome from the population
    public Chromosome getFittest()
    {
	float bestFitness = 0;
	float currentFitness = 0;
	Chromosome c1 = chromosomes.get(0);

	for (int i = 0; i < populationSize; i++)
        {
	    currentFitness = chromosomes.get(i).getFitness();
	    if (currentFitness >= bestFitness)
	    {
		c1 = chromosomes.get(i);
		bestFitness = currentFitness;
	    }
	}
	return c1;
    }

    // Inserts a chromosome into the population
    public void insertChromosome(Chromosome c1)
    {
	chromosomes.add(c1);
	populationSize = populationSize + 1;
    }

    // Gets the size of the population
    public int getSize()
    {
	return populationSize;
    }

    // Removes the least fit chromosome from the population
    public void deleteUnfit()
    {
	float worstFit = 1;
	float currentFit;
	int worstPos = 0;
	for (int i = 0; i < populationSize; i++)
	{
	    currentFit = chromosomes.get(i).getFitness();
	    if (currentFit <= worstFit)
	    {
		worstFit = currentFit;
		worstPos = i;
	    }
	}

	chromosomes.remove(worstPos);
	populationSize = populationSize - 1;
    }
}
