package service.tac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.tac.TacSentence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tac.Evaluation;
import Jama.Matrix;

public class AMLMain {
	static Logger logger = LoggerFactory.getLogger(AMLMain.class);

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		String eventName = "D1001A";
//		AMLMain amm = new AMLMain();
//		amm.runAML(eventName);
//	}
	
//	public static void main(String[] args) {
//		AMLMain amm = new AMLMain();
//		amm.runForTuningPara();
//	}
	public static void main(String[] args) {
		AMLMain amm = new AMLMain();
		amm.runAll();
	}
	private void runAll(){
		List<String> events = sentenceService.findEvents();
		List<Evaluation> evas = new ArrayList<>();
		int errorCount = 0;
		for(String event : events){
			Evaluation eva = runAML(event);
			if(eva != null){
				evas.add(eva);
			}else{
				logger.error("event : {} 没有标注结果", event);
				errorCount++;
			}
		}
		logger.info("公布最后的结果: 数据总共 {} 个， 无效数据为{}个", events.size(), errorCount);
		double recallTotal = 0;
		double preTotal = 0;
		double fTotal = 0;
		for(Evaluation eva : evas){
			logger.info(eva.toString());
			recallTotal += eva.getRecall();
			preTotal += eva.getPrecise();
			fTotal += eva.getfValue();
		}
		logger.info("平均值：recall: {}, precise: {}", (double)recallTotal/evas.size(), (double) preTotal/evas.size());
		logger.info("fValue : {}", (double) fTotal/evas.size());
	}
	
	/**
	 * 调参数用 
	 */
	int rightCount = 0;
	private void runForTuningPara(){
		List<String> events = sentenceService.findEvents();
		int max = 0;
		int runs = 0;
		for(double alpha = 0.001, beta = 0.001; alpha < 1.5 && beta < 1.5; alpha=alpha+0.005, beta = beta+0.005){
			rightCount = 0;
			for(String event : events){
				sentenceService.setAlpha(alpha);
				sentenceService.setBeta(beta);
				runAML(event);
			}
			if(max > rightCount){
				logger.error("alpha : {}, beta : {}", alpha, beta);
				logger.error("rightCount : ", rightCount);
			}
			runs++;
			if(runs % 5 ==0){
				logger.error("runs: {}", runs);
			}
		}
	}
	
	private TacSentenceService sentenceService = new TacSentenceService();
	
