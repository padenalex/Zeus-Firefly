package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.Chromosome;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.Configuration;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.IChromosome;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.IFitnessFunction;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.IGene;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.IGeneticOperator;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.IntegerGene;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.Population;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.RandomGenerator;

public class FireFlyOperator implements IGeneticOperator
{
	private Configuration configuration;
	private int crossoverCount;
	
	public FireFlyOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		crossoverCount = 0;
	}
	
	@Override
	public void operate(Population pop)
	{
		operate(pop, pop.getChromosomes());
	}

//==============================================================================================
//==============================================================================================	
//==============================================================================================
//==============================================================================================	
	
	
	@Override
	public void operate(Population ogPop, LinkedList<IChromosome> ogPopLL){
		Settings.printDebug(Settings.COMMENT, "Into Operator");
		IFitnessFunction fitFunc = configuration.getFitnessFunction();
		//double test = fitnessFunction.evaluate(ogPopLL.getLast());

		int totalGen = configuration.getNumGenerations();
		int totalPop = configuration.getPopulationSize();
		int tspSize = configuration.getChromosomeSize();
		Population newPop = new Population(configuration, ogPopLL);
		
		//System.out.println(totalGen + " " + totalPop + " " + tspSize);
		//System.out.println(newPop.calculateFittestChromosome().getTspCost());
		
//----Test section
		IChromosome currBright = newPop.getChromosomes().getFirst();
		FindNewEdge testedge = new FindNewEdge(currBright, ogPopLL.getLast(), configuration);


		Chromosome test = new Chromosome(configuration);
		test.fillRandomGenes(configuration.getRandomGenerator());
		
		System.out.println(currBright.getTspCost());
		
		//invertMutation(currBright);
		//xToy(testedge, ogPopLL.getLast());
		yTox(testedge, ogPopLL.getLast());
		
		

		
//----Test section
		
		
		for(int g=0; g<totalGen; g++) {
			//IChromosome currBright = newPop.getChromosome(0);
			for(int i=0; i<totalPop; i++) {
				//findNewEdge
				//xToy
				//ytoX
				//xFromy
				//yFromx
				
				
			}
		}
		
		
//-----------------------End Calculations---------------------------------
}
	
//==============================================================================================
//==============================================================================================
	

	private IChromosome invertMutation(IChromosome ogChromo) {
		int tspSize = configuration.getChromosomeSize();
		Chromosome newChromo = new Chromosome(configuration);
		newChromo.fillRandomGenes(configuration.getRandomGenerator());
		newChromo.setGenes(ogChromo.getGenes());
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

	private IChromosome xToy(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		newChromo.fillRandomGenes(configuration.getRandomGenerator());
		newChromo.setGenes(ogChromo.getGenes());
		Vector<Integer> nsLout = new Vector<Integer>();
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
	
	private IChromosome yTox(FindNewEdge edge, IChromosome ogChromo) {
		Chromosome newChromo = new Chromosome(configuration);
		newChromo.fillRandomGenes(configuration.getRandomGenerator());
		newChromo.setGenes(ogChromo.getGenes());
		Vector<Integer> nsRout = new Vector<Integer>();
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
	
	
	
	
	
	
	
	public Configuration getConfiguration(){ return configuration; }
}




