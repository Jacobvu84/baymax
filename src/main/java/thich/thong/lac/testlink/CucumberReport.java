package thich.thong.lac.testlink;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CucumberReport {
	public ArrayList<HashMap<String, String>> getResultFromJunitReport(String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		File fXmlFile = new File(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		ArrayList<HashMap<String, String>> params = new ArrayList<HashMap<String,String>>();
		NodeList nTestCase;
		nTestCase = doc.getElementsByTagName("testcase");
		for (int temp = 0; temp < nTestCase.getLength(); temp++) {
			HashMap<String,String> executionData = new HashMap<String,String>();
			Node nNode = nTestCase.item(temp);
			String name="";
			String result="";	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				name = eElement.getAttribute("name");
				NodeList nResult = eElement.getElementsByTagName("system-out");
				if(nResult.getLength()==0){
					result="f";
				}
				else{
					result="p";
				}
				executionData.put("name", name);
				executionData.put("result", result);
				params.add(executionData);
				System.out.println("result of testcase "+name+" is "+result);
			}
		}
		return params;
	}
}
