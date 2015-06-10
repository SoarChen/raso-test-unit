package com.raso.test.xml;

import java.io.InputStream;
import com.raso.test.ITestUnit;


public interface IXMLParser {
	
	public boolean initXml();
	public ITestUnit parseXmlDocumentToTestUnit(InputStream inputStream);
	public void createXmlFileFromTestUnit(ITestUnit testUnit, String filePath);
}
