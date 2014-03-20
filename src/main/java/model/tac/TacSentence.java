package model.tac;

import java.util.Map;

/**
 * 
 * @author ChenJie
 * @date Mar 19, 2014
 */
public class TacSentence {

	private int id;
	private int sentenceId;// 句子id
	private String content;// 句子内容
	private String docName;// 文件名
	private String eventName;// 事件名
	private boolean isSummary;// 是否是摘要
	private String publishDate;// 发布时间
	private int total;
	private String headline;
	private Map<String, Integer> TF; //词

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sentence_id
	 */
	public int getSentenceId() {
		return sentenceId;
	}

	/**
	 * @param sentence_id
	 *            the sentence_id to set
	 */
	public void setSentenceId(int sentenceId) {
		this.sentenceId = sentenceId;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * @return the docname
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @param docname
	 *            the docname to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean isSummary() {
		return isSummary;
	}

	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Map<String, Integer> getTF() {
		return TF;
	}

	public void setTF(Map<String, Integer> tF) {
		TF = tF;
	}

}
