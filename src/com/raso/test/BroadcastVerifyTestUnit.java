package com.raso.test;

import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;

public class BroadcastVerifyTestUnit extends TestUnitDecorator {
	
	private final String TAG = "BroadcastVerifyTestUnit";
	
	private static final String ACTION_RUN_TEST = "com.raso.testsuiteprogram.action_run_test";
	private static final String EXTRA_TEST_UNIT = "extra_test_unit";
	
	private static final String EXTRA_MODE = "extra_mode";
	
	// To test 
	public static final int MODE_TEST = 1;
	
	// To capture the test item
	public static final int MODE_CAPTURE = 2;	
	
	public BroadcastVerifyTestUnit(TestUnit testUnit) {
		super(testUnit);
	}

	@Override
	public void execute(Context context) {
		mTestUnit.execute(context);
		if (context != null) {
			Log.d(TAG, "Soar Broadcast RunTestUnit ");
			Intent intent = new Intent(ACTION_RUN_TEST);
			intent.putExtra(EXTRA_TEST_UNIT, this);
			//intent.putExtra(EXTRA_MODE, MODE_CAPTURE);
			intent.putExtra(EXTRA_MODE, MODE_TEST);
			context.sendBroadcast(intent);
		}
	}			
}
