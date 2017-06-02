package ai.usher.utils;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    public static String truncate(String value, int maxLength, boolean useEllipsis) {
        if (value == null) {
            return "";
        }
        if (value.length() <= maxLength) {
            return value;
        }
        if (useEllipsis) {
            return value.substring(0, maxLength - 3) + "...";
        } else {
            return value.substring(0, maxLength);
        }
    }

    @SuppressWarnings("deprecation")
    public static String truncateAtWordBoundary(String value, int maxLength, boolean useEllipsis) {
        if (value == null) {
            return "";
        }
        if (value.length() <= maxLength) {
            return value;
        }
        int length = maxLength;
        while (length < value.length() && !Character.isSpace(value.charAt(length))) {
            length++;
        }
        if (length < value.length()) {
            if (useEllipsis) {
                return value.substring(0, length) + " ...";
            } else {
                return value.substring(0, length);
            }
        } else {
            return value;
        }
    }

    public static String truncate(String value) {
        return truncate(value, 50, true);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean areNotBlank(String... str) {
        if (null == str || str.length == 0) {
            return false;
        }
        for (String string : str) {
            if (isBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areBlank(String... str) {
        if (null == str) {
            return true;
        }
        for (String string : str) {
            if (isNotBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static int indexOfAny(String str, String searchCharacters) {
        int ret = -1;
        if (!isBlank(str)) {
            char[] charArray = searchCharacters.toCharArray();
            for (char c : charArray) {
                int found = str.indexOf(c);
                if (found > -1 && (ret == -1 || found < ret)) {
                    ret = found;
                }
            }
        }
        return ret;
    }

    public static boolean startsWith(String string, String prefix) {
        if (string == null || prefix == null) {
            return false;
        }
        return string.startsWith(prefix);
    }

    public static boolean equals(String string1, String string2) {
        if (string1 == null) {
            return StringHelper.isBlank(string2);
        }
        if (string2 == null) {
            return StringHelper.isBlank(string1);
        }
        return string1.equals(string2);
    }

    public static int compareStringPairsIgnoreNull(String s1a, String s1b, String s2a, String s2b) {
        if (StringHelper.isBlank(s1a) && StringHelper.isBlank(s2a) && StringHelper.isBlank(s1b) && StringHelper.isBlank(s2b)) {
            return 0;
        } else if (StringHelper.isBlank(s1a) && StringHelper.isBlank(s2a)) {
            // both A's are blank, just compare B's
            return StringHelper.compareStrings(s1b, s2b);
        } else if (StringHelper.isBlank(s1a)) {
            // A1 is blank
            int compareStrings = StringHelper.compareStrings(s1b, s2a);
            // compare 1b to 2a
            if (0 != compareStrings) {
                return compareStrings;
            }
            // compare 1b to 2b
            return StringHelper.compareStrings(s1b, s2b);
        } else if (StringHelper.isBlank(s2a)) {
            // 2A is blank
            int compareStrings = StringHelper.compareStrings(s1a, s2b);
            // compare 1a to 2b
            if (0 != compareStrings) {
                return compareStrings;
            }
            // compare 1b to 2b
            return StringHelper.compareStrings(s1b, s2b);
        } else if (StringHelper.isBlank(s1b)) {
            // B1 is blank
            int compareStrings = StringHelper.compareStrings(s1a, s2a);
            // compare 1b to 2a
            if (0 != compareStrings) {
                return compareStrings;
            }
            // compare 1b to 2b
            return StringHelper.compareStrings(s1a, s2b);
        } else if (StringHelper.isBlank(s2b)) {
            // 2B is blank
            int compareStrings = StringHelper.compareStrings(s1a, s2a);
            // compare 1a to 2a
            if (0 != compareStrings) {
                return compareStrings;
            }
            // compare 1b to 2a
            return StringHelper.compareStrings(s1b, s2a);
        } else {
            // compare 1's
            int compareStrings = StringHelper.compareStrings(s1a, s2a);
            if (0 != compareStrings) {
                return compareStrings;
            }
            // compare 2s
            return StringHelper.compareStrings(s1b, s2b);
        }
    }

    public static int compareStrings(String s1, String s2) {
        if (StringHelper.isBlank(s1) && StringHelper.isBlank(s2)) {
            return 0;
        } else if (StringHelper.isBlank(s1)) {
            return -1;
        } else if (StringHelper.isBlank(s2)) {
            return 1;
        } else {
            return s1.compareToIgnoreCase(s2);
        }
    }

    public static boolean areStringsEqual(String s1, String s2) {
        return equals(s1, s2);
    }

    public static boolean areStringsEqualIgnoreCase(String s1, String s2) {
        if (s1 == null) {
            return StringHelper.isBlank(s2);
        }
        if (s2 == null) {
            return StringHelper.isBlank(s1);
        }
        return s1.equalsIgnoreCase(s2);
    }

    public static boolean equalsIgnoreCaseTrimed(String string1, String string2) {
        if (StringHelper.isBlank(string1) && StringHelper.isBlank(string2)) {
            return true;
        }
        if (StringHelper.isBlank(string1) && StringHelper.isNotBlank(string2)) {
            return false;
        }
        if (StringHelper.isNotBlank(string1) && StringHelper.isBlank(string2)) {
            return false;
        }
        return string1.trim().equalsIgnoreCase(string2.trim());
    }

    public static String join(String[] strings, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String string : strings) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public static String join(Iterable<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String string : strings) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public static int indexOf(String string, String indexOf) {
        if (string == null) {
            return -1;
        }
        return string.indexOf(indexOf);
    }

    public static boolean equals(List<String> list1, List<String> list2) {
        return equals(list1, list2, false);
    }

    public static boolean equals(List<String> list1, List<String> list2, boolean ignoreOrder) {
        if (list1 == null) {
            if (list2 == null) {
                return true;
            } else {
                return list2.isEmpty();
            }
        } else if (list2 == null) {
            return list1.isEmpty();
        } else if (list1.size() == list2.size()) {
            for (int i = 0; i < list1.size(); i++) {
                if (ignoreOrder) {
                    if (!list2.contains(list1.get(i))) {
                        return false;
                    }
                } else {
                    if (!equals(list1.get(i), list2.get(i))) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param value
     * @return a {@link String}[] containing exactly two elements. The second
     *         element contains the truncated text. It can be "null".
     */
    public static TruncatedTitle truncateTitle(String value) {
        if (value == null) {
            return null;
        }
        String[] split = value.split("[\\-\\|\\;\\:\\(\\[\\{\\u2022]", 2);
        String title = split[0].trim();
        String rest = null;
        if (split.length > 1) {
            rest = split[1].trim();
        }
        return new TruncatedTitle(title, rest);
    }

    public static class TruncatedTitle {
        private final String title;
        private final String truncation;

        public TruncatedTitle(String title, String truncation) {
            this.title = title;
            this.truncation = truncation;
        }

        public String getTitle() {
            return title;
        }

        public String getTruncation() {
            return truncation;
        }
    }

    public static boolean contains(String src, CharSequence sequence) {
        if (src == null) {
            return false;
        }
        return src.contains(sequence);
    }

    public static boolean endsWithIgnoreCase(String srcString, String suffix) {
        if (isBlank(srcString)) {
            return false;
        }
        if (isBlank(suffix)) {
            throw new IllegalArgumentException("Suffix cannot be blank or null");
        }
        return endsWith(srcString.toLowerCase(), suffix.toLowerCase());
    }

    public static boolean endsWith(String srcString, String suffix) {
        if (isBlank(srcString)) {
            return false;
        }
        if (isBlank(suffix)) {
            throw new IllegalArgumentException("Suffix cannot be blank or null");
        }
        return srcString.endsWith(suffix);
    }

    public static boolean containsIgnoreCase(String fullString, String substring) {
        if (fullString == null || StringHelper.isBlank(substring)) {
            return false;
        }
        return fullString.toLowerCase().contains(substring.toLowerCase());
    }

    public static String stripPunctuation(String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    public static String trim(String src) {
        if (null == src) {
            return null;
        }
        return src.trim();
    }

    public static String pad(String value, int width, boolean truncate) {
        StringBuilder sb = new StringBuilder();
        int count = width;
        if (value != null) {
            if (truncate && value.length() > width - 1) {
                value = value.substring(0, width - 1);
            }
            sb.append(value);
            count -= value.length();
        }
        while (count > 0) {
            sb.append(" ");
            count--;
        }
        return sb.toString();
    }

    public static void listToLower(List<String> strings) {
        if (null == strings) {
            return;
        }
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, StringHelper.toLower(strings.get(i)));
        }
    }

    public static String toLower(String string) {
        if (null == string) {
            return null;
        }
        return string.toLowerCase();
    }

    public static String getCommonPrefix(String arg0, String arg1) {
        if (arg0 == null || arg1 == null) {
            return null;
        }
        int index = 0;
        while (arg0.length() > index && arg1.length() > index) {
            if (arg0.charAt(index) != arg1.charAt(index)) {
                break;
            }
            index++;
        }
        return arg0.substring(0, index);
    }

    public static String removeCommonPrefixWords(String prefix, String value) {
        if (prefix == null || value == null) {
            return null;
        }
        String[] pwords = prefix.split("\\s+");
        String[] vwords = value.split("\\s+");
        int index = 0;
        while (pwords.length > index && vwords.length > index) {
            if (!StringHelper.equalsIgnoreCaseTrimed(pwords[index], vwords[index])) {
                break;
            }
            index++;
        }
        if (index == 0) {
            return value;
        }
        if (index >= pwords.length) {
            index--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < vwords.length; i++) {
            sb.append(vwords[i] + " ");
        }
        return sb.toString().trim();
    }

    public static boolean containsStringIgnoreCaseTrim(List<String> list, String containsString) {
        for (String value : list) {
            if (StringHelper.equalsIgnoreCaseTrimed(containsString, value)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> split(String list, String separatorRegex) {
        List<String> result = new ArrayList<String>();
        if (list == null) {
            return result;
        }
        String[] parts = list.split(separatorRegex);
        for (String part : parts) {
            result.add(part.trim());
        }
        return result;
    }

    public static void toLower(List<String> list) {
        List<String> other = new ArrayList<String>();
        for (String item : list) {
            other.add(item.toLowerCase());
        }
        list.clear();
        list.addAll(other);
    }
}
