package com.duck.dart;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		 Parse.initialize(this, "OsdRFzMmWfRGiFew2PoYK94QtjJjTCl4x7rniH5c", "Ggw2gf8RtA3Hicoq8GdxCIENqJiAV2Q6E9i8mFXt");
		 ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}
	

}
