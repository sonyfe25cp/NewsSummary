package utils;

import java.util.ArrayList;
import java.util.List;

import model.Document;
import model.Paragraph;
import model.Sentence;

public class SentenceSegment {
	
	/**
	 * 把文档切成句子
	 * 	1. 切成段落
	 * 	2. 从段落中切句子，并更新句子数
	 * @param document
	 * @return
	 */
	public List<Sentence> segment(Document document){
		String body = document.getBody();
		String[] originParas = body.split("\r");
		int count = 0;//记录全文句子数
		List<Sentence> sentencesAll = new ArrayList<Sentence>();
		for(String para : originParas){
			Paragraph paragraph = new Paragraph(para);
			List<Sentence> sentences = segmentFromParagraph(para, count);
			int sentenceCount = paragraph.getSentencesCount();
			count += sentenceCount;
			paragraph.setSentences(sentences);
			document.putParagraph(paragraph);
			sentencesAll.addAll(sentences);
		}
		return sentencesAll;
	}
	
	
	
	/**
	 * 从段落中切句子
	 * @param paragraph
	 * @param count 该段句子起始数
	 * @return
	 */
	public List<Sentence> segmentFromParagraph(String paragraph, int count){
		int j = 0;//标示段落长度
		int k = 0;
		String s1;//表示当前字符
		String s2;//表示句子
		List<Sentence> sentences = new ArrayList<Sentence>();
		int sentenceCount = 0;
		while(j<paragraph.length()){
			s1=String.valueOf(paragraph.charAt(j));
			if(s1.equals("…"))
				j=j+1;//因为……是由两个…组成的，一次只能得到一个…，所以要多做一步处理
			if(s1.equals("。")||s1.equals("？")||s1.equals("！")||s1.equals("…")){
				if((j+1)<paragraph.length()&&String.valueOf(paragraph.charAt(j+1)).equals("”")){
					j=j+1;
				}
				s2=new String(paragraph.substring(k, j+1));//new sentence
				Sentence sentence = new Sentence(s2, sentenceCount + count, sentenceCount);
				sentences.add(sentence);
				k=j+1;
			}
			j=j+1;
		}
		return sentences;
	}

}
