package com.my.newproject9;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




public class MainActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear1;
	private TextView textview1;
	private ProgressBar progressbar1;
	
	private TimerTask spltimr;
	private Intent i = new Intent();
	private SharedPreferences credentials;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();

		//getting the toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);

		//setting the title
		toolbar.setTitle("GrabnSnack");

		//placing toolbar in place of actionbar
		setActionBar(toolbar);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.toolbarmenu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
			case R.id.menuAbout:
				Uri webpage = Uri.parse("https://www.aboutus.com");
				Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
				startActivity(webIntent);
				return true;

		}
		return true;
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		textview1 = (TextView) findViewById(R.id.textview1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		credentials = getSharedPreferences("credentials", Activity.MODE_PRIVATE);
	}
	private void initializeLogic() {
		spltimr = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (credentials.getString("logged", "").equals("1")) {
							if (credentials.getString("hash", "").equals("")) {
								i.setClass(getApplicationContext(), LoginActivity.class);
								startActivity(i);
								finish();
							}
							else {
								i.setClass(getApplicationContext(), ContentActivity.class);
								startActivity(i);
								finish();
							}
						}
						else {
							i.setClass(getApplicationContext(), LoginActivity.class);
							startActivity(i);
							finish();
						}
					}
				});
			}
		};
		_timer.schedule(spltimr, (int)(3000));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}

	public void OpenShoppingCart(MenuItem item) {
		Intent activity2Intent = new Intent(getApplicationContext(), shoppingcart.class);
		startActivity(activity2Intent);
	}

	public void OpenProfile(MenuItem item) {
		Intent activity2Intent = new Intent(getApplicationContext(), userprofile.class);
		startActivity(activity2Intent);
	}

	public void Logout(MenuItem item) {
		Intent activity2Intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(activity2Intent);

	}
}
