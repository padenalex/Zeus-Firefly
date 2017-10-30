package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Fireflys are a representation of a potential solution to the problem that is 
 * attempting to be solved by a nature algorithm.  A firefly consists of an
 * instance of the current configuration, a list of nodes, a string indicating  
 * the type of node that the firefly is made of, the age of the firefly, 
 * the calculated cost value of the firefly and the minimum and maximum cost
 * values. AbstractFirefly implements IFirefly.  Any type of Firefly that
 * extends AbstractFirefly will need to implement the functionality of the
 * interface class.  See IFirefly.java for all of the necessary functions. 
 * 
 */

public abstract class AbstractFirefly implements IFirefly, Serializable
{	
	private Configuration configuration;
	private LinkedList<INode> nodes;
	private double cost;
	private int age;
	private double costRatio;
	private double minCostValue;
	private double maxCostValue;
	private int size;
	
	
	/**
	 * Basic constructor for a firefly
	 * 
	 * @param currentConfiguration
	 * 
	 * */
	
	public AbstractFirefly(Configuration currentConfiguration)
	{ 
		configuration = currentConfiguration; 
		nodes = new LinkedList<INode>();
	}
	
	
	/**
	 * Set the minimum cost value of this firefly
	 * 
	 * @param min
	 */
	
	public void setMinCostValue(double min){ minCostValue = min; }
	
	
	/**
	 * Set the maximum cost value of this firefly
	 * 
	 * @param max
	 */
	public void setMaxCostValue(double max){ maxCostValue = max; }
	
	
	/**
	 * Get the maximum cost value of this firefly
	 * 
	 * @return min_cost_value
	 */
	
	public double getMinCostValue(){ return minCostValue; }
	
	
	/**
	 * Get the maximum cost value of this firefly
	 * 
	 * @return max_cost_value
	 */
	
	public double getMaxCostValue(){ return maxCostValue; }
	

	/**
	 * Add a node to the firefly
	 * 
	 * @param node
	 */
	
	public void addNode(INode node){ nodes.add(node); }
	
	
	/**
	 * Set the node in the firefly at the specific index
	 * 
	 * @param index, node
	 */
	
	public void setNode(int index, INode node){ nodes.set(index, node); }
	
	
	/**
	 * Set this firefly's nodes with a LinkedList of new nodes
	 * 
	 * @param currentNodes
	 */
	
	public void setNodes(LinkedList<INode> currentNodes){ nodes = currentNodes; }
	
	
	/**
	 * Get node at the given index
	 * 
	 * @param index
	 */
	
	public INode getNode(int index){ return (INode) nodes.get(index); }
	
	
	/**
	 * Get all of the nodes in this firefly
	 * 
	 * @return nodes
	 */
	
	public LinkedList<INode> getNodes(){ return nodes; }
	
	
	/**
	 * Set cost of this firefly 
	 * 
	 * @param currentCost
	 */
	
	public void setCostValue(double currentCost){ cost = currentCost; }
	
	
	/**
	 * Get the current cost value of this firefly
	 * 
	 * @return cost
	 */
	
	public double getCostValue(){ return cost; }
	
	
	/**
	 * Returns the size of the firefly
	 * 
	 * @return size of firefly
	 */
	
	public int size(){ return nodes.size(); }
	
	
	/**
	 * Set the cost ratio of this firefly.  
	 * The cost ratio is calculate by dividing this fireflys cost value 
	 * by the total of all of the cost vaules of the fireflys in the population
	 * 
	 * @param currentCostRatio
	 */
	
	public void setCostRatio(double currentCostRatio){ costRatio = currentCostRatio; }
	
	
	/**
	 * Get the current cost ratio of this firefly
	 * 
	 * @return cost_ratio
	 */
	
	public double getCostRatio(){ return costRatio; }
	
	
	/**
	 * Get the current configuration of the this firefly
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Set the age of this firefly
	 * 
	 * @param currentAge
	 */
	
	public void setAge(int currentAge){ age = currentAge; }
	
	
	/**
	 * Get the current age of the firefly
	 * 
	 * @return
	 */
	
	public int getAge(){ return age; }
	
	
	/**
	 * Increment the age of this firefly by one
	 * 
	 */
	
	public void increaseAge(){ age++; }
	
	
	/**
	 * newFireflyInternal is to be implemented by the class that extends 
	 * AbstractFirefly.  It is the responsibility of the child class to define what 
	 * a new firefly is.  See Firefly.java for example.
	 * 
	 * @return new IFirefly
	 */
	
	public abstract IFirefly newFireflyInternal();
	
	
	/**
	 * Returns a new Instance of this firefly as a clone
	 * 
	 * @return new IFirefly
	 */
	
	public IFirefly newFirefly()
	{
		
		return newFireflyInternal();
	}
	
	
	/**
	 * Returns the string representation of a firefly
	 * 
	 * @return this.toString()
	 */
	
	public String toString()
	{
			StringBuffer buffer = new StringBuffer();
			buffer.append("[ ");
			
			for(int i = 0; i < getConfiguration().getFireflySize(); i++)
			{
				if(i > 0) buffer.append(", ");
				
				buffer.append(nodes.get(i).toString());
			}
			
			buffer.append(" ]");
			
			return buffer.toString();
	}
	
}