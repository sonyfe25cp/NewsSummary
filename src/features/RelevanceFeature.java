package features;

import java.util.HashSet;

import service.SentenceService;
import source.Sentence;
import utils.TokenizerUtils;

public class RelevanceFeature implements FeatureCompute{

	private SentenceService sentenceService;
	@Override
	public double[] compute(Object d) {
		Sentence sentence = (Sentence) d;
		double firstRelDoc = 0;
		int sentenceId = sentence.getSentenceId();
		if(sentenceId == 1){//feature 1
			firstRelDoc =1;
		}else{
			Sentence firstSentence = sentenceService.getFirstSenceOfDoc(sentence.getDocName());
			
			firstRelDoc = computeRel(sentence, firstSentence);
		}
		
		double firstRelPara = 0; //feature2
		
		double pageRankRel = computePageRank(sentence);
		
		double[] relevanceFeature = {firstRelDoc, firstRelPara, pageRankRel};
		
		return relevanceFeature;
	}
	
	private double computeRel(Sentence sentence, Sentence otherSentence){
		
		String[] tokens1 = TokenizerUtils.tokenizer(sentence.getSentenceContent());
		String[] tokens2 = TokenizerUtils.tokenizer(otherSentence.getSentenceContent());
		
		HashSet<String> set = new HashSet<String>();
		for(String token: tokens1){
			set.add(token);
		}
		for(String token: tokens2){
			set.add(token);
		}

		int[] v1 = computeVector(set, tokens1);
		int[] v2 = computeVector(set, tokens2);
		
		double rel = cos(v1, v2);
		
		return rel;
	}
	private double cos(int[] v1, int[] v2){
		double mult = 0;
		for(int i = 0 ; i < v1.length ; i++){
			double tmp = v1[i] * v2[i];
			mult+=tmp;
		}
		double lv1 = computeL1(v1);
		double lv2 = computeL1(v2);
		
		double cos = mult / lv1*lv2;
		return cos;
	}
	private double computeL1(int[] v1){
		double squre = 0;
		for(int v : v1){
			double tmp = v*v;
			squre += tmp;
		}
		return Math.sqrt(squre);
	}

	private int[] computeVector(HashSet<String> set, String[] array){
		int[] vector = new int[set.size()];
		int i = 0;
		for(String token : set){
			if(contains(array, token)){
				vector[i] = 1;
			}else{
				vector[i] = 0;
			}
			i++;
		}
		return vector;
	}
	
	private boolean contains(String[] array, String word){
		for(String token: array){
			if(word.equals(token)){
				return true;
			}
		}
		return false;
	}
	
	private double computePageRank(Sentence sentence){
		
		return 0;
	}

	public SentenceService getSentenceService() {
		return sentenceService;
	}

	public void setSentenceService(SentenceService sentenceService) {
		this.sentenceService = sentenceService;
	}

	

}
