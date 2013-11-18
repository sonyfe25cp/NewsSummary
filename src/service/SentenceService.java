package service;

import java.util.List;

import mapper.SentenceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import source.Sentence;

@Service
public class SentenceService{

	@Autowired
	private SentenceMapper sentenceMapper;
	
	public Sentence getFirstSenceOfDoc(String docName){
		Sentence s = sentenceMapper.getSentenceBySentenceIdAndDocName(1, docName);
		return s;
	}
	
	public List<Sentence> getAllSentences(){
		return sentenceMapper.getAllSentences();
	}
	
	
	public SentenceMapper getSentenceMapper() {
		return sentenceMapper;
	}
	public void setSentenceMapper(SentenceMapper sentenceMapper) {
		this.sentenceMapper = sentenceMapper;
	}
}
