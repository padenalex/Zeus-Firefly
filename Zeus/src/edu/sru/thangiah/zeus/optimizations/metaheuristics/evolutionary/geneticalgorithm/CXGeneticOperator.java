package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Chromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Configuration;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IChromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IGene;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IGeneticOperator;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Population;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.RandomGenerator;

public class CXGeneticOperator implements IGeneticOperator
{
	private Configuration configuration;
	private int crossoverCount;

	public CXGeneticOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		crossoverCount = 0;
	}
	
	@Override
	public void operate(Population pop)
	{
		operate(pop, pop.getChromosomes());
	}
	
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		int popSize = getConfiguration().getPopulationSize();
		int chromosomeSize = getConfiguration().getChromosomeSize();
		
		Chromosome chr1, chr2, resultChromosome1, resultChromosome2;
		
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
			
			chr1 = (Chromosome) pop.getChromosome(chrome1);
			chr2 = (Chromosome) pop.getChromosome(chrome2);
			
			doCrossover = random.nextDouble();
			if(doCrossover < getConfiguration().getCrossoverProbability())
			{
				//get first offspring
				resultChromosome1 = new Chromosome(getConfiguration(), chromosomeSize);
				resultChromosome1.setGenes(new LinkedList<IGene>());
				for (int i = 0; i < getConfiguration().getChromosomeSize(); i++)
				{
					IGene temp = chr1.getGene(i);
					resultChromosome1.addGene(temp);
				}
				int origGeneVal = (int) chr1.getGene(point1).getInternalValue();
				int currGeneVal = (int) chr2.getGene(point1).getInternalValue();
				int currIndex = point1;
				do
				{
					//get chromosome 1 value at index
					currGeneVal = (int) chr1.getGene(currIndex).getInternalValue();
					//replace result chromosome at current index
					resultChromosome1.getGene(currIndex).setAllele((int) chr2.getGene(point1).getInternalValue());
					Settings.printDebug(Settings.COMMENT, Integer.toString(currGeneVal));
					currIndex = findGeneIndex(chr2, currGeneVal);
				}
				while (currGeneVal != origGeneVal); //loop until the original gene value is found in second chromosome
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