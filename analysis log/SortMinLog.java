

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortMinLog extends SortLog {

	List<LogEntity> listLogEntity = new ArrayList<LogEntity>();

	protected void formatData(String one, String two) {
		HashMap<Integer, LogEntity> hm = readFileByLines(one);
		System.out.println("Total line number : " + hm.size());

		int totalOutSize = 0;
		BigDecimal totalmillSeconds = BigDecimal.ZERO;

		for (int i = 1; i <= hm.size(); i++) {
			if (hm.get(i) != null) {
				String timeString = getMin(((LogEntity) hm.get(i))
						.getTimeString());

				String urlString = getNewURLString(((LogEntity) hm.get(i))
						.getUrlString());
				int outsize = Integer.parseInt(((LogEntity) hm.get(i))
						.getOutSizeString());
				// System.out.println(outsize);
				BigDecimal b1 = new BigDecimal(
						((LogEntity) hm.get(i)).getMillsecondString());

				if (two.equals(timeString)) {
					LogEntity le = null;
					if (urlString.contains("GET") && urlString.contains(".jpg")) {
						le = new LogEntity();
						le.setTimeString(timeString);
						le.setUrlString("\"GET /media/*.jpg HTTP/1.1\"");
						le.setOutSizeString(String.valueOf(outsize));
						le.setMillsecondString(String.valueOf(b1));

						listLogEntity.add(le);

					} else {
						le = (LogEntity) hm.get(i);
						listLogEntity.add(le);

					}
					// System.out.println(outsize);
					totalOutSize += outsize;
					// System.out.println(totalOutSize);
					totalmillSeconds = totalmillSeconds.add(b1);
				}
			}

		}

		System.out.println("Total OutSize : " + totalOutSize);

		System.out.println("Total Seconds : " + totalmillSeconds);
		showTimesEachURL(two);
	}

	private void showTimesEachURL(String two) {
		System.out.println("\n Total times for each url at " + two);

		List<TempLogEntity> listTempLogEntity = new ArrayList<TempLogEntity>();
		List<String> listNewURL = new ArrayList<String>();

		for (int i = 0; i < listLogEntity.size(); i++) {
			String timeString = getMin(((LogEntity) listLogEntity.get(i))
					.getTimeString());
			if (two.equals(timeString)) {
				TempLogEntity tempLogEntity = new TempLogEntity();
				tempLogEntity.setUrl(listLogEntity.get(i).getUrlString());

				tempLogEntity.setSize(listLogEntity.get(i).getOutSizeString());
				tempLogEntity.setMillsec(listLogEntity.get(i)
						.getMillsecondString());
				listNewURL.add(listLogEntity.get(i).getUrlString());
				listTempLogEntity.add(tempLogEntity);
			}
		}
		Set<String> uniqueSet = new HashSet(listNewURL);
		for (String temp : uniqueSet) {

			System.out.println(temp + "  "
					+ Collections.frequency(listNewURL, temp) + "  "
					+ getSize(listTempLogEntity, temp) + "  "
					+ getMillSec(listTempLogEntity, temp));
		}
	}

}