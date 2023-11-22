package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {
	public static String convertSqlDateToString(Date sqlDate) {
		if(sqlDate == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(sqlDate);
	}
}
