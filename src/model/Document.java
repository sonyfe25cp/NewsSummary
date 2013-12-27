package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Document {

	private int id;
	private String body;//原文
	private int totalWords;//总词数
	private List<Sentence> sentences;//原文所有的句子
	private Map<String, Integer> termsFrequencyMap;//词频信息
	private List<Paragraph> paragraphs;
	
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
	
	public void putParagraph(Paragraph paragraph){
		if(paragraphs == null){
			paragraphs = new ArrayList<Paragraph>();
		}
		paragraphs.add(paragraph);
		if(this.sentences==null){
			this.sentences = new ArrayList<Sentence>();
		}
		this.sentences.addAll(paragraph.getSentences());
	}
//	public void putSentences(List<Sentence> sentences){
//		if(this.sentences==null){
//			this.sentences = new ArrayList<Sentence>();
//		}
//		this.sentences.addAll(sentences);
//	}
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
