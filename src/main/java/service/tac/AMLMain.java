package service.tac;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.Sentence;
import model.tac.TacSentence;
import service.LSA;
import Jama.Matrix;

public class AMLMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String eventName = "";
		AMLMain amm = new AMLMain();
		amm.runAML(eventName);
	}
	
	private TacSentenceService sentenceService = null;
	
	private void runAML(String eventName){
		List<TacSentence> sentencesSameEvent = sentenceService.getSentencesSameEvent(eventName);//同一个事件的句子
		Map<String, Double> wordsIDF = sentenceService.compuateLocalIDFFromSentences(sentencesSameEvent);//该事件内的idf
		Map<String, List<Sentence>> sentenceSameDay = sentenceService.groupSentenceByDate(sentencesSameEvent);//同一个事件的句子按照时间分组
		int i = 0;
		for(Entry<String, List<Sentence>> entry : sentenceSameDay.entrySet()){
			String date = entry.getKey();
			List<Sentence> sentences = entry.getValue();
			Matrix sentenceMatrix = sentenceService.constructMatrix(sentences);//构造当天的矩阵
			Matrix lsaResult = LSA.svd(sentenceMatrix);
			Map<String, Double> energy = sentenceService.computeEnergeTody(sentences); //计算当天句子中所有词的营养值
			if(i == 0){//第一天
				
			}else{//后续
				
			}
			
			//得到今天所有句子的特征
			//加入到svm进行训练
			//得到model
			
		}
	}
	
	private double computeEnergy(TacSentence sentence){//计算某个句子的能量值
		//1.先算TFIDF
		//2.然后算当天的营养值
		//3.昨天的能量减掉衰减值
		//4.剩余能量+营养值
		return 0;
	}
	
	
}
