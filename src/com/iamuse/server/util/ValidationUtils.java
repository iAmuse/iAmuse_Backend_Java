package com.iamuse.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ValidationUtils is a utility class to validate input data
 * It has serveral methods to validate the data.
 * @author Rapidsoft
 * @version 1.5
 */
public final class ValidationUtils {


    private ValidationUtils() {
        // Override default constructor; prevents instantiation.
    }


    // Object Comparison ******************************************************

    /**
     * Checks and answers if the two objects are
     * both <code>null</code> or equal.
     *
     * <pre>
     * ValidationUtils.equals(null, null)  == true
     * ValidationUtils.equals("Hi", "Hi")  == true
     * ValidationUtils.equals("Hi", null)  == false
     * ValidationUtils.equals(null, "Hi")  == false
     * ValidationUtils.equals("Hi", "Ho")  == false
     * </pre>
     *
     * @param o1        the first object to compare
     * @param o2        the second object to compare
     * @return boolean  <code>true</code> if and only if
     *    both objects are <code>null</code> or equal
     */
    public static boolean equals(Object o1, Object o2) {
        return    (o1 != null && o2 != null && o1.equals(o2))
               || (o1 == null && o2 == null);
    }


    
    // String Validations ***************************************************

    /**
     * Checks and answers if the given string is whitespace,
     * empty ("") or <code>null</code>.
     *
     * <pre>
     * ValidationUtils.isBlank(null)    == true
     * ValidationUtils.isBlank("")      == true
     * ValidationUtils.isBlank(" ")     == true
     * ValidationUtils.isBlank(" abc")  == false
     * ValidationUtils.isBlank("abc ")  == false
     * ValidationUtils.isBlank(" abc ") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is whitespace, empty
     *    or <code>null</code>
     *
     * @see #isEmpty(String)
     */
    public static boolean isBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string is not empty (""),
     * not <code>null</code> and not whitespace only.
     *
     * <pre>
     * ValidationUtils.isNotBlank(null)    == false
     * ValidationUtils.isNotBlank("")      == false
     * ValidationUtils.isNotBlank(" ")     == false
     * ValidationUtils.isNotBlank(" abc")  == true
     * ValidationUtils.isNotBlank("abc ")  == true
     * ValidationUtils.isNotBlank(" abc ") == true
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is not empty
     *    and not <code>null</code> and not whitespace only
     *
     * @see #isEmpty(String)
     *
     * @since 1.2
     */
    public static boolean isNotBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks and answers if the given string is empty ("") or <code>null</code>.
     *
     * <pre>
     * ValidationUtils.isEmpty(null)  == true
     * ValidationUtils.isEmpty("")    == true
     * ValidationUtils.isEmpty(" ")   == false
     * ValidationUtils.isEmpty("Hi ") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is empty or <code>null</code>
     *
     * @see #isBlank(String)
     */
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }


    /**
     * Checks and answers if the given string is not empty ("")
     * and not <code>null</code>.
     *
     * <pre>
     * ValidationUtils.isNotEmpty(null)  == false
     * ValidationUtils.isNotEmpty("")    == false
     * ValidationUtils.isNotEmpty(" ")   == true
     * ValidationUtils.isNotEmpty("Hi")  == true
     * ValidationUtils.isNotEmpty("Hi ") == true
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is not empty and not <code>null</code>
     *
     * @see #isBlank(String)
     */
    public static boolean isNotEmpty(String str) {
        return (str != null) && (str.length() > 0);
    }


    /**
     * Checks and answers if the given string has at least the
     * specified minimum length.
     * Strings that are <code>null</code> or contain only blanks have length 0.
     *
     * <pre>
     * ValidationUtils.hasMinimumLength(null,  2) == false
     * ValidationUtils.hasMinimumLength("",    2) == false
     * ValidationUtils.hasMinimumLength(" ",   2) == false
     * ValidationUtils.hasMinimumLength("   ", 2) == false
     * ValidationUtils.hasMinimumLength("Hi ", 2) == true
     * ValidationUtils.hasMinimumLength("Ewa", 2) == true
     * </pre>
     *
     * @param str   the string to check
     * @param min   the minimum length
     * @return <code>true</code> if the length is greater or equal to the minimum,
     *     <code>false</code> otherwise
     */
    public static boolean hasMinimumLength(String str, int min) {
        int length = str == null ? 0 : str.trim().length();
        return min <= length;
    }


    /**
     * Checks and answers if the given string is shorter than
     * the specified maximum length.
     * Strings that are <code>null</code> or contain only blanks have length 0.
     *
     * <pre>
     * ValidationUtils.hasMaximumLength(null,  2) == true
     * ValidationUtils.hasMaximumLength("",    2) == true
     * ValidationUtils.hasMaximumLength(" ",   2) == true
     * ValidationUtils.hasMaximumLength("   ", 2) == true
     * ValidationUtils.hasMaximumLength("Hi ", 2) == true
     * ValidationUtils.hasMaximumLength("Ewa", 2) == false
     * </pre>
     *
     * @param str   the string to check
     * @param max   the maximum length
     * @return <code>true</code> if the length is less than or equal to the minimum,
     *     <code>false</code> otherwise
     */
    public static boolean hasMaximumLength(String str, int max) {
        int length = str == null ? 0 : str.trim().length();
        return length <= max;
    }


    /**
     * Checks and answers if the length of the given string is in the
     * bounds as specified by the interval [min, max].
     * Strings that are <code>null</code> or contain only blanks have length 0.
     *
     * <pre>
     * ValidationUtils.hasBoundedLength(null,  1, 2) == false
     * ValidationUtils.hasBoundedLength("",    1, 2) == false
     * ValidationUtils.hasBoundedLength(" ",   1, 2) == false
     * ValidationUtils.hasBoundedLength("   ", 1, 2) == false
     * ValidationUtils.hasBoundedLength("Hi ", 1, 2) == true
     * ValidationUtils.hasBoundedLength("Ewa", 1, 2) == false
     * </pre>
     *
     * @param str   the string to check
     * @param min   the minimum length
     * @param max   the maximum length
     * @return <code>true</code> if the length is in the interval,
     *     <code>false</code> otherwise
     * @throws IllegalArgumentException if min > max
     */
    public static boolean hasBoundedLength(String str, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(
                "The minimum length must be less than or equal to the maximum length.");
        }
        int length = str == null ? 0 : str.trim().length();
        return (min <= length) && (length <= max);
    }


    // Character Validations **************************************************


    /**
     * Checks and answers if the given string contains only unicode letters.
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isAlpha(null)   == false
     * ValidationUtils.isAlpha("")     == true
     * ValidationUtils.isAlpha("   ")  == false
     * ValidationUtils.isAlpha("abc")  == true
     * ValidationUtils.isAlpha("ab c") == false
     * ValidationUtils.isAlpha("ab2c") == false
     * ValidationUtils.isAlpha("ab-c") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode letters,
     *     and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only unicode letters
     * and space (' ').
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isAlphaSpace(null)   == false
     * ValidationUtils.isAlphaSpace("")     == true
     * ValidationUtils.isAlphaSpace("   ")  == true
     * ValidationUtils.isAlphaSpace("abc")  == true
     * ValidationUtils.isAlphaSpace("ab c") == true
     * ValidationUtils.isAlphaSpace("ab2c") == false
     * ValidationUtils.isAlphaSpace("ab-c") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode letters
     *     and space, and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) && (c != ' ')) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only
     * unicode letters or digits.
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isAlphanumeric(null)   == false
     * ValidationUtils.isAlphanumeric("")     == true
     * ValidationUtils.isAlphanumeric("   ")  == false
     * ValidationUtils.isAlphanumeric("abc")  == true
     * ValidationUtils.isAlphanumeric("ab c") == false
     * ValidationUtils.isAlphanumeric("ab2c") == true
     * ValidationUtils.isAlphanumeric("ab-c") == false
     * ValidationUtils.isAlphanumeric("123")  == true
     * ValidationUtils.isAlphanumeric("12 3") == false
     * ValidationUtils.isAlphanumeric("12-3") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode letters
     *     or digits, and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only
     * unicode letters or digits or space (' ').
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isAlphanumericSpace(null)   == false
     * ValidationUtils.isAlphanumericSpace("")     == true
     * ValidationUtils.isAlphanumericSpace("   ")  == true
     * ValidationUtils.isAlphanumericSpace("abc")  == true
     * ValidationUtils.isAlphanumericSpace("ab c") == true
     * ValidationUtils.isAlphanumericSpace("ab2c") == true
     * ValidationUtils.isAlphanumericSpace("ab-c") == false
     * ValidationUtils.isAlphanumericSpace("123")  == true
     * ValidationUtils.isAlphanumericSpace("12 3") == true
     * ValidationUtils.isAlphanumericSpace("12-3") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode letters,
     *     digits or space (' '), and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c) && (c != ' ')) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only unicode digits.
     * A decimal point is not a unicode digit and returns <code>false</code>.
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isNumeric(null)   == false
     * ValidationUtils.isNumeric("")     == true
     * ValidationUtils.isNumeric("   ")  == false
     * ValidationUtils.isNumeric("abc")  == false
     * ValidationUtils.isNumeric("ab c") == false
     * ValidationUtils.isNumeric("ab2c") == false
     * ValidationUtils.isNumeric("ab-c") == false
     * ValidationUtils.isNumeric("123")  == true
     * ValidationUtils.isNumeric("12 3") == false
     * ValidationUtils.isNumeric("12-3") == false
     * ValidationUtils.isNumeric("12.3") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode digits,
     *     and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFloatingNumber(String str) {
    	char point=0;
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
        	if(str.charAt(i)=='.')
        		++point;
        	if(point>1)
        		return false;
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i)!='.') {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks and answers if the given string contains only unicode digits
     * or space (' '). A decimal point is not a unicode digit and
     * returns <code>false</code>.
     * <code>null</code> returns false,
     * an empty string ("") returns <code>true</code>.
     *
     * <pre>
     * ValidationUtils.isNumericSpace(null)   == false
     * ValidationUtils.isNumericSpace("")     == true
     * ValidationUtils.isNumericSpace("   ")  == true
     * ValidationUtils.isNumericSpace("abc")  == false
     * ValidationUtils.isNumericSpace("ab c") == false
     * ValidationUtils.isNumericSpace("ab2c") == false
     * ValidationUtils.isNumericSpace("ab-c") == false
     * ValidationUtils.isNumericSpace("123")  == true
     * ValidationUtils.isNumericSpace("12 3") == true
     * ValidationUtils.isNumericSpace("12-3") == false
     * ValidationUtils.isNumericSpace("12.3") == false
     * </pre>
     *
     * @param str   the string to check, may be <code>null</code>
     * @return <code>true</code> if the string contains only unicode digits
     *     or space, and is non-<code>null</code>
     *
     * @since 1.2
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && (c != ' ')) {
                return false;
            }
        }
        return true;
    }


    // Date Validations *******************************************************

    /**
     * Determines and answers if the day of the given <code>Date</code>
     * is in the past.
     *
     * @param date   the date to check
     * @return <code>true</code> if in the past, <code>false</code> otherwise
     */
    public static boolean isPastDay(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar today = getRelativeCalendar( 0);
        return in.before(today);
    }


    /**
     * Determines and answers if the given <code>Date</code> is yesterday.
     *
     * @param date   the date to check
     * @return <code>true</code> if yesterday, <code>false</code> otherwise
     */
    public static boolean isYesterday(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar yesterday = getRelativeCalendar(-1);
        Calendar today     = getRelativeCalendar( 0);
        return !in.before(yesterday)
             && in.before(today);
    }


    /**
     * Determines and answers if the given <code>Date</code> is today.
     *
     * @param date   the date to check
     * @return <code>true</code> if today, <code>false</code> otherwise
     */
    public static boolean isToday(Date date) {
        GregorianCalendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar today    = getRelativeCalendar( 0);
        Calendar tomorrow = getRelativeCalendar(+1);
        return !in.before(today)
             && in.before(tomorrow);
    }


    /**
     * Determines and answers if the given <code>Date</code> is tomorrow.
     *
     * @param date   the date to check
     * @return <code>true</code> if tomorrow, <code>false</code> otherwise
     */
    public static boolean isTomorrow(Date date) {
        GregorianCalendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar tomorrow = getRelativeCalendar(+1);
        Calendar dayAfter = getRelativeCalendar(+2);
        return !in.before(tomorrow)
             && in.before(dayAfter);
    }


    /**
     * Determines and answers if the day of the given <code>Date</code>
     * is in the future.
     *
     * @param date   the date to check
     * @return <code>true</code> if in the future, <code>false</code> otherwise
     */
    public static boolean isFutureDay(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar tomorrow = getRelativeCalendar(+1);
        return !in.before(tomorrow);
    }


    /**
     * Computes the day that has the given offset in days to today
     * and returns it as an instance of  <code>Date</code>.
     *
     * @param offsetDays   the offset in day relative to today
     * @return the <code>Date</code> that is the begin of the day
     *     with the specified offset
     */
    public static Date getRelativeDate(int offsetDays) {
        return getRelativeCalendar(offsetDays).getTime();
    }


    /**
     * Computes the day that has the given offset in days to today
     * and returns it as an instance of <code>Calendar</code>.
     *
     * @param offsetDays   the offset in day relative to today
     * @return a <code>Calendar</code> instance that is the begin of the day
     *     with the specified offset
     */
    public static Calendar getRelativeCalendar(int offsetDays) {
        Calendar today = new GregorianCalendar();
        return getRelativeCalendar(today, offsetDays);
    }


    /**
     * Computes the day that has the given offset in days from the specified
     * <em>from</em> date and returns it as an instance of <code>Calendar</code>.
     *
     * @param from         the base date as <code>Calendar</code> instance
     * @param offsetDays   the offset in day relative to today
     * @return a <code>Calendar</code> instance that is the begin of the day
     *     with the specified offset from the given day
     */
    public static Calendar getRelativeCalendar(Calendar from, int offsetDays) {
        Calendar temp =
            new GregorianCalendar(
                from.get(Calendar.YEAR),
                from.get(Calendar.MONTH),
                from.get(Calendar.DATE),
                0,
                0,
                0);
        temp.add(Calendar.DATE, offsetDays);
        return temp;
    }
    
	/**
	 * This method is used to validate the user email
	 * @param email
	 * @return true/false
	 */
    public static boolean validateEmail(String email){
    	
    	Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    	// Match the given string with the pattern
    	Matcher m = p.matcher(email);
    	// check whether match is found
    	boolean matchFound = m.matches();
    	StringTokenizer st = new StringTokenizer(email,".");
    	String lastToken = null;
    	while (st.hasMoreTokens()) {
    	lastToken = st.nextToken();
    	}
    	if (matchFound && lastToken.length() >= 2
    	&& email.length() - 1 != lastToken.length()) {
    	// validate the country code
    	return true;
    	}
    	else return false;
    	}
    
	/**
	 * This method is used to validate the date
	 * @param StringDate
	 * @return true/false
	 */
    public static boolean isValidDate(String StringDate)
    {
    	 boolean result=false;
    	
    	  try{
    	  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	  formatter.setLenient(false);
    	  Date date = (Date)formatter.parse(StringDate);  
    	 
    	  result=true;
    	
    	  } catch (Exception e)
    	  {
    		  result=false;
    	  }  
    	 return result;
    	 }
    
    /**
	 * This method is used to validate the digit.
	 * @param StringDate
	 * @return true/false
	 */
    public static boolean isValidDateDigit(String StringDate)
    {
    	 boolean result=false;
    	
    	  try{
    	  Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
          Matcher matcher = pattern.matcher(StringDate);
          if (matcher.matches()) {
        	  result=true;
          }
    	 
    	
    	  } catch (Exception e)
    	  {
    		  result=false;
    	  }  
    	 return result;
    	 }
   
	/**
	 * This method is used to validate session
	 * @param session
	 * @return true/false
	 */
    public static boolean isValidOldSession(String StringDate)
    {
    	 boolean result=false;
    	
    	  try{
    	  Pattern pattern = Pattern.compile("\\d{1}@\\d{1}@\\d{2}");
          Matcher matcher = pattern.matcher(StringDate);
          if (matcher.matches()) {
        	  result=true;
          }
    	 
    	
    	  } catch (Exception e)
    	  {
    		  result=false;
    	  }  
    	 return result;
    	 }
    
    
    public static boolean isValidLatLong(String latlong)
    {
    	 boolean result=false;
    	
    	  try{
    		  String latLongArr[] = latlong.split(",");
    		  if(latLongArr.length==2)
    			  result=true;
    		  	
    	  } catch (Exception e)
    	  {
    		  result=false;
    	  }  
    	 return result;
    }
    
    public static boolean validatPropertyType(String propertyType)
    {
    	boolean result =false;
    	try {
			int id =Integer.parseInt(propertyType);
			if(id>0 && id<=5)
			result=true;	
		} catch (Exception e) {
			return false;
		}
		return result;
    }
    
    public static boolean validatPostedBy(String postedBy)
    {
    	boolean result =false;
    	try {
			int id =Integer.parseInt(postedBy);
			if(id>0 && id<=3)
			result=true;	
		} catch (Exception e) {
			return false;
		}
		return result;
    }

    public static boolean validatUploadPurpose(String uploadPurpose)
    {
    	boolean result =false;
    	try {
			int id =Integer.parseInt(uploadPurpose);
			if(id>0 && id<=2)
			result=true;	
		} catch (Exception e) {
			return false;
		}
		return result;
    }
    
    public static boolean validatPushState(String state)
    {
    	boolean result =false;
    	try {
			int id =Integer.parseInt(state);
			if(id>=0 && id<=1)
			result=true;	
		} catch (Exception e) {
			return false;
		}
		return result;
    }
    
    
    
    public static void main(String[] args) {
		System.out.println(ValidationUtils.validatPushState("2"));
	}
    
}