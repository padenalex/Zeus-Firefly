package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;


/**
 * Nature operators are an essential part of evolutionary algorithms.  A nature operator
 * is an algorithm that will operate on a LinkedList of IFireflys to modify it in some way.  
 * Two examples are BasicCrossoverOperator.java and MutationOperator.java that both implement
 * this interface.
 * 
 * @author Charlie Davis
 *
 */

public interface INatureOperator {
	
	
	/**
	 * From the given population get the list of fireflys to be operated on
	 * 
	 * @param pop
	 * @param fireo
	 */
	
	
	public void operate(Population pop, LinkedList<IFirefly> fireo);
	
}
