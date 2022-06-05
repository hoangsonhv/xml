package com.example.xml;

import com.example.xml.handler.StudentHanlder;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class XmlApplication {

    public static void main(String[] args) {
       try {
           File inputFile = new File("mypom.xml");
           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser saxParser = factory.newSAXParser();
           StudentHanlder studentHanlder = new StudentHanlder();
           saxParser.parse(inputFile, studentHanlder);
           List<Student> lists = studentHanlder.getStudents();
           for (int i = 0; i < lists.size(); i++) {
               System.out.println(lists.get(i).toString());
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
