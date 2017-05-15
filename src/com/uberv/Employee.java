package com.uberv;

import java.io.Serializable;

// Class must implement the Serializable interface
public class Employee implements Serializable {
    // All class's fields must also be Serializable
    // (Object implement Serializable orprimitives)
    public String name;
    public String address;
    // Non-serializable fields must be declared as transient
    public transient int SSN;
    public int number;

    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", SSN=" + SSN +
                ", number=" + number +
                '}';
    }
}
