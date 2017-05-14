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
public class Logs {
    private static int employeeID;
    private static String fullName;
    private static String pageName;
    private static String action;
    private static String timestamp;

    public static int getEmployeeID() {
        return employeeID;
    }

    public static void setEmployeeID(int employeeID) {
        Logs.employeeID = employeeID;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        Logs.fullName = fullName;
    }

    public static String getPageName() {
        return pageName;
    }

    public static void setPageName(String pageName) {
        Logs.pageName = pageName;
    }

    public static String getAction() {
        return action;
    }

    public static void setAction(String action) {
        Logs.action = action;
    }

    public static String getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(String timestamp) {
        Logs.timestamp = timestamp;
    }
}
