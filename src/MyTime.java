public class MyTime extends Time {

    String name;

    public MyTime(String name, String zt) {
        super(name, zt);
        this.name = name;
    }

    public String getThisName() {
        return name;
    }
    public String getSuperName(){
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
        myTime.name = "son";
        System.out.println(myTime.getSuperName());
        System.out.println(myTime.getThisName());
        System.out.println(myTime.getZt());
        System.out.println(myTime.getSuperZt());
    }
}