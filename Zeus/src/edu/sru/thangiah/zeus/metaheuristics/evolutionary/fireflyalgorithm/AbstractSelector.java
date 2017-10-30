package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * A selection operator is another type of operator essential to an evolutionary 
 * algorithm.  Any classes that extend this abstract class will decided how the fireflys
 * are selected for the next population noderation.  
 * 
 * @author Charlie Davis
 *
 */


public abstract class AbstractSelector implements ISelector{
	protected Configuration configuration;
	
	
	/**
	 * Selects the number of fireflys from the current population to move into the next
	 * population.
	 * 
	 * @param num_to_select
	 * @param population
	 */
	
	public void select(int numToSelect , Population pop){
		
	}
	
	
	/**
	 * Get the configuration of this Selection Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){
		return configuration;
	}
	
}
