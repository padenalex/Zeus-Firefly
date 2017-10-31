package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;

import java.util.LinkedList;


public class Configuration {
	
	private int populationSize;
	private int fireFlySize;
	private int numGenerations;
	private boolean isLocked;
	private FireFly sampleFireFly;
	private AbstractFitnessFunction fitnessFunction;
	//private LinkedList<IGeneticOperator> geneticOperators;
	//private LinkedList<ISelector> selectionOperators;
	//private LinkedList<IGene> sampleGenes;
	//private String encoding;
	//private RandomGenerator randomGenerator;
	//private ISampleGeneChooser sampleGeneChooser;
	private int currentGenerationNum;
	
	
	public void setPopulationSize(int populationSize){ this.populationSize = populationSize; } 
	public void setFireFlySize(int fireFlySize){ this.fireFlySize = fireFlySize; }
	public void setNumGenerations(int numGenerations){ this.numGenerations = numGenerations; }
	
	/*
	 * public void setMutationProbability(double mutationProbability){ this.mutationProbability = mutationProbability; }
	public void setSelectionProbability(double selectionProbability){ this.selectionProbability = selectionProbability; }
	public void setCrossoverProbability(double crossoverProbability){ this.crossoverProbability = crossoverProbability; }
	
	add fire fly operators
	
	*/
	
	//public void setEncoding(String encoding){ this.encoding = encoding;}
	public void setSampleFireFly(FireFly sample){ this.sampleFireFly = sample; }
	//public void setFitnessFunction(AbstractFitnessFunction function){ this.fitnessFunction = function; }
	//public void setGeneticOperators(LinkedList<IGeneticOperator> geneticOps){ this.geneticOperators = geneticOps; }
	//public void addGeneticOperator(IGeneticOperator geneticOp){ this.geneticOperators.add(geneticOp); }
	//public void setSampleGenes(LinkedList<IGene> sampleGenes){ this.sampleGenes = sampleGenes; }
	
	/*
	public void setSelectionOperators(LinkedList<ISelector> selectors){ this.selectionOperators = selectors; }
	public void addSelectionOperator(ISelector selector){ this.selectionOperators.add(selector);}
	
	
	public void lockSettings(){ if(isLocked == false) isLocked = true; }
	public void setRandomGenerator(RandomGenerator random){ randomGenerator = random; }
	public void setSampleGeneChooser(ISampleGeneChooser gene_chooser){sampleGeneChooser = gene_chooser;}
	public void setCurrentGenerationNum(int currentGenerationNum){ currentGenerationNum = currentGenerationNum; }
	public void incrementGenerationNum(){ currentGenerationNum++; }
	*/
	
	public int getPopulationSize(){ return populationSize; }
	public int getFireFlySize(){ return fireFlySize; }
	public int getNumGenerations(){ return numGenerations; }
	
	/*
	public double getMutationProbability(){ return mutationProbability; }
	public double getSelectionProbability(){ return selectionProbability; }
	public double getCrossoverProbability(){ return crossoverProbability; }
	
	public String getEncoding(){ return encoding; }
	public IChromosome getSampleChromosome(){ return sampleChromosome; }
	*/
	public AbstractFitnessFunction getFitnessFunction(){ return fitnessFunction; }
	/*
	public LinkedList<IGeneticOperator> getGeneticOperators(){ return geneticOperators; }
	public LinkedList<IGene> getSampleGenes(){return sampleGenes;}
	public LinkedList<ISelector> getSelectors(){return selectionOperators;}
	
	public boolean isLocked(){ return isLocked; }
	public RandomGenerator getRandomGenerator(){ return randomGenerator; }
	public ISampleGeneChooser getSampleGeneChooser(){return sampleGeneChooser;}
	public int getCurrentGenerationNum(){ return currentGenerationNum; }
	*/
}
