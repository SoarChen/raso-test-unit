package com.raso.test.compare;

import java.util.Set;

import com.raso.test.ITestUnit;

import android.text.TextUtils;
import android.util.Log;

public class TestUnitCompare implements ITestUnitComparison {
	
	private final static String TAG = "TestUnitCompare";
	
	private IItemComparisonFactory mItemCompareFactory = null;
	
	@Override
	public void init(IItemComparisonFactory itemCompareFactory) {
		mItemCompareFactory = itemCompareFactory;
	}
			
	
	@Override
	public boolean compare(ITestUnit subjectUnit, ITestUnit sourceUnit) {		
		Log.d(TAG, "Soar compare");
		
		if (mItemCompareFactory == null) {
			Log.d(TAG, "Soar no item compare factory");
			return false;
		}
		
		boolean result = true;
		int matchCount = 0;
		int mismatchCount = 0;
		if (sourceUnit != null && subjectUnit != null) {
			Set<String> leftTestItemKey = subjectUnit.getTestItemKey();
						
			//if (leftUnit.getTestItemCount() == rightUnit.getTestItemCount()) {
			
				if (leftTestItemKey != null) {
					for (String leftItemKey : leftTestItemKey) {
						if (leftItemKey != null) {						
							String subjectItemValue = subjectUnit.getTestItem(leftItemKey);
							String sourceItemValue = sourceUnit.getTestItem(leftItemKey);
							String type = subjectUnit.getTestItemType(leftItemKey);
							if (type == null)
								type = sourceUnit.getTestItemType(leftItemKey);
							IItemComparison itemComparision = mItemCompareFactory.getItemComparision(type);
							if (itemComparision != null && itemComparision.compare(subjectItemValue, sourceItemValue)) {
								Log.d(TAG, "Soar item key = " + leftItemKey + ", left item value = " + 
										subjectItemValue + ", Result equalled");
								matchCount++;
							} else  {
								Log.w(TAG, "Soar item key = " + leftItemKey + ", left item value = " + 
										subjectItemValue + ", right item value = " + sourceItemValue + " Result not equalled");
								result = false;
								mismatchCount++;
							}								
						}
					}
				}
				
				Log.d(TAG, "Soar match count = " + matchCount + "mismatchCount = " + mismatchCount);
			/*} else {
				Log.d(TAG, "Soar Count not equalled. Result not equalled. left count = " + leftUnit.getTestItemCount() + 
						" right count " + rightUnit.getTestItemCount());
				result = false;
			}*/
		}
		
		return result;
	}

}
