package service.tac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Jama.Matrix;

import mapper.tac.TacSentenceMapper;
import model.Sentence;
import model.tac.TacDocument;
import model.tac.TacSentence;

public class TacSentenceService {

	private TacSentenceMapper sentenceMapper;

	/**
	 * 计算这一组文档中词的IDF,以句子为单位
	 * 
	 * @param documents
	 * @return
	 */
	private Map<String, Double> compuateLocalIDF(List<TacDocument> documents) {
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
				for (String tmp : wordsSet) {
					Integer c = docCount.get(tmp);
					if (c == null) {
						c = 1;
					} else {
						c++;
					}
					docCount.put(tmp, c);
				}
			}
		}
		for (Entry<String, Integer> entry : docCount.entrySet()) {
			String word = entry.getKey();
			int containsCount = entry.getValue();
			double idf = Math.log(documents.size() / containsCount);
			idfMap.put(word, idf);
		}
		return idfMap;
	}

	/**
	 * 计算这一组文档中词的IDF,以句子为单位
	 * 
	 * @param documents
	 * @return
	 */
	public Map<String, Double> compuateLocalIDFFromSentences(List<TacSentence> sentences) {
		Map<String, Double> idfMap = new HashMap<>();
		Map<String, Integer> docCount = new HashMap<>();
		for (TacSentence sentence : sentences) {
			Set<String> wordsSet = new HashSet<>();
			Map<String, Integer> tf = sentence.getTF();
			for (Entry<String, Integer> entry : tf.entrySet()) {
				String word = entry.getKey();
				wordsSet.add(word);
			}
			for (String tmp : wordsSet) {
				Integer c = docCount.get(tmp);
				if (c == null) {
					c = 1;
				} else {
					c++;
				}
				docCount.put(tmp, c);
			}
		}
		for (Entry<String, Integer> entry : docCount.entrySet()) {
			String word = entry.getKey();
			int containsCount = entry.getValue();
			double idf = Math.log(sentences.size() / containsCount);
			idfMap.put(word, idf);
		}
		return idfMap;
	}

	public List<TacSentence> getSentencesSameEvent(String eventName) {
		return null;
	}

	public Map<String, List<Sentence>> groupSentenceByDate(
			List<TacSentence> sentencesSameEvent) {
		return null;
	}

	public Matrix constructMatrix(List<Sentence> sentences) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getEventNames(){
		return null;
	}

	public Map<String, Double> computeEnergeTody(List<Sentence> sentences) {
		// TODO Auto-generated method stub
		return null;
	}

}
