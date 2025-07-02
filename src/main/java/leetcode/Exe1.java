package leetcode;

public class Exe1 {

    public static void main(String[] args) {
        ClassLoader classLoader = Exe125.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("Hello World");

        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
