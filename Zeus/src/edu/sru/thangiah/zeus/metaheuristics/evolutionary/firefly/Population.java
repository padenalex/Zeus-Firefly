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
	
	LinkedList<FireFly> fireFlies;
	//Pop Size is the number of Randon TSP Populations Generated
	//TotalGen is the number of times operators will run on these random TSPs
	public static int popsize = 30;
	public static int TotalGen = 250;
	private NodesLinkedList FinalFly;
	public double totalTime;
	public double effPercent;
	public double newCost;
	public double ogCost;
	
	public Population(NodesLinkedList mainNodes) {

		System.out.println("\n --- FireFly Algorithm Engaged... ---");
		double startTime = System.currentTimeMillis();
		this.ogCost = mainNodes.getCost();
		
		this.fireFlies = new LinkedList<FireFly>();
		this.fireFlies.add(new FireFly(mainNodes));
		int FireFlySize = FireFly.FireflyDimension;
		
		for(int i = 0; i < 20; i++) {
			this.fireFlies.add(new FireFly());			
		}
		//Runs Operators Then Sets Best Fly
		Operators RunOpts = new Operators(fireFlies);
		SetFinalFly(RunOpts);
		
		this.newCost = this.FinalFly.getCost();
		this.effPercent = (1-(newCost/ogCost))*100;
		double endTime   = System.currentTimeMillis();
		this.totalTime = (endTime - startTime)/1000;
		
		//Final Firefly Output
		System.out.println("\n" + "FireFly route found is: " + this.FinalFly.getRouteString());
		System.out.println("FireFly route cost is: " + this.FinalFly.getCost());
		System.out.println("FireFly Aglorithm Runtime (Seconds) Is: " + totalTime);
		System.out.println("FireFly Reduced Cost By: " + effPercent + "%");
	}
	
	public Population() {
		this.fireFlies = new LinkedList<FireFly>();
	}

	void SetFinalFly(Operators RunOpts) {
		this.FinalFly = RunOpts.FinalBright.FireFlyt;
	}
	
	public NodesLinkedList GetFinalFly() {
		return FinalFly;
	}


}