package jvm.classloader;

import leetcode.Exe1;

public class ClassLoaderTest {

    private static final Exe1 exe1 = new Exe1();

    public static void main(String[] args) {
        Class<? extends Exe1> aClass = exe1.getClass();
        System.out.println(aClass);
        ClassLoader classLoader = aClass.getClassLoader();

    }
}
