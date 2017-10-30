package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * A cost function is arguably the most important part of a nature algorithm.
 * The cost function evaluates fireflys to determine their cost against a known 
 * metric.  It is up to the user to decide how fit a firefly is for the population
 * 
 * @author Charles Davis
 *
 */

public abstract class AbstractCostFunction implements ICostFunction
{
	private double lastComputedCostValue;
	
	
	/**
	 * Set the last computed cost value
	 * 
	 * @param currentCost
	 */
	
	public void setLastComputedCost(double currentCost)
	{
		lastComputedCostValue = currentCost;
	}
	
	
	/**
	 * Get the last computed cost value
	 * 
	 * @return last_computed_cost_value
	 */
	
	public double getLastComputedCost()
	{
		return lastComputedCostValue;
	}
	
	
	/**
	 * Abstract function that evaluates the cost of a given firefly
	 * It is the responsibility of the user to decided how to calculate the 
	 * cost of the fireflys in the population
	 * 
	 * @param IFirefly fly
	 */
	
	public abstract double evaluate(IFirefly fly);
}