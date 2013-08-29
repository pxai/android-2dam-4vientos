package info.pello.android.service;

public class RssData {
	private String title;
	private String content;
	private String url;
	
	public RssData () {
		
	}
		
	/**
	 * @param title
	 * @param content
	 * @param url
	 */
	public RssData(String title, String content, String url) {
		this.title = title;
		this.content = content;
		this.url = url;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
