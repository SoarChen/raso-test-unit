package com.raso.test.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.raso.test.ITestUnit;
import com.raso.test.TestUnit;

import android.text.TextUtils;
import android.util.Log;

public class DomXMLParser implements IXMLParser {
	private final static String TAG = "XmlParser";
//	protected Document mDocument = null;
	protected Element mRootNode = null;
	static private DocumentBuilder mDocumentBuilder = null;
	
	
	private final String ELEMENT_TEST_ITEM = "TestItem";
	private final String ELEMENT_TEST_ITEM_TYPE = "TestItemType";
	
	public DomXMLParser() {
		//initXml();
	}
	
	public boolean initXml() {    
    	boolean  result = true;    	
		try {
			if (mDocumentBuilder == null) {
				DocumentBuilderFactory documentBuildFactory = DocumentBuilderFactory.newInstance();
				mDocumentBuilder = documentBuildFactory.newDocumentBuilder();
			}
		} catch (ParserConfigurationException e ) {                
           e.printStackTrace();
           result = false;   		   
        } 

    	return result;
    } 
	
	public ITestUnit parseXmlDocumentToTestUnit(InputStream inputStream) {
		if(mDocumentBuilder == null) {
			return null;
		}
		
		synchronized (mDocumentBuilder) {			
			Document document = null;
			try {		
				document = mDocumentBuilder.parse(inputStream);
			} catch (IOException ioe) {		         
				ioe.printStackTrace();
			} catch (SAXException e) {                  
				e.printStackTrace();
			} catch (Exception ex) {                
				Log.d(TAG, "XML parse fail");
			}
			
			ITestUnit testUnit = new TestUnit();
			mRootNode = document.getDocumentElement();
			if (mRootNode != null) {
				String rootTagName = mRootNode.getTagName();
				testUnit.setUnitName(rootTagName);				
				NodeList nodeList = mRootNode.getChildNodes();
				if (nodeList != null) {
					for (int i = 0; i < nodeList.getLength(); ++i) {
						Node node = nodeList.item(i);
						if (node instanceof Element) {
							String nodeName = node.getNodeName();
							if (TextUtils.equals(nodeName, ELEMENT_TEST_ITEM)) {
								NamedNodeMap attributeds = node.getAttributes();
								int attributeLength = attributeds.getLength();								
								for (int attrIndex = 0; attrIndex < attributeLength; ++attrIndex) {
									Node attribute = attributeds.item(attrIndex);
									String attributeName = attribute.getNodeName();
									String attributeValue = attribute.getNodeValue();
									if (attributeName != null) {
										Log.d(TAG, "Soar testItem Name is " + attributeName + " node value is " + attributeValue);
										testUnit.addTestItem(attributeName, attributeValue);
									} else {
										Log.d(TAG, "testItem is null");
									}
								}																								
							} else if (TextUtils.equals(nodeName, ELEMENT_TEST_ITEM_TYPE)) {
								NamedNodeMap attributeds = node.getAttributes();
								int attributeLength = attributeds.getLength();
								for (int attrIndex = 0; attrIndex < attributeLength; ++attrIndex) {
									Node attribute = attributeds.item(attrIndex);
									String attributeName = attribute.getNodeName();
									String attributeValue = attribute.getNodeValue();
									if (attributeName != null) {
										Log.d(TAG, "Soar testItemType Name is " + attributeName + " testItemType value is " + attributeValue);
										testUnit.addTestItemType(attributeName, attributeValue);
									} else {
										Log.d(TAG, "testItem is null");
									}
								}							
							} else {
								Log.d(TAG, "Soar element unknown type");
							}
						}
					}
				}
				/*NamedNodeMap attributeds = mRootNode.getAttributes();
				int attributeLength = attributeds.getLength();
				for (int i = 0; i < attributeLength; ++i) {
					Node node = attributeds.item(i);
					String nodeName = node.getNodeName();
					String nodeValue = node.getNodeValue();
					if (nodeName != null) {
						Log.d(TAG, "nodeName is " + nodeName + " node value is " + nodeValue);
						testUnit.addTestItem(nodeName, nodeValue);
					} else {
						Log.d(TAG, "nodeName is null");
					}
				}*/				
			} else {
				Log.d(TAG, "root node is null");
			}
			
			
			
			return testUnit;
		}
    }
	
	public void createXmlFileFromTestUnit(ITestUnit testUnit, String filePath) {
		synchronized (mDocumentBuilder) {
			if (mDocumentBuilder != null) {
				Document doc = mDocumentBuilder.newDocument();
				Element root = doc.createElement(testUnit.getUnitName());
				
								
												
				Set<String> testItemKeys = testUnit.getTestItemKey();
				if (testItemKeys.size() > 0) {
					Element testItemNode = doc.createElement(ELEMENT_TEST_ITEM);
					Element testItemTypeNode = doc.createElement(ELEMENT_TEST_ITEM_TYPE);					
					if (testItemKeys != null) {
						for (String testItemKey : testItemKeys) {
							if (testItemKey != null) {
								String testItem = testUnit.getTestItem(testItemKey);
								testItemNode.setAttribute(testItemKey, testItem);
								String testItemType = testUnit.getTestItemType(testItemKey);
								if (testItemType != null)
									testItemTypeNode.setAttribute(testItemKey, testItemType);
							} else {
								Log.d(TAG, "testItemKey is null");
							}
						}
					}
					root.appendChild(testItemNode);
					root.appendChild(testItemTypeNode);
				}
								
				
							
				doc.appendChild(root);
				try {
					Transformer transformer = TransformerFactory.newInstance().newTransformer();
					StreamResult result = new StreamResult(new File(filePath));
					try {
						transformer.transform(new DOMSource(doc), result);
					} catch (TransformerException e) {
						e.printStackTrace();
					}
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	
}
