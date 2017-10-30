package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

/**
 * RandomSampleNodeChooser chooses a random sample node from a LinkedList 
 * 
 * @author Emily Van Horn
 *
 */

public class RandomSampleNodeChooser implements ISampleNodeChooser{

	@Override
	public INode choose(Configuration config) {
		LinkedList<INode> sampleNodes = config.getSampleNodes();
		RandomGenerator randomGenerator = config.getRandomGenerator();
		
		return sampleNodes.get(randomGenerator.nextInt() % sampleNodes.size());
	}

}
