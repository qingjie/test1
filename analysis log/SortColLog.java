

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SortColLog extends SortLog {

	List<LogEntity> listLogEntity = new ArrayList<LogEntity>();
	List<ColLogEntity> listColLogEntity = new ArrayList<ColLogEntity>();

	protected void formatData(String one, String two) {
		HashMap<Integer, LogEntity> hm = readFileByLines(one);
		System.out.println("Total line number : " + hm.size());

		int totalOutSize = 0;
		BigDecimal totalmillSeconds = BigDecimal.ZERO;
		
		for (int i = 1; i <= hm.size(); i++) {
			if (hm.get(i) != null) {
				String timeString = getMin(((LogEntity) hm.get(i))
						.getTimeString());
				// System.out.println(timeString);
				String urlString = getNewURLString(((LogEntity) hm.get(i))
						.getUrlString());
				int outsize = Integer.parseInt(((LogEntity) hm.get(i))
						.getOutSizeString());
				BigDecimal b1 = new BigDecimal(
						((LogEntity) hm.get(i)).getMillsecondString());
				// System.out.println(urlString);
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

				totalOutSize += outsize;
				totalmillSeconds = totalmillSeconds.add(b1);

			}

		}
		System.out.println("Total OutSize : " + totalOutSize);

		System.out.println("Total Seconds : " + totalmillSeconds);
		showTimesEachURL(two);
	}

	private void showTimesEachURL(String two) {
		System.out.println("\n Total times for each url ");

		List<TempLogEntity> listTempLogEntity = new ArrayList<TempLogEntity>();
		List<String> listNewURL = new ArrayList<String>();

		for (int i = 0; i < listLogEntity.size(); i++) {
			TempLogEntity tempLogEntity = new TempLogEntity();
			tempLogEntity.setUrl(listLogEntity.get(i).getUrlString());
			tempLogEntity.setSize(listLogEntity.get(i).getOutSizeString());
			tempLogEntity
					.setMillsec(listLogEntity.get(i).getMillsecondString());
			listNewURL.add(listLogEntity.get(i).getUrlString());
			listTempLogEntity.add(tempLogEntity);

		}
		Set<String> uniqueSet = new HashSet(listNewURL);
		for (String temp : uniqueSet) {

			// System.out.println(temp + ": "
			// + Collections.frequency(listNewURL, temp) + " : "
			// + getSize(listTempLogEntity, temp) + " : "
			// + getMillSec(listTempLogEntity, temp));
			ColLogEntity colLogEntity = new ColLogEntity(temp,
					Collections.frequency(listNewURL, temp), getSize(
							listTempLogEntity, temp), getMillSec(
							listTempLogEntity, temp));

			listColLogEntity.add(colLogEntity);
		}
		sortTimes(two, listColLogEntity);
	}

	private void sortTimes(String two, List<ColLogEntity> listColLogEntity) {
		if (listColLogEntity != null) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < listColLogEntity.size(); i++) {
				if ("times".equalsIgnoreCase(two)) {
					map.put(listColLogEntity.get(i).getSumUrlString(),
							(Integer) (listColLogEntity.get(i)
									.getSumResponseCount()));
				} else if ("bytes".equalsIgnoreCase(two)) {
					map.put(listColLogEntity.get(i).getSumUrlString(),
							(Integer) (listColLogEntity.get(i)
									.getSumOutSizeCount()));
				} else {
					System.out.println("The map of sortTime() is null");
				}

			}
			sortCol(two, map);
		}
	}

	private void sortCol(String two, HashMap hashColLogEntity) {
		ValueComparator bvc = new ValueComparator(hashColLogEntity);

		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(bvc);
		treeMap.putAll(hashColLogEntity);

		System.out.println("\nSorted:");

		printMap(two, treeMap);
	}

	private void printMap(String two, Map<String, Integer> map) {

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			int s = 0;
			String str = null;
			for (int i = 0; i < listColLogEntity.size(); i++) {
				if (listColLogEntity.get(i).getSumUrlString()
						.equals(entry.getKey())) {
					if ("times".equalsIgnoreCase(two)) {
						s = listColLogEntity.get(i).getSumOutSizeCount();
					} else if ("bytes".equalsIgnoreCase(two)) {
						s = listColLogEntity.get(i).getSumResponseCount();
					}
					str = listColLogEntity.get(i).getSumMillsecondCount();
				}
			}
			System.out.println(entry.getKey() + "  " + entry.getValue() + " "
					+ s + " " + str);
		}

	}

}