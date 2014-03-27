package mapper.pub;

import java.util.List;

import model.pub.PubSentence;

public interface PubSentenceMapper {

	public void insert(PubSentence sentence);

	public List<PubSentence> findByEventnameNoSummary(String eventName);

	public List<String> findDocumentsByEventname(String eventName);

	public List<PubSentence> findByDocName(String docName);

	public List<PubSentence> findAllSentences();

	public void update(PubSentence sentence);

	public List<String> findEvents();

	public List<PubSentence> findSentencesLabeledEvent(String eventName);
	
}
