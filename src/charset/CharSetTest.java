package charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;

public class CharSetTest {
    public static void main(String[] args) throws Exception {
        String str = "[{\"text\" 郭诗雨:\"\uD83D\uDE03\uD83D\uDE0F\uD83D\uDE20\uD83D\uDE21\uD83D\uDE1E\uD83D\uDE1E\",\"type\":\"HIMText\"}]";
        System.out.println(str);
        Charset charset = Charset.forName("GB2312");
        CharBuffer charBuffer = CharBuffer.wrap(str);
        ByteBuffer encode = charset.newEncoder()
                .onUnmappableCharacter(CodingErrorAction.REPLACE)
                .onMalformedInput(CodingErrorAction.REPLACE)
                .encode(charBuffer);
        String str2 = new String(encode.array(), "GB2312");
        byte[] bytes =  str2.getBytes("UTF-8");

        System.out.print(new String(bytes).trim());
    }
}
