package service;

import mapper.SentenceMapper;
import source.Sentence;

public class SentenceService extends Service{


	private SentenceMapper sentenceMapper = session.getMapper(SentenceMapper.class) ;
	
	public Sentence getFirstSenceOfDoc(String docName){
		Sentence s = sentenceMapper.getSentenceBySentenceIdAndDocName(1, docName);
		return s;
	}
	public SentenceMapper getSentenceMapper() {
		return sentenceMapper;
	}
	public void setSentenceMapper(SentenceMapper sentenceMapper) {
		this.sentenceMapper = sentenceMapper;
	}
}
