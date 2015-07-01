

import java.io.Serializable;

public class ColLogEntity implements Serializable {

	private static final long serialVersionUID = 2591413821892163143L;

	private String sumUrlString;
	private int sumResponseCount;
	private int sumOutSizeCount;
	private String sumMillsecondCount;

	public ColLogEntity(String sumUrlString, int sumResponseCount,
			int sumOutSizeCount, String sumMillsecondCount) {
		this.sumUrlString = sumUrlString;
		this.sumResponseCount = sumResponseCount;
		this.sumOutSizeCount = sumOutSizeCount;
		this.sumMillsecondCount = sumMillsecondCount;
	}

	public String getSumUrlString() {
		return sumUrlString;
	}

	public void setSumUrlString(String sumUrlString) {
		this.sumUrlString = sumUrlString;
	}

	public int getSumResponseCount() {
		return sumResponseCount;
	}

	public void setSumResponseCount(int sumResponseCount) {
		this.sumResponseCount = sumResponseCount;
	}

	public int getSumOutSizeCount() {
		return sumOutSizeCount;
	}

	public void setSumOutSizeCount(int sumOutSizeCount) {
		this.sumOutSizeCount = sumOutSizeCount;
	}

	public String getSumMillsecondCount() {
		return sumMillsecondCount;
	}

	public void setSumMillsecondCount(String sumMillsecondCount) {
		this.sumMillsecondCount = sumMillsecondCount;
	}

}
