package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.LinkedList;


	/*
	 * A Population is a generation of chromosomes that are stored in a list.
	 * Population also has an instance of the current configuration and the 
	 * last know fittest chromosome determined by the fitness function
	 */

public class Population
{
	private FireFly brightestFireFly;
	private LinkedList<FireFly> fireFlies;
	private Configuration configuration;
	
	
	/*
	 * Constructor for the population takes in the current configuration as
	 * a parameter
	 */
	
	public Population(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		fireFlies = new LinkedList<FireFly>();
		//Class clazz;
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			fireFlies.add(new FireFly());
		}
	}
	
	public Population(Configuration currentConfiguration, LinkedList<FireFly> flies)
	{
		configuration = currentConfiguration;
		fireFlies = flies;
	}
	
	
	private Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return configuration;
	}


	/*
	 * Add the given IChromosome to the list of chromosomes in the population
	 */
	
	public void addFireFly(final FireFly fly){ fireFlies.add(fly); }
	
	public void addFireFly(int index, final FireFly fly){ fireFlies.add(index, fly); }
	
	
	
	/*
	 * Set the IChromosome at the given index in the list of chromosomes in the population.
	 */
	
	public void setFireFly(final int index, final FireFly fly){ fireFlies.add(index, fly); fireFlies.remove(index + 1); }
	
	
	/*
	 * Get an IChromosome from the list of chromosomes in the population at the given index
	 */
	
	public FireFly getFireFly(final int index){ return fireFlies.get(index); }
	
	
	/*
	 * Get the entire list of chromosomes in the population
	 */
	
	public LinkedList<FireFly> getFireFlies(){ return fireFlies; }
	
	
	/*
	 * Returns the size of the population.  Which is the number of chromosomes
	 * in the population
	 */
	
	public int size(){ return fireFlies.size(); }
	
	
	/*
	 * Remove a chromosome in the population at the given index.  
	 * Returns true if the remove was successful.
	 */
	
	public boolean removeFireFly(final int index)
	{ 
		if(fireFlies.remove(index) != null) 	return true; 
		else 									return false;
	}
	
	
	/*
	 *  Returns the last known fittest chromosome in this population
	 */
	
	public FireFly getBrightestFireFly()
	{
		return brightestFireFly;
	}
	
	
	/*
	 * Calculates the fittest chromosome in the population at current time
	 */
	
	public FireFly calculateBrightestFireFly()
	{
		//fittest_chromosome = null;
		IFitnessFunction fitnessFunction = configuration.getFitnessFunction();
		FireFly currentBrightest = fireFlies.get(0);
		
		double currentHighestBrightness = 0.0, nextBrightness = 0.0;
		
		for (int i = 1; i < fireFlies.size(); i++) 
		{
			currentHighestBrightness = fitnessFunction.evaluate(currentBrightest);
			nextBrightness = fitnessFunction.evaluate(fireFlies.get(i));
			
            if(currentHighestBrightness < nextBrightness )
            {
            	currentBrightest = fireFlies.get(i);
            }
        }
		
		return currentBrightest; 
		
	}
	
	
	/*
	 * Manually set the fittest chromosome.
	 */
	
	
	public void setBrightestFireFly(){ brightestFireFly = calculateBrightestFireFly(); }
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			if(i > 0) buffer.append(", ");
			
			buffer.append(fireFlies.get(i).toString());
		}
		
		buffer.append(" ]");
		
		return buffer.toString();
	}
	
}