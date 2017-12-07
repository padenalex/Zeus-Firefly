package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.Settings;

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
	public void operate(Population pop)
	{
		operate(pop, pop.getChromosomes());
	}
	
	@Override
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		int popSize = getConfiguration().getPopulationSize();
		int chromosomeSize = getConfiguration().getChromosomeSize();
		
		IChromosome chr1, chr2, resultChromosome1, resultChromosome2;
		
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
				
				for (int i = 0; i < getConfiguration().getChromosomeSize(); i++)
				{
					resultChromosome1.getGenes().add(new IntegerGene(getConfiguration(), -1, 280));
				}
				Settings.printDebug(Settings.COMMENT, Integer.toString(resultChromosome1.getGenes().size()));
				for (int i = point1; i <= point2; i++)
				{//move crossover section
					resultChromosome1.setGene(i, (IntegerGene) chr2.getGene(i));
				}
				
				int count = 0;
				for (int i = 0; i < point1; i++)
				{	//move points before the crossover that are not in the crossover section
					while (resultChromosome1.getGenes().contains(chr1.getGene(count)))
					{
						count ++;
					}
					resultChromosome1.setGene(i, chr1.getGene(count));
				}
				
				for (int i = point2 +1; i < chromosomeSize; i++)
				{//move points after crossover that are not in crossover section
					while (resultChromosome1.getGenes().contains(chr1.getGene(count)))
					{
						count ++;
					}
					resultChromosome1.setGene(i, chr1.getGene(count));
				}
			}
		}
	}
	
	public Configuration getConfiguration(){ return configuration; }
}