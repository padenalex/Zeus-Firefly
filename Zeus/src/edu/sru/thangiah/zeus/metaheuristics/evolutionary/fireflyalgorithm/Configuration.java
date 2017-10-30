package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

//import antlr.collections.List;

public class Configuration
{
	
	private int populationSize;
	private int fireflySize;
	private int numNoderations;
	private double mutationProbability;
	private double selectionProbability;
	private double crossoverProbability;
	private boolean isLocked;
	private IFirefly sampleFirefly;
	private AbstractCostFunction costFunction;
	private LinkedList<INatureOperator> natureOperators;
	private LinkedList<ISelector> selectionOperators;
	private LinkedList<INode> sampleNodes;
	private String encoding;
	private RandomGenerator randomGenerator;
	private ISampleNodeChooser sampleNodeChooser;
	private int currentNoderationNum;
	
	
	public void setPopulationSize(int populationSize){ this.populationSize = populationSize; } 
	public void setFireflySize(int fireflySize){ this.fireflySize = fireflySize; }
	public void setNumNoderations(int numNoderations){ this.numNoderations = numNoderations; }
	
	public void setMutationProbability(double mutationProbability){ this.mutationProbability = mutationProbability; }
	public void setSelectionProbability(double selectionProbability){ this.selectionProbability = selectionProbability; }
	public void setCrossoverProbability(double crossoverProbability){ this.crossoverProbability = crossoverProbability; }
	
	public void setEncoding(String encoding){ this.encoding = encoding;}
	public void setSampleFirefly(IFirefly sample){ this.sampleFirefly = sample; }
	public void setCostFunction(AbstractCostFunction function){ this.costFunction = function; }
	public void setNatureOperators(LinkedList<INatureOperator> natureOps){ this.natureOperators = natureOps; }
	public void addNatureOperator(INatureOperator natureOp){ this.natureOperators.add(natureOp); }
	public void setSampleNodes(LinkedList<INode> sampleNodes){ this.sampleNodes = sampleNodes; }
	
	public void setSelectionOperators(LinkedList<ISelector> selectors){ this.selectionOperators = selectors; }
	public void addSelectionOperator(ISelector selector){ this.selectionOperators.add(selector);}
	
	
	public void lockSettings(){ if(isLocked == false) isLocked = true; }
	public void setRandomGenerator(RandomGenerator random){ randomGenerator = random; }
	public void setSampleNodeChooser(ISampleNodeChooser node_chooser){sampleNodeChooser = node_chooser;}
	public void setCurrentNoderationNum(int currentNoderationNum){ currentNoderationNum = currentNoderationNum; }
	public void incrementNoderationNum(){ currentNoderationNum++; }
	
	
	public int getPopulationSize(){ return populationSize; }
	public int getFireflySize(){ return fireflySize; }
	public int getNumNoderations(){ return numNoderations; }
	
	public double getMutationProbability(){ return mutationProbability; }
	public double getSelectionProbability(){ return selectionProbability; }
	public double getCrossoverProbability(){ return crossoverProbability; }
	
	public String getEncoding(){ return encoding; }
	public IFirefly getSampleFirefly(){ return sampleFirefly; }
	public AbstractCostFunction getCostFunction(){ return costFunction; }
	public LinkedList<INatureOperator> getNatureOperators(){ return natureOperators; }
	public LinkedList<INode> getSampleNodes(){return sampleNodes;}
	public LinkedList<ISelector> getSelectors(){return selectionOperators;}
	
	public boolean isLocked(){ return isLocked; }
	public RandomGenerator getRandomGenerator(){ return randomGenerator; }
	public ISampleNodeChooser getSampleNodeChooser(){return sampleNodeChooser;}
	public int getCurrentNoderationNum(){ return currentNoderationNum; }
		

}