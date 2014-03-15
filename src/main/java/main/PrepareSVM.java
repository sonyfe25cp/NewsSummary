package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Sentence;

import service.SentenceFeature;
import service.SentenceService;

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
		sf.setSentenceService(ss);
		List<Sentence> sentences = ss.getAllSentences();
		List<String> vectors = new ArrayList<String>();
		int i = 100;
		for(Sentence sentence : sentences){
			String vector = sf.toString(sentence);
			System.out.println(vector);
			vectors.add(vector);
			if(i> 200) break;
			i++;
		}
		String trainFile ="test.data";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(trainFile)));
			for(String vector : vectors){
				bw.write(vector);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
