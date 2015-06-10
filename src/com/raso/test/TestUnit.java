package com.raso.test;

import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class TestUnit implements ITestUnit, Parcelable{
	
	private static final String TAG = "TestUnit";
	
	
	
	
	
	private HashMap<String, String> mStringTestItems = new HashMap<String, String>();
	private HashMap<String, String> mStringTestItemTypes = new HashMap<String, String>();
	
	private String mUnitName = null;
	

	
	@Override
	public void execute(Context context) {
		/*if (context != null) {
			Log.d(TAG, "Soar execute");
			Intent intent = new Intent(ACTION_RUN_TEST);
			intent.putExtra(EXTRA_TEST_UNIT, this);
			//intent.putExtra(EXTRA_MODE, MODE_CAPTURE);
			intent.putExtra(EXTRA_MODE, MODE_TEST);
			context.sendBroadcast(intent);
		}*/
	
		
	}

	@Override
	public void addTestItem(String itemName, String value) {
		mStringTestItems.put(itemName, value);
	}
	
	@Override
	public void addTestItemType(String itemName, String type) {
		mStringTestItemTypes.put(itemName, type);
	}
	
	@Override
	public String getTestItemType(String itemName) {
		return mStringTestItemTypes.get(itemName);
	}
	
	@Override
	public void setUnitName(String unitName) {
		mUnitName = unitName;		
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		Log.d(TAG, "Soar writeToParcel xxx");
		
		dest.writeInt(mStringTestItems.size());
		
		for (HashMap.Entry<String, String> entry : mStringTestItems.entrySet()) {
			dest.writeString(entry.getKey());
			dest.writeString(entry.getValue());
		}
		
		dest.writeInt(mStringTestItemTypes.size());
		
		for (HashMap.Entry<String, String> entry : mStringTestItemTypes.entrySet()) {
			dest.writeString(entry.getKey());
			dest.writeString(entry.getValue());
		}
		
		
		dest.writeString(mUnitName);
		
	}
	
	public static final Parcelable.Creator<TestUnit> CREATOR = new Creator() {

		@Override
		public Object createFromParcel(Parcel source) {
			Log.d(TAG, "Soar createFromParcel xxx");
			TestUnit testUnit = new TestUnit();
			int itemSize = source.readInt();
			String key = null;
			String value = null;
			for (int i = 0; i < itemSize; ++i) {
				key = source.readString();
				value = source.readString();
				testUnit.addTestItem(key, value);
			}
			
			int itemTypesize = source.readInt();			
			for (int i = 0; i < itemTypesize; ++i) {
				key = source.readString();
				value = source.readString();
				testUnit.addTestItemType(key, value);
			}
			
			
			testUnit.setUnitName(source.readString());
			return testUnit;
		}

		@Override
		public Object[] newArray(int size) {
			Log.d(TAG, "Soar newArray xxx size = " + size);
			return new TestUnit[size];
		}
		
	};
	
	@Override
	public String getTestItem(String itemName) {
		String value = mStringTestItems.get(itemName);
		
		return value;
	}
	
	@Override
	public Set<String> getTestItemKey() {
		return mStringTestItems.keySet();
	}
	
	
	@Override
	public int getTestItemCount() {
		return mStringTestItems.size();
	}

	@Override
	public String getUnitName() {
		return mUnitName;
	}	
	
}
