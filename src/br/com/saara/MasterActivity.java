package br.com.saara;

import android.app.Activity;
import android.os.Bundle;

import com.google.analytics.tracking.android.EasyTracker;


public class MasterActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//BugSenseHandler.initAndStartSession(this, "c8c053dd");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//BugSenseHandler.closeSession(this);
	}
	
	 @Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
}
