

import java.io.Serializable;

public class TempLogEntity implements Serializable {

	private static final long serialVersionUID = -6328341516024152095L;

	private String url = null;
	private String millsec = null;
	private String size = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMillsec() {
		return millsec;
	}

	public void setMillsec(String millsec) {
		this.millsec = millsec;
	}

}
