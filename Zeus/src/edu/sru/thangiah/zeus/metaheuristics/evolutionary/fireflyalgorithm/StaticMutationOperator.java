package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

//import com.opensymphony.xwork2.util.ArrayUtils;

/**
 * 
 * StaticMutationOperation is a MutationOperator that decides at the beginning of runtime 
 * which nodes in which noderations will be mutated.  It does this by using a random number
 * generator that noderates a certain number of nodes indexes to be mutated based on the
 * mutation rate, firefly size, population size and number of noderations.
 * 
 * @author Charlie
 *
 */




public class StaticMutationOperator extends MutationOperator
{
	private int [] nodesToMutate;
	private Configuration configuration;
	int mutated;
	
	/**
	 * Most basic constructor for the StaticMutationOperator 
	 * sets the values of this MutationOperator based on the currentConfiguration
	 * 
	 * @param currentConfiguration
	 */
	
	public StaticMutationOperator(Configuration currentConfiguration)
	{	
			configuration = currentConfiguration;
			double currentMutationRate = getConfiguration().getMutationProbability();
			setMutationRate(currentMutationRate);
			initializeStaticValues();
	}
	
	
	/**
	 * Set the array of nodes that are going to be mutated for the whole run
	 * 
	 * @param nodesToMutate
	 */
	
	public void setNodesToMutate(int [] nodesToMutate)
	{
		this.nodesToMutate = nodesToMutate;
	}
	
	
	/**
	 * Get the nodes to be mutated for the whole run of the program
	 * 
	 * 
	 * @return nodes_to_mutate
	 */
	
	public int [] getNodesToMutate()
	{
		return nodesToMutate;
	}
	
	
	/**
	 * Returns the configuration of this StaticMutation Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Method to initialize all of the static values of the nodes to be 
	 * mutated.  
	 * 
	 */
	
	public void initializeStaticValues()
	{
		
		RandomGenerator random = getConfiguration().getRandomGenerator();
		double mutationRate = getMutationRate();
		int numNoderations = getConfiguration().getNumNoderations();
		int numFireflys = getConfiguration().getPopulationSize();
		int numNodes = getConfiguration().getFireflySize();
		
		// total amount of nodes in the entire run of the Genoype
		int totalNodes = numNoderations * numFireflys * numNodes;
		
		// total number of nodes to be mutated based on mutation rate
		int totalMutations = (int)(totalNodes * mutationRate);
		
		// get a temp array of random ints that don't repeat
		// values can be from 0 to total number of nodes in the Flytype
		// fill it with as mant integers as there are totalMutations
		int [] temp = random.nextNonRepeatingRandomIntArray(0, totalNodes, totalMutations);
		
		//sort the array
		Arrays.sort(temp);
		
		// set it to the main array of nodes to be mutated.
		setNodesToMutate(temp);
	}
	
	
	/**
	 * The operate method for the StaticMutationOperator takes in the current population
	 * and decides whether or not a given node is to be mutated based on the initialization
	 * of the nodes_to_be_mutated array.  
	 * 
	 * @param pop
	 * @param fireo
	 * 
	 */
	
	public void operate(Population pop, LinkedList<IFirefly> fireo)
	{
		
		double mutationRate = getMutationRate();
		int numNoderations = getConfiguration().getNumNoderations();
		int numFireflys = getConfiguration().getPopulationSize();
		int numNodesInFirefly = getConfiguration().getFireflySize();
		
		
		// number of total nodes in the whole geno type
		int totalNodes = numNoderations * numFireflys * numNodesInFirefly;
		
		
		int currentNoderation = getConfiguration().getCurrentNoderationNum();
		int flyNum, nodeNum, tempNodeIndex;
		
		//bolean used to see if current node is found in the nodes_to_mutate array
		boolean contains = false;
		
		
		// total number of nodes in a population
		int totalNodesInPop = numFireflys*numNodesInFirefly;
		
		//get a local copy of the nodes to be mutated
		int [] nodesToMutate = getNodesToMutate();
		
		// for each node in the population check to see if it should be mutated
		for(int i = 0; i < totalNodesInPop; i++)
		{
			// tempNodeIndex is the overall node number to be checked against the node_to_mutate array
			tempNodeIndex = (numNodesInFirefly * numFireflys * currentNoderation) + i;
			
			// for every node in the nodesToMutate array check it
			for(int j = 0; j < nodesToMutate.length; j++)
			{
				// if it is in there contains is true
				if(tempNodeIndex == nodesToMutate[j])	contains = true;
			}
			
			// if it is contained in there let's mutate it
			if(contains)
			{
				flyNum = i / numNodesInFirefly;
				
				// cannot divided by zero
				if(flyNum == 0)
				{
					nodeNum = i;
				}else
				{
					nodeNum = i % flyNum;
				}
				
				// get the node and mutate it
				pop.getFirefly(flyNum).getNode(nodeNum).mutate();
				
				//flip contains back to false for next iteration
				contains = false;
				
			}
			
		}
		
	}
	
}