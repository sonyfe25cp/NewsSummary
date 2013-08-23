package main;

import java.util.ArrayList;
import java.util.List;

import service.SentenceFeature;
import service.SentenceService;
import source.Sentence;

/**
 * @author ChenJie
 * 
 * 生成svm需要的数据文件格式
 * Aug 23, 2013
 */
public class PrepareSVM {
	public static void main(String[] args) {
		SentenceService ss = new SentenceService();
		SentenceFeature sf = new SentenceFeature();
		List<Sentence> sentences = ss.getAllSentences();
		List<String> vectors = new ArrayList<String>();
		for(Sentence sentence : sentences){
			String vector = sf.toString(sentence);
			System.out.println(vector);
			vectors.add(vector);
		}
		
	}
	

}
