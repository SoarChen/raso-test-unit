package com.raso.test.compare;

import com.raso.test.ITestUnit;

public interface ITestUnitComparison {
	public void init(IItemComparisonFactory itemCompareFactory);
	public boolean compare(ITestUnit sourceUnit, ITestUnit subjectUnit);
}
