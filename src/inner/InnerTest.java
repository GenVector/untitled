package inner;

public class InnerTest {
    public static void main(String[] args) {
        String name = Inner.name;
        Inner inner = new Inner();
        inner.level = "level";
    }

    public static class Inner {
        public static String name;
        public String level;

        public void test() {
            int i = 5;
        }

        public static void main(String[] args) {

        }

    }
}
