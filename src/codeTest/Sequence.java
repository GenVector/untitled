package codeTest;
// innerclasses/Sequence.java
// Holds a sequence of Objects


//abstract class Selector {
//    abstract boolean end();
//
//    abstract Object current();
//
//    abstract void next();
//}

interface Selector {
    boolean end();

    Object current();

    void next();
}

public class Sequence {

    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    protected class SequenceSelector implements Selector, Sorted {
        private int i = 0;

        @Override
        public boolean end() {
            i = next;
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) i++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}