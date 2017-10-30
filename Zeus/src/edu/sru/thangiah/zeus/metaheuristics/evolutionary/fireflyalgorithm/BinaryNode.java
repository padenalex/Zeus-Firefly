package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * A BinaryNode is a node that has the integer value of either 1 or 0
 * 
 * @author Charlie Davis
 *
 */


public class BinaryNode extends AbstractNode
{
	public BinaryNode(final Configuration currentConfiguration)
	{
		super(currentConfiguration);
	}

	public BinaryNode(final Configuration currentConfiguration, final int currentValue)
	{
		super(currentConfiguration);
		setValue(currentValue);
		  
	}
	 
	
	/**
	 * Mutate this node.  Flip the value from 0 to 1 or from 1 to 0
	 * 
	 */

	public void mutate()
	{
		if((int)getInternalValue() == 1)
		{
			setValue(0);
		}else
		{
			setValue(1);
		}
	}
	
	
	/**
	 * newNodeInternal is to be implemented by the class that extends 
	 * AbstractNode.  It is the responsibility of the child class to define what 
	 * a new node is.  See Binary.java for example.
	 * 
	 * @return new IFirefly
	 */
	
	@Override
	protected INode newNodeInternal() {
		return new BinaryNode(getConfiguration());
	}
	
	
	/**
	 * Get the internal value of this node to be implement by the class that extends
	 * AbstractNode.  See BinaryNode.java for an example
	 * 
	 * @return value
	 */
	
	@Override
	public Object getInternalValue() {
		return (int)value;
	}
	
	
	/**
	 * Set this node's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator) 
	{
		int random = numberGenerator.nextIntBetween(0,1);
		setValue(random);	
	}
	
}