

public class SortLogResult {

	public static void main(String[] args) {
		try {

			String one = args[0];
			String two = args[1];

			if (one != null && two != null) {
				System.out.println("The first arg : " + one);
				System.out.println("The second arg : " + two);

				if (two.equalsIgnoreCase("all")) {
					SortAllLog sortCol = new SortAllLog();
					sortCol.formatData(one);
				} else if (two.equalsIgnoreCase("times")
						|| two.equalsIgnoreCase("bytes")) {
					SortColLog sortCol = new SortColLog();
					sortCol.formatData(one, two);
				} else {
					SortMinLog sortCol = new SortMinLog();
					sortCol.formatData(one, two);
				}

			} else {
				System.out.println("Invalied Args.");
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException caught");
			e.printStackTrace();
		}

	}

}
