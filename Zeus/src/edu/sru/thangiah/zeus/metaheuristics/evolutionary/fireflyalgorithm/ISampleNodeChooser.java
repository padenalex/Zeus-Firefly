package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

/**
 * A SampleNodeChooser is an interface class that helps a firefly figure out what type
 * of node it is for creating new instances of Fireflys
 * 
 * @author Emily Van Horn
 *
 */

public interface ISampleNodeChooser {
	public INode choose(Configuration config);
}
