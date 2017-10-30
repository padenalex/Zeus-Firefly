package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**	
 * Node interface class
 */


public interface INode
{
	
	
	/**
	 * Returns a new instance of the specific type of this node
	 * 
	 * @return new INode
	 */
	
	public INode newNode();
	
	
	/**
	 * Set the allele of this node with a specific value.  
	 * 
	 * @param value
	 */
	
	public void setAllele(Object value);
	
	
	/**
	 * Get the allele object within this node
	 * 
	 * @return Object allele
	 */
	
	public Object getAllele();
	
	
	/**
	 * Set this node's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator);
	
	
	/**
	 * Returns the string representation of this node
	 * 
	 * @return
	 */
	
	public String toString();
	
	
	/**
	 * Get the size of the node
	 * 
	 * @return
	 */
	
	public int size();
	
	
	/**
	 * Set the configuration of this node
	 * 
	 * @param currentConfiguration
	 */
	
	public void setConfiguration(Configuration currentConfiguration);
	
	
	/**
	 * Get the configuration of this node
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration();
	
	
	/**
	 * Mutate this node.  See Binary node for an example.
	 * 
	 */

	public void mutate();
	
	
	/**
	 * Get the internal value of this node
	 * 
	 * @return value
	 */

	public Object getInternalValue();
	
}