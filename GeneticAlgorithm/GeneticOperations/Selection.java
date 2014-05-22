package GeneticAlgorithm.GeneticOperations;

import GeneticAlgorithm.Population.Population;
import GeneticAlgorithm.Population.Chromosome;

import java.util.Random;

// Selects chromosomes for crossover
// Uses tournament selection, the size of the tornament is a paramter
public class Selection
{
    public static Chromosome select(Population p1, int size)
    {
	Random rand = new Random();
	int popSize = p1.getSize();
	Chromosome c1;
	int start = rand.nextInt(popSize);
	int end;
	float currentFitness = 0;
	float bestFitness = 0;
	
	// If the start size is too close to the end, keep trying
	while (start > popSize - size)
	{
	    start = rand.nextInt(popSize);
	}
	end = start + size;
	c1 = p1.getChromosome(start);

	// Get the fittest in the subset
	for (int i = start; i < end; i++)
	{
	    currentFitness = p1.getChromosome(i).getFitness();
	    if (currentFitness >= bestFitness)
	    {
		bestFitness = currentFitness;
		c1 = p1.getChromosome(i);
	    }
	}
	return c1;
    }
}
