package features;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import source.Sentence;

/**
 * centroidUni centroidBi sigTermUni sigTermBi freqWordUni freqWordBi
 * 
 * @author coder
 * 
 */
public class ContentFeature implements FeatureCompute {

	@Override
	public double[] compute(Sentence sentence) {
		
		return null;
	}

	

	public static void main(String[] args){

	}
}
