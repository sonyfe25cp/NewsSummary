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
	public float[] compute(Sentence sentence) {
		
		return null;
	}

	private String[] tokenizer(Sentence sentence) {
		String content = sentence.getSentenceContent();
		InputStream modelIn = null;
		String[] tokens = null;
		try {
			modelIn = new FileInputStream("opennlp/en-token.bin");
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			tokens = tokenizer.tokenize(content);
			
			for(String token : tokens){
				System.out.println(token);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}
		return tokens;
	}
	
	private void tagging(Sentence sentence){
		InputStream modelIn = null;
		String content = sentence.getSentenceContent();
		try {
		  modelIn = new FileInputStream("opennlp/en-pos-maxent.bin");
		  POSModel model = new POSModel(modelIn);
		  POSTaggerME tagger = new POSTaggerME(model);
		  String sent[] =  tokenizer(sentence);
		  String tags[] = tagger.tag(sent);
		  for(String tag : tags){
				System.out.println(tag);
			}
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}

	public static void main(String[] args){
		Sentence sentence = new Sentence();
//		sentence.setSentenceContent("The intuition with respect to the importance of a sentence stems from the following observations.");
		sentence.setSentenceContent("I want to buy a iphone");
		ContentFeature cf = new ContentFeature();
//		cf.tokenizer(sentence);
		cf.tagging(sentence);
	}
}
