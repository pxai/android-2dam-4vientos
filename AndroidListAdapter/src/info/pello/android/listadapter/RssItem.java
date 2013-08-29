package info.pello.android.listadapter;

/**
 * represents an item from a Rss source
 * @author Pello Xabier Altadill Izura
 * @greetz to all the fresh news 
 */
public class RssItem {
	private String title;
	private String text;
	private String url;
	private String imageUrl;
	private String rssDate;
	
	/**
	 * default constructor
	 */
	public RssItem () {
		
	}
	
	/**
	 * constructor with minimal params
	 * @param title
	 * @param text
	 * @param url
	 */
	public RssItem (String title, String text, String url) {
		this.title = title;
		this.text = text;
		this.url = url;
		
	}
	/**
	 * @param title
	 * @param text
	 * @param url
	 * @param imageUrl
	 * @param rssDate
	 */
	public RssItem(String title, String text, String url, String imageUrl,
			String rssDate) {
		this.title = title;
		this.text = text;
		this.url = url;
		this.imageUrl = imageUrl;
		this.rssDate = rssDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RssItem [" + (title != null ? "title=" + title + ", " : "")
				+ (text != null ? "text=" + text + ", " : "")
				+ (url != null ? "url=" + url + ", " : "")
				+ (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "")
				+ (rssDate != null ? "rssDate=" + rssDate : "") + "]";
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the rssDate
	 */
	public String getRssDate() {
		return rssDate;
	}
	/**
	 * @param rssDate the rssDate to set
	 */
	public void setRssDate(String rssDate) {
		this.rssDate = rssDate;
	}
	
}
