package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

public class CXGeneticOperator implements IGeneticOperator
{
	private Configuration configuration;
	private int crossoverCount;

	public CXGeneticOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		crossoverCount = 0;
	}
	
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		
	}
	
	public int findGeneIndex(Chromosome chromey, int geneValue)
	{
		int geneIndex = -1;
		for (int i = 0; i <= getConfiguration().getChromosomeSize() -1; i++)
		{
			if ((int) chromey.getGene(i).getInternalValue() == geneValue)
			{
				geneIndex = i;
			}
		}
		return geneIndex;
	}
	
	public Configuration getConfiguration(){ return configuration; }
}
