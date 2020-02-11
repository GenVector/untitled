package gc;

import java.util.ArrayList;
import java.util.List;

public class OOMObject {
    public static void main(String[] args) {
            System.out.print("Xmx=");
            System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

            System.out.print("free mem=");
            System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

            System.out.print("total mem=");
            System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

