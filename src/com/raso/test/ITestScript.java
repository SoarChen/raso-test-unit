package com.raso.test;

public interface ITestScript {
	public void convertToScript(ITestUnit testUnit, String filePath);
	public ITestUnit revertToTestUnit(String filePath);
}
