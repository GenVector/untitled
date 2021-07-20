package timeAndExtends;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Time> timeList = new ArrayList<>();
    }

    public List<? extends Time> getTimeList() {

//        List<Time> timeList = new ArrayList<>();
//        return timeList;

        List<MyTime> myTimeList = new ArrayList<>();
        myTimeList.add(new MyTime());
        return myTimeList;
    }

}
