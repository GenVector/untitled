import codetest.AnnotationTest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class Time {

    protected String name;
    public String zt = zt();

    public String state = state();

    public String state() {
        System.out.println("time state");
        return "time state";
    }

    public String zt() {
        System.out.println("time zt");
        return "zt";
    }

    public Time(String name, String zt) {
        this();
        System.out.println("time init");
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
//        long t1 = Instant.now().getEpochSecond();
//        long t2 = Instant.now().toEpochMilli();
//        long t3 = new Date().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        System.out.println(simpleDateFormat.format(t2));
//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println(t3);
        Instant ins = Instant.parse("2019-09-08T08:40:55.493Z");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println((new Date().getTime() - (ins.toEpochMilli() - 3600000L * 72L)) / (1000 * 60 * 60));
    }

    public static String rturnStr() {
        Instant now = Instant.now();
        Instant ins = Instant.parse("2018-11-08T08:40:55.493Z");
        ins = Instant.parse("2019-09-12 16:36:01");
        ins.toEpochMilli();
        return ins.toString();
    }
}
