package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

/**
 * A BasicCrossoverOperator is an INatureOperator that takes the nodes of two fireflys
 * and crosses over their values.  This particular CrossoverOperator creates a random crossover point 
 * between two firesomes and swaps their values making sure to fully fill in the rest of the firefly
 * This is an important part of a  nature algorithm.  
 * 
 * @author Charlie Davis
 *
 */


public class BasicCrossoverOperator implements INatureOperator
{
		private Configuration configuration;
		private int crossoverCount;
	
		public BasicCrossoverOperator(Configuration currentConfiguration)
		{
			configuration = currentConfiguration;
			crossoverCount = 0;
		}
		
		
		/**
		 * With the given population do crossover on the fireflys 
		 * 
		 * @param pop
		 * @param flys
		 * 
		 */
		
		public void operate(Population pop, LinkedList<IFirefly> flys) 
		{
			RandomGenerator random = getConfiguration().getRandomGenerator();
			int fly1, fly2, crossoverPoint;
			int popSize = getConfiguration().getPopulationSize();
			int fireflySize = getConfiguration().getFireflySize();
			IFirefly ffy1, ffy2, resultFirefly1, resultFirefly2;
			LinkedList<IFirefly> tempFlys = new LinkedList<IFirefly>();
			
			
			for(IFirefly fly : flys)
			{
				double doCrossover;
				
				fly1 = random.nextIntBetween(0, popSize-1);
				fly2 = random.nextIntBetween(0, popSize-1);
				
				//resultFirefly1 = ffy1.newFirefly();
				//resultFirefly2 = ffy1.newFirefly();
				
				crossoverPoint = random.nextIntBetween(1, fireflySize-2);
				
				while(fly1 == fly2)
				{
					fly2 = random.nextIntBetween(0, popSize-1);
				}
				
				ffy1 = pop.getFirefly(fly1);
				ffy2 = pop.getFirefly(fly2); 
				
				doCrossover = random.nextDouble();
				
				
				
				
				
				// if the random number is less than the crossover probability do the crossover
				
				if(doCrossover < getConfiguration().getCrossoverProbability()){
					LinkedList<INode> tempFirefly1 = new LinkedList<INode>();
					for(INode g : ffy1.getNodes()){
						tempFirefly1.addLast(g);
					}
					
					for(int i = crossoverPoint; i < configuration.getFireflySize(); i++){
						ffy1.getNodes().set(i, ffy2.getNodes().get(i));
						ffy2.getNodes().set(i, tempFirefly1.get(i));
					}
					
					
				}
				
				//tempFlys.add(resultFirefly1);
			}
			//pop = new Population(getConfiguration(), tempFlys);
		}
		
		
		/**
		 * Get the configuration of this BasicCrossover Operator
		 * 
		 * @return configuration
		 */
		
		
		public Configuration getConfiguration(){ return configuration; }
		
		
		
}
