package com.trio.vmalogo.util.ips;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlTool {
	
	DocumentBuilderFactory docBuilderFactory;
	DocumentBuilder docBuilder;
	Document doc;
	public NodeList nodeList;

	public XmlTool() {
	}

	public void SetDocument(String xml)
	{
		
		try
		{
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setValidating(false);
			docBuilder = docBuilderFactory.newDocumentBuilder();

			xml = xml.trim();
			InputStream inputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));//xml为要解析的字符串

			doc = docBuilder.parse(inputStream);
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
		
	}
	                
	public int getNodeListCount(String NodeName)
	{
		NodeList list = doc.getDocumentElement().getElementsByTagName(NodeName);
	    int count = list.getLength();
	    return count;
	}
	
	public String getNodeValue(String NodeName)
	{
		try
		{
			NodeList list = doc.getDocumentElement().getElementsByTagName(NodeName);
			if (list == null || list.getLength() <= 0) {
				return "";
			}
	
	    	String txt = list.item(0).getFirstChild() == null ? "":list.item(0).getFirstChild().getNodeValue();

	    	return txt;
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return "";
		}
	    
	}

	public String getNodeXml(String... NodeNames)
	{
		StringBuilder sb = new StringBuilder();

		try
		{
			NodeList list = doc.getDocumentElement().getChildNodes();
			System.out.println(list.getLength());
			
			if (list.getLength() >  0) {
				for (int i=0; i<list.getLength(); i++) {
					if (!contains(list.item(i).getNodeName(), NodeNames)) {

						String txt = getNodeValue(list.item(i).getNodeName());
						if (txt=="")
							sb.append("<"+list.item(i).getNodeName()+"></"+list.item(i).getNodeName()+">");
						else
							sb.append("<"+list.item(i).getNodeName()+">"+txt+"</"+list.item(i).getNodeName()+">");
					
						System.out.println(sb.toString().trim());
					}
				}
			}

	    	return sb.toString();
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
			return "";
		}
	    
	}
	
	private boolean contains(String NodeName, String... NodeNames)
	{
		for (int i=0; i<NodeNames.length; i++)
		{
			if (NodeName.equals(NodeNames[i]))
				return true;
			else
				return false;
		}
		return false;
	}
	
	private NodeList getNodeList(String NodeName)
	{
		NodeList list = doc.getDocumentElement().getElementsByTagName(NodeName);
	    return list;
	}

	//2016-03-28 by limer2
	public List<Map<String, String>> getMapList(String NodeName)
	{
		List<Map<String, String>> ml = new ArrayList<Map<String, String>>();

		try {
			NodeList nodeList = doc.getDocumentElement().getElementsByTagName(NodeName);
			nodeList = nodeList.item(0).getChildNodes();
			final int nodeListCount = nodeList.getLength();

			for (int i = 0; i < nodeListCount; i++) {
				Map<String, String> map = new HashMap<String, String>();
				NodeList childNodeList = nodeList.item(i).getChildNodes();
				final int childNodeListCount = childNodeList.getLength();

				String name, value;
				for (int j = 0; j < childNodeListCount; j++) {
					name = childNodeList.item(j).getNodeName();
					value = childNodeList.item(j).getFirstChild() == null? "":childNodeList.item(j).getFirstChild().getNodeValue();
					map.put(name, value);
				}
				ml.add(map);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());

		}

		return ml;
	}

}
