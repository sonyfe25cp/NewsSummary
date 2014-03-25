package mapper.tac;

import java.util.List;

import model.tac.TacSentence;

import org.apache.ibatis.annotations.Param;

public interface TacSentenceMapper {

	public List<TacSentence> findByEventname(String eventName);
	
	public List<TacSentence> findByDocName(String docName);
	
	public TacSentence findById(int id);
	
	public List<TacSentence> findByEventNameAndIsSummary(@Param("eventName")String eventName, @Param("isSummary") boolean isSummary);

	public List<String> findEvents();
	
	public List<String> findDocumentsByEventname(String eventName);
	
	public List<TacSentence> findAllSentences();

	public void update(TacSentence sentence);

}
