package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

public class PMXGeneticOperator implements IGeneticOperator
{
	private Configuration configuration;
	private int crossoverCount;
	
	public PMXGeneticOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		crossoverCount = 0;
	}

	@Override
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		// TODO Auto-generated method stub
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		int popSize = getConfiguration().getPopulationSize();
		int chromosomeSize = getConfiguration().getChromosomeSize();
		
		IChromosome chr1, chr2, resultChromosome1, resultChromosome2;
		LinkedList<IChromosome> tempChromes = new LinkedList<IChromosome>();
		
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
				point2 = random.nextIntBetween(1, chromosomeSize-2);
			}
			
			chr1 = pop.getChromosome(chrome1);
			chr2 = pop.getChromosome(chrome2);
			
			doCrossover = random.nextDouble();
			if(doCrossover < getConfiguration().getCrossoverProbability())
			{
				if (point1 > point2)//swap so that point one is less than point 2
				{
					int i = point2;
					point2 = point1;
					point1 = i;
				}
				
				//get first offspring
				resultChromosome1 = chr1.newChromosome();
				for (int i = point1; i <= point2; i++)
				{
					resultChromosome1.setGene(i, chr2.getGene(i).newGene());//move gene from second chromosome into child at index i
					
					//finds the index of the gene in first chromosome with the value of the gene in the second chromosome at index i
					int index = findGeneIndex((Chromosome) chr1, (int) chr2.getGene(i).getInternalValue());
					
					resultChromosome1.setGene(index, chr1.getGene(i).newGene());
				}
				
				//get second offspring
				resultChromosome2 = chr2.newChromosome();
				for (int i = point1; i <= point2; i++)
				{
					//same as above but reversed
					resultChromosome2.setGene(i, chr1.getGene(i).newGene());
					int index = findGeneIndex((Chromosome) chr2, (int) chr1.getGene(i).getInternalValue());
					resultChromosome2.setGene(index, chr2.getGene(i).newGene());
				}
				
				pop.addChromosome(resultChromosome1);
				pop.addChromosome(resultChromosome1);
			}
			
			Population tempPop = new Population(configuration);
			for (int i = 0; i < getConfiguration().getPopulationSize() - 1; i ++)
			{
				pop.calculateFittestChromosome();
				tempPop.addChromosome(pop.getFittestChromosome());
				pop.getChromosomes().remove(pop.getFittestChromosome());
			}
		}
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