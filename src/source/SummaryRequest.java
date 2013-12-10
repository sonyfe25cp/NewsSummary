package source;

import java.util.List;

/**
 * 请求summary服务的request
 * @author coder
 *
 */
public class SummaryRequest {
	
	private int id;//本地的id
	private List<String> texts;//待摘要的文本集合，可以多个，可以单个
	private String source;//回调地址
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getTexts() {
		return texts;
	}
	public void setTexts(List<String> texts) {
		this.texts = texts;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
