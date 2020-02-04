package io.aashay.Mario;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class MapCreation {
    private static void  getXML(){
        File mapXML = new File("/src/main/resources/map.xml");
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document map = dBuilder.parse(mapXML);

            System.out.println(map.getDocumentElement().getNodeName());
            
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}

