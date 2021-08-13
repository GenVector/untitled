package proxy;

class Animal {
    public void run() {
        System.out.println("animal run");
    }
}

class Person extends Animal {
    public void run() {
        System.out.println("human run");
        super.run();
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        Person person = new Person();
        Animal animal = person;
        animal.run();
    }
}
