

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SortLog {
	protected int getSize(List<TempLogEntity> listTempLogEntity, String str) {
		int totalSizePerURL = 0;
		if (listTempLogEntity != null || str != null) {
			for (int i = 0; i < listTempLogEntity.size(); i++) {
				if (listTempLogEntity.get(i).getUrl().equals(str)) {
					totalSizePerURL += Integer.parseInt(listTempLogEntity
							.get(i).getSize());
				}
			}
		}
		return totalSizePerURL;
	}

	protected String getMillSec(List<TempLogEntity> listTempLogEntity,
			String str) {
		BigDecimal totalmillSeconds = BigDecimal.ZERO;
		if (listTempLogEntity != null || str != null) {
			for (int i = 0; i < listTempLogEntity.size(); i++) {
				if (listTempLogEntity.get(i).getUrl().equals(str)) {
					BigDecimal b2 = new BigDecimal(listTempLogEntity.get(i)
							.getMillsec());
					totalmillSeconds = totalmillSeconds.add(b2);
				}
			}
		}
		return totalmillSeconds.toString();
	}

	protected String getMin(String txt) {
		String tempStr = null;

		String re1 = ".*?"; // Non-greedy match on filler
		String re2 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])"; // DDMMMYYYY
																																																																								// 1
		String re3 = ".*?"; // Non-greedy match on filler
		String re4 = "(\\d+)"; // Integer Number 1
		String re5 = ".*?"; // Non-greedy match on filler
		String re6 = "(\\d+)"; // Integer Number 2

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find()) {
			String ddmmmyyyy1 = m.group(1);
			String int1 = m.group(2);
			String int2 = m.group(3);
			// System.out.print("(" + ddmmmyyyy1.toString() + ")" + "("
			// + int1.toString() + ")" + "(" + int2.toString() + ")"
			// + "\n");
			tempStr = ddmmmyyyy1.toString() + ":" + int1.toString() + ":"
					+ int2.toString();
			// System.out.println(tempStr);
		}
		return tempStr;
	}

	protected String getNewURLString(String strOld) {
		String strNew = null;
		if (strOld != null) {
			strNew = strOld.replace(" HTTP/1.1", "");
			//if (strNew.contains(".jpg")) {
				//System.out.println(strNew + "====" + getSimlarJPG(strNew));
				//strNew = "GET /media/*.jpg";
			//}
		}

		return strNew;
	}

	protected String getSimlarJPG(String txt) {
		String s = null;
		txt = txt.replace("GET /media/", "");
		txt = txt.replace(".jpg", "");

		return "GET /media/*.jpg";
	}

	protected LogEntity getIt(String txt) {
		LogEntity logEntity = null;
		if (txt != null) {

			String re1 = ".*?"; // Non-greedy match on filler
			String re2 = "(\\[.*?\\])"; // Square Braces 1
			String re3 = ".*?"; // Non-greedy match on filler
			String re4 = "(\".*?\")"; // Double Quote String 1
			String re5 = ".*?"; // Non-greedy match on filler
			String re6 = "(\\d+)"; // Integer Number 1
			String re7 = ".*?"; // Non-greedy match on filler
			String re8 = "(\\d+)"; // Integer Number 2
			String re9 = ".*?"; // Non-greedy match on filler
			String re10 = "([+-]?\\d*\\.\\d+)(?![-+0-9\\.])"; // Float 1

			Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7
					+ re8 + re9 + re10, Pattern.CASE_INSENSITIVE
					| Pattern.DOTALL);
			Matcher m = p.matcher(txt);
			if (m.find()) {
				String sbraces1 = m.group(1);
				String string1 = m.group(2);
				String int1 = m.group(3);
				String int2 = m.group(4);
				String float1 = m.group(5);
				// System.out.print("(" + sbraces1.toString() + ")" + "("
				// + string1.toString() + ")" + "(" + int1.toString()
				// + ")" + "(" + int2.toString() + ")" + "("
				// + float1.toString() + ")" + "\n");
				logEntity = new LogEntity(sbraces1.toString(),
						string1.toString(), int1.toString(), int2.toString(),
						float1.toString());

			}
		}
		return logEntity;
	}

	protected HashMap<Integer, LogEntity> readFileByLines(String fileName) {
		HashMap<Integer, LogEntity> hashmap = new HashMap<Integer, LogEntity>();
		File file = new File(fileName);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + " : " + tempString);
				LogEntity logEntity = null;
				if (tempString.length() == 0) {
					reader.skip(line);
					hashmap.put(line, null);
				} else {
					logEntity = getIt(tempString);
					hashmap.put(line, logEntity);
				}

				line++;

				// if (line == 71) {
				// break;
				// }
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return hashmap;

	}
}
