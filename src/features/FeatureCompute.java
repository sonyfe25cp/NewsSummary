package features;

import source.Sentence;

public interface FeatureCompute {

	public float[] compute(Sentence sentence);
	
}
