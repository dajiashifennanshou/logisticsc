package com.brightsoft.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 
* @类名: XmlParser 
* @类功能描述: 解析xml
 */
public class XmlParser{
	
	
	
	public static String InputStreamToString(InputStream in){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for (;;) {
                int size = in.read(temp);
                if (size != -1) {
                    out.write(temp, 0, size);
                } else {
                    break;
                }
            }
            String result = new String(out.toString().getBytes(),"UTF-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public static Document InputStreamToDocument(InputStream in){
		try {
			
			
			 DocumentBuilder documentBuilder = null;
			try {
				documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Document document = null;
			try {
				document = documentBuilder.parse(in);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
