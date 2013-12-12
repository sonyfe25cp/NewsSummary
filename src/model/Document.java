package model;

import java.util.List;
import java.util.Map;

public class Document {

	private int id;
	private String body;//原文
	private List<Sentence> sentences;//原文所有的句子
	private int totalWords;//总词数
	private Map<String, Integer> termsFrequencyMap;//词频信息
	
	

	
	
	
	/**
	 * 返回该文档的句子总数
	 * @return
	 */
	public int getSentenceCount(){
		if(sentences != null){
			return sentences.size();
		}else{
			return 0;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Sentence> getSentences() {
		return sentences;
	}
	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}
	public int getTotalWords() {
		return totalWords;
	}
	public void setTotalWords(int totalWords) {
		this.totalWords = totalWords;
	}
	public Map<String, Integer> getTermsFrequencyMap() {
		return termsFrequencyMap;
	}
	public void setTermsFrequencyMap(Map<String, Integer> termsFrequencyMap) {
		this.termsFrequencyMap = termsFrequencyMap;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
