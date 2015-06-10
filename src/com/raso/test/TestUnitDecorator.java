package com.raso.test;

import java.util.Set;

import android.os.Parcel;

public abstract class TestUnitDecorator extends TestUnit {
	
	
	protected TestUnit mTestUnit =  null;
	
	public TestUnitDecorator(TestUnit testUnit) {
		if (testUnit == null) {
			throw new NullPointerException("TestUnit should not be null");
		}
		
		mTestUnit = testUnit;
		
	}
	
	@Override
	public void addTestItem(String itemName, String value) {
		mTestUnit.addTestItem(itemName, value);
	}


	@Override
	public void addTestItemType(String itemName, String type) {
		mTestUnit.addTestItemType(itemName, type);
	}


	@Override
	public String getTestItemType(String itemName) {
		return mTestUnit.getTestItemType(itemName);
	}


	@Override
	public void setUnitName(String unitName) {
		mTestUnit.setUnitName(unitName);
	}


	@Override
	public String getTestItem(String itemName) {
		return mTestUnit.getTestItem(itemName);
	}


	@Override
	public Set<String> getTestItemKey() {
		return mTestUnit.getTestItemKey();
	}


	@Override
	public int getTestItemCount() {
		return mTestUnit.getTestItemCount();
	}


	@Override
	public String getUnitName() {
		return mTestUnit.getUnitName();
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return mTestUnit.describeContents();
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		mTestUnit.writeToParcel(dest, flags);
	}
	
}
