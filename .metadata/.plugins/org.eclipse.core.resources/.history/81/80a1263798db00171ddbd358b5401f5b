package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg.firefly;

import java.util.LinkedList;
import edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg.INodeLL;
import edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg.Population;


public class FFOperators {
private INodeLL finalBrightest;
	
	public FFOperators(Population ogPopulation){
		
		int numGenerations = ogPopulation.ReturnConfig().getNumGenerations();
		int numPop = ogPopulation.ReturnConfig().getPopulationSize();
		int tspSize = ogPopulation.ReturnConfig().getTspSize();
		Population newPopulation = new Population();
		
		for(int i=0; i < numPop; i++) {newPopulation.ReturnPopLL().add(ogPopulation.ReturnPopLL().get(i));}

		//System.out.println("TESTSTS --> " + newPopulation.ReturnPopLL());
		
//Start Generation
		for(int g=0; g < numGenerations; g++) {
			INodeLL currBrightest = newPopulation.ReturnPopLL().getFirst();
			for(int i=1; i < numPop; i++) {
				FindNewEdge Edge = new FindNewEdge(currBrightest, newPopulation.ReturnPopLL().get(i));
				//XtoY
				//YtoX
				//XfromY
				//YfromX
			}
			
			//InvertMutation On Best Fly
			//TrimPopulation Back to Pop Size
		}
		
		//this.finalBrightest = newPopulation.ReturnPopLL().getFirst();
		
//----End Generation		
	}
	
	
	
	
	
	
}
