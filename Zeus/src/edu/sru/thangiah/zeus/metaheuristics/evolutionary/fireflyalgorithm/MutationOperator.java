package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

/**
 * This is the most basic form of a Mutation Operator.  See StaticMutationOperator.java for an
 * example of how to implement a MutationOperator.
 * 
 * @author Charlie Davis
 *
 */

public class MutationOperator extends AbstractNatureOperator
{
	private double mutationRate;
	
	
	/**
	 * Set the mutation rate of this MutationOperator 
	 * 
	 * @param mutRate
	 */
	
	public void setMutationRate(double mutRate)
	{
		mutationRate = mutRate;
	}
	
	
	/**
	 * Get the mutation rate of this MutationOperator
	 * 
	 * @return mutation_rate
	 */
	
	public double getMutationRate()
	{
		return mutationRate;
	}
	
	
	/**
	 * Method to be implemented by a class that extends this class.  
	 * See StaticMutationOperator.java for an example
	 * 
	 */
	@Override
	public void operate(Population pop, LinkedList<IFirefly> fireo) {
		// TODO Auto-noderated method stub
		
	}
	
}