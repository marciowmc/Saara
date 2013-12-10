package br.com.saara;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SaaraActivity extends Activity {
	
	private Runnable start = new Runnable() {
		@Override
		public void run() {
			startActivity(new Intent().setClass(SaaraActivity.this,
					CategoriasActivity.class));
			SaaraActivity.this.finish();
		}
	};
	
	private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        handler = new Handler();
        handler.postDelayed(start, 3000);
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    	handler.removeCallbacks(start);
    }
}
