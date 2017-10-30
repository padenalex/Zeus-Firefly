package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

public class IntegerNode extends AbstractNode
{
	int max;
	public IntegerNode (final Configuration currentConfiguration, int maximum)
	{
		super (currentConfiguration);
		max = maximum;
	}
	
	public IntegerNode (final Configuration currentConfiguration, final int currentValue, int maximum)
	{
		super(currentConfiguration);
		setValue(currentValue);
		max = maximum;
	}
	
	public void mutate()
	{
		RandomGenerator gen = new RandomGenerator();
		setValue(gen.nextInt(max));
	}
	
	@Override
	protected INode newNodeInternal() {
		return new IntegerNode(getConfiguration(), max);
	}
	
	@Override
	public Object getInternalValue() {
		return (int)value;
	}
	
	public void setToRandomValue(RandomGenerator numberGenerator) 
	{
		int random = numberGenerator.nextInt(max);
		setValue(random);
	}
}
