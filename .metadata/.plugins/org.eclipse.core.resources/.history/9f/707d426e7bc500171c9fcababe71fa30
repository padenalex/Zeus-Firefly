package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

public class OXGeneticOperator implements IGeneticOperator
{
	private Configuration configuration;
	private int crossoverCount;
	
	public OXGeneticOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		crossoverCount = 0;
	}
	
	@Override
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		int popSize = getConfiguration().getPopulationSize();
		int chromosomeSize = getConfiguration().getChromosomeSize();
		
		IChromosome chr1, chr2, resultChromosome1, resultChromosome2;
		LinkedList<IntegerGene> tempGenes = new LinkedList<IntegerGene>();
		
		for(IChromosome chrome : chromes)
		{
			double doCrossover;
			
			chrome1 = random.nextIntBetween(0, popSize-1);
			chrome2 = random.nextIntBetween(0, popSize-1);
			
			point1 = random.nextIntBetween(1, chromosomeSize-2);
			point2 = random.nextIntBetween(1, chromosomeSize-2);
			
			while(chrome1 == chrome2)
			{
				chrome2 = random.nextIntBetween(0, popSize-1);
			}
			
			while(point1 == point2)
			{
				point2 = random.nextIntBetween(0, chromosomeSize-1);
			}
			
			chr1 = pop.getChromosome(chrome1);
			chr2 = pop.getChromosome(chrome2);
			
			doCrossover = random.nextDouble();
			if(doCrossover < getConfiguration().getCrossoverProbability())
			{
				resultChromosome1 = new Chromosome(configuration, chromosomeSize);
				if (point1 > point2)//swap so that point one is less than point 2
				{
					int i = point2;
					point2 = point1;
					point1 = i;
				}
				
				for (int i = point1; i <= point2; i++)
				{
					tempGenes.add((IntegerGene) chr2.getGene(i));
					resultChromosome1.setGene(i, (IntegerGene) chr2.getGene(i));
				}
				
				for (int i = 0; i < chromosomeSize; i++)
				{
					
				}
			}
		}
	}
	
	public Configuration getConfiguration(){ return configuration; }
}
