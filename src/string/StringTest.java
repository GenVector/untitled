package string;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.stream.Collectors;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class StringTest {
    public static void main(String[] args) {
        replaceTest();
    }

    public static void splitTest1() {
        String str = ";;;;;1;;";
        String[] arr = str.split(";");
        for (String st : arr) {
            System.out.println(st);
        }
        System.out.println(str.split(";").length);
    }

    public static void replaceTest() {
        String picurl = "nodasdakjahfkja@1@RiZhao-MEDIA";
        System.out.println(picurl);
        picurl = picurl.replace("@RiZhao-MEDIA", "");
        System.out.println(picurl);
        String[] strs = picurl.split("@");
        if (strs != null && strs.length > 2) {
            picurl = strs[0] + "@" + strs[1];
        }
        System.out.println(picurl);
        System.out.println("yyyyMMddHHmmss".length());
    }

    //split not throw exception,min length is 1
    public static void splitTest() {
        String str1 = "asdas_dsad\\as\n\t\t\td/asd[asd]asd";
        String str = str1.split("_")[0];
        System.out.println(str);
        str = str1.split("\\[")[0];
        System.out.println(str);
        str = str1.split("\\/")[0];
        System.out.println("----\\/:" + str);
        str = str1.split("/")[0];
        System.out.println("----/:" + str);
        str = str1.split("\\\\")[0];
        System.out.println(str);
        str = str1.split("\n")[0];
        System.out.println("----\\n:" + str);

    }

    //\\ test
    public static void zhuanyiTest() {
        System.out.println("hi shiny\thi sherry ");
        System.out.println("hi shiny\nhi sherry ");
    }


    //一个正确的String，一个错误的String，找出错误的位置
    public static void findErrChar(String strTrue, String strFalse) {
        System.out.println(strTrue.equals(strFalse));
        char[] trueArr = strTrue.toCharArray();
        char[] falseArr = strFalse.toCharArray();
        for (int i = 0; i < falseArr.length; i++) {
            if (falseArr[i] - trueArr[i] != 0) {
                System.out.println(i);
                System.out.println(trueArr[i]);
                System.out.println(falseArr[i]);
            }
        }

    }

    //long -> timeString
    public static String LongToTimeStr(Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(time));
        return simpleDateFormat.format(date);
    }

    //找出b插入的字符
    public static void StrTest() {
        String a = "23221312asdafa";
        String b = "23221312Aasdafa";
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        int i;
        int al = 0;
        int bl = 0;
        for (i = 0; i < charsA.length; i++) {
            if (charsA[i] != charsB[i]) {
                System.out.println(i + 1 + ":" + charsB[i]);
                break;
            }
        }
        if (i > charsA.length)
            System.out.println(charsB.length + ":" + charsB[charsA.length]);

        for (i = 0; i < charsA.length; i++) {
            al += charsA[i];
            bl += charsB[i];
        }
        bl += charsB[i];
        System.out.println((char) (bl - al));

        //System.out.println(a.compareTo(b));
    }

    //format test
    public static void stringFormatTest() {
        String str = "test %s %s";
        str = String.format(str, "2", "3");
        String[] strs = str.split(",");
        PrintStream outAlias = System.out;
        Formatter formatter = new Formatter();
        str = "test %s %s";
        formatter.format(str, "2", "3");
    }

    public static String format(String format, Object... args) {
        return new Formatter().format(format, args).toString();
    }

    //lambda collectors
    public static String string2() {
        String result = new Random(47)
                .ints(25, 0, 100)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    public static String getPhotoUrl(String key) {
        String md5 = "sd67dfdsgsdsdsasadasgg";
        String url = "http://50.105.13.165:80" + "/car_image/" + md5.substring(0, 2) + "/" + md5.substring(2, 4) + "/" + key + ".jpg";
        System.out.println(url);
        return url;
    }
}