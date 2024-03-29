package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Chromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Configuration;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IChromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IGeneticOperator;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IntegerGene;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Population;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.RandomGenerator;

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
	public void operate(Population pop)
	{
		operate(pop, pop.getChromosomes());
	}

	@Override
	public void operate(Population pop, LinkedList<IChromosome> chromes)
	{
		Settings.printDebug(Settings.COMMENT, "Into Operator");
		// TODO Auto-generated method stub
		RandomGenerator random = getConfiguration().getRandomGenerator();
		int chrome1, chrome2, point1, point2;
		
		int popSize = getConfiguration().getPopulationSize();
		int chromosomeSize = getConfiguration().getChromosomeSize();
		
		IChromosome chr1, chr2, resultChromosome1, resultChromosome2;
		LinkedList<IChromosome> tempChromes = new LinkedList<IChromosome>();
		
		for(int j = 0; j < getConfiguration().getPopulationSize() ; j++)
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
			Settings.printDebug(Settings.COMMENT, chr1.toString());
			Settings.printDebug(Settings.COMMENT, chr2.toString());
			
			doCrossover = random.nextDouble();
			if(doCrossover < getConfiguration().getCrossoverProbability())
			{
				Settings.printDebug(Settings.COMMENT, "Doing Crossover");
				if (point1 > point2)//swap so that point one is less than point 2
				{
					int i = point2;
					point2 = point1;
					point1 = i;
				}
				
				//get first offspring
				resultChromosome1 = new Chromosome(getConfiguration(), chromosomeSize);
				
				for (int i = 0; i < chromosomeSize; i++)
				{
					resultChromosome1.getGenes().add(new IntegerGene(getConfiguration(), (int) (chr1.getGene(i).getInternalValue()), chromosomeSize));
				}
				for (int i = point1; i <= point2; i++)
				{
					resultChromosome1.setGene(i, new IntegerGene(getConfiguration(), (int) chr2.getGene(i).getInternalValue(), getConfiguration().getChromosomeSize()));//move gene from second chromosome into child at index i
					
					//finds the index of the gene in first chromosome with the value of the gene in the second chromosome at index i
					int index = findGeneIndex((Chromosome) chr1, (int) chr2.getGene(i).getInternalValue());
					resultChromosome1.setGene(index, new IntegerGene(getConfiguration(), (int) chr2.getGene(i).getInternalValue(), getConfiguration().getChromosomeSize()));
				}
				
				//get second offspring
				resultChromosome2 = new Chromosome(getConfiguration(), chromosomeSize);
				for (int i = 0; i < chromosomeSize; i++)
				{
					resultChromosome2.getGenes().add(new IntegerGene(getConfiguration(), (int) chr2.getGene(i).getInternalValue(), chromosomeSize));
				}
				for (int i = point1; i <= point2; i++)
				{
					//same as above but reversed
					resultChromosome2.setGene(i, new IntegerGene(getConfiguration(), (int) chr1.getGene(i).getInternalValue(), getConfiguration().getChromosomeSize()));
					int index = findGeneIndex((Chromosome) chr2, (int) chr1.getGene(i).getInternalValue());
					resultChromosome2.setGene(index, new IntegerGene(getConfiguration(), (int) chr1.getGene(i).getInternalValue(), chromosomeSize));
				}
				
				pop.addChromosome(resultChromosome1);
				pop.addChromosome(resultChromosome2);
				Settings.printDebug(Settings.COMMENT, "New Child 1 : " + resultChromosome1.toString());
				Settings.printDebug(Settings.COMMENT, "New Child 2 : " + resultChromosome2.toString());
			}
		}
		//reduce population back down to configuration population size
		Population tempPop = new Population(configuration);
		for (int i = 0; i < getConfiguration().getPopulationSize(); i ++)
		{
			pop.calculateFittestChromosome();
			tempPop.addChromosome(pop.getFittestChromosome());
			pop.getChromosomes().remove(pop.getFittestChromosome());
		}
		
		Settings.printDebug(Settings.COMMENT, "Pop size" + getConfiguration().getPopulationSize());
		
		pop = new Population(getConfiguration());
		Settings.printDebug(Settings.COMMENT, "Pop size" + pop.size());
		for (int i = 0; i < getConfiguration().getPopulationSize(); i ++)
		{
			pop.setChromosome(i, tempPop.getChromosome(i));
		}
		//pop.calculateFittestChromosome();
		Settings.printDebug(Settings.COMMENT, "Pop size" + pop.size());
		
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