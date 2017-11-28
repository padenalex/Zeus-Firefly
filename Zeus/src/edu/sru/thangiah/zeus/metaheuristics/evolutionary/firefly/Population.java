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
	static int popsize = 40;
	static int TotalGen = 500;
	public NodesLinkedList FinalFly;
	
	public Population(NodesLinkedList mainNodes) {

		this.fireFlies = new LinkedList<FireFly>();
		
		this.fireFlies.add(new FireFly(mainNodes));
		int FireFlySize = FireFly.FireflyDimension;
		
		for(int i = 0; i < 20; i++) {
			this.fireFlies.add(new FireFly());			
		}
		
		Operators RunOpts = new Operators(fireFlies);
		SetFinalFly(RunOpts);
		//System.out.println("Fireflies is "+fireFlies.get(0).FireFlyt.);

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