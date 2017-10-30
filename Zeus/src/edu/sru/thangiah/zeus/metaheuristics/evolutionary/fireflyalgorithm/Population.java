package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;


/*
 * A Population is a noderation of fireflys that are stored in a list.
 * Population also has an instance of the current configuration and the 
 * last know best firefly determined by the cost function
 */

public class Population
{
	private IFirefly bestFirefly;
	private LinkedList<IFirefly> fireflys;
	private Configuration configuration;
	
	
	/*
	 * Constructor for the population takes in the current configuration as
	 * a parameter
	 */
	
	public Population(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		fireflys = new LinkedList<IFirefly>();
		//Class clazz;
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			
			fireflys.add(configuration.getSampleFirefly().newFirefly());
		}
	}
	
	public Population(Configuration currentConfiguration, LinkedList<IFirefly> flys)
	{
		configuration = currentConfiguration;
		fireflys = flys;
	}
	
	
	private Configuration getConfiguration() {
		// TODO Auto-noderated method stub
		return configuration;
	}


	/*
	 * Add the given IFirefly to the list of fireflys in the population
	 */
	
	public void addFirefly(final IFirefly fly){ fireflys.add(fly); }
	
	public void addFirefly(int index, final IFirefly fly){ fireflys.add(index, fly); }
	
	
	
	/*
	 * Set the IFirefly at the given index in the list of fireflys in the population.
	 */
	
	public void setFirefly(final int index, final IFirefly fly){ fireflys.add(index, fly); }
	
	
	/*
	 * Get an IFirefly from the list of fireflys in the population at the given index
	 */
	
	public IFirefly getFirefly(final int index){ return fireflys.get(index); }
	
	
	/*
	 * Get the entire list of fireflys in the population
	 */
	
	public LinkedList<IFirefly> getFireflys(){ return fireflys; }
	
	
	/*
	 * Returns the size of the population.  Which is the number of fireflys
	 * in the population
	 */
	
	public int size(){ return fireflys.size(); }
	
	
	/*
	 * Remove a firefly in the population at the given index.  
	 * Returns true if the remove was successful.
	 */
	
	public boolean removeFirefly(final int index)
	{ 
		if(fireflys.remove(index) != null) 	return true; 
		else 									return false;
	}
	
	
	/*
	 *  Returns the last known best firefly in this population
	 */
	
	public IFirefly getBestFirefly()
	{
		return bestFirefly;
	}
	
	
	/*
	 * Calculates the best firefly in the population at current time
	 */
	
	public IFirefly calculateBestFirefly()
	{
		//best_firefly = null;
		ICostFunction costFunction = configuration.getCostFunction();
		IFirefly currentBest = fireflys.get(0);
		
		double currentHighestCost = 0.0, nextCost = 0.0;
		
		for (int i = 1; i < fireflys.size(); i++) 
		{
			currentHighestCost = costFunction.evaluate(currentBest);
			nextCost = costFunction.evaluate(fireflys.get(i));
			
            if(currentHighestCost < nextCost )
            {
            	currentBest = fireflys.get(i);
            }
        }
		
		return currentBest; 
		
	}
	
	
	/*
	 * Manually set the best firefly.
	 */
	
	
	public void setBestFirefly(){ bestFirefly = calculateBestFirefly(); }
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			if(i > 0) buffer.append(", ");
			
			buffer.append(fireflys.get(i).toString());
		}
		
		buffer.append(" ]");
		
		return buffer.toString();
	}
	
}