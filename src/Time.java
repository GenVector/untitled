import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Time {

    protected String name;
    protected String zt;

    public Time(String name, String zt) {
        this();
        this.name = "no one";
        this.zt = zt;
    }

    public Time() {
        name = "";
    }

    public String getZt() {
        return this.zt;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String args[]) {

        timeTest();
    }

    public static void timeTest() {
        long t1 = Instant.now().getEpochSecond();
        long t2 = Instant.now().toEpochMilli();
        long t3 = new Date().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        System.out.println(simpleDateFormat.format(t2));
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }

    public static String rturnStr() {
        Instant now = Instant.now();
        Instant ins = Instant.parse("2018-11-08T08:40:55.493Z");
        ins.toEpochMilli();
        return ins.toString();
    }
}
