package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * AbstractNode is a the abstract version of a basic INode
 * 
 * @author Charlie Davis 
 *
 */


public abstract class AbstractNode implements INode
{
	Object value;
	private Configuration configuration;
	
	public AbstractNode(Configuration currentConfiguration)
	{
		 setConfiguration(currentConfiguration);
	}
	
	
	/**
	 * Set the configuration of this node
	 * 
	 * @param currentConfiguration
	 */
	
	public void setConfiguration(Configuration currentConfiguration){ configuration = currentConfiguration; }
	
	
	/**
	 * Get the configuration of this node
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Set the allele of this node with a specific value.  
	 * 
	 * @param value
	 */
	
	
	public void setAllele(Object value){ this.value = value; }
	
	
	/**
	 * Get the allele object within this node
	 * 
	 * @return Object allele
	 */
	
	public Object getAllele(){ return value; }
	
	
	/**
	 * Set this node's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator){}
	
	
	/**
	 * Returns a new instance of the specific type of this node
	 * 
	 * @return new INode
	 */
	
	public INode newNode()
	{ 
		INode result = newNodeInternal();
		return result;
	}
	
	
	/**
	 * newNodeInternal is to be implemented by the class that extends 
	 * AbstractNode.  It is the responsibility of the child class to define what 
	 * a new node is.  See Binary.java for example.
	 * 
	 * @return new IFirefly
	 */
	
	protected abstract INode newNodeInternal();
	
	
	/**
	 * Get the internal value of this node to be implement by the class that extends
	 * AbstractNode.  See BinaryNode.java for an example
	 * 
	 * @return value
	 */
	
	public abstract Object getInternalValue();
	
	
	/**
	 * Set the allele of this node with a specific value.  
	 * 
	 * @param value
	 */
	
	public void setValue(Object currentValue)
	{
		setAllele(currentValue);
	}
	
	
	/**
	 * Returns the string representation of this node
	 * 
	 * @return
	 */
	
	public String toString()
	{
		String representation;
	    if (getInternalValue() == null) 
	    {
	      representation = "null";
	    }
	    else 
	    {
	      representation = getInternalValue().toString();
	    }
	    return representation;
	}
	
	
	public int size(){ return 1;}
		
}