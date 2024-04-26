package com.saket.ui;

import java.util.ArrayList;
import java.util.List;

/** This sample is to test java formatting for Spotless */
public class SampleClass {

    public String getName() {
        System.out.println("Get Class name called ");
        return "SampleClass";
    }

    public void printListOfNames() {
        List<String> lstNames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lstNames.add("Item: " + i);
        }
        lstNames.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}
