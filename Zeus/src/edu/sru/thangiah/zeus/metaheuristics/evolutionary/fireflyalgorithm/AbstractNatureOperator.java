package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
* Nature operators are an essential part of evolutionary algorithms.  A nature operator
* is an algorithm that will operate on a LinkedList of IFireflys to modify it in some way.  
* Two examples are BasicCrossoverOperator.java and MutationOperator.java that both implement
* the INatureOperator interface
* 
* @author Charlie Davis
*
*/

public abstract class AbstractNatureOperator implements INatureOperator{
	private Configuration configuration;
	
	/**
	 * Get the configuration of this Nature Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){
		return configuration;
	}
}
