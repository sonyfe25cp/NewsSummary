package model;

import java.util.Map;

/**
 * Sentence的基本类
 * @author ChenJie
 * @date Mar 27, 2014
 */
public class Sentence {

	private int id;
	private int sentenceId;// 句子id
	private String content;// 句子内容
	private String docName;// 文件名
	private String eventName;// 事件名
	private boolean isSummary;// 是否是摘要
	private String publishDate;// 发布时间
	private int total;
	private Map<String, Integer> TF; // 词

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public boolean isSummary() {
		return isSummary;
	}

	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Map<String, Integer> getTF() {
		return TF;
	}

	public void setTF(Map<String, Integer> tF) {
		TF = tF;
	}

	@Override
	public String toString() {
		return "Sentence [id=" + id + ", sentenceId=" + sentenceId
				+ ", content=" + content + ", docName=" + docName
				+ ", eventName=" + eventName + ", isSummary=" + isSummary
				+ ", publishDate=" + publishDate + ", total=" + total + "]";
	}

}
