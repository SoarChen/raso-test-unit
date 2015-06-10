package com.raso.test.compare;

import android.text.TextUtils;
import android.util.Log;

public class StringItemComparision implements IItemComparison {
	private static final String TAG = "StringItemComparision";

	@Override
	public boolean compare(String leftItem, String rightItem) {
		if (TextUtils.equals(leftItem, rightItem)) {
			Log.d(TAG, "Soar left item value = " + leftItem + ", Result equalled"); 					
			return true;
		} else  {
			Log.w(TAG, "Soar , left item value = " + leftItem + ", right item value = " + 
					rightItem + " Result not equalled"); 					
			return false;
		}
	}

}