	private Evaluation runAML(String eventName){
		int top = 1;
		logger.info("事件名称 {}, 预计每天选择 {} 个摘要句", eventName, top);
		List<TacSentence> sentencesSameEvent = sentenceService.getSentencesSameEvent(eventName);//同一个事件的句子
		logger.info("该事件有 {} 个句子", sentencesSameEvent.size());
		Map<String, Double> wordsIDF = sentenceService.compuateLocalIDFFromSentences(sentencesSameEvent);//该事件内的idf
		logger.info("该事件有 {} 个词", wordsIDF.size());
		Map<String, List<TacSentence>> sentenceSameDay = sentenceService.groupSentenceByDate(sentencesSameEvent);//同一个事件的句子按照时间分组
		logger.info("该事件的时间宽度为 {} 天",sentenceSameDay.size());
		int i = 0;
		Map<String, Double> energyLast = new HashMap<>();
		List<TacSentence> summarySentencesLast = new LinkedList<>();
		List<TacSentence> summaryThisEvent = new LinkedList<>();
		for(Entry<String, List<TacSentence>> entry : sentenceSameDay.entrySet()){
			String date = entry.getKey();
			List<TacSentence> sentences = entry.getValue();
			logger.info("开始计算 {} 的摘要, 句子数: {}", date, sentences.size());
			CountMapper cm = new CountMapper();
			Matrix sentenceMatrix = sentenceService.constructLSAMatrix(sentences, wordsIDF, cm);//构造当天的矩阵
			Map<String, Double> energy = sentenceService.computeEnergeTody(sentences, wordsIDF.keySet()); //计算当天句子中所有词的营养值
//			for(Entry<String, Double> e : energy.entrySet()){
//				logger.info("{} 能量值: {}", e.getKey(), e.getValue());
//				logger.info("{} IDF: {}", e.getKey(), wordsIDF.get(e.getKey()));
//			}
			Matrix wordsWeightCurrent = sentenceService.merge(energy, sentenceMatrix, cm);//把能量值与lsa结果合并得到词的权重
			Matrix wordsWeightToday = null;
			if(i == 0){//第一天
				wordsWeightToday = wordsWeightCurrent;
			}else{//后续
				wordsWeightToday = sentenceService.plusEnergyLast(wordsWeightCurrent, energyLast, cm);//先把昨天的能量做衰减，然后加到今天的上面
			}
			sentenceService.mergeEnergy(energyLast, energy);//把衰减后的昨天的能量与今天新的能量加和
			
			sentenceService.computeAgingFeature(wordsWeightToday, sentences);
			//按照大小排序
			Collections.sort(sentences, new Comparator<TacSentence>(){
				@Override
				public int compare(TacSentence o1, TacSentence o2) {
					if(o2.getAging() > o1.getAging()){
						return 1;
					}else{
						return -1;
					}
				}
			});
//			for(TacSentence sentence : sentences){
//				logger.info("句子编号：{}， aging值：{}", sentence.getId(), sentence.getAging());
//				logger.info("句子：{}， aging值：{}", sentence.getContent(), sentence.getAging());
//			}
			//如果只看aging部分的摘要选择
			List<TacSentence> summarySentencesToday = new LinkedList<>();
			top = top > sentences.size() ? sentences.size() : top;
			for(int tmp = 0; tmp < top; tmp++){
				TacSentence summary = sentences.get(tmp);
				logger.info("句子：{}， aging值：{}", summary.getContent(), summary.getAging());
				if(summary.isSummary()){
					logger.info("*********该句子是标注的摘要句!*********");
					rightCount ++;
				}
				summarySentencesToday.add(summary);
			}
			summaryThisEvent.addAll(summarySentencesToday);
			//得到今天所有句子的特征
			
			sentenceService.computeSurfaceFeatures(sentences);
			
			sentenceService.computeNoveltyFeatures(sentences, summarySentencesLast);//计算今天的句子与昨天的摘要的距离
			summarySentencesLast = summarySentencesToday;//把摘要延续到下一天
			
			sentenceService.computeImportanceFeatures(sentences, wordsIDF);
			
			String output = "/tmp/features.txt";
			writeFeaturesToFile(sentences, output);
			
			writerSMOTEFeaturesToFile(sentences);
			
			
			//加入到svm进行训练
			//得到model
			
		}
		return Evaluation.evaluate(sentencesSameEvent, summaryThisEvent);
	}
	
	private void writerSMOTEFeaturesToFile(List<TacSentence> sentences){
		String output = "/tmp/features-s.txt";
		List<TacSentence> positive = new ArrayList<>();
		List<TacSentence> negtive = new ArrayList<>();
		for(TacSentence sentence : sentences){
			if(sentence.isSummary()){
				positive.add(sentence);
			}else{
				negtive.add(sentence);
			}
		}
		
		for(int run = 0; run < positive.size(); run = run + 2){
			TacSentence newSentence = new TacSentence(sentences.get(run), sentences.get(run+1));
			sentences.add(newSentence);
		}
		writeFeaturesToFile(sentences, output);
	}
	
	private void writeFeaturesToFile(List<TacSentence> sentences, String output){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(output), true));
			for(TacSentence sentence : sentences){
				StringBuilder sb = new StringBuilder();
				sb.append(sentence.isSummary() ? 1 : -1);
				sb.append(" ");
				sb.append("1:");
				sb.append(sentence.getAging());
				sb.append(" ");
				sb.append("2:");
				sb.append(sentence.getImportance());
				sb.append(" ");
				sb.append("3:");
				sb.append(sentence.getNovelty());
				sb.append(" ");
				sb.append("4:");
				sb.append(sentence.getLength());
				sb.append(" ");
				sb.append("5:");
				sb.append(sentence.getSentenceId());
				String line = sb.toString();
				bw.write(line);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	class CountMapper {
		private Map<String, Integer> countMap = new HashMap<String, Integer>();;
		int num = -1;

		public int getSeqNumber(String str) {// 返回从0开始
			if (countMap.containsKey(str)) {
				return countMap.get(str);
			} else {
				num++;
				countMap.put(str, num);
			}
			return num;
		}
	}
	
}
