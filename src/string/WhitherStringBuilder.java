package string;

import java.util.Random;
import java.util.stream.Collectors;

import static base64.ImageToBase64.imageToBase64ByLocal;

public class WhitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for(String field : fields) {
            result += field;
        }
        return result;
    }
    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for(String field : fields) {
            result.append(field);
        }
        String res = new Random(47)
                .ints(25, 0, 100)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        return result.toString();
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder =new StringBuilder();
        String path="/Users/guoshiyu/IdeaProjects/4.jpg";
        String base64=imageToBase64ByLocal(path);
        stringBuilder.append(base64);
        stringBuilder.append(base64);

    }
}