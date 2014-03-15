package model;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
	
	private int id;//段落id
	
	private List<Sentence> sentences;//本段内的句子
	
	private String content;//本段内容
	
	public Paragraph(){
		
	}
	public Paragraph(String content){
		this.content = content;
	}
	public void setSentences(List<Sentence> sentences){
		if(this.sentences==null){
			this.sentences = new ArrayList<Sentence>();
		}
		this.sentences = sentences;
	}
	
	public int getSentencesCount(){
		return sentences.size();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Sentence> getSentences() {
		return sentences;
	}
}