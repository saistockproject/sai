package com.app.smpt.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.smpt.common.SysConstants;

public class SysUtilities {
	private static final String NUMBER_FORMAT = "0123456789";

	private static String EMPTY_STRING = "";
	public static final String DATE_PATTERN_GEORGIAN = "yyyy-MM-dd";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	public static final String DATE_PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	public static String getNullSafeString(String str) {
		if (null == str) {
			return EMPTY_STRING;
		} else {
			return str;
		}
	}
	
	public static boolean validateFieldLength(int maxLength, int minLength, String fieldValue){
		
		boolean success = false;
		
		if(!isEmpty(fieldValue) && fieldValue.length() <= maxLength && fieldValue.length() >= minLength){
			success = true;
		}
		
		return success;
	}

	public static String getUpperString(String str) {
		if (null == str) {
			return EMPTY_STRING;
		} else {
			return str.trim().toUpperCase();
		}
	}

	public static String getUpperStringNoTrim(String str) {
		if (null == str) {
			return EMPTY_STRING;
		} else {
			return str.toUpperCase();
		}
	}

	public static boolean isEmptyString(String str) {
		if (null == str) {
			return true;
		}
		if (trim(str).contentEquals(EMPTY_STRING)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Object object) {
		if (null == object) {
			return true;
		}
		return isEmptyString(object.toString());
	}

	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}

	public static String trim(String str) {
		return getNullSafeString(str).trim();
	}

