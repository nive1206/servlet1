package com.gsc.sample;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config{

	public static void main(String[] args) {
		 File stocks = new File("config.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder;
		
				try {
					dBuilder = dbFactory.newDocumentBuilder();
				
			
		    Document doc = dBuilder.parse(stocks);
		    doc.getDocumentElement().normalize();

		    System.out.println(doc.getDocumentElement().getNodeName());
		    NodeList nodes = doc.getElementsByTagName("definition");

		    for (int i = 0; i < nodes.getLength(); i++) {
		      Node node = nodes.item(i);
		      if (node.getNodeType() == Node.ELEMENT_NODE) {
		        Element element = (Element) node;
		        System.out.println("Value " + getValue("scriptPath", element));
		        
		      }
		    }
		  
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
		  static String getValue(String tag, Element element) {
		    NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		    Node node = (Node) nodes.item(0);
		    return node.getNodeValue();
		  }
}
