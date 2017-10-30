package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * Simple cost function that evaluates the cost of a given firefly
 * This particular cost function test to see if the IFirefly sent to is 
 * the highest possible binary number.
 * 
 * @author Charlie Davis modified from JGAP
 *
 */

public class MaxFunction
    extends AbstractCostFunction {

	/**
	 * Function that evaluates the cost of a given firefly
	 * 
	 * @param IFirefly fly
	 */	
	
	  public double evaluate(IFirefly fly) {
	    int total = 0;
	
	    for (int i = 0; i < fly.size(); i++) {
	      BinaryNode value = (BinaryNode) fly.getNode(fly.size() -
	          (i + 1));
	      
	      	total += Math.pow(2.0, (double) i) * (int)value.getInternalValue();
	    }
	
	    return total;
	  }
}
