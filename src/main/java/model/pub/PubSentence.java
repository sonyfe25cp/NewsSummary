package model.pub;

import model.Sentence;

public class PubSentence extends Sentence{

	private double aging;
	private double importance;
	private double novelty;
	
	//surfaceFeature 长度，句子id
	private double length;
	
	public PubSentence(PubSentence pubSentence, PubSentence pubSentence2) {
		PubSentence newSentence = new PubSentence();
		newSentence.setAging((pubSentence.getAging() + pubSentence2.getAging() )/2);
		newSentence.setImportance((pubSentence.getImportance() + pubSentence2.getImportance() )/2);
		newSentence.setNovelty((pubSentence.getNovelty() + pubSentence2.getNovelty() )/2);
		newSentence.setLength((pubSentence.getLength() + pubSentence2.getLength() )/2);
		newSentence.setSentenceId((pubSentence.getSentenceId() + pubSentence2.getSentenceId() )/2);
	}

	public PubSentence() {
		super();
	}

	@Override
	public String toString() {
		return "PubSentence ["+super.toString()+"]";
	}

	public double getAging() {
		return aging;
	}

	public void setAging(double aging) {
		this.aging = aging;
	}

	public double getImportance() {
		return importance;
	}

	public void setImportance(double importance) {
		this.importance = importance;
	}

	public double getNovelty() {
		return novelty;
	}

	public void setNovelty(double novelty) {
		this.novelty = novelty;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	

}
