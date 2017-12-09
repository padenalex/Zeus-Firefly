package edu.sru.thangiah.zeus.metaheuristics.evolutionary;

import edu.sru.thangiah.zeus.tsp.*;

/**
 * Simple fitness function that evaluates the fitness of a given chromosome
 * This particular fitness function test to see if the IChromosome sent to is 
 * the highest possible binary number.
 * 
 * @author Charlie Davis modified from JGAP
 *
 */

public class MaxFunction
    extends AbstractFitnessFunction {

	/**
	 * Function that evaluates the fitness of a given chromosome
	 * 
	 * @param IChromosome chrome
	 */	
	
	  public double evaluate(IChromosome chrome) {
	    double total = 0;
	    TSPNodesLinkedList headNode = new TSPNodesLinkedList();
	
	    for (int i = 0; i < chrome.size(); i++) 
	    {
	    	TSPNodes temp = new TSPNodes(new TSPShipment((int) chrome.getGene(i).getInternalValue()));
	    	headNode.insertNodeLast(temp);
	    }
	    total = headNode.getCost();
	
	    return total;
	  }
}