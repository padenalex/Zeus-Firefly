package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.firefly;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Chromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Configuration;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IChromosome;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IGene;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.IGeneticOperator;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.Population;

public class FireFlyOperator implements IGeneticOperator
{
	private Configuration configuration;
	
	public FireFlyOperator(Configuration currentConfiguration){
		configuration = currentConfiguration;
	}
	
//==============================================================================================
	
	public void operate(Population newPop) {
		Settings.printDebug(Settings.COMMENT, "Into Operator");
		//IChromosome ogTSPChromo = configuration.getOGChromosome();  	//Adds original TSP Chromo to population (comment to remove)
		//ogPop.getChromosomes().addFirst(ogTSPChromo); 				//Adds original TSP Chromo to population (comment to remove)
		//Population newPop = new Population(configuration, ogPop.getChromosomes());  //Uncomment to retain original population and change passed in to ogPop
		int totalGen = configuration.getNumGenerations();
		int totalPop = configuration.getPopulationSize();

		for(int g=0; g<totalGen; g++) {
			IChromosome currBright = newPop.calculateFittestChromosome();
			for(int i=0; i<4; i++) {newPop.addChromosome(invertMutation(currBright));}
			for(int i=1; i<totalPop; i++) {
				FindNewEdge testedge = new FindNewEdge(currBright, newPop.getChromosome(i), configuration);
				newPop.addChromosome(xToy(testedge, newPop.getChromosomes().get(i)));
				newPop.addChromosome(yTox(testedge, newPop.getChromosomes().get(i)));
				newPop.addChromosome(xFromy(testedge, newPop.getChromosomes().get(i)));
				newPop.addChromosome(yFromx(testedge, newPop.getChromosomes().get(i)));
			}
			newPop = trimPopulation(newPop);
			
			//Prints Fittest Chromosome Each Generation. Comment to remove
			System.out.println("current fittest cost: " + newPop.getChromosomes().getFirst().getTspCost() + " Generation # " + g);
		}
	}

//==============================================================================================


	//  ---X--------Y---
	//  ---Y~~~~~~~~X---
	//Inverts X+Y AND everything inbetween them. Both X and Y positions will be randomly generated/
	//This is used only on the brightest fly to move it randomly. However, I've added it to trimpopulation and it's used if the new population contains 2 matching chromosomes
	private IChromosome invertMutation(IChromosome ogChromo) {
		int tspSize = configuration.getChromosomeSize();
		Chromosome newChromo = new Chromosome(configuration);
		LinkedList<IGene> tGenes = new LinkedList<>();
		for(int i=0; i<ogChromo.getGenes().size(); i++) {tGenes.add(ogChromo.getGenes().get(i));}
		newChromo.setGenes(tGenes);
		Random rand = new Random();
		Vector<IGene> flipVect = new Vector<IGene>();

		int  rand1 = rand.nextInt(tspSize) + 0;
		int  rand2 = rand.nextInt(tspSize) + 0;
		while(rand1==rand2 || rand1==rand2-1 || rand1==rand2+1) {rand2 = rand.nextInt(tspSize) + 0;}
		
		if(rand1 > rand2) { //Set Rand1 to smaller Rand2 larger
			int temp;
			temp = rand1;
			rand1 = rand2;
			rand2 = temp;
		}
		
		int loopSize = rand2-rand1;
		int temprand = rand1;
		
		//Collects Genes in given section stores in vector, removes from chromosome, flips, puts back in chromosome reverse order
		for(int i=0; i<loopSize; i++) {flipVect.add(newChromo.getGene(temprand)); temprand++;}
		for(int i=0; i<loopSize; i++) {newChromo.getGenes().remove(rand1);}
		Collections.reverse(flipVect);
		for(int i=0; i<loopSize; i++) {newChromo.getGenes().add(rand1, flipVect.get(i)); rand1++;}
		
		return newChromo;
	}

	//  ---X--------Y---
	//  -----------XY---
	private IChromosome xToy(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		LinkedList<IGene> tGenes = new LinkedList<>();
		for(int i=0; i<ogChromo.getGenes().size(); i++) {tGenes.add(ogChromo.getGenes().get(i));}
		newChromo.setGenes(tGenes);
		
		LinkedList<Integer> nsLout = new LinkedList<Integer>();
		nsLout = edge.getNSLout();
		int sRightIndex = edge.getSRindex();
		
		for(int i=0; i<nsLout.size(); i++) {
			int tmpLPos = newChromo.findGenePos(nsLout.get(i));
			IGene tempGene = newChromo.getGene(tmpLPos);
			int tmpRPos = newChromo.findGenePos(sRightIndex)-1;
			newChromo.getGenes().remove(tmpLPos);
			newChromo.getGenes().add(tmpRPos, tempGene);
			sRightIndex = (int) tempGene.getInternalValue();
		}
		return newChromo;
	}
	
	//  ---X--------Y---
	//  ---XY-----------
	private IChromosome yTox(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		LinkedList<IGene> tGenes = new LinkedList<>();
		for(int i=0; i<ogChromo.getGenes().size(); i++) {tGenes.add(ogChromo.getGenes().get(i));}
		newChromo.setGenes(tGenes);
		LinkedList<Integer> nsRout = new LinkedList<Integer>();
		nsRout = edge.getNSRout();
		int sLeftIndex = edge.getSLindex();
		
		for(int i=0; i<nsRout.size(); i++) {
			int tmpRPos = newChromo.findGenePos(nsRout.get(i));
			IGene tempGene = newChromo.getGene(tmpRPos);
			int tmpLPos = newChromo.findGenePos(sLeftIndex)+1;
			newChromo.getGenes().remove(tmpRPos);
			newChromo.getGenes().add(tmpLPos, tempGene);
			sLeftIndex = (int) tempGene.getInternalValue();
		}
		return newChromo;
	}
	
