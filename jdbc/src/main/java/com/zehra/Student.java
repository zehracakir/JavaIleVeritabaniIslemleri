package com.zehra;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int class1;

    public Student() {
    }

    public Student(String firstName, String lastName, int class1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.class1 = class1;
    }

    public Student(int id, String firstName, String lastName, int class1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.class1 = class1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return this.lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public int getclass() {
        return this.class1;
    }

    public void setclass(int class1) {
        this.class1 = class1;
    }

}
