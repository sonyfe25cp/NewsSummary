package features;

import model.Sentence;

/**
 * centroidUni centroidBi sigTermUni sigTermBi freqWordUni freqWordBi
 * 
 * @author coder
 * 
 */
public class ContentFeature implements FeatureCompute<Sentence> {

    @Override
    public double[] compute(Sentence d) {

        return new double[0];
    }

}
