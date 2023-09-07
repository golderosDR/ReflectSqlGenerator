package fwrk;

import classes.Staff;
import classes.test.TestClass1;
import mysql.SQLGenerator;

public class Main {
    public static void main(String[] args) {
        SQLGenerator sqlGenerator = new SQLGenerator();
        System.out.println(sqlGenerator.generateTable(Staff.class));
        System.out.println(sqlGenerator.generateTable(TestClass1.class));
    }
}