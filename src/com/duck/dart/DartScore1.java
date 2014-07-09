package com.duck.dart;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

 

public class DartScore1 extends Activity {
	int addNewRount = 0;
	int RoundStatic = 0;
	int clickSum = 0;
	int click1Count = 0;
	int click20Count = 0;
	int thisroundscoreFirst = 0;
	int thisroundscoreSecond = 0;
	int thisroundscoreThird = 0;
	int thisroundscoreSUM = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.dartscore1);
		
		final EditText roundscoresum = (EditText)findViewById(R.id.RoundScoreSum);
//		EditText dart2score = (EditText)findViewById(R.id.Dart2Score);
//		EditText dart3score = (EditText)findViewById(R.id.Dart3Score);
		
		Button Point20 = (Button)findViewById(R.id.point20);
		Button Point1 = (Button)findViewById(R.id.point1);
		Button Point18 = (Button)findViewById(R.id.point18);
		Button Point4 = (Button)findViewById(R.id.point4);
		Button Point13 = (Button)findViewById(R.id.point13);
		Intent i = getIntent();
	  Bundle ScoreBundle =this.getIntent().getExtras();
	  int thisroundscore = ScoreBundle.getInt("addNewRount");
      Log.d("This Round Score", String.valueOf(thisroundscore));
//		addNewRount= i.getIntExtra("addNewRount", 0);
//		RoundStatic= i.getIntExtra("RoundStatic",1);
		// Locate the TextView in singleitemview.xml
//	TextView txtname1 = (TextView) findViewById(R.id.);
//	TextView txtname2 = (TextView) findViewById(R.id.AVGirlName);
		// Load the text into the TextView
//	txtname1.setText((String.valueOf(thisroundscore)));
//	txtname2.setText(AVgirlname);

//		switch(buttonClick = 0) {
//		case 1:{
//			
//		}
//			
//		}
		
		Point20.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//clickSum can't over 3 times
			while(clickSum++ >= 3){
				clickSum = 0;
				thisroundscoreSUM = 0;
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));
				Toast.makeText(DartScore1.this, "Input over 3 darts", Toast.LENGTH_SHORT).show();
			}
			// Print clickSum 
				Log.d("clickSum20",String.valueOf(clickSum));
				//roundscoresum can't over 60
				if (Integer.parseInt(roundscoresum.getText().toString())>=60){
					thisroundscoreSUM = 0;
					Toast.makeText(DartScore1.this, "Input over 60 points", Toast.LENGTH_SHORT).show();
				}
				
				//get 20 point
				thisroundscoreSUM = thisroundscoreSUM + 20; 
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));

//				 Bundle postAddScore = new Bundle();
//				 postAddScore.putInt("AddScore", score);
//				 Intent addtoscore = new Intent();
//				 addtoscore.setClass( DartScore1.this, target.class );
//				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
//				 addtoscore.putExtras(postAddScore);
//		         startActivity(addtoscore);
			}
		});
			
		Point1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				while(clickSum++ >= 3){
					clickSum = 0;
					thisroundscoreSUM = 0;
					roundscoresum.setText(String.valueOf(thisroundscoreSUM));
					Toast.makeText(DartScore1.this, "Input over 3 darts", Toast.LENGTH_SHORT).show();

				}
				Log.d("clickSum1",String.valueOf(clickSum));
				click1Count = click1Count + 1;
				if (thisroundscoreSUM>=60){
					thisroundscoreSUM = 0;
				}
				
				
				thisroundscoreSUM = thisroundscoreSUM + 1; 
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));

			}
		});
			
		Point18.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//clickSum can't over 3 times
			while(clickSum++ >= 3){
				clickSum = 0;
				thisroundscoreSUM = 0;
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));
				Toast.makeText(DartScore1.this, "Input over 3 darts", Toast.LENGTH_SHORT).show();

			}
			// Print clickSum 
				Log.d("clickSum18",String.valueOf(clickSum));
				//roundscoresum can't over 60
				if (Integer.parseInt(roundscoresum.getText().toString())>=60){
					thisroundscoreSUM = 0;
				}
				
				//get 18 point
				thisroundscoreSUM = thisroundscoreSUM + 18; 
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));

			}
		});
			
		Point4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//clickSum can't over 3 times
			while(clickSum++ >= 3){
				clickSum = 0;
				thisroundscoreSUM = 0;
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));
				Toast.makeText(DartScore1.this, "Input over 3 darts", Toast.LENGTH_SHORT).show();

			}
			// Print clickSum 
				Log.d("clickSum4",String.valueOf(clickSum));
				//roundscoresum can't over 60
				if (Integer.parseInt(roundscoresum.getText().toString())>=60){
					thisroundscoreSUM = 0;
				}
				
				//get 4 point
				thisroundscoreSUM = thisroundscoreSUM + 4; 
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));

			}
		});
			
		Point13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//clickSum can't over 3 times
			while(clickSum++ >= 3){
				clickSum = 0;
				thisroundscoreSUM = 0;
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));
				Toast.makeText(DartScore1.this, "Input over 3 darts", Toast.LENGTH_SHORT).show();

			}
			// Print clickSum 
				Log.d("clickSum13",String.valueOf(clickSum));
				//roundscoresum can't over 60
				if (Integer.parseInt(roundscoresum.getText().toString())>=60){
					thisroundscoreSUM = 0;
				}
				
				//get 13 point
				thisroundscoreSUM = thisroundscoreSUM + 13; 
				roundscoresum.setText(String.valueOf(thisroundscoreSUM));

			}
		});
			

	}
	

	  @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		  super.onResume();
		
	}


	@Override
	    public boolean onCreateOptionsMenu(Menu menu) {

		  getMenuInflater().inflate(R.menu.nexstep, menu);
	        ActionBar actionBar = getActionBar();  
	        actionBar.setDisplayHomeAsUpEnabled(false);  
	        MenuItem AddItem = menu.findItem(R.id.menu_next);
	     

	        return super.onCreateOptionsMenu(menu);
	    }
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	      // Handle presses on the action bar items
	      switch (item.getItemId()) {
	          case R.id.menu_next:

					FlurryAgent.logEvent("menu_next");
	        	  Intent intent = new Intent(this, target.class);
	        	  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	  Bundle bundle = new Bundle();
//				    bundle.putInt("addNewRount", thisroundscore);
//				    bundle.putInt("RoundStatic", RoundStatic);
//				    DartScore1.putExtras(bundle);
	        	  startActivity(intent);
//	        	 this.finish();
	        	  return true;
	 //         case R.id.menu_settings:
	            //  openSettings();
	             // return true;
	          default:
	              return super.onOptionsItemSelected(item);
	      }
	  }
}
