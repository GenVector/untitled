package socket;

import java.lang.reflect.Array;

public class Generator<T> {
    public static void main(String[] args) {
        MList<String> mList = new MList<>(String.class, 10);
        mList.add("shiny");
        System.out.println(mList.get(0));
        System.out.println(mList.test());
    }

    private T t;

    public static <T> void printArray(T[] inputArray) {
        // 输出数组元素
        for (T element : inputArray) {
            System.out.print(element);
        }
        System.out.println();
    }

    public void printArr(T[] inputArray) {
        // 输出数组元素
        for (T element : inputArray) {
            System.out.print(element);
        }
        System.out.println();
    }
}

class FruitGenerator<T> extends Generator<T> {
    private T t;

    public T next() {
        return t;
    }

    public FruitGenerator(T t) {
        this.t = t;
    }

    public static void main() {
        FruitGenerator<String> fruitGenerator = new FruitGenerator<String>("codeTest");
    }
}

class MList<T> extends Generator<T> {
    private Object[] ts;
    private T[] tArr;
    private int hand;
    private int size;

    private <T> T elementData(int index) {
        return (T) ts[index];
    }

    private T getByIndex(int index) {
        return tArr[index];
    }

    public void add(T t) {
        if (size == ts.length) {
            Object[] arr = new Object[size * 2];
            for (int i = 0; i < size; i++)
                arr[i] = ts[i];
            ts = arr;
        }
        ts[size] = t;
        tArr[size++] = t;

    }

    public MList(Class<T> type, int size) {
        tArr = (T[]) Array.newInstance(type, size);
        ts = new Object[size];
        this.size = 0;
    }


    public T next() {
        if (hand < size)
            return elementData(hand++);
        else return null;
    }

    public T get(int i) {
        return this.tArr[i];
    }

    public int test() {
        try {
            System.out.println("try");
            //System.exit(0);
            return 0;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
            return 2;
        }
        //return 1;
    }
}