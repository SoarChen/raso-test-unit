package com.raso.test.compare;

public class ItemComparisonFactory implements IItemComparisonFactory {

	
	IItemComparison mStringItemComparison = null;
	
	public ItemComparisonFactory() {
		
	}
	
	@Override
	public IItemComparison getItemComparision(String type) {
		if (type != null) {
			if (mStringItemComparison == null)
				mStringItemComparison = new StringItemComparision();
			
			return mStringItemComparison;
		} else {
			if (mStringItemComparison == null)
				mStringItemComparison = new StringItemComparision();
			
			return mStringItemComparison;
		}
		
	}

}
