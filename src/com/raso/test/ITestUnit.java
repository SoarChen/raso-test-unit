package com.raso.test;

import java.util.Set;

import android.content.Context;

public interface ITestUnit  {
	
	
	public void execute(Context context);
	
	public void setUnitName(String unitName);
	public String getUnitName();	
	public void addTestItem(String itemName, String value);
	public void addTestItemType(String itemName, String type);
	public String getTestItem(String itemName);
	public String getTestItemType(String itemName);
	public Set<String> getTestItemKey();	
	public int getTestItemCount();
	
}
