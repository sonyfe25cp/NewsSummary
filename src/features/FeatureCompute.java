package features;

import source.Sentence;

public interface FeatureCompute {

	public double[] compute(Sentence sentence);
	
}
