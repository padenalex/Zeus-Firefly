package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg;

import java.util.LinkedList;
import edu.sru.thangiah.zeus.core.NodesLinkedList;


/*
 * A Population is a generation of chromosomes that are stored in a list.
 * Population also has an instance of the current configuration and the 
 * last know fittest chromosome determined by the fitness function
 */

public class Population
{
	private INodeLL bestTSP;
	private LinkedList<INodeLL> popLL;
	private Configuration configuration;
	
	
	public Population(Configuration eaConf, NodesLinkedList ogNodeLL){
		
		this.configuration = eaConf;
		this.popLL = new LinkedList<INodeLL>();
		
		System.out.println("Test ------ " + ogNodeLL.getNodesAtPosition(0).getIndex());
		
		//The initial list populates in the order that it was passed in from Zeus
		//This is done to preserve a reasonable first list for algorithms like FireFly 
		INodeLL theOGList = new INodeLL(eaConf, ogNodeLL);
		popLL.add(theOGList);
		
		for(int i = 0; i < configuration.getPopulationSize()-1; i++){
			INodeLL temp = new INodeLL(eaConf);
			popLL.add(temp);
		}
		
	//Set FinalBest
	//Get FinalBest
	}
	
	//New Population Here Which Is Not Limited By Popsize (Expands and Contracts For Firefly Population)
	public Population() {this.popLL = new LinkedList<INodeLL>();}

}