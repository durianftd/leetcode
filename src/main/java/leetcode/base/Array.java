package leetcode.base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Array {

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        List<Integer> ll = new LinkedList<>();

        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        try {
            list.add(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(list);
    }
}
