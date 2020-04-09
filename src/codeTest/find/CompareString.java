package codeTest.find;

public class CompareString {
    public static void main(String[] args) {
        String str1 = "abc346dfgdkasjdkfjkk3467";
        String str2 = "346f";
        System.out.println(contains(str1, str2));

    }

    public static boolean contains(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        boolean bool = false;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                for (int j = 0; j < chars2.length; j++) {
                    if (j + i >= chars1.length)
                        return bool;
                    bool = chars1[j + i] == chars2[j];
                }
                if (bool == true)
                    return bool;
            }
        }
        return bool;
    }


}
