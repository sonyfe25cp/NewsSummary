package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import source.Sentence;

public interface SentenceMapper {

	
	public Sentence getSentenceById(int id);
	
	public Sentence getSentenceBySentenceIdAndDocName(@Param("sentenceId") int sentenceId, @Param("docName") String docName);
	
	public List<Sentence> getSentencesByDocumentName(String docName);
	
	public List<Sentence> getSentencesByEventName(String eventName);

}