	public static boolean isNumeric(String value) {
		if (isEmptyString(value))
			return false;
		try {
			new BigDecimal(value);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static Integer getIntegerValue(String value) {
		try {
			if (SysUtilities.isNotEmptyString(value)) {
				return Integer.parseInt(value);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static boolean isNumber(String value) {
		boolean flagNumberCheck = false;
		String validChars = NUMBER_FORMAT;
		char Char;
		if (isEmptyString(value)) {
			return false;
		}
		for (int i = 0; i < value.length(); i++) {
			flagNumberCheck = true;
			Char = value.charAt(i);
			if (validChars.indexOf(Char) == -1) {
				flagNumberCheck = false;
				break;
			}
		}
		return flagNumberCheck;
	}

	public static boolean isValidInput(String stringToValidate, String validChars) {
		boolean flagNumberCheck = false;
		if (isEmpty(stringToValidate)) {
			return true;
		} else {
			Pattern p = Pattern.compile(validChars);
			Matcher matcher = p.matcher(stringToValidate);
			flagNumberCheck = matcher.matches();
		}
		return flagNumberCheck;
	}

	public static String getNullSafeDatetoString(java.sql.Date sqlDate) {
		SimpleDateFormat simpleDateFormat;
		String strDate = null;
		String dateFormat = DATE_PATTERN;

		if (null == sqlDate)
			return EMPTY_STRING;

		try {
			simpleDateFormat = new SimpleDateFormat(dateFormat);
			strDate = simpleDateFormat.format(sqlDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public static String getNullSafeDatetoString(java.util.Date utilDate) {
		SimpleDateFormat simpleDateFormat;
		String strDate = null;
		String dateFormat = DATE_PATTERN;

		if (null == utilDate)
			return EMPTY_STRING;

		try {
			simpleDateFormat = new SimpleDateFormat(dateFormat);
			strDate = simpleDateFormat.format(utilDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public static String getNullSafeDatetoString(java.util.Date utilDate, String requiredFormat) {
		SimpleDateFormat simpleDateFormat;
		String strDate = null;
		String dateFormat = requiredFormat;

		if (null == utilDate)
			return EMPTY_STRING;

		try {
			simpleDateFormat = new SimpleDateFormat(dateFormat);
			strDate = simpleDateFormat.format(utilDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public static String sqlNullSafeDatetoString(Object obj) throws Exception {
		if (obj instanceof Timestamp) {
			return sqlNullSafeDatetoString((Timestamp) obj);
		}
		return getNullSafeDatetoString((java.sql.Date) obj);
	}

	public static String sqlNullSafeDatetoString(Timestamp timestamp) throws Exception {
		String strDate = null;
		String dateFormat = "yyyy-MM-dd HH:mm:ss";

		if (null == timestamp)
			return "";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			strDate = simpleDateFormat.format(timestamp);
		} catch (Exception e) {
			throw new Exception("Exception in parsing date.Please provide valid input in request.");
		}
		return strDate;
	}

	public static Timestamp getTimeStamp(String str) throws Exception {
		try {
			return Timestamp.valueOf(str);
		} catch (Exception e) {
			throw new Exception("Timestamp parser exception.Please provide valid input in request.");
		}
	}

	public static java.util.Date dataBaseDateStringToUtilDate(String dbDateString, String dateFormat) {
		java.util.Date convertedDate = null;
		SimpleDateFormat simpleDateFormat;
		if (isEmptyString(dbDateString)) {
			return null;
		}
		try {
			simpleDateFormat = new SimpleDateFormat(dateFormat);
			convertedDate = simpleDateFormat.parse(dbDateString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertedDate;
	}

	public static int compareDates(String firstDate, String secondDate) {
		Date firstDateObject = dataBaseDateStringToUtilDate(firstDate, DATE_PATTERN);
		Date secondDateObject = dataBaseDateStringToUtilDate(secondDate, DATE_PATTERN);
		return firstDateObject.compareTo(secondDateObject);
	}

	public static int compareDates(Date firstDateObject, Date dateObjectToCompare) {
		return firstDateObject.compareTo(dateObjectToCompare);
	}

	public static boolean validateFieldLength(int maxLength, String fieldValue) {

		boolean success = false;

		if (!isEmpty(fieldValue) && fieldValue.length() <= maxLength) {
			success = true;
		}

		return success;
	}

	public static boolean validateDateRange(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(1);
		String strYear = String.valueOf(year);
		if (strYear.length() == 2) {
			strYear = SysConstants.DEFAULT_TWO_DIGIT_YEAR_PREFIX + strYear;
		} else if (strYear.length() == 1) {
			strYear = SysConstants.DEFAULT_ONE_DIGIT_YEAR_PREFIX + strYear;
		}
		year = Integer.parseInt(strYear);
		if (year < SysConstants.MIN_YEAR) {
			return false;
		} else if (year > SysConstants.MAX_YEAR) {
			return false;
		} else {
			return true;
		}
	}

	public static Date convertDateYearInYYYYFormat(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(1);
		String strYear = String.valueOf(year);
		if (strYear.length() == 2) {
			strYear = SysConstants.DEFAULT_TWO_DIGIT_YEAR_PREFIX + strYear;
		} else if (strYear.length() == 1) {
			strYear = SysConstants.DEFAULT_ONE_DIGIT_YEAR_PREFIX + strYear;
		}
		year = Integer.parseInt(strYear);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date addDays(Date date, int numberOfDays)

	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
		Calendar calendar = getCalendar(date, 12, 0, 0, 0);
		calendar.add(5, numberOfDays);
		String newDate = dateFormatter.format(calendar.getTime());
		Date newDateObject = dataBaseDateStringToUtilDate(newDate, DATE_PATTERN);
		return newDateObject;
	}

	public static Date subtractDays(Date date, int numberOfDays)

	{
		return addDays(date, -numberOfDays);
	}

	public static Calendar getCalendar(Date date, int hour, int minutes, int seconds, int milliseconds) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, hour);
			calendar.set(12, minutes);
			calendar.set(13, seconds);
			calendar.set(14, milliseconds);
			return calendar;
		}
	}

	public static int getYear(Date date) {
		Calendar calendar = getCalendar(date, 12, 0, 0, 0);
		return calendar.get(1);
	}

	public static boolean isFutureDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Date currentDate = calendar.getTime();
		if (date != null) {
			return date.after(currentDate);
		} else {
			return false;
		}
	}

	public static boolean isPastDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Date currentDate = calendar.getTime();
		if (date != null) {
			return date.before(currentDate);
		} else {
			return false;
		}
	}

	public static String getFormattedDate() {

		Calendar calender = Calendar.getInstance();
		int currYear = calender.get(Calendar.YEAR);
		String currentYear = String.valueOf((currYear)).substring(2, 4);
		int currMonth = calender.get(Calendar.MONTH);
		String currentMonth = "";
		if (currMonth < 10)
			currentMonth = "0" + String.valueOf(currMonth + 1);
		else
			currentMonth = String.valueOf(currMonth + 1);
		String currentDay = String.valueOf(calender.get(Calendar.DATE));
		if (currentDay.length() == 1)
			currentDay = "0" + currentDay;
		String currentHour = String.valueOf(calender.get(Calendar.HOUR_OF_DAY));
		if (currentHour.length() == 1)
			currentHour = "0" + currentHour;
		String currentMinute = String.valueOf(calender.get(Calendar.MINUTE));
		if (currentMinute.length() == 1)
			currentMinute = "0" + currentMinute;
		String currentSecond = String.valueOf(calender.get(Calendar.SECOND));
		if (currentSecond.length() == 1)
			currentSecond = "0" + currentSecond;

		return currentYear + currentMonth + currentDay + currentHour + currentMinute + currentSecond;
	}
	
	public static String getFormattedDateForRef() {

		Calendar calender = Calendar.getInstance();
		int currYear = calender.get(Calendar.YEAR);
		String currentYear = String.valueOf((currYear)).substring(2, 4);
		int currMonth = calender.get(Calendar.MONTH);
		currMonth = currMonth+1;
		String currentMonth = "";
		if (currMonth < 10)
			currentMonth = "0" + String.valueOf(currMonth );
		else
			currentMonth = String.valueOf(currMonth );
		String currentDay = String.valueOf(calender.get(Calendar.DATE));
		if (currentDay.length() == 1)
			currentDay = "0" + currentDay;
		String currentHour = String.valueOf(calender.get(Calendar.HOUR_OF_DAY));
		if (currentHour.length() == 1)
			currentHour = "0" + currentHour;
		String currentMinute = String.valueOf(calender.get(Calendar.MINUTE));
		if (currentMinute.length() == 1)
			currentMinute = "0" + currentMinute;
		String currentSecond = String.valueOf(calender.get(Calendar.SECOND));
		if (currentSecond.length() == 1)
			currentSecond = "0" + currentSecond;

		return  currentMonth + currentDay +currentYear;
	}


	public static String createMergeFileName(String fleetMaster, String trxCode, String reqTypeCode) {
		int length = fleetMaster.length();
		if (length <= 10) {
			int len = 10 - length;
			for (int i = 0; i < len; i++) {
				fleetMaster = "0" + fleetMaster;
			}
		}
		String fomattedDate = SysUtilities.getFormattedDate();
		return fleetMaster + "_" + trxCode + "_" + reqTypeCode + "_" + fomattedDate;
	}

	public static String createMergePrintLabelFileName(String fleetMaster, String trxCode) {
		int length = fleetMaster.length();
		if (length <= 10) {
			int len = 10 - length;
			for (int i = 0; i < len; i++) {
				fleetMaster = "0" + fleetMaster;
			}
		}
		String fomattedDate = SysUtilities.getFormattedDate();
		return fleetMaster + "_" + trxCode + "_" + fomattedDate;
	}

	public static Date getDateObject(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date dateObj = null;
		try {
			dateObj = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateObj;
	}

	public static String getSystemDateAsString() {

		Date now = new Date();
		DateFormat df = new SimpleDateFormat(DATE_PATTERN);
		return df.format(now);
	}

	public static String convertNumberToCommaSeperatedString(Object numberToConvert) {
		if (SysUtilities.isEmpty(numberToConvert)) {
			return EMPTY_STRING;
		}
		if (numberToConvert instanceof Integer || numberToConvert instanceof Long || numberToConvert instanceof Double) {
			return NumberFormat.getNumberInstance(Locale.US).format(numberToConvert);
		} else {
			return null;
		}

	}

	public static String getPersonFormattedName(String lastName, String firstName) {
		String name = null;
		if (!SysUtilities.isEmptyString(lastName)) {
			if (!SysUtilities.isEmptyString(firstName)) {
				name = lastName + ", " + firstName;
			} else {
				name = lastName;
			}
		} else {
			if (!SysUtilities.isEmptyString(firstName)) {
				name = firstName;
			}
		}

		return name;
	}

	
	public static boolean validatePassword(String inputPassword, String accountName) {
		int validGroups = 0;
		if (isEmptyString(inputPassword)) {
			return false;
		}
		String[] patternGroup = new String[4];
		patternGroup[0] = "[a-z]";
		patternGroup[1] = "[A-Z]";
		patternGroup[2] = "[0-9]";
		patternGroup[3] = "[@#$%^&+=]";
		for (int i = 0; i < 4; i++) {
			Pattern pattern = Pattern.compile(patternGroup[i]);
			if (pattern.matcher(inputPassword).find()) {
				validGroups++;
			}
		}
		if (validGroups >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validatePwdforAccountName(String inputPassword, String accountName) {
		List<String> list = new ArrayList<String>();
		String token = null;
		boolean status = true;

		if (!SysUtilities.isEmptyString(accountName)) {
			String[] strArr = accountName.split("\\s");
			for (int i = 0; i < strArr.length; i++) {
				String accountToken = strArr[i];
				while (accountToken.length() >= 3) {
					if (accountToken.length() >= 3) {
						token = accountToken.substring(0, 3);
						list.add(token);
						accountToken = accountToken.substring(1);
					} else {
						break;
					}
				}
			}
			for (String item : list) {
				Pattern pattern = Pattern.compile(item);
				if (pattern.matcher(inputPassword).find()) {
					status = false;
					break;
				}
			}
		}

		return status;
	}

	public static String convertListToCommaSeparatedString(List<String> list) {
		StringBuffer strBuffer = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (String company : list) {
				if (!strBuffer.toString().equals("")) {
					strBuffer.append(",");
				}
				strBuffer.append("'" + company + "'");
			}
		}
		return strBuffer.toString();
	}

	public static String getExtensionNumber(String extn) {
		int index = 0;
		if (!isEmptyString(extn)) {
			for (; index < extn.length(); index++) {
				if (Character.isDigit(extn.charAt(index))) {
					break;
				}
			}
			extn = extn.substring(index);
		}
		return extn;
	}

	public static String capitalizeFirstChar(String str) {
		if (str != null && str.length() > 0) {
			String fChar = str.substring(0, 1);
			String restChar = (str.length() > 1 ? str.substring(1) : "");
			str = fChar.toUpperCase() + restChar;
		}
		return str;
	}

	public static String getCurrencyValue(Double value) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		if (value != null) {
			return formatter.format(value);
		} else {
			return null;
		}
	}

	/**
	 * Rules:
	 * 
	 * An @ character must separate the local and domain parts
	 * 
	 * Local part:
	 * 
	 * 1. Allow all characters except following 2. Should not contain @ 3.
	 * should not start or end with dot(.) 4. should not contain consecutive two
	 * or more dots(..) 5. should not contain white space
	 * 
	 * Domain part:
	 * 
	 * 1. Only letters, digits, hyphens and dots are allowed. 2. should not
	 * start or end with dot(.) 3. should not start or end with hyphen(-) 4.
	 * should not contain consecutive two or more dots(..) 5. should not contain
	 * consecutive two or more hyphens(--) 6. Dot(.) and hyphen(-) should not
	 * come together eg. (-.) or (.-) not allowed.
	 * 
	 * 
	 */
	public static boolean isValidEmailAddress(String emailAddress) {

		// String patternStr =
		// "^[^\\.@]+(\\.[^\\.@]+)*@[a-zA-Z0-9]+((-)?[a-zA-Z0-9])*(\\.[a-zA-Z0-9]+((-)?[a-zA-Z0-9])*)*$"
		// ;
		//String patternStr = "[\\.\\w\\-]+@\\w+\\.\\w+";
		String patternStr ="^(.+)@(.+)$";
		Pattern p = Pattern.compile(patternStr);

		if (isEmptyString(emailAddress)) {
			return false;
		} else {
			Matcher matcher = p.matcher(emailAddress);
			return matcher.matches();
		}
	}
	
}