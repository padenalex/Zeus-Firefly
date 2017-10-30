package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

public class DefaultConfiguration extends Configuration
{
	public DefaultConfiguration()
	{
		super();
		setPopulationSize(20);
		setNumNoderations(100);
		setMutationProbability(.001);
		setCrossoverProbability(.70);
		lockSettings();
		setRandomGenerator(new RandomGenerator());
		setSampleNodeChooser(new RandomSampleNodeChooser());
		setCurrentNoderationNum(0);
		setNatureOperators(new LinkedList<INatureOperator>());
		setSelectionOperators(new LinkedList<ISelector>());
	}
}