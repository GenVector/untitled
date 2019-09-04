import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTest {
    public static void main(String[] args) {
        zhuanyiTest();
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

    //split not throw exception,min length is 1
    public void splitTest() {
        String str = "asdas_dsad";
        String str1 = "asda";
        str = str.split("_")[0];
        System.out.println(str);
    }


    //找出b插入的字符
    public static void StrTest(){
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

    public static void stringFormatTest() {
        String str = "test %s %s";
        str = String.format(str, "2", "3");
        String[] strs = str.split(",");
    }

    public static void zhuanyiTest() {
        System.out.println("hi shiny\thi sherry ");
        System.out.println("hi shiny\nhi sherry ");
    }
}