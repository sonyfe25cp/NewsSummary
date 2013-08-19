package source;

public class Sentence {


	private int id;
	private int sentenceId;//同一个doc内句子id
	private String sentenceContent;//句子内容
	private String docName;//doc名
	private String eventName;//事件名
	private String isSummary;//是否是摘要
	private int total;//该doc内的句子数量
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("sentenceId:"+sentenceId);
		sb.append("\n");
		sb.append("docName:"+docName);
		sb.append("\n");
		sb.append("content:"+sentenceContent);
		sb.append("\n");
		sb.append("isSummary:"+isSummary);
		sb.append("\n");
		return sb.toString();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSentenceId() {
		return sentenceId;
	}
	public void setSentenceId(int sentenceId) {
		this.sentenceId = sentenceId;
	}
	public String getSentenceContent() {
		return sentenceContent;
	}
	public void setSentenceContent(String sentenceContent) {
		this.sentenceContent = sentenceContent;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
