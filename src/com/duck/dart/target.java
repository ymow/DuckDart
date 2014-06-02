package com.duck.dart;

import com.flurry.android.FlurryAgent;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

 

public class target extends Activity {
 
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Get the view from listview_main.xml
			setContentView(R.layout.target);
			ImageButton	DD_Target1 = (ImageButton) findViewById(R.id.dd_target1);
//			ImageButton	DD_Target2 = (ImageButton) findViewById(R.id.dd_target2);
//			ImageButton	DD_Target3 = (ImageButton) findViewById(R.id.dd_target3);
//			ImageButton	DD_Target4 = (ImageButton) findViewById(R.id.dd_target4);
			
			DD_Target1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					    Intent DartScore = new Intent();
					    DartScore.setClass( target.this, DartScore1.class );
//					    DartScore.putExtras(postParams);
			            startActivity(DartScore);
				}
			});
			
		}
		
		  @Override
		    public boolean onCreateOptionsMenu(Menu menu) {

			  getMenuInflater().inflate(R.menu.activity_main, menu);
		        ActionBar actionBar = getActionBar();  
		        actionBar.setDisplayHomeAsUpEnabled(false);  
		        MenuItem AddItem = menu.findItem(R.id.menu_score);
		     

		        return super.onCreateOptionsMenu(menu);
		    }
		  @Override
		  public boolean onOptionsItemSelected(MenuItem item) {
		      // Handle presses on the action bar items
		      switch (item.getItemId()) {
		          case R.id.menu_score:

						FlurryAgent.logEvent("menu_score");
		        	  Intent intent = new Intent(this, ScoreBoard.class);
		        	  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	  startActivity(intent);
//		        	 this.finish();
		        	  return true;
		 //         case R.id.menu_settings:
		            //  openSettings();
		             // return true;
		          default:
		              return super.onOptionsItemSelected(item);
		      }
		  }
	}
