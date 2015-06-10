package com.raso.test;

import java.util.HashSet;
import java.util.Set;

import android.database.Cursor;

public class CursorTestUnit extends TestUnit {
	
	
	public CursorTestUnit(Cursor cursor) {
		buildTestItem(cursor, null);
	}
	
	public CursorTestUnit(Cursor cursor, String[] testColumns) {
		buildTestItem(cursor, testColumns);
	}
	
	private void buildTestItem(Cursor cursor, String[] testColumns) {
		
		Set<String> needTestNames = null;
		
		if (testColumns != null) {
			needTestNames = new HashSet<String>();
			for (String testName : testColumns) {
				needTestNames.add(testName);
			}
		}
		
		if (cursor != null && !cursor.isClosed()) {
			cursor.moveToFirst();
			String[] columnNames = cursor.getColumnNames();
			final int columnLength = columnNames.length;
			int cursorIndex = 0;
			while (!cursor.isAfterLast()) {				
				for (int i = 0; i < columnLength; ++i) {
					StringBuilder itemName = new StringBuilder(columnNames[i]);
					if (needTestNames == null || needTestNames.contains(itemName.toString())) {
						String value = cursor.getString(cursor.getColumnIndex(columnNames[i]));										
						itemName.append("-" + "Cursor-" + cursorIndex);
						addTestItem(itemName.toString(), value);	
					}					
				}				
				cursor.moveToNext();
				++cursorIndex;
			}
		}		
	}
			
}