	//  ---X--------Y---
	//  ------------YX--
	//xFromY will also flip the Y/X list so the head of nsLout and nsRout are connected
	private IChromosome xFromy(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		LinkedList<IGene> tGenes = new LinkedList<>();
		for(int i=0; i<ogChromo.getGenes().size(); i++) {tGenes.add(ogChromo.getGenes().get(i));}
		newChromo.setGenes(tGenes);
		LinkedList<Integer> nsLout = new LinkedList<Integer>();
		LinkedList<Integer> nsRout = new LinkedList<Integer>();
		nsLout = edge.getNSLout();
		nsRout = edge.getNSRout();
		int sRightIndex = edge.getSRindex();
		
		//Puts the Left Set to the Right after Y
		for(int i=0; i<nsLout.size(); i++) {
			int tLpos = newChromo.findGenePos(nsLout.get(i));
			IGene tempGene = newChromo.getGenes().get(tLpos);
			newChromo.getGenes().remove(tLpos);
			int sRpos = newChromo.findGenePos(sRightIndex) + 1;
			newChromo.getGenes().add(sRpos, tempGene);
			sRightIndex = (int) tempGene.getInternalValue();
		}
		
		sRightIndex = edge.getSRindex(); //reset right index back to the original (y)
	
		//Puts the Right set to the left before Y
		for(int i=1; i<nsRout.size(); i++) {
			int tRpos = newChromo.findGenePos(nsRout.get(i));
			IGene tempGene = newChromo.getGenes().get(tRpos);
			newChromo.getGenes().remove(tRpos);
			int sRpos = newChromo.findGenePos(sRightIndex);
			newChromo.getGenes().add(sRpos, tempGene);
			sRightIndex = (int) tempGene.getInternalValue();
		}
		return newChromo;
	}
	
	//  ---X--------Y---
	//  ---YX-----------
	//yFromx will also flip the X/Y list so the head of nsLout and nsRout are connected
	private IChromosome yFromx(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		LinkedList<IGene> tGenes = new LinkedList<>();
		for(int i=0; i<ogChromo.getGenes().size(); i++) {tGenes.add(ogChromo.getGenes().get(i));}
		newChromo.setGenes(tGenes);
		LinkedList<Integer> nsLout = new LinkedList<Integer>();
		LinkedList<Integer> nsRout = new LinkedList<Integer>();
		nsLout = edge.getNSLout();
		nsRout = edge.getNSRout();
		int sLeftIndex = edge.getSLindex();
		
		for(int i=0; i<nsRout.size(); i++) {
			int tRpos = newChromo.findGenePos(nsRout.get(i));
			IGene tempGene = newChromo.getGenes().get(tRpos);
			newChromo.getGenes().remove(tRpos);
			int sRpos = newChromo.findGenePos(sLeftIndex);
			newChromo.getGenes().add(sRpos, tempGene);
			sLeftIndex = (int) tempGene.getInternalValue();
		}

		sLeftIndex = edge.getSLindex(); //reset Left Index to original 
		
		for(int i=1; i<nsLout.size(); i++) {
			int tLpos = newChromo.findGenePos(nsLout.get(i));
			IGene tempGene = newChromo.getGenes().get(tLpos);
			newChromo.getGenes().remove(tLpos);
			int sLpos = newChromo.findGenePos(sLeftIndex)+1;
			newChromo.getGenes().add(sLpos, tempGene);
			sLeftIndex = (int) tempGene.getInternalValue();
		}		
		return newChromo;
	}

	
	//Trim population brings in the grown population (popSize * 4) and trims it back down to the (popSize) best Flys/Chromosomes
	private Population trimPopulation(Population ogPop) {
		LinkedList<IChromosome> tempChromes = new LinkedList<IChromosome>();

		for(int i=0; i<configuration.getPopulationSize(); i++) {
			IChromosome tempFit = ogPop.calculateFittestChromosome();
			ogPop.getChromosomes().remove(tempFit);
			
			int invertAgain = 0;
			for(int x=0; x <tempChromes.size(); x++) { 
				String tempS = tempFit.toString();
				if(tempS.equals(tempChromes.get(x).toString())) {invertAgain=1;}
			}
			
			//num loops is how different it will become
			if(invertAgain==1) { for(int x=0; x<2; x++) {tempFit = invertMutation(tempFit); }}
			
			tempChromes.add(tempFit);
		}
		ogPop.getChromosomes().removeAll(ogPop.getChromosomes());
		ogPop.getChromosomes().addAll(tempChromes);
		
		//Print all Chromosome cost in new pop
		//for(int i=0; i<configuration.getPopulationSize(); i++) {System.out.println(ogPop.getChromosome(i).getTspCost());}
		return ogPop;
	}
	

	public Configuration getConfiguration(){ return configuration; }

	@Override
	public void operate(Population pop, LinkedList<IChromosome> chromo) {
		//GA is setup to use this for a reason I do not know.
		//This is not used or needed. Pop contains the chromo LL so why pass them both in
	}
}





