package timeAndExtends;

//test extends
public class MyNewTime extends MyTime {
    public MyNewTime(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getSuperSuperName() {
        return super.getSuperName();
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        MyNewTime myNewTime = new MyNewTime("my new time", "my new time state");
        System.out.println("----------------");
        System.out.println(myNewTime.getName());
        System.out.println(myNewTime.getSuperSuperName());
        Time time = myNewTime;
        System.out.println(time.name);
    }
}

