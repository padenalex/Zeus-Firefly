package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.Settings;

public class Chromosome extends AbstractChromosome
{
	public Chromosome(final Configuration a_configuration)
	{
		super (a_configuration);
		if (a_configuration.getChromosomeSize() <= 0) 
		{
			throw new IllegalArgumentException("Chromosome size must be greater than zero");
		}
	}
	
	public Chromosome(final Configuration a_configuration, final int chromosomeSize)
	{
		super(a_configuration);
		if (chromosomeSize <= 0) 
		{
			throw new IllegalArgumentException("Chromosome size must be greater than zero");
		}
	}
	
	
	public Chromosome(final Configuration a_configuration,
            final IGene a_sampleGene, final int chromosomeSize)
	{	
		this(a_configuration, chromosomeSize);
		for(int i = 0; i< chromosomeSize; i++)
		{
			addGene(null);
		}
		
		initFromGene(a_sampleGene);
	}
	
	/**
	 * Protected function used by Constructor to make new instances of this chromosome
	 * The sampleGene is the type of gene that is currently stored in this chromosome.
	 * 
	 * @param sampleGene
	 */
	
	protected void initFromGene(IGene sampleGene) {
	    // Do sanity checking to make sure the parameters we were
	    // given are valid.
	    // ------------------------------------------------------
	    if (sampleGene == null) {
	      throw new IllegalArgumentException(
	          "Sample Gene cannot be null.");
	    }
	    // Populate the array of genes it with new Gene instances
	    // created from the sample gene.
	    // ------------------------------------------------------
	    int size = getConfiguration().getChromosomeSize();
	    for (int i = 0; i < size; i++) {
	    	
	      setGene(i, sampleGene.newGene());
	    }
	  }
	
	public int findGenePos(int geneValue)
	{
		Chromosome chromey = this; 
		int geneIndex = -1;
		for (int i = 0; i <= this.getGenes().size() -1; i++)
		{
			if ((int) chromey.getGene(i).getInternalValue() == geneValue)
			{
				geneIndex = i;
			}
		}
		return geneIndex;
	}
	
	
	@Override
	public int getValue() {
		return 0;
	}


	/**
	 * Sets the genes of this chromosome to random values of their instanciated type
	 * 
	 * @param generator
	 */
	
	@Override
	public void setRandomGenes(RandomGenerator generator) {
	
		for(int i = 0; i < this.size(); i++)
		{
			this.getGenes().get(i).setToRandomValue(generator);
		}	
		
	}
	
	public void fillRandomGenes(RandomGenerator generator)
	{
		this.setGenes(new LinkedList<IGene>());
		int[] geneList = generator.nextNonRepeatingRandomIntArray(0, this.getConfiguration().getChromosomeSize(), this.getConfiguration().getChromosomeSize());
		for(int i = 0; i < this.getConfiguration().getChromosomeSize(); i++)
		{
			this.getGenes().add(new IntegerGene(super.getConfiguration(), geneList[i], this.getConfiguration().getChromosomeSize()));
		}
	}
	
	
	/**
	 * newChromosomeInternal used by abstract newChromosome to make a new
	 * instance of this chromosome.
	 * 
	 * @return new IChromosome
	 */
	
	public Chromosome newChromosomeInternal(){
		Configuration config = getConfiguration();
		IGene sampleGene = getConfiguration().getSampleGeneChooser().choose(config);
		int size = config.getChromosomeSize();
		
		Chromosome chrome = new Chromosome(config, sampleGene, size);
		
		return chrome;

	}
	
	public int findGeneIndex(int valueToFind)
	{
		int index = -1;
		for (int i = 0; i < super.size(); i++)
		{
			if ((int) super.getGenes().get(i).getInternalValue() == valueToFind)
			{
				index = i;
			}
		}
		return index;
	}
	
	public double getTspCost() {
		Configuration config = getConfiguration();
		IFitnessFunction fitFunc = config.getFitnessFunction();
		double cost = fitFunc.evaluate(this);
		return cost;
	}
	
	
}