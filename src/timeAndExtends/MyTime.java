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

    public MyTime(String name, String zt) {
        super(name, zt);
        System.out.println("MyTime init");
        this.name = name;
    }

    public String getThisName() {
        return name;
    }

    public String getSuperName() {
        return super.getName();
    }

    public String getThisName(final Time time) {
        //final 变量无法 更新引用
        //time = new Time();
        return name;
    }

    public String getThisName(final int time) {
        //time = 3;
        return name;
    }

    //override注解只是帮助你检查合法性,此时你仍然可以通过super访问到父类方法
    @Override
    public String getZt() {
        return "init";
    }

    public String getSuperZt() {
        return super.getZt();
    }

    public static void main(String[] args) {
//        MyExtend myExtend = new MyExtend();
//        myExtend.name = "test";
//        myExtend.name = "eee";
//        System.out.println(myExtend.name);
        MyTime myTime = new MyTime("father", "healthy");
        Time time = (Time) myTime;
        System.out.println(myTime.state);
        System.out.println(time.state);
        System.out.println(time.state);
//        myTime.name = "son";
//        System.out.println(myTime.getSuperName());
//        System.out.println(myTime.getThisName());
//        System.out.println(myTime.getZt());
//        System.out.println(myTime.getSuperZt());

        long t = 950400000L;
        Instant instant=Instant.now().minusMillis(t);
        System.out.println(instant);
    }
}