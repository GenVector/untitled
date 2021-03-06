// innerclasses/Parcel2.java
// Returning a reference to an inner class
public class Parcel1 {
    class Contents {
        private int i = 11;

        public int value() { return i; }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() { return label; }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");
        Parcel1 q = new Parcel1();
        // Defining references to inner classes:
        Parcel1.Contents c = q.contents();
        Parcel1.Destination d = q.to("Borneo");
    }
}