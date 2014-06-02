package com.duck.dart;

 
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ScoreBoard extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
//		setContentView(R.layout.scoreboard);

		 setListAdapter(new ArrayAdapter<String>(this,
		 android.R.layout.simple_list_item_1, mStrings));
		 
		 //啟用按鍵過濾功能
		 getListView().setTextFilterEnabled(true);
		 }
		 
		 private static final String[] mStrings = new String[] {
		 "Round 1", "Round 2", "Round 3", "Round 4", "Round 5",
		 "Round 6", "Round 7", "Round 8"
		 };
	}

