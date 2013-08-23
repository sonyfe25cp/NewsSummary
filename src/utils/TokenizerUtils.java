package utils;

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

public class TokenizerUtils {

	public static String[] tokenizer(String content) {
		InputStream modelIn = null;
		String[] tokens = null;
		try {
			modelIn = new FileInputStream("opennlp/en-token.bin");
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			tokens = tokenizer.tokenize(content);
			
		} catch (FileNotFoundException e1) {
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
	
	public static void tagging(String content){
		InputStream modelIn = null;
		try {
		  modelIn = new FileInputStream("opennlp/en-pos-maxent.bin");
		  POSModel model = new POSModel(modelIn);
		  POSTaggerME tagger = new POSTaggerME(model);
		  String sent[] =  tokenizer(content);
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sentence sentence = new Sentence();
//		sentence.setSentenceContent("The intuition with respect to the importance of a sentence stems from the following observations.");
		sentence.setSentenceContent("I want to buy a iphone with good luck");
//		tokenizer(sentence.getSentenceContent());
		tagging(sentence.getSentenceContent());
	}

}
