package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.firefly;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Configuration;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IChromosome;




public class FindNewEdge {
	private LinkedList<Integer> nsLout = new LinkedList<Integer>(); // <-- -->
	private LinkedList<Integer> nsRout = new LinkedList<Integer>(); // <-- -->
	private int sLeftIndex;
	private int sRightIndex;
	
	public FindNewEdge(IChromosome brightFly, IChromosome randomFly, Configuration configuration) {
		Random rand = new Random();
		int brightPos1 = 999999999;
		int brightPos2 = 999999999;
		int a1 = 999999998;
		int a2 = 999999998;
		int b1 = 999999999;
		int b2 = 999999999;
		int tspMax = configuration.getChromosomeSize()-1;  //Max tsp size in chromosome LL (#node -1 for start at 0) 
		int keepRunning = 1;

		int cIsflip = 0; 
		int timesRun =0;
		
		//Generates 2 connected random positions which are connected in the brightest fly/chromosome but not in the current/random one
		while(keepRunning == 1) {
			brightPos1 = rand.nextInt(tspMax) + 0;
			int nextOrprev = rand.nextInt(1) + 0;	
			//1 is next and 0 is prev
			if(nextOrprev == 0 && brightPos1 !=0) {brightPos2 = brightPos1-1;}
			else if(nextOrprev == 0 && brightPos1 == 0) {brightPos2 = brightPos1+1;}
			else if(nextOrprev == 1 && brightPos1 != tspMax) {brightPos2 = brightPos1+1;}
			else if(nextOrprev == 1 && brightPos1 == tspMax) {brightPos2 = brightPos1-1;}
			else {System.out.println("Error section #1 occured in findNewEdge of Firefly");}
		
			a1 = (int) brightFly.getGenes().get(brightPos1).getInternalValue();
			a2 = (int) brightFly.getGenes().get(brightPos2).getInternalValue();
			int tempPos = randomFly.findGenePos(a1);
			try {
			b1 = (int) randomFly.getGenes().get(tempPos-1).getInternalValue();
			b2 = (int) randomFly.getGenes().get(tempPos+1).getInternalValue();
			} catch(Exception e) {};
			
			if(a2 != b1 && a2!= b2) {keepRunning=0;}
			timesRun++;
			if(timesRun > 100) {break;} //If runs more than 100 times without non connected edge then break to prevent deadlock on equal fly. yFromx and xFromy will still change this fly.
		}
		
		if(brightPos1 > brightPos2) {
			int temp = brightPos1;
			brightPos1 = brightPos2;
			brightPos2 = temp;
			temp = a1;
			a1 = a2;
			a2 = temp;
		}
		
		int currPos1 = randomFly.findGenePos(a1);
		int currPos2 = randomFly.findGenePos(a2);
		b1 = a1;
		b2 = a2;
		
		//Set currpos and curr position index (b1b2) to left or right
		if(currPos1 > currPos2) {
			int temp = currPos1;
			currPos1 = currPos2;
			currPos2 = temp;
			int temp1 = b1;
			b1 = b2;
			b2 = temp1;
			cIsflip = 1; //Signals current pos is flipped
		}
		
		//Adds the first index
		nsLout.add(b1);
		nsRout.add(b2);
		
		
		//bLeft-RightIndex is the brightest (fly's) left and right index's for a connected edge
		//cLeft-RightIndex is the current (fly's) left and right index which are equal to the index of bright (Positions are NOT equal)
		int bLeftIndex = a1;
		int bRightIndex = a2;
		int cLeftIndex = b1;
		int cRightIndex = b2;
		int currtemp;
		int brighttemp;
		sLeftIndex = cLeftIndex;
		sRightIndex = cRightIndex;
	
		//Below will locate the current(random) fly's index for the left side. 
		//It will then work its way out until it reaches the beginning or the previous node/gene is no longer equal to that of the bright fly's
		while(randomFly.findGenePos(cLeftIndex) != 0 && brightFly.findGenePos(bLeftIndex) != 0) {
			currtemp = (int) randomFly.getGene(randomFly.findGenePos(cLeftIndex)-1).getInternalValue();
			
			if(cIsflip == 1) {brighttemp = (int) brightFly.getGene(brightFly.findGenePos(bRightIndex)+1).getInternalValue();}
			else {brighttemp = (int) brightFly.getGene(brightFly.findGenePos(bLeftIndex)-1).getInternalValue();}
			
			if(currtemp == brighttemp) {nsLout.add(currtemp);}
			else {break;}
			
			cLeftIndex = currtemp;
			bLeftIndex = brighttemp;
		}

		//reset variables which have been changed as the edge extends in the above whileloop
		bLeftIndex = a1;
		bRightIndex = a2;
		cLeftIndex = b1;
		cRightIndex = b2;
		
		//Below will locate the current(random) fly's index for the right side. 
		//It will then work its way out until it reaches the end or the next node/gene is no longer equal to that of the bright fly's
		while(randomFly.findGenePos(cRightIndex) != tspMax && brightFly.findGenePos(bRightIndex) != tspMax) {
			currtemp = (int) randomFly.getGene(randomFly.findGenePos(cRightIndex)+1).getInternalValue();
			
			if (cIsflip==1 && brightFly.findGenePos(bLeftIndex) == 0) {break;}
			else if(cIsflip == 1) {brighttemp = (int) brightFly.getGene(brightFly.findGenePos(bLeftIndex)-1).getInternalValue();}
			else {brighttemp = (int) brightFly.getGene(brightFly.findGenePos(bRightIndex)+1).getInternalValue();}
			
			if(currtemp == brighttemp) {nsRout.add(currtemp);}
			else {break;}
			
			cRightIndex = currtemp;
			bRightIndex = brighttemp;
		}
	}
	
	public LinkedList<Integer> getNSLout() {return this.nsLout;}
	
	public LinkedList<Integer> getNSRout() {return this.nsRout;}
	
	public int getSLindex() {return this.sLeftIndex;}
	
	public int getSRindex() {return this.sRightIndex;}
	
}








