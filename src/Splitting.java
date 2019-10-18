// strings/Splitting.java import java.util.*; 

import java.util.Arrays;

public class Splitting {
    public static String knights =
            "Then, when you have found the shrubbery, " +
                    "you must cut down the mightiest tree in the " +
                    "forest...with... a herring!";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
//        split(" "); // Doesn't have to contain regex chars
//        split("\\W+"); // Non-word characters
//        split("n\\W+"); // 'n' followed by non-words
        replacing();
    }

    public static void matches(String regex) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }

    public static void replacing() {
        System.out.println(knights.replaceFirst("f\\w+", "located"));
        System.out.println(knights.replaceAll("shrubbery|tree|herring", "banana"));
    }
}

class Rudolph {
    public static void main(String[] args) {
        String[] patterns = new String[]{
                "Rudolph",
                "[rR]udolph",
                "[rR][aeiou][a-z]ol.*",
                "R.*"};
        for (String pattern : patterns)
            System.out.println("Rudolph".matches(pattern));
    }
}
