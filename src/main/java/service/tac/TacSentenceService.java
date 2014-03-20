package service.tac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.tac.TacDocument;
import model.tac.TacSentence;

public class TacSentenceService {

	/**
	 * 计算这一组文档中词的IDF,以句子为单位
	 * @param documents
	 * @return
	 */
	public Map<String, Double> compuateLocalIDF(List<TacDocument> documents) {
		Map<String, Double> idfMap = new HashMap<>();
		Map<String, Integer> docCount = new HashMap<>();
		for (TacDocument document : documents) {
			List<TacSentence> sentences = document.getSentences();
			for (TacSentence sentence : sentences) {
				Set<String> wordsSet = new HashSet<>();
				Map<String, Integer> tf = sentence.getTF();
				for (Entry<String, Integer> entry : tf.entrySet()) {
					String word = entry.getKey();
					wordsSet.add(word);
				}
				for(String tmp : wordsSet){
					Integer c = docCount.get(tmp);
					if(c == null){
						c = 1;
					}else{
						c ++;
					}
					docCount.put(tmp, c);
				}
			}
		}
		for(Entry<String, Integer> entry : docCount.entrySet()){
			String word = entry.getKey();
			int containsCount = entry.getValue();
			double idf = Math.log(documents.size()/containsCount);
			idfMap.put(word, idf);
		}
		return idfMap;
	}

}
