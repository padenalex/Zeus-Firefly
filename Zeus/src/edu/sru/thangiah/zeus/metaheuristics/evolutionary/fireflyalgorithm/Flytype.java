package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

import com.sun.media.sound.InvalidFormatException;

/**
 * A Flytype is the represetation of the entire problem you are trying to solve.
 * Flytype contains the population, a configuration of the current problem, a cost
 * function and the best firefly in the Flytype
 * 
 * 
 * @author Charlie Davis
 *
 */

public class Flytype
{
	private Population population;
	private Configuration configuration;
	private AbstractCostFunction costFunction;
	private int fireflySize;
	private IFirefly bestFirefly;
	private transient Configuration lastConf;
	private transient Population lastPop;
	
	
	public Flytype(Configuration config)
	{
		configuration = config;
		costFunction = configuration.getCostFunction();
		fireflySize = configuration.getFireflySize();
		lastPop = null;
	}
	
	public Flytype(Configuration config, Population pop)
	{
		this(config);
		setPopulation(pop);
	}
	
	
	/**
	 * This method randomizes the initial population to random fireflys of the given
	 * node type found in the configuration
	 * 
	 * @param populationSize
	 */
	
	public void randomizeInitialPopulation(int populationSize)
	{
		RandomGenerator generator = configuration.getRandomGenerator();
		
		// for each firefly in the population set its nodes to random values
		for(IFirefly tempFirefly : getPopulation().getFireflys())
		{
			tempFirefly.setRandomNodes(generator);
		}
		
	}
	
	
	/**
	 * 
	 * @param configuration
	 * @return randomized Flytype
	 */
	
	
	public static Flytype randomInitialFlytype(Configuration configuration)
	{
		if (configuration.getCurrentNoderationNum() == 0) 
		{
		    if (configuration == null) {
		      throw new IllegalArgumentException(
		          "The Configuration instance may not be null.");
		    }
		    configuration.lockSettings();
		    // Create an array of fireflys equal to the desired size in the
		    // active Configuration and then populate that array with Firefly
		    // instances constructed according to the setup in the sample
		    // Firefly, but with random node values (alleles). The Firefly
		    // class randomInitialFirefly() method will take care of that for
		    // us.
		    // ------------------------------------------------------------------
		    int populationSize = configuration.getPopulationSize();
		    Population pop = new Population(configuration);
		    // Do randomized initialization.
		    // -----------------------------
		    Flytype result = new Flytype(configuration, pop);
		    result.randomizeInitialPopulation(populationSize);
		    
		    return result;
		}
		
		return null;
	}
	
	
	/**
	 * Evolve actually does the work of applying natural selectors and nature operators
	 * 
	 */
	
	public void evolve()
	{
		//System.out.println("Before Evolve " + population.toString());
		Population tempPop = population;
		lastPop = population;
		
		ICostFunction costFunction = getConfiguration().getCostFunction();
		IFirefly best = null;
		
		int size = population.size();
		
		// for every firefly in the population
		// get the firefly and increase its age
		// evaluate its cost
		
		for (int i = 0; i < size; i++)
		{
			IFirefly fire = tempPop.getFirefly(i);
	        fire.increaseAge();
	        fire.setCostValue(costFunction.evaluate(fire));
	    }
		
		// apply all Nature operators and NaturalSelectors
		
		applyNaturalSelectors(getConfiguration(), tempPop);
		applyNatureOperators(getConfiguration(), tempPop);
		
		
		// increment the current noderation number
		
		configuration.incrementNoderationNum();
		lastConf = configuration;
		
		// Save the best firefly
		// set the population with the new population
		setBestFirefly(calculateBestFirefly());
		setPopulation(tempPop);
		
		//System.out.println("After Evolve " + population.toString());
	}
	
	
	/**
	 * Get the current population
	 * 
	 * @return population
	 */
	
	public Population getPopulation()
	{
		return population;
	}
	
	
	/**
	 * Get the last population
	 * 
	 * @return lastPop
	 */
	
	public Population getLastPopulation()
	{
		return lastPop;
	}
	
	
	/**
	 * Set the current population with the new population 
	 * 
	 * @param newPopulation
	 */
	
	public void setPopulation(Population newPopulation)
	{
		population = newPopulation;
	}
	
	
	/**
	 * Set the last population 
	 * 
	 * @param newPopulation
	 */
	
	public void setLastPopulation(Population newPopulation)
	{
		lastPop = newPopulation;
	}
	
	
	/**
	 * Get the configuration of this Flytype
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration()
	{
		return configuration;
	}
	
	
	/**
	 * Get the best firefly in the this Flytype
	 * 
	 * @return best_firefly
	 */
	
	public IFirefly getBestFirefly()
	{
		return bestFirefly;
	}
	
	
	/**
	 * Set the the best firefly of this Flytype
	 * 
	 * @param fly
	 */
	
	public void setBestFirefly(IFirefly fly)
	{
		bestFirefly = fly;
	}
	
  /**
   * Applies all NatureOperators registered with the Configuration.
   *
   * @param currentConfig the configuration to use
   * @param currentPopulation the population to use as input
   *
   */

	
	public IFirefly calculateBestFirefly()
	{
		//best_firefly = null;
		ICostFunction costFunction = configuration.getCostFunction();
		IFirefly currentBest = getPopulation().getFireflys().get(0);
		
		double currentHighestCost = 0.0, nextCost = 0.0;
		
		for (int i = 1; i < population.size(); i++) 
		{
			currentHighestCost = costFunction.evaluate(currentBest);
			nextCost = costFunction.evaluate(population.getFireflys().get(i));
			
            if(currentHighestCost < nextCost )
            {
            	currentBest = population.getFireflys().get(i);
            }
        }
		
		setBestFirefly(currentBest);
		return currentBest; 
		
	}
	
	
  protected void applyNatureOperators(Configuration currentConfig, Population currentPopulation) 
  {
	    LinkedList<INatureOperator> natureOperators = currentConfig.getNatureOperators();
	    
	    // for each operator in the list of nature operators
	    // operate!
	    
	    for(INatureOperator operator : natureOperators)
	    {
	      INatureOperator tempOperator = (INatureOperator) operator;
	      tempOperator.operate(currentPopulation, currentPopulation.getFireflys());
	      
	    }
  }
  
  
  /**
   * Applies all NaturalSelectors registered with the Configuration.
   *
   * @param currentConfig the configuration to use
   * @param currentPopulation the population to use as input
   *
   */
  
  protected void applyNaturalSelectors(Configuration currentConfig, Population currentPopulation)
  {
	  LinkedList<ISelector> selectors = currentConfig.getSelectors();
	  int numToSelect = currentPopulation.size();
	  
	  // for each Selector in the list of Natural Selectors
	  // Select!
	  
	  for(ISelector selector: selectors)
	  {
		  ISelector tempSelector = (ISelector) selector;
		  tempSelector.select(numToSelect, currentPopulation);
	  }
  }
	  
	
}