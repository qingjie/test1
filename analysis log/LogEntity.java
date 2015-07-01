

import java.io.Serializable;

public class LogEntity implements Serializable {

	private static final long serialVersionUID = -4346038848443039385L;

	private String timeString = null;
	private String urlString = null;
	private String responseString = null;
	private String outSizeString = null;
	private String millsecondString = null;

	public LogEntity(){}

	public LogEntity(String timeString, String urlString,
			String responseString, String outSizeString, String millsecondString) {
		this.timeString = timeString;
		this.urlString = urlString;
		this.responseString = responseString;
		this.outSizeString = outSizeString;
		this.millsecondString = millsecondString;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public String getOutSizeString() {
		return outSizeString;
	}

	public void setOutSizeString(String outSizeString) {
		this.outSizeString = outSizeString;
	}

	public String getMillsecondString() {
		return millsecondString;
	}

	public void setMillsecondString(String millsecondString) {
		this.millsecondString = millsecondString;
	}

}
