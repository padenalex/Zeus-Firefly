package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

public class IntegerGene extends AbstractGene
{
	int max;
	public IntegerGene (final Configuration currentConfiguration, int maximum)
	{
		super (currentConfiguration);
		max = maximum;
	}
	
	public IntegerGene (final Configuration currentConfiguration, final int currentValue, int maximum)
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
	protected IGene newGeneInternal() {
		return new IntegerGene(getConfiguration(), max);
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
