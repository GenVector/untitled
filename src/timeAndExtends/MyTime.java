package timeAndExtends;

import java.time.Instant;

public class MyTime extends Time {

    String name;
    String state = state();

    public String state() {
        System.out.println("mytime state");
        //此处super.state=this.state,子类state()会替换掉父类
        System.out.println(super.state);
        return "mytime state";
    }

    public MyTime() {
        name = "my time";
    }

    public MyTime(String name, String state) {
        System.out.println("MyTime init");
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getSuperName() {
        return super.getName();
    }

    public String getSuperState() {
        return super.state();
    }

    public String getState() {
        return state;
    }

    public static void main(String[] args) {
//        MyExtend myExtend = new MyExtend();
//        myExtend.name = "test";
//        myExtend.name = "eee";
//        System.out.println(myExtend.name);
        MyTime myTime = new MyTime("my time", "my time state");
        Time time = (Time) myTime;
        System.out.println("-------------");
        System.out.println(myTime.state);
        System.out.println(myTime.name);
        System.out.println(time.state);
        System.out.println(time.name);
        System.out.println(time.state());
//        myTime.name = "son";
       System.out.println(myTime.getSuperName());
//        System.out.println(myTime.getThisName());
//        System.out.println(myTime.getZt());
        System.out.println(myTime.getSuperState());

//        long t = 950400000L;
//        Instant instant = Instant.now().minusMillis(t);
//        System.out.println(instant);
    }
}