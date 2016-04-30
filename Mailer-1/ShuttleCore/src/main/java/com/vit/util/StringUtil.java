package com.vit.util;

import org.apache.commons.lang.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rajeevsingh on 18/10/14.
 */
public class StringUtil {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringUtil() {

    }

    /**
     * Escapes characters for text appearing as data in the
     * <a href='http://www.json.org/'>Javascript Object Notation</a>
     * (JSON) data interchange format.
     * <p/>
     * <P>The following commonly used control characters are escaped :
     * <table border='1' cellpadding='3' cellspacing='0'>
     * <tr><th> Character </th><th> Escaped As </th></tr>
     * <tr><td> " </td><td> \" </td></tr> Do not need to do this as browser itself is escaping this
     * <tr><td> \ </td><td> \\ </td></tr>
     * <tr><td> / </td><td> \/ </td></tr>
     * <tr><td> back space </td><td> \b </td></tr>
     * <tr><td> form feed </td><td> \f </td></tr>
     * <tr><td> line feed </td><td> \n </td></tr>
     * <tr><td> carriage return </td><td> \r </td></tr>
     * <tr><td> tab </td><td> \t </td></tr>
     * </table>
     */
    public static String skipSpecialCharacterForJSON(String aText) {

        if (isEmpty(aText))
            return aText;

        final StringBuilder result = new StringBuilder();
        StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            /*if (character == '\"') {
                result.append("\\\"");
            } else*/
            if (character == '\\') {
                result.append("\\\\");
            } else if (character == '/') {
                result.append("\\/");
            } else if (character == '\b') {
                result.append("\\b");
            } else if (character == '\f') {
                result.append("\\f");
            } else if (character == '\n') {
                result.append("\\n");
            } else if (character == '\r') {
                result.append("\\r");
            } else if (character == '\t') {
                result.append("\\t");
            } else {
                //the char is not a special one
                //add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString().replaceAll("[\u0000-\u001f]", "");
    }

    static final byte[] HEX_CHAR_TABLE = {
            (byte) '0', (byte) '1', (byte) '2', (byte) '3',
            (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
            (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
    };

    static NumberFormat twoDigitNumber = NumberFormat.getInstance();
    static NumberFormat threeDigitNumber = NumberFormat.getInstance();
    static NumberFormat fourDigitNumber = NumberFormat.getInstance();

    static {
        twoDigitNumber.setGroupingUsed(false);
        twoDigitNumber.setMinimumIntegerDigits(2);

        threeDigitNumber.setGroupingUsed(false);
        threeDigitNumber.setMinimumIntegerDigits(3);

        fourDigitNumber.setGroupingUsed(false);
        fourDigitNumber.setMinimumIntegerDigits(4);
    }

    public static boolean isEmpty(String str) {
        if (str == null) return true;
        return "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * replace all commas wioth spaces
     *
     * @param l
     * @return
     */
    public static final String commaToSpace(String l) {
        return l.replaceAll(",", " ");
    }

    public static String getHexString(byte[] raw) throws UnsupportedEncodingException {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, "ASCII");
    }

    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }

    public static String arrayToString(int[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }

    public static String to2Digits(int value) {
        return twoDigitNumber.format(value);
    }

    public static String to3Digits(int value) {
        return threeDigitNumber.format(value);
    }

    public static String to4Digits(int value) {
        return fourDigitNumber.format(value);
    }

    public static String to4Digits(String value, int lowValue, int def) {
        int v;
        try {
            v = isNotEmpty(value) ? Integer.parseInt(value) : def;
            if (v < lowValue)
                v = def;
        } catch (Exception e) {
            v = def;
        }
        return to4Digits(v);
    }

    /**
     * returns true if input starts with substring
     *
     * @param input
     * @param substring
     * @return
     */
    public static boolean startsWith(String input, String substring) {
        return isNotEmpty(input) && isNotEmpty(substring) && input.startsWith(substring);
    }

    public static String replaceLineBreaks(String input) {
        if (input.contains("\r")) {
            input = input.replaceAll("\\r", " ");
        } else if (input.contains("\n")) {
            input = input.replaceAll("\\n", " ");
        } else if (input.contains("\r\n")) {
            input = input.replaceAll("\\r\\n", " ");
        }
        return input;
    }

    /**
     * <p>Find the Levenshtein distance between two Strings.</p>
     * <p/>
     * <p>This is the number of changes needed to change one String into
     * another, where each change is a single character modification (deletion,
     * insertion or substitution).</p>
     * <p/>
     * <p>The previous implementation of the Levenshtein distance algorithm
     * was from <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
     * <p/>
     * <p>Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError
     * which can occur when my Java implementation is used with very large strings.<br>
     * This implementation of the Levenshtein distance algorithm
     * is from <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a></p>
     * <p/>
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance("","")               = 0
     * StringUtils.getLevenshteinDistance("","a")              = 1
     * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
     * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
     * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
     * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
     * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
     * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
     * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
     * </pre>
     *
     * @param s the first String, must not be null
     * @param t the second String, must not be null
     * @return result distance
     * @throws IllegalArgumentException if either String input <code>null</code>
     */
    public static int getLevenshteinDistance(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

      /*
         The difference between this impl. and the previous is that, rather
         than creating and retaining a matrix of size s.length()+1 by t.length()+1,
         we maintain two single-dimensional arrays of length s.length()+1.  The first, d,
         is the 'current working' distance array that maintains the newest distance cost
         counts as we iterate through the characters of String s.  Each time we increment
         the index of String t we are comparing, d is copied to p, the second int[].  Doing so
         allows us to retain the previous cost counts as required by the algorithm (taking
         the minimum of the cost count to the left, up one, and diagonally up and to the left
         of the current cost count being calculated).  (Note that the arrays aren't really
         copied anymore, just switched...this is clearly much better than cloning an array
         or doing a System.arraycopy() each time  through the outer loop.)

         Effectively, the difference between the two implementations is this one does not
         cause an out of memory condition when calculating the LD over two very large strings.
       */

        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            String tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }

    public static boolean isValid(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static String trim(String str) {
        if (isNotEmpty(str)) {
            return str.trim();
        }
        return "";
    }

    public static String getRandomNumber(int digCount) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }

    /**
     * split the text by the given regex and return the item in specified position
     * empty string otherwise
     *
     * @param text
     * @param regex
     * @param position
     * @return
     */
    public static String getSplitItem(String text, String regex, int position) {
        String[] items = StringUtil.isNotEmpty(text) ? text.split(regex) : null;
        return items != null && items.length > position ? items[position] : "";
    }

    public static String randomString(int len) {
        return RandomStringUtils.randomAlphanumeric(len).toUpperCase();
        /*Random rnd = new Random();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();*/
    }

    public static boolean isEmptyOrUnknown(String s) {
        return isEmpty(s) || "unknown".equalsIgnoreCase(s);
    }

    public static String cleanHTML(String s) {
        return s;
    }
}
