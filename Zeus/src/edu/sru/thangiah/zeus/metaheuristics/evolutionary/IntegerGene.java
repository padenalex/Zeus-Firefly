package edu.sru.thangiah.zeus.metaheuristics.evolutionary;

public class IntegerGene extends AbstractGene
{
	int max;
	/*public IntegerGene (final Configuration currentConfiguration, int maximum)
	{
		super (currentConfiguration);
		max = maximum;
	}*/
	
	public IntegerGene (final Configuration currentConfiguration, final int currentValue, int maximum)
	{
		super(currentConfiguration);
		setValue(currentValue);
		max = maximum;
	}
	
	public int mutate()
	{
		int i = (int) getInternalValue();
		RandomGenerator gen = new RandomGenerator();
		while (i == (int) getInternalValue())
		{
			setValue(gen.nextInt(max));
		}
		return (int) getInternalValue();
	}
	
	@Override
	protected IGene newGeneInternal() {
		return new IntegerGene(getConfiguration(), -1, max);
	}
	
	@Override
	public Object getInternalValue() {
		return (int) value;
	}
	
	public void setToRandomValue(RandomGenerator numberGenerator) 
	{
		int random = numberGenerator.nextInt(max);
		setValue(random);
	}
}