package com.example.xml;

import com.example.xml.handler.StudentHanlder;

public class Student extends StudentHanlder {
    private String firstName;
    private String lastName;
    private String nickName;
    private Integer mark;

    public Student() {
    }

    public Student(String firstName, String lastName, String nickName, Integer mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.mark = mark;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mark=" + mark +
                '}';
    }
}
