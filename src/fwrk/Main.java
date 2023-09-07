package fwrk;

import models.Staff;
import mysql.SQLGenerator;

public class Main {
    public static void main(String[] args) {
SQLGenerator sqlGenerator = new SQLGenerator();
        System.out.println(sqlGenerator.generateTable(Staff.class));
    }
}