package features;

import mapper.SentenceMapper;
import source.Sentence;

public class RelevanceFeature implements FeatureCompute{

	private SentenceMapper sentenceMapper;
	
	@Override
	public float[] compute(Sentence sentence) {
		float firstRelDoc = 0;
		int sentenceId = sentence.getSentenceId();
		if(sentenceId == 1){//feature 1
			firstRelDoc =1;
		}else{
			Sentence firstSentence = sentenceMapper.getFirstSentence();
			firstRelDoc = computeRel(sentence, firstSentence);
		}
		
		float firstRelPara = 0; //feature2
		
		float pageRankRel = computePageRank(sentence);
		
		float[] relevanceFeature = {firstRelDoc, firstRelPara, pageRankRel};
		
		return relevanceFeature;
	}
	
	private float computeRel(Sentence sentence, Sentence otherSentence){
		return 0;
	}
	
	private float computePageRank(Sentence sentence){
		
		
		
		return 0;
	}

}
