package source;
/**
 * 用于接收summary服务的json
 * @author coder
 *
 */
public class SummaryResponse {
	
	private int id;//请求方的原ID
	private String summary;//对应的summary
	private boolean flag;//是否可以计算出，若true则有内容，若false则无内容
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
