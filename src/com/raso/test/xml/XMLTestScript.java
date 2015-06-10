package com.raso.test.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

import android.util.Log;

import com.raso.test.ITestScript;
import com.raso.test.ITestUnit;

public class XMLTestScript implements ITestScript {
	
	private final String TAG = "XMLTestScript";
	
	
	@Override
	public void convertToScript(ITestUnit testUnit, String filePath) {
		Log.d(TAG, "Soar convertToScript filePath = " + filePath );
		IXMLParser xmlParser = new DomXMLParser();
        xmlParser.initXml();                
        xmlParser.createXmlFileFromTestUnit(testUnit, filePath);

	}

	@Override
	public ITestUnit revertToTestUnit(String filePath) {
		Log.d(TAG, "Soar revertToTestUnit filePath = " + filePath);
		IXMLParser xmlParser = new DomXMLParser();
        xmlParser.initXml();        
        
        File file = new File(filePath);
        if (file.exists()) {
        	InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				Log.d(TAG, "Soar FileNotFoundException ");
				e.printStackTrace();
			}
			ITestUnit sourceTestUnit = xmlParser.parseXmlDocumentToTestUnit(inputStream);
			return sourceTestUnit;
        } else {
        	Log.d(TAG, "Soar revertToTestUnit filePath not exist");
        	return null;
        }
        	
	}

}
