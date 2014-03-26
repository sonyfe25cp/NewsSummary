package tac;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.tac.TacSentence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Evaluation {
	private double recall;
	private double precise;
	private double fValue;
	
	static Logger logger = LoggerFactory.getLogger(Evaluation.class);
	public static Evaluation evaluate(List<TacSentence> sentences,
			List<TacSentence> summaries) {
		Set<Integer> manualLabel = new HashSet<>();
		for (TacSentence sentence : sentences) {
			if (sentence.isSummary()) {
				manualLabel.add(sentence.getId());
			}
		}
		if(manualLabel.size() == 0 ){
			logger.error("这个没有标注啊！");
			return null;
		}
		int count = 0;
		for (TacSentence summary : summaries) {
			int id = summary.getId();
			if (manualLabel.contains(id)) {
				count++;
			}
		}
		double recall = (double) count / manualLabel.size();
		double precise = (double) count / summaries.size();
		double fValue = (double) (2 * recall * precise) / (recall + precise);
		return new Evaluation(recall, precise, fValue);
	}
	public double getRecall() {
		return recall;
	}
	public void setRecall(double recall) {
		this.recall = recall;
	}
	public double getPrecise() {
		return precise;
	}
	public void setPrecise(double precise) {
		this.precise = precise;
	}
	public double getfValue() {
		return fValue;
	}
	public void setfValue(double fValue) {
		this.fValue = fValue;
	}
	public Evaluation(double recall, double precise, double fValue) {
		super();
		this.recall = recall;
		this.precise = precise;
		this.fValue = fValue;
	}
	@Override
	public String toString() {
		return "Evaluation [recall=" + recall + ", precise=" + precise
				+ ", fValue=" + fValue + "]";
	}

}
