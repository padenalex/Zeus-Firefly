package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

public class Firefly extends AbstractFirefly
{
	public Firefly(final Configuration a_configuration, final int fireflySize)
	{
		super(a_configuration);
		if (fireflySize <= 0) 
		{
			throw new IllegalArgumentException("Firefly size must be greater than zero");
		}
	}
	
	
	public Firefly(final Configuration a_configuration,
            final INode a_sampleNode, final int fireflySize)
	{	
		this(a_configuration, fireflySize);
		for(int i = 0; i< fireflySize; i++)
		{
			addNode(null);
		}
		
		initFromNode(a_sampleNode);
	}
	
	/**
	 * Protected function used by Constructor to make new instances of this firefly
	 * The sampleNode is the type of node that is currently stored in this firefly.
	 * 
	 * @param sampleNode
	 */
	
	protected void initFromNode(INode sampleNode) {
	    // Do sanity checking to make sure the parameters we were
	    // given are valid.
	    // ------------------------------------------------------
	    if (sampleNode == null) {
	      throw new IllegalArgumentException(
	          "Sample Node cannot be null.");
	    }
	    // Populate the array of nodes it with new Node instances
	    // created from the sample node.
	    // ------------------------------------------------------
	    int size = getConfiguration().getFireflySize();
	    for (int i = 0; i < size; i++) {
	    	
	      setNode(i, sampleNode.newNode());
	    }
	  }
	
	
	@Override
	public int getValue() {
		return 0;
	}


	/**
	 * Sets the nodes of this firefly to random values of their instanciated type
	 * 
	 * @param generator
	 */
	
	@Override
	public void setRandomNodes(RandomGenerator generator) {
	
		for(int i = 0; i < this.size(); i++)
		{
			this.getNodes().get(i).setToRandomValue(generator);
		}	
		
	}
	
	
	/**
	 * newFireflyInternal used by abstract newFirefly to make a new
	 * instance of this firefly.
	 * 
	 * @return new IFirefly
	 */
	
	public Firefly newFireflyInternal(){
		Configuration config = getConfiguration();
		INode sampleNode = getConfiguration().getSampleNodeChooser().choose(config);
		int size = config.getFireflySize();
		
		Firefly fly = new Firefly(config, sampleNode, size);
		
		return fly;

	}
	
	
}