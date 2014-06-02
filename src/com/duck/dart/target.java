package com.duck.dart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
			ImageButton	DD_Target2 = (ImageButton) findViewById(R.id.dd_target2);
			ImageButton	DD_Target3 = (ImageButton) findViewById(R.id.dd_target3);
			ImageButton	DD_Target4 = (ImageButton) findViewById(R.id.dd_target4);
			
			DD_Target1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					    Intent DartScore = new Intent();
					    DartScore.setClass( target.this, DartScore.class );
//					    DartScore.putExtras(postParams);
			            startActivity(DartScore);

				}
			});
		}
	}
