package timeAndExtends;

//test extends
public class MyNewTime extends MyTime {
    public MyNewTime(String name, String zt) {
        super(name, zt);
    }

    public String getSuperSuperName() {
        return super.getSuperName();
    }

    public static void main(String[] args) {
        MyNewTime myNewTime = new MyNewTime("shiny", "live");
        Time time = myNewTime;
        System.out.println(time.name);
    }
}
