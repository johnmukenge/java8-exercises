import java.lang.reflect.Array;

public class UtilsFunctions {

    // 5.1. Counting vowels and consonants -->

    private static final Set allVowels = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static Pair countVowelsAndConsonants(String str) {
        str = str.toLowerCase();
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (allVowels.contains(ch)) {
                vowels++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                consonants++;
            }
        }
        return Pair.of(vowels, consonants);
    }

    // 5.2. Counting vowels and consonants --> functional style
    public static Pair countVowelsAndConsonants2(String str) {
        str = str.toLowerCase();
        long vowels = str.chars()
                .filter(c -> allVowels.contains((char) c))
                .count();
        long consonants = str.chars()
                .filter(c -> !allVowels.contains((char) c))
                .filter(ch -> (ch >= 'a' && ch <= 'z'))
                .count();
        return Pair.of(vowels, consonants);
    }

    // 6.1 Counting the occurrences of a certain character
    public static int countOccurrencesOfACertainCharacter(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    // 6.2
    public static int countOccurrencesOfACertainCharacter(String str, String ch) {
        if (ch.codePointCount(0, ch.length()) > 1) {
            // there is more than 1 Unicode character in the given String
            return -1;
        }
        int result = str.length() - str.replace(ch, "").length();
        // if ch.length() return 2 then this is a Unicode surrogate pair
        return ch.length() == 2 ? result / 2 : result;
    }

    // 6.3
    public static int countOccurrencesOfACertainCharacter2(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // 6.4
    public static long countOccurrencesOfACertainCharacter3(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }

    // 7 Converting a String into int, long, float, double
    private static final String TO_INT = "453";
    private static final String TO_LONG = "45234223233";
    private static final String TO_FLOAT = "45.823F";
    private static final String TO_DOUBLE = "13.83423D";

    // A proper solution for converting String into int, long, float, or double
    // consists of using the following Java methods of the Integer, Long, Float, and
    // Double classes—parseInt(), parseLong(), parseFloat(), and parseDouble():

    int toInt = Integer.parseInt(TO_INT);
    long toLong = Long.parseLong(TO_LONG);
    float toFloat = Float.parseFloat(TO_FLOAT);
    double toDouble = Double.parseDouble(TO_DOUBLE);

    // Converting String into an Integer, Long, Float, or Double object can be
    // accomplished via the following Java methods—Integer.valueOf(),
    // Long.valueOf(), Float.valueOf(), and Double.valueOf():

    Integer toINT = Integer.valueOf(TO_INT);
    Long toLONG = Long.valueOf(TO_LONG);
    Float toFLOAT = Float.valueOf(TO_FLOAT);
    Double toDOUBLE = Double.valueOf(TO_DOUBLE);

    // When a String cannot be converted successfully, Java throws a
    // NumberFormatException exception. The following code speaks for itself:

    private static final String WRONG_NUMBER = "452w";

    public static convertStringTO(String str){
        try{
            Integer toIntWrong1 = Integer.valueOf(WRONG_NUMBER);
        }catch(
        NumberFormatException e)
        { System.err.println(e); // handle exception}try { int toIntWrong2 = Integer.parseInt(WRONG_NUMBER);} catch (NumberFormatException e) { System.err.println(e); // handle exception}

        }
    }

    // 8. Removing whitespace from a string

    // Mainly, \s removes all white spaces, including the non-visible ones, such as
    // \t, \n, and \r:
    public static String removeWhitespaces(String str) {
        return str.replaceAll("\\s", "");
    }

    // Starting with JDK 11, String.isBlank() checks whether the string is empty or
    // contains only white space code points.

    // 9. Joining multiples Strings with a delimiter

    // Before Java 8, a convenient approach relied on StringBuilder, as follows:
    public static String joinByDelimiter(char delimiter, String... args) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (i = 0; i < args.length - 1; i++) {
            result.append(args[i]).append(delimiter);
        }
        result.append(args[i]);
        return result.toString();
    }

    // Starting with Java 8, there is three more solutions to this problem
    // One of these solutions relies on the StringJoiner utility class. This class
    // can be used to construct a sequence of characters separated by a delimiter
    // (for example, a comma).

    public static String joinByDelimiter2(char delimiter, String... args) {
        StringJoiner joiner = new StringJoiner(String.valueOf(delimiter));
        for (String arg : args) {
            joiner.add(arg);
        }
        return joiner.toString();
    }

    // Another solution relies on the String.join() method. This method was
    // introduced in Java 8 and comes in two flavors:

    String result = String.join(" ", "how", "are", "you"); // how are you

    // Going further, Java 8 streams and Collectors.joining() can be useful as well:
    public static String joinByDelimiter3(char delimiter, String... args) {
        return Arrays.stream(args, 0, args.length)
                .collect(Collectors.joining(String.valueOf(delimiter)));
    }

    // 9. Generating all permutations

    /*
     * Problems that involve permutations commonly involve recursivity as well.
     * Basically, recursivity is defined as a process where some initial state is
     * given and each successive state is defined in terms of the preceding state.
     */

    // Starting with A B C --> 1. swap A with A: A B C; 2. swap A with B: B A C; 3.
    // swap A with C: C B A
    // From 1. consider A fixed, 1.1 swap B with B: A B C; 1.2 swap B with C: A C B
    // From 2. consider B fixed, 2.1 swap A with A: B A C; 2.2 swap A with C: B C A
    // From 3. consider C fixed, 3.1 swap B with B: C B A; 3.2 swap B with A: C A B

    // Coding this algorithm will result in something like the following:
    public static void permuteAndPrint(String str) {
        permuteAndPrint("", str);
    }

    private static void permuteAndPrint(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                permuteAndPrint(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i));
            }
        }
    }

    // Initially, the prefix should be an empty string, "". At each iteration, the
    // prefix will concatenate (fix) the next letter from the string. The remaining
    // letters are passed through the method again.

    public static Set permuteAndStore(String str) {
        return permuteAndStore("", str);
    }

    private static Set permuteAndStore(String prefix, String str) {
        Set permutations = new HashSet();
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutations
                        .addAll(permuteAndStore(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i)));
            }
        }
        return permutations;
    }
    // For example, if the passed string is TEST, then Set will cause the following
    // output (these are all unique permutations):

    // Trying to implement this solution in Java 8 functional style will result in
    // something like the following:

    private static void permuteAndPrintStream(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n).parallel().forEach(
                    i -> permuteAndPrintStream(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i)));
        }
    }

    // 10. Checking wether a string is a palindrome
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (right > left) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 10.1
    public static boolean isPalindrome2(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // But can this solution be reduced to a single line of code? The answer is
    // yes.The Java API provides the StringBuilder class, which uses the reverse()
    // method. As its name suggests, the reverse() method returns the reverse given
    // string. In the case of a palindrome, the given string should be equal to the
    // reverse version of it:

    public static boolean isPalindrome3(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    // In Java 8 functional style, there is a single line of code for this as well.
    // Simply define IntStream ranging from 0 to half of the given string and use
    // the noneMatch() short-circuiting terminal operation with a predicate that
    // compares the letters by following the meet-in-the-middle approach:

    public static boolean isPalindrome4(String str) {
        return IntStream.range(0, str.length() / 2)
                .noneMatch(p -> str.charAt(p) != str.charAt(str.length() - p - 1));
    }

    // 12. Removing duplicate characters
    // Let's start with a solution to this problem that relies on StringBuilder.
    // Mainly, the solution should loop the characters of the given string and
    // construct a new string containing unique characters (it is not possible to
    // simply remove characters from the given string since, in Java, a string is
    // immutable).
    public static String removeDuplicates(String str) {
        char[] chArray = str.toCharArray();
        // or, use charAt(i)
        StringBuilder sb = new StringBuilder();
        for (char ch : chArray) {
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // The next solution relies on a collaboration between HashSet and
    // StringBuilder. Mainly, HashSet ensures that duplicates are eliminated, while
    // StringBuilder stores the resulting string. If HashSet.add() returns true,
    // then we add the character in StringBuilder as well:
    public static String removeDuplicates2(String str) {
        char[] chArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Set chHashSet = new HashSet();
        for (char c : chArray) {
            if (chHashSet.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // The solutions we've presented so far use the toCharArray() method to convert
    // the given string into char[]. Alternatively, both solutions can use
    // str.charAt(position) as well.

    // The third solution relies on Java 8 functional style:
    public static String removeDuplicates3(String str) {
        return Arrays.asList(str.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
    }

    // 13 Removing a given character
    public static String removeCharacter(String str, char ch) {
        return str.replaceAll(Pattern.quote(String.valueOf(ch)), "");
    }

    // Notice that the regular expression is wrapped in the Pattern.quote() method.
    // This is needed to escape special characters such as . Mainly, this method
    // returns a literal pattern string for the specified string.

    public static String removeCharacter2(String str, char ch) {
        StringBuilder sb = new StringBuilder();
        char[] chArray = str.toCharArray();
        for (char c : chArray) {
            if (c != ch) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Finally, let's focus on a Java 8 functional style approach. This is a
    // four-step approach:
    // 1.Convert the string into IntStream via the String.chars() method
    // 2. Filter IntStream to eliminate duplicates
    // 3. Map the resulted IntStream to Stream Join the strings from this stream and
    // 4. collect them as a single string

    public static String removeCharacter3(String str, char ch) {
        return str.chars()
                .filter(c -> c != ch)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    // 14. Finding the character with the most apparences

    // A pretty straightforward solution relies on HashMap.This solution consists of
    // three steps:
    // 1. First, loop the characters of the given string and put the pairs of the
    // key-value in
    // HashMap where the key is the current character and the value is the current
    // number of occurrences
    // 2. Second, compute the maximum value in HashMap (for example, using
    // Collections.max())
    // representing the maximum number of occurrences
    // 3. Finally, get the character that has the maximum number of occurrences by
    // looping the HashMap entry set

    public static Pair maxOccurenceCharacter(String str) {
        Map counter = new HashMap();
        char[] chStr = str.toCharArray();
        for (int i = 0; i < chStr.length; i++) {
            char currentCh = chStr[i];
            if (!Character.isWhitespace(currentCh)) {
                // ignore spaces
                Integer noCh = counter.get(currentCh);
                if (noCh == null) {
                    counter.put(currentCh, 1);
                } else {
                    counter.put(currentCh, ++noCh);
                }
            }
        }
        int maxOccurrences = Collections.max(counter.values());
        char maxCharacter = Character.MIN_VALUE;
        for (Entry entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurrences) {
                maxCharacter = entry.getKey();
            }
        }
        return Pair.of(maxCharacter, maxOccurrences);
    }

    // If using HashMap looks cumbersome, then another solution (that's a little
    // faster) consists of relying on the ASCII codes. This solution starts with an
    // empty array of 256 indexes (256 is the maximum number of extended ASCII table
    // codes; more information can be found in the Finding the first non-repeated
    // character section). Furthermore, this solution loops the characters of the
    // given string and keeps track of the number of appearances for each character
    // by increasing the corresponding index in this array:

    private static final int EXTENDED_ASCII_CODES = 256;

    public static Pair maxOccurenceCharacter2(String str) {
        char currentCh = chStr[i];
        if (!Character.isWhitespace(currentCh)) {
            // ignoring space
            int code = (int) currentCh;
            asciiCodes[code]++;
            if (asciiCodes[code] > maxOccurrences) {
                maxOccurrences = asciiCodes[code];
                maxCharacter = currentCh;
            }
        }
        return Pair.of(maxCharacter, maxOccurrences);
    }

    // The last solution we will discuss here relies on Java 8 functional style:

    public static Pair maxOccurenceCharacter3(String str) {
        return str.chars().filter(c -> Character.isWhitespace(c) == false)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, counting()))
                .entrySet().stream()
                .max(comparingByValue())
                .map(p -> Pair.of(p.getKey(), p.getValue()))
                .orElse(Pair.of(Character.MIN_VALUE, -1L));
    }

    // To start, this solution collects distinct characters as keys in Map, along
    // with their number of occurrences as values. Furthermore, it uses the Java 8
    // Map.Entry.comparingByValue() and max() terminal operations to determine the
    // entry in the map with the highest value (highest number of occurrences).
    // Since max() is a terminal operation, the solution may return Optional>, but
    // this solution adds an extra step and maps this entry to Pair.

    // 15. Sorting an array of string by length

    // The first thing that comes to mind when sorting is the use of a comparator.
    // In this case, the solution should compare lengths of strings,
    // and so the integers are returned by calling String.length() for each string
    // in the given array. So, if the integers are sorted (ascending or descending),
    // then the strings will be sorted.
    // The Java Arrays class already provides a sort() method that takes the array
    // to sort and a comparator.
    // In this case, Comparator should do the job.

    public static void sortArrayByLength(String[] strs, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            Arrays.sort(strs, (String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
        } else {
            Arrays.sort(strs, (String s1, String s2) -> (-1) * Integer.compare(s1.length(), s2.length()));
        }
    }

    // Starting with Java 8, the Comparator interface was enriched with a
    // significant number of useful methods. One of these methods is comparingInt(),
    // which takes a function that extracts an int sort key from the generic type
    // and returns a Comparator value that compares it with that sort key. Another
    // useful method is reversed(), which reverses the current Comparator
    // value.Based on these two methods, we can empower Arrays.sort() as follows:

    public static void sortArrayByLength(String[] strs, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            Arrays.sort(strs, Comparator.comparingInt(String::length));
        } else {
            Arrays.sort(strs, Comparator.comparingInt(String::length).reversed());
        }
    }

    // The solutions we've presented here return void, which means that they sort
    // the given array. To return a new sorted array and not alter the given array,
    // we can use Java 8 functional style, as shown in the following snippet of
    // code:

    public static String[] sortArrayByLength(String[] strs, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            return Arrays.stream(strs)
                    .sorted(Comparator.comparingInt(String::length))
                    .toArray(String[]::new);
        } else {
            return Arrays.stream(strs)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .toArray(String[]::new);
        }
    }

    // 16. Checking that a string contains a substring
    String text = "hello world!";
    String subtext = "orl";
    // pay attention that this will return true for subtext=""
    boolean contains = text.contains(subtext);

    public static boolean contains(String text, String subtext) {
        return text.indexOf(subtext) != -1;
        // or lastIndexOf()
    }

    public static boolean contains2(String text, String subtext) {
        return text.matches("(?i).*" + Pattern.quote(subtext) + ".*");
    }

    // 17. Counting substring occurrences in string
    // Counting the number of occurrences of a string in another string is a problem
    // that can have at least two interpretations: 11 in 111 occurs 1 time11 in 111
    // occurs 2 times In the first case (11 in 111 occurs 1 time), the solution can
    // rely on the String.indexOf() method. One of the flavors of this method allows
    // us to obtain the index within this string of the first occurrence of the
    // specified substring, starting at the specified index (or -1, if there is no
    // such occurrence). Based on this method, the solution can simply traverse the
    // given string and count the given substring occurrences. The traversal starts
    // from position 0 and continues until the substring is not found:

    public static int countStringInString(String string, String toFind) {
        int position = 0;
        int count = 0;
        int n = toFind.length();
        while ((position = string.indexOf(toFind, position)) != -1) {
            position = position + n;
            count++;
        }
        return count;
    }

    // Alternatively, the solution can use the String.split() method. Basically, the
    // solution can split the given string using the given substring as a delimiter.
    // The length of the resulting String[] array should be equal to the number of
    // expected occurrences:

    public static int countStringInString2(String string, String toFind) {
        int result = string.split(Pattern.quote(toFind), -1).length - 1;
        return result < 0 ? 0 : result;
    }

    // In the second case (11 in 111 occurs 2 times), the solution can rely on the
    // Pattern and Matcher classes in a simple implementation, as follows:

    public static int countStringInString3(String string, String toFind) {
        Pattern pattern = Pattern.compile(Pattern.quote(toFind));
        Matcher matcher = pattern.matcher(string);
        int position = 0;
        int count = 0;
        while (matcher.find(position)) {
            position = matcher.start() + 1;
            count++;
        }
        return count;
    }

    // Checking whether two strings are anagrams

    // Two strings that have the same characters, but that are in a different order,
    // are anagrams. Some definitions impose that anagrams are case-insensitive
    // and/or that white spaces (blanks) should be ignored.So, independent of the
    // applied algorithm, the solution must convert the given string into lowercase
    // and remove white spaces (blanks). Besides that, the first solution we
    // mentioned sorts the arrays via Arrays.sort() and will check their equality
    // via Arrays.equals().Once they are sorted,if they are anagrams,
    // they will be equal (the following diagram shows two words that are anagrams):

    // 18. Declaring multiline strings
    // Starting with JDK 8, a solution may rely on String.join(), as follows:
    String testo1 = String.join(LS, "My high school, ", "the Illinois Mathematics and Science Academy,",
            "showed me that anything is possible ", "and that you're never too young to think big.");

    // Before JDK 8, an elegant solution may have relied on StringBuilder. This
    // solution is available in the code bundled with this book.While the preceding
    // solutions are good fits for a relatively large number of strings, the
    // following two are okay if we just have a few strings. The first one uses the
    // + operator:

    String testo2 = "My high school, " + LS + "the Illinois Mathematics and Science Academy," + LS
            + "showed me that anything is possible " + LS + "and that you're never too young to think big.";

    // The second one uses String.format():

    String testo3 = String.format("%s" + LS + "%s" + LS + "%s" + LS + "%s", "My high school, ",
            "the Illinois Mathematics and Science Academy,", "showed me that anything is possible ",
            "and that you're never too young to think big.");

    // 20. Concatenating the same string n times

    // Before JDK 11, a solution could be quickly provided via StringBuilder, as
    // follows:

    public static String concatRepeat(String str, int n) {
        StringBuilder sb = new StringBuilder(str.length() * n);
        for (int i = 1; i <= n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // Starting with JDK 11, the solution relies on the String.repeat(int count)
    // method. This method returns a string resulting from concatenating this string
    // count times. Behind the scenes, this method uses System.arraycopy(), which
    // makes this very fast:

    String risultato = "hello".repeat(5);

    // Other solutions that can fit well in different scenarios are listed as
    // follows: Following is a String.join()-based solution:

    String risultato2 = String.join("", Collections.nCopies(5, TEXT));

    // Following is a Stream.generate()-based solution:
    String risultato3 = Stream.generate(() -> TEXT).limit(5)
            .collect(joining());

    // Following is a String.format()-based solution:
    String risultato4 = String.format("%0" + 5 + "d", 0).replace("0", TEXT);

    // Following is a char[] based solution:
    String risultato5 = new String(new char[5]).replace("\0", TEXT);

    // To check whether a string is a sequence of the same substring, rely on the
    // following method:
    public static boolean hasOnlySubstrings(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() / 2; i++) {
            sb.append(str.charAt(i));
            String resultStr = str.replaceAll(sb.toString(), "");
            if (resultStr.length() == 0) {
                return true;
            }
        }
        return false;
    }

    // 20. Removing leading and trailing spaces
    String testo4 = "\n \n\n hello \t \n \r";
    String trimmed = testo4.trim();

    // The preceding snippet of code will work as expected. The trimmed string will
    // be hello. This only works because all of the white spaces that are being used
    // are less than U+0020 or 32 (the space character). There are 25 characters
    // (https://en.wikipedia.org/wiki/Whitespace_character#Unicode) defined as white
    // spaces and trim() covers only a part of them (in short, trim() is not Unicode
    // aware). Let's consider the following string:

    char space = '\u2002';
    String testo5 = space + "\n \n\n hello \t \n \r" + space;

    // \u2002 is another type of white space that trim() doesn't recognize (\u2002
    // is above \u0020). This means that, in such cases, trim() will not work as
    // expected. Starting with JDK 11, this problem has a solution named strip().
    // This method extends the power of trim() into the land of Unicode:

    // 22. Finding the longest common prefix
    // Let's consider the following array of strings:
    String[] texts = { "abc", "abcd", "abcde", "ab", "abcd", "abcdef" };

    // Now, let's put these string one below the other, as follows:
    // abc
    // abcd
    // abcde
    // ab
    // abcd
    // abcdef
    // A simple comparison of these strins reveals that "ab" is the longest common
    // prefix.
    // This solution takes the first string from the array and compares each of its
    // characters in the rest of the strings. The algorithm stops if either of the
    // following happens: -The length of the first string is greater than the length
    // of any of the other strings -The current character of the first string is not
    // the same as the current character of any of the other strings

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        int firstLen = strs[0].length();
        for (int prefixLen = 0; prefixLen < firstLen; prefixLen++) {
            if (prefixLen = strs[i].length() || strs[i].charAt(prefixLen) != ch) {
                return strs[i].substring(0, prefixLen);
            }
        }
        return strs[0];
    }

    // 23. Applying indentation
    // Starting with JDK 12, we can indent text via the String.indent(int n)
    // method.Let's assume that we have the following String values:

    // String days = "Sunday\n"
    // + "Monday\n"
    // + "Tuesday\n"
    // + "Wednesday\n"
    // + "Thursday\n"
    // + "Friday\n"
    // + "Saturday";
    // Printing this String values with an indentation of 10 spaces can be done as
    // follows:
    // System.out.print(days.indent(10));

    // Now, let's try a cascade indentation:

    public static String cascadeIndentation(List<String> days) {
        // List days = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
        // "Thursday", "Friday", "Saturday");
        for (int i = 0; i < days.size(); i++) {
            System.out.print(days.get(i).indent(i));
        }
    }

    // How about indenting a piece of HTML code? Let's see:
    String html = "";
    String body = "";
    String h2 = "";
    String textHtml = "Hello world!";
    String closeH2 = "";
    String closeBody = "";
    String closeHtml = "";
    // System.out.println(html.indent(0) + body.indent(4) + h2.indent(8) +
    // textHtml.indent(12) + closeH2.indent(8) + closeBody.indent(4) +
    // closeHtml.indent(0));

    // 24. Transforming Strings

    // Let's assume that we have a string and we want to transform it into another
    // string (for example, transform it into upper case). We can do this by
    // applying a function such as Function.In JDK 8, we can accomplish this via
    // map(), as shown in the following two simple examples:

    // hello world
    String resultMap = Stream.of("hello")
            .map(s -> s + " world")
            .findFirst()
            .get();
    // GOOOOOOOOOOOOOOOOL! GOOOOOOOOOOOOOOOOL!
    String resultMap2 = Stream.of("gooool! ")
            .map(String::toUpperCase)
            .map(s -> s.repeat(2))
            .map(s -> s.replaceAll("O", "OOOO"))
            .findFirst()
            .get();

    // Starting with JDK 12, we can rely on a new method named transform​(Function
    // f). Let's rewrite the preceding snippets of code via transform():

    // hello world
    String result3 = "hello".transform(s -> s + " world");
    // GOOOOOOOOOOOOOOOOL! GOOOOOOOOOOOOOOOOL!
    String result4 = "gooool!".transform(String::toUpperCase)
            .transform(s -> s.repeat(2))
            .transform(s -> s.replaceAll("O", "OOOO"));

    // While map() is more general, transform() is dedicated to applying a function
    // to a string and returns the resulting string.

    // 25. Computing the minimum and maximum of two numbers

    // Before JDK 8, a possible solution would be to rely on the Math.min() and
    // Math.max() methods, as follows:

    int i1 = -45;
    int i2 = -15;
    int min = Math.min(i1, i2);
    int max = Math.max(i1, i2);

    // The Math class provides a min() and a max() method for each primitive numeric
    // type (int, long, float, and double).Starting with JDK 8, each wrapper class
    // of primitive numeric types (Integer, Long, Float, and Double) comes with
    // dedicated min() and max() methods, and, behind these methods, there are
    // invocations of their correspondents from the Math class. See the following
    // example (this is a little bit more expressive):

    double d1 = 0.023844D;
    double d2 = 0.35468856D;
    double min2 = Double.min(d1, d2);
    double max2 = Double.max(d1, d2);

    // In a functional style context, a potential solution will rely on the
    // BinaryOperator functional interface. This interface comes with two methods,
    // minBy() and maxBy():
    float f1 = 33.34F;
    final float f2 = 33.213F;
    float min3 = BinaryOperator.minBy(Float::compare).apply(f1, f2);
    float max3 = BinaryOperator.maxBy(Float::compare).apply(f1, f2);

    // 26. Summing two large int/long values and operation overflow

    // Examples:
    int x = 2;
    int y = 7;
    int z = x + y; // 9

}