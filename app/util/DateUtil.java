package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {

	public static String getFormattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

	/**
	 * Parse from yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date parseToDate(String str) {
		
		TimeZone GMT = TimeZone.getTimeZone("GMT-03:00");
		
		try {			
			int year = Integer.parseInt(str.substring(0, 4));
			int month = Integer.parseInt(str.substring(5, 7)) - 1;
			int day = Integer.parseInt(str.substring(8, 10));
			
			Calendar dateTime = new GregorianCalendar(GMT);
			dateTime.set(year, month, day);
			return dateTime.getTime();

		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

}
