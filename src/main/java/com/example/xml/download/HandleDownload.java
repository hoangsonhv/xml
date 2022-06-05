package com.example.xml.download;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandleDownload {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            File inputFile = new File("mypom.xml");
            Document doc = builder.parse(inputFile);
            NodeList node = doc.getElementsByTagName("dependency");
            List<DependencyParser> dependencyParserList= new ArrayList<>();
            for (int i = 0; i < node.getLength(); i++) {
                Node nNode = node.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) nNode;
                    String groupId = element.getElementsByTagName("groupId").item(0).getTextContent();
                    String artifactId = element.getElementsByTagName("artifactId").item(0).getTextContent();
                    String version = element.getElementsByTagName("version").item(0).getTextContent();
                    DependencyParser article = new DependencyParser(groupId,artifactId,version);
                    dependencyParserList.add(article);
                }
            }
            String urlBase ="https://repo1.maven.org/maven2/%s/%s/%s/%s";//group,Artifact,version,nameVer
            for (DependencyParser dependency: dependencyParserList
            ) {
                String group = dependency.getGroupId().replace(".","/");
                String nameVer = dependency.getArtifactId()+"-"+dependency.getVersion()+".jar";
                String linkComplete= String.format(urlBase,group,dependency.getArtifactId(),dependency.getVersion(),nameVer);
                new Thread(()->HandleDownload.downloadFile(nameVer,linkComplete));
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
    public static void downloadFile(String fileName, String fileLink) {
        try {
            Connection.Response response = Jsoup.connect(fileLink).ignoreContentType(true).execute();
            FileOutputStream out = (new FileOutputStream(fileName));
            out.write(response.bodyAsBytes());
            out.close();
            System.out.printf("Dowload file %s success!", fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
