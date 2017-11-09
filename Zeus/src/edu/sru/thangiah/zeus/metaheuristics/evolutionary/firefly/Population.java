package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.tsp.*;


	/*
	 * A Population is a generation of chromosomes that are stored in a list.
	 * Population also has an instance of the current configuration and the 
	 * last know fittest chromosome determined by the fitness function
	 */

public class Population {
	
	//private FireFly brightestFireFly;
	LinkedList<FireFly> fireFlies;
	static int popsize = 20;
	static int TotalGen = 10;
	
	public Population(NodesLinkedList mainNodes) {

		fireFlies = new LinkedList<FireFly>();
		
		fireFlies.add(new FireFly(mainNodes));
		int FireFlySize = FireFly.FireflyDimension;
		
		for(int i = 0; i < 20; i++) {
			//FireFly test = new FireFly();
			//System.out.println("> " + test.FireFlyt.getRouteString());
			fireFlies.add(new FireFly());			
		}

		
		
		//System.out.println(" \n ===== \n");
		//for(int i=0; i < 20; i++) {
		//System.out.println("the old pop is " + fireFlies.get(i).FireFlyt.getRouteString());
		//}
		
		Operators RunOpts = new Operators(fireFlies);
		//System.out.println("Fireflies is "+fireFlies.get(0).FireFlyt.);

	}
	public Population() {
		fireFlies = new LinkedList<FireFly>();
	}



}