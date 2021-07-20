package visitcontrol;

public class ProtectClassT1 {
    protected String getStr() {
        return "123";
    }

    public static void main(String[] args) {
        ProtectClassT1 t1 = new ProtectClassT1();
        t1.getStr();
    }
}
class ProtectClassT2 {
    protected String getStr() {
        return "123";
    }

    public static void main(String[] args) {
        ProtectClassT1 t1 = new ProtectClassT1();
        t1.getStr();
    }
}