package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * RouletteSelectionOperator is a Natural Selector that uses the concept of a 
 * Roulette wheel to select from a population of fireflys.  Each firefly's cost is 
 * calculated individually and added together for a total cost.  Then each firefly is 
 * given a cost value percentage related to the total cost of all of the fireflys.
 * A random number is noderated from 0.0 to 100.0 just like you were spinning a Roulette wheel.
 * The firefly that is stored in the NavigableMap at that value is selected to move into the 
 * next population.
 * 
 * @author Charlie Davis
 *
 */


public class RouletteSelectionOperator implements ISelector
{
	private Configuration configuration;
	private NavigableMap<Double, IFirefly> wheel;
	protected double totalCost;
		
	public RouletteSelectionOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		wheel = new TreeMap<Double, IFirefly>();	
	}
		
	
	/**
	 * Get the current instance of the configuration of this RouletteSelectionOperator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration()
	{
		return configuration;
	}
	
	
	/**
	 * Method to select the fireflys moving into the next population.  
	 * 
	 * 
	 * @param num_to_select
	 * @param population
	 */
	
	public void select(int numToSelect, Population population) 
	{
		// reset the total cost to 0.0
		totalCost = 0.0;
		
		double spin, currentCostRatio = 0.0;
		
		// create a temporary wheel for this selection
		NavigableMap<Double, IFirefly> tempWheel = getWheel();
		
		// get the current 
		RandomGenerator random = getConfiguration().getRandomGenerator();
		
		// make a temporary Linked List of fireflys
		LinkedList<IFirefly> flys = new LinkedList<IFirefly>();
		
		// for each IFirefly in the population
		// add to the totalCost the current firefly's cost value
		for(IFirefly fly : population.getFireflys())
		{
			totalCost += fly.getCostValue();
		}
		
		// for each IFirefly in the population
		// set the cost ratio of this firefly
		// add the firefly to the wheel with the costRatio being the key
		// keep adding to the current cost ratio to build our map keys
		for(IFirefly fly : population.getFireflys())
		{
			fly.setCostRatio(fly.getCostValue() / totalCost);
			tempWheel.put(currentCostRatio, fly);
			currentCostRatio += fly.getCostRatio(); 
		}
		
		// for as many firesomes to be selected 
		// spin the wheel get the firefly from the map
		// and add it to our temporary fireflys LinkedList
		
		for(int i = 0; i < numToSelect-1; i++)
		{
			spin = random.nextDouble();
			IFirefly tempFly = tempWheel.get(tempWheel.floorKey(spin));
			flys.add(tempFly);
			
		}
			
			// finally add the best firefly from the previous population 
			flys.add(population.getBestFirefly());
			
			// set the population to the new population we just created.
			population = new Population(getConfiguration(), flys);
	}
	
	
	public TreeMap<Double,IFirefly> getWheel(){ return (TreeMap<Double, IFirefly>) wheel; }

}