//package mapper;
//
//import java.util.List;
//
//import model.Sentence;
//
//import org.apache.ibatis.annotations.Param;
//
//
//public interface SentenceMapper {
//
//	
//	public Sentence getSentenceById(int id);
//	
//	public Sentence getSentenceBySentenceIdAndDocName(@Param("sentenceId") int sentenceId, @Param("docName") String docName);
//	
//	public List<Sentence> getSentencesByDocumentName(String docName);
//	
//	public List<Sentence> getSentencesByEventName(String eventName);
//	
//	public List<Sentence> getAllSentences();
//
//}
