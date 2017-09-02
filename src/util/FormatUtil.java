package util;

public class FormatUtil {

	public static double format(double number) {
		int tmp = (int) (number * 100);
		return tmp / 100.00;
	}
	
}
