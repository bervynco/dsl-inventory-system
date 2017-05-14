/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author L R E
 */
public class User {
    private static int employeeID;
    private static  String fullName;
    private static String role;

    public static int getEmployeeID() {
        return employeeID;
    }

    public static void setEmployeeID(int employeeID) {
        User.employeeID = employeeID;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        User.fullName = fullName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        User.role = role;
    }
}
