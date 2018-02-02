package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/29 10:13
 * Description
 */
public class RegexExample {

    public static void main(String[] args) {
        String input = "Hello World ";

        Pattern pattern = Pattern.compile("\\S+");
        boolean match = pattern.asPredicate().test(input);
        System.out.println(match);
        Matcher matcher = pattern.matcher(input);

        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "@@@");
            System.out.println(matcher.group(0));
        }
        matcher.appendTail(stringBuffer);

        System.out.println(stringBuffer.toString());

        nonSuffix();
        // imageRegex();
    }

    private static void nonSuffix() {
        final String[] urls = {"http://i04.pic.sogou.com/3c445b477f98381d", "http://emoi-cncdn.bing.com/emotionimage/chat/37f6d9bb8d3ef5ca2c4efb01d938025d.gif"};
        Stream.of(urls).forEach(RegexExample::getImgFileName);
    }

    private static String getImgFileName(final String imgUrl) {
        final String suffixRegex = "[\\w\\d]+\\.(jpe?g|png|gif)";
        final String nonSuffixRegex = "[\\w\\d]+$";
        Pattern suffixPattern;
        Pattern nonSuffixPattern;
        Matcher suffixMatcher;
        Matcher nonSuffixMatcher;

        suffixPattern = Pattern.compile(suffixRegex, Pattern.CASE_INSENSITIVE);
        nonSuffixPattern = Pattern.compile(nonSuffixRegex, Pattern.CASE_INSENSITIVE);
        suffixMatcher = suffixPattern.matcher(imgUrl);
        nonSuffixMatcher = nonSuffixPattern.matcher(imgUrl);
        String fileName = "";
        if (suffixMatcher.find()) {
            fileName = suffixMatcher.group();
        } else if (nonSuffixMatcher.find()) {
            fileName = nonSuffixMatcher.group() + ".gif";
        }
        System.out.println(fileName);
        return fileName;
    }

    private static void imageRegex() {
        final String input = "heheh he heh <img src=\"http://sslieflskjfoelakji.jpg\" /> asfasffasdf s <img src=\"https://sslieflskjfoelakji.JPEG\" />";

        final String imageRegex = "(https?://)\\S+(\\.(jpe?g|png|gif))";
        Pattern pattern = Pattern.compile(imageRegex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(input);
        int groupCnt = matcher.groupCount();
        System.out.println("Group Cnt: " + groupCnt);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group());
            matcher.appendReplacement(buffer, "@");
        }
        matcher.appendTail(buffer);
        System.out.println(buffer.toString());
    }

}
