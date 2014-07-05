package com.duck.dart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

 

public class DartScore1 extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.dartscore1);
		
		final int score = 0;
//		int buttonClick  = 0 ;
		Button Point20 = (Button)findViewById(R.id.point20);
		Button Point1 = (Button)findViewById(R.id.point18);
		Button Point18 = (Button)findViewById(R.id.point1);
		Button Point4 = (Button)findViewById(R.id.point4);
		Button Point13 = (Button)findViewById(R.id.point13);
		
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
				 Bundle postAddScore = new Bundle();
				 postAddScore.putInt("AddScore", score);
				 Intent addtoscore = new Intent();
				 addtoscore.setClass( DartScore1.this, target.class );
				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
				 addtoscore.putExtras(postAddScore);
		         startActivity(addtoscore);
			}
		});
			
		Point1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Bundle postAddScore = new Bundle();
				 postAddScore.putInt("AddScore", score);
				 Intent addtoscore = new Intent();
				 addtoscore.setClass( DartScore1.this, target.class );
				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
				 addtoscore.putExtras(postAddScore);
		         startActivity(addtoscore);
			}
		});
			
		Point18.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Bundle postAddScore = new Bundle();
				 postAddScore.putInt("AddScore", score);
				 Intent addtoscore = new Intent();
				 addtoscore.setClass( DartScore1.this, target.class );
				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
				 addtoscore.putExtras(postAddScore);
		         startActivity(addtoscore);
			}
		});
			
		Point4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Bundle postAddScore = new Bundle();
				 postAddScore.putInt("AddScore", score);
				 Intent addtoscore = new Intent();
				 addtoscore.setClass( DartScore1.this, target.class );
				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
				 addtoscore.putExtras(postAddScore);
		         startActivity(addtoscore);
			}
		});
			
		Point13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Bundle postAddScore = new Bundle();
				 postAddScore.putInt("AddScore", score);
				 Intent addtoscore = new Intent();
				 addtoscore.setClass( DartScore1.this, target.class );
				 addtoscore.setClass( DartScore1.this, ScoreBoard.class );
				 addtoscore.putExtras(postAddScore);
		         startActivity(addtoscore);
			}
		});
			

	}
}
