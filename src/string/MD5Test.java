package string;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MD5Test {
    public static void main(String[] args) {

        System.out.println(Timestamp.from(Instant.now()).toString());
        System.out.println(new Date().toString());
        String url = "/openapi/service/vss/footfall/getFootfallData";
        String dataJson = "";
        String secret = "5bf8d88cfec14babb3dc68a08e5f2e0f";
        String md5 = MD5Utils.stringToMD5(url + dataJson + secret);
        System.out.println(md5);


        String authUrl = "/openapi/service/base/user/getDefaultUserUuid";
        Long time = Instant.now().toEpochMilli();
        Map<String, Object> requetParam = new HashMap<>();
        requetParam.put("appkey", "9361ffa9");
        requetParam.put("time", time);
    }


}

class MD5Utils {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code.toUpperCase();
    }

}
