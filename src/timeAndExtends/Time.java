package timeAndExtends;

import java.text.SimpleDateFormat;
import java.time.Instant;

public class Time {

    protected String name;

    public String state = state();

    public String state() {
        System.out.println("time state");
        return "time state";
    }

    public String getState() {
        return state;
    }

    public Time(String name, String state) {
        this();
        System.out.println("time init");
        this.name = name;
        this.state = state;
    }

    public Time() {
        name = "time";
    }

    public String getName() {
        return this.name;
    }

    public static void main(String args[]) throws Exception {

        timeTest();
    }


    public static void timeTest() throws Exception {
        //10位
        long t1 = Instant.now().getEpochSecond();
        long t2 = Instant.now().toEpochMilli();
        System.out.println(t1);
        System.out.println(t2);
        //13位
//        long t2 = Instant.now().toEpochMilli();
//        long t3 = new Date().getTime();
//        Instant instant = Instant.now();
//        System.out.println(instant);
//        instant = instant.plusMillis(-1000);
//        System.out.println(instant);
//        instant = instant.plusSeconds(-1000);
//        System.out.println(instant);
//        System.out.println(instant.isAfter(instant.plusSeconds(-1000)));
//        System.out.println(instant.isBefore(instant.plusSeconds(-1000)));
//
//        Timestamp timestamp =Timestamp.from(instant);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        System.out.println(simpleDateFormat.format(t2));
//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println(t3);
//        String timeStr = "2019-09-08T08:40:55.493Z";
//        timeStr.substring(0, 10);
//        Instant ins = Instant.parse("2019-09-08T08:40:55.493Z");

        String timeStr = "2019-12-04T14:03:53.000+08:00";
//        timeStr = "2001-07-04T12:08:56.235-07:00";
        //Instant ins = Instant.parse(timeStr);

        //Object nDate = new Date(timeStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Instant instant = Instant.ofEpochMilli(simpleDateFormat.parse(timeStr).getTime());
        System.out.println(instant);
//        System.out.println((new Date().getTime() - (ins.toEpochMilli() - 3600000L * 72L)) / (1000 * 60 * 60));
    }

    public static String rturnStr() {
        Instant now = Instant.now();
        Instant ins = Instant.parse("2018-11-08T08:40:55.493Z");
        ins = Instant.parse("2019-09-12 16:36:01");
        ins.toEpochMilli();
        return ins.toString();
    }
}
