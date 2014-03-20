package model.tac;

import java.util.List;

public class TacDocument {

	private int id;
	private List<TacSentence> sentences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TacSentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<TacSentence> sentences) {
		this.sentences = sentences;
	}

}
