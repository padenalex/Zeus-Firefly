package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.tsp.*;


	/*
	 * A Population is a generation of chromosomes that are stored in a list.
	 * Population also has an instance of the current configuration and the 
	 * last know fittest chromosome determined by the fitness function
	 */

public class Population
{
	private FireFly brightestFireFly;
	LinkedList<FireFly> fireFlies;
	static int popsize = 20;
	static int TotalGen = 100;
	
	public Population(NodesLinkedList mainNodes)
	{
		fireFlies = new LinkedList<FireFly>();
		
		fireFlies.add(new FireFly(mainNodes));
		int FireFlySize = FireFly.FireflyDimension;
		
		for(int i = 0; i < popsize-1; i++) //first one is original one
		{
		fireFlies.add(new FireFly());
		}
		
		Operators RunOpts = new Operators(fireFlies);
		//System.out.println("Fireflies is "+fireFlies.get(0).FireFlyt.);
		
	}
	public Population() {
		fireFlies = new LinkedList<FireFly>();
	}



}