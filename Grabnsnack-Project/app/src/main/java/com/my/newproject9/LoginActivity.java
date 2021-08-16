package com.my.newproject9;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends Activity {
	
	
	private double num = 0;
	private String DOMAIN = "";
	private String logmail = "";
	private String logpass = "";
	private String regname = "";
	private String regmail = "";
	private String regpass = "";
	private HashMap<String, Object> map = new HashMap<>();
	private double valid = 0;
	private HashMap<String, Object> jsonMap = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> jsonData = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear4;
	private LinearLayout log_layout;
	private LinearLayout reg_layout;
	private EditText log_email;
	private EditText log_pass;
	private Button log_btn;
	private EditText reg_name;
	private EditText reg_email;
	private EditText reg_pass;
	private Button reg_btn;
	private Button shift;
	
	private SharedPreferences credentials;
	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private Intent i = new Intent();
	private Intent g = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		log_layout = (LinearLayout) findViewById(R.id.log_layout);
		reg_layout = (LinearLayout) findViewById(R.id.reg_layout);
		log_email = (EditText) findViewById(R.id.log_email);
		log_pass = (EditText) findViewById(R.id.log_pass);
		log_btn = (Button) findViewById(R.id.log_btn);
		reg_name = (EditText) findViewById(R.id.reg_name);
		reg_email = (EditText) findViewById(R.id.reg_email);
		reg_pass = (EditText) findViewById(R.id.reg_pass);
		reg_btn = (Button) findViewById(R.id.reg_btn);
		shift = (Button) findViewById(R.id.shift);
		credentials = getSharedPreferences("credentials", Activity.MODE_PRIVATE);
		network = new RequestNetwork(this);
		
		log_email.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				log_email.setError(null);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		log_pass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				log_pass.setError(null);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		log_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				valid = 0;
				logmail = log_email.getText().toString().trim();
				logpass = log_pass.getText().toString().trim();
				if (logmail.equals("")) {
					log_email.setError("Please enter your email");
					valid++;
				}
				else {
					if(emailValidator(logmail)){
					}else{
						log_email.setError("Incorrect Email format");
						valid++;
					}
				}
				if (logpass.equals("")) {
					log_pass.setError("Please enter your password");
					valid++;
				}
				else {
					if(pwdValidator(logpass)){
					}else{
						log_pass.setError("Incorrect Password Format");
						valid++;
					}
				}
				if (valid == 0) {
					log_email.setEnabled(false);
					log_pass.setEnabled(false);
					log_btn.setEnabled(false);
					shift.setEnabled(false);
					SketchwareUtil.showMessage(getApplicationContext(), "Connecting... Please Wait");
					_sent_request(logmail, logpass, "null", "login");
				}
			}
		});
		
		reg_name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				reg_name.setError(null);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		reg_email.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				reg_email.setError(null);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		reg_pass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				reg_pass.setError(null);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		reg_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				valid = 0;
				regmail = reg_email.getText().toString().trim();
				regpass = reg_pass.getText().toString().trim();
				regname = reg_name.getText().toString().trim();
				if (regmail.equals("")) {
					reg_email.setError("Email can't be empty");
					valid++;
				}
				else {
					if(emailValidator(regmail)){
					}else{
						reg_email.setError("Email is not in correct format");
						valid++;
					}
				}
				if (regpass.equals("")) {
					reg_pass.setError("Password can't be empty");
					valid++;
				}
				else {
					if(pwdValidator(regpass)){
					}else{
						reg_pass.setError("Password is not in correct format.Please use a-z,0-9,and symbols");
						valid++;
					}
				}
				if (valid == 0) {
					reg_name.setEnabled(false);
					reg_email.setEnabled(false);
					reg_pass.setEnabled(false);
					reg_btn.setEnabled(false);
					shift.setEnabled(false);
					SketchwareUtil.showMessage(getApplicationContext(), "Connecting Please wait...");
					_sent_request(regmail, regpass, regname, "register");
				}
			}
		});
		
		shift.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (num == 0) {
					reg_layout.setVisibility(View.VISIBLE);
					log_layout.setVisibility(View.GONE);
					num = 1;
					shift.setText("Click to login");
				}
				else {
					reg_layout.setVisibility(View.GONE);
					log_layout.setVisibility(View.VISIBLE);
					num = 0;
					shift.setText("Register here!!");
				}
			}
		});
		
		_network_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				if (_tag.equals("login")) {
					log_email.setEnabled(true);
					log_pass.setEnabled(true);
					log_btn.setEnabled(true);
					shift.setEnabled(true);
					jsonMap = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
					if (jsonMap.get("status").toString().equals("OK")) {
						credentials.edit().putString("logged", "1").commit();
						credentials.edit().putString("hash", jsonMap.get("hash").toString()).commit();
						i.setClass(getApplicationContext(), ContentActivity.class);
						startActivity(i);
						SketchwareUtil.showMessage(getApplicationContext(), "Successfully logged in");
						finish();
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Problem occurred. Check on your login details...");
					}
				}
				else {
					reg_name.setEnabled(true);
					reg_email.setEnabled(true);
					reg_pass.setEnabled(true);
					reg_btn.setEnabled(true);
					shift.setEnabled(true);
					jsonMap = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
					if (jsonMap.get("status").toString().equals("OK")) {
						SketchwareUtil.showMessage(getApplicationContext(), "User successfully created...");
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Problem occurred.Check on your registration again...");
					}
				}
				jsonMap.clear();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (_tag.equals("login")) {
					log_email.setEnabled(true);
					log_pass.setEnabled(true);
					log_btn.setEnabled(true);
					shift.setEnabled(true);
				}
				else {
					reg_name.setEnabled(true);
					reg_email.setEnabled(true);
					reg_pass.setEnabled(true);
					reg_btn.setEnabled(true);
					shift.setEnabled(true);
				}
				SketchwareUtil.showMessage(getApplicationContext(), "Something happened");
			}
		};
	}
	private void initializeLogic() {
		DOMAIN = "https://grabnsnack.000webhostapp.com/GrabnSnack/";
		reg_layout.setVisibility(View.GONE);
		if (credentials.getString("logged", "").equals("1")) {
			g.setClass(getApplicationContext(), ContentActivity.class);
			startActivity(g);
			finish();
		}
		else {
			
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	private void _extra () {
	}
	public boolean emailValidator(String email) 
	{
		    java.util.regex.Pattern pattern;
		    java.util.regex.Matcher matcher;
		    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		    pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN);
		    matcher = pattern.matcher(email);
		    return matcher.matches();
	}
	public boolean pwdValidator(String pwd) 
	{
		
		final String allowedChars = "^(?=.*[@$%&#_()=+?»«<>£§€{}\\[\\]-])(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*(?<=.{6,10})$";
		    java.util.regex.Pattern pattern;
		    java.util.regex.Matcher matcher;
		   
		    pattern = java.util.regex.Pattern.compile(allowedChars);
		    matcher = pattern.matcher(pwd);
		    return matcher.matches();
		
	}
	{

	}
	
	
	private void _sent_request (final String _email, final String _password, final String _name, final String _type) {
		map = new HashMap<>();
		if (_type.equals("login")) {
			map.put("email", _email);
			map.put("password", _password);
			map.put(_type, "true");
			network.setParams(map, RequestNetworkController.REQUEST_BODY);
			network.startRequestNetwork(RequestNetworkController.POST, DOMAIN.concat("login.php"), _type, _network_request_listener);
		}
		else {
			if (_type.equals("register")) {
				map.put("name", _name);
				map.put("email", _email);
				map.put("password", _password);
				map.put(_type, "true");
				network.setParams(map, RequestNetworkController.REQUEST_BODY);
				network.startRequestNetwork(RequestNetworkController.POST, DOMAIN.concat("register.php"), _type, _network_request_listener);
			}
		}
		map.clear();
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
	
}
