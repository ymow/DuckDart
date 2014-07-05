package com.duck.dart;

 

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;
 
public class SingleItemView extends Activity {
	private static final String  BrowserBug = null;
	// Declare Variables
 
      String AVgirlname,videoname;
//	  private AdView adView2;
	  private ShareActionProvider mShareActionProvider;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
		  // get action bar   
        ActionBar actionBar = getActionBar();
		 getActionBar().setDisplayHomeAsUpEnabled(true);
		 getActionBar().setDisplayUseLogoEnabled(true);
 
	    //  adView
/*	    adView2 = (AdView)findViewById(R.id.adView2);
 
	    // Big ad banner
//	    AdRequest adRequest = new AdRequest.Builder().build();
	    AdRequest request = new AdRequest.Builder()
	    .setGender(AdRequest.GENDER_MALE)
	   // .setBirthday(new GregorianCalendar(1995, 1, 1).getTime())
	    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	     .addTestDevice("2DA6E0791384A56BFA49EC62B3880F42") // ?????? HTC C300 ????????????
	    .build();
	    // ????????????????????? adView???
	    adView2.loadAd(request);
*/	    
		// Retrieve data from MainActivity on item click event
		Intent i = getIntent();
//		Intent k = getIntent();
			
		// Get the name
		videoname = i.getStringExtra("videoname");
		AVgirlname = i.getStringExtra("AVgirlname");
		// Locate the TextView in singleitemview.xml
	TextView txtname1 = (TextView) findViewById(R.id.VideoName);
	TextView txtname2 = (TextView) findViewById(R.id.AVGirlName);
		// Load the text into the TextView
	txtname1.setText(videoname);
	txtname2.setText(AVgirlname);
	 
   
	//Search URL
//		Uri uri = Uri.parse("https://www.google.com.tw/search?q="+name);
//		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//		 startActivity(intent);
		 
		 //AV Browser
		   String TAG = BrowserBug;
 
		 WebView avBrowser=(WebView)findViewById(R.id.AVbrowser);
		 WebSettings websettings = avBrowser.getSettings();  
	        websettings.setSupportZoom(true);  
	        websettings.setBuiltInZoomControls(false);   
	        avBrowser = (ProgressWebView) findViewById(R.id.mWebView);
//	        websettings.setJavaScriptEnabled(true);  	         
	        avBrowser.setWebViewClient(new WebViewClient());  
//	        avBrowser.loadUrl("file:///android_asset/index.html");
	        avBrowser.loadUrl("https://www.google.com.tw/search?q="+videoname);  
	      if(AVgirlname != null)
	      {
	    	  getActionBar().setTitle(AVgirlname);
	      }else{
	        getActionBar().setTitle(R.string.TitlePeople);
		   }	  
	        //getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions   
	        Log.v(TAG, "WebView OK");

	        avBrowser.setWebViewClient(new MyWebViewClient());

	           
	}
	@Override
	protected void onStart()
	{
		super.onStart();
		FlurryAgent.onStartSession(this, "85YC8XFWVNR5NJ4B4CJD");
	}
	 
	@Override
	protected void onStop()
	{
		super.onStop();		
		FlurryAgent.onEndSession(this);
	}
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView avBrowser, String url) {
	
		if (Uri.parse(url).getHost().indexOf("google") == 4) {
		//Open Browser
		  
//			Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//	  			startActivity(intent1);
		        Log.v("MyWebViewClient", "g" );
		return false;
		}
		// ?????????????????????????????????????????????Activity????????????URL
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
  			startActivity(intent2);
	        Log.v("MyWebViewClient", "d");
		return true;
		}
		}
	
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate menu resource file.
	    getMenuInflater().inflate(R.menu.singleitemview, menu);
 
	    
	    // Locate MenuItem with ShareActionProvider
	    MenuItem item = menu.findItem(R.id.menu_share);
 
//	    // Fetch and store ShareActionProvider
	    mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.menu_share).getActionProvider();
	    mShareActionProvider.setShareIntent(getDefaultShareIntent());
	    return true;
	}

	private Intent getDefaultShareIntent() {
		// TODO Auto-generated method stub
		  String playStoreLink = "https://play.google.com/store/apps/details?id=" +
  		        getPackageName();
  		String yourShareText = "????????? "+AVgirlname+"???"+videoname +"?????????????????? "+" Install this app " + playStoreLink;

		 Intent intent = new Intent(Intent.ACTION_SEND);
//	        intent.setComponent(new ComponentName("jp.naver.line.android",
//	                "com.facebook.katana"));
	        intent.setType("text/plain"); 
	        intent.putExtra(Intent.EXTRA_SUBJECT, "???????????????AVDb?????????????????????");
	        intent.putExtra(Intent.EXTRA_TEXT, yourShareText);
	        return intent;
	     
	}

	// Call to update the share intent
	private void setShareIntent(Intent shareIntent) {
	    if (mShareActionProvider != null) {
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	        case android.R.id.home:
	            this.finish();
//	            startActivity(new Intent(SingleItemView.this,MainActivity.class));
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {//???????????????
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {  
//            startActivity(new Intent(SingleItemView.this,
//                    MainActivity.class));
            finish();
//            ConfirmExit();//????????????????????????????????????
            return true;   
        }   
        return super.onKeyDown(keyCode, event);   
    }
//    public void ConfirmExit(){//????????????
//        AlertDialog.Builder ad=new AlertDialog.Builder(MyOpenDataActivity.this);
//        ad.setTitle("??????");
//        ad.setMessage("????????????????");
//        ad.setPositiveButton("???", new DialogInterface.OnClickListener() {//????????????
//            public void onClick(DialogInterface dialog, int i) {
//                // TODO Auto-generated method stub
//                MyOpenDataActivity.this.finish();//??????activity
//  
//            }
//        });
//        ad.setNegativeButton("???",new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int i) {
//                //?????????????????????????????????
//            }
//        });
//        ad.show();//????????????
//    }
}
