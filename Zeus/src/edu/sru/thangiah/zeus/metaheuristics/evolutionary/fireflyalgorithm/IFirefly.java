package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

/**
 * Fireflys are a representation of a potential solution to the problem that is 
 * attempting to be solved by a nature algorithm.  A firefly consists of an
 * instance of the current configuration, a list of nodes, a string indicating  
 * the type of node that the firefly is made of, the age of the firefly, 
 * the calculated cost value of the firefly and the minimum and maximum cost
 * values. IFirefly is the interface class that is implemented by AbstractFirefly. 
 * Any type of Firefly that extends AbstractFirefly will need to implement the 
 * functionality of the this interface class. 
 * 
 */

public interface IFirefly
{
	/**
	 * Set the node in the firefly at the specific index
	 * 
	 * @param index, node
	 */
	
	public void setNode(int index, INode node);
	
	
	/**
	 * Get node at the given index
	 * 
	 * @param index
	 */
	
	public INode getNode(int index);
	
	
	/**
	 * Get all of the nodes in this firefly
	 * 
	 * @return nodes
	 */
	
	public LinkedList<INode> getNodes();
	
	
	/**
	 * Get the internal value of the firefly.  See BinaryNode for an example
	 */
	
	public int getValue();
	
	
	/**
	 * Set cost of this firefly 
	 * 
	 * @param currentCost
	 */
	
	public void setCostValue(double currentCost);
	
	
	/**
	 * Get the current cost value of this firefly
	 * 
	 * @return cost
	 */
	
	public double getCostValue();
	
	
	/**
	 * Set the cost ratio of the firefly
	 */
	
	public void setCostRatio(double currentCostRatio);
	
	
	/**
	 * Get the last know cost ratio of the firefly
	 */
	
	public double getCostRatio();
	
	
	/**
	 * Set the minimum cost value of the firefly
	 */
	
	public void setMinCostValue(double costValue);
	
	
	/**
	 * Set the maximum cost value of the fireomsome
	 */
	
	public void setMaxCostValue(double costValue);
	
	
	/**
	 * Get the minimum cost value of the firefly
	 */
	
	public double getMinCostValue();
	
	
	/**
	 * Get the maximum cost value of the firefly
	 */
	
	public double getMaxCostValue();
	
	
	/**
	 * Returns the size of the firefly
	 */
	
	public int size();
	
	
	/**
	 * Increment the age of this firefly by one
	 * 
	 */
	
	public void increaseAge();
	
	
	/**
	 * Set each node in the firefly to a random value
	 * 
	 * @param generator
	 */
	
	public void setRandomNodes(RandomGenerator generator);
	
	
	/**
	 * Returns a new Instance of this firefly as a clone
	 * 
	 * @return new IFirefly
	 */
	
	public IFirefly newFirefly();
	
}