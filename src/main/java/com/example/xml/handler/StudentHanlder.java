package com.example.xml.handler;

import com.example.xml.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class StudentHanlder extends DefaultHandler {
    private List<Student> students = new ArrayList<>();
    private Student currentStudent;
    private boolean isFirstName;
    private boolean isLastName;
    private boolean isNickName;
    private boolean isMark;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Thẻ mở của thẻ: " + qName);
        if (qName.equals("student")){
            currentStudent = new Student();
        } else if (qName.equals("firstname")){
            isFirstName = true;
        } else if (qName.equals("lastName")){
            isLastName = true;
        } else if (qName.equals("nickname")){
            isNickName = true;
        }else if (qName.equals("marks")){
            isMark = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Thẻ đóng của thẻ: " + qName);
        if (qName.equals("student")){
            students.add(currentStudent);
        } else if (qName.equals("firstname")){
            isFirstName = false;
        } else if (qName.equals("lastName")){
            isLastName = false;
        } else if (qName.equals("nickname")){
            isNickName = false;
        }else if (qName.equals("marks")){
            isMark = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        if (isFirstName){
            currentStudent.setFirstName(content);
        }else if (isLastName){
            currentStudent.setLastName(content);
        }else if (isNickName){
            currentStudent.setNickName(content);
        }else if (isMark){
            currentStudent.setMark(Integer.parseInt(content));
        }
        System.out.println("Nội dung trong thẻ: " + new String(ch, start, length));
    }
}
