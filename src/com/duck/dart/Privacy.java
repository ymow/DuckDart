/*AVDB Copyright*/
package com.duck.dart;

 

import com.flurry.android.FlurryAgent;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Privacy extends Activity {
 


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.privacy);
    Button TermsOK = (Button)findViewById(R.id.TermsOK);
    TermsOK.setOnClickListener(new Button.OnClickListener(){ 

        @Override

        public void onClick(View v) {
			FlurryAgent.logEvent("Privacy");         
        	startActivity(new Intent(Privacy.this,MainActivity.class));
	                 finish();
	                 
       			}	 
    });     
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

}    
    
    
//	ToggleButton termbtn = (ToggleButton)findViewById(R.id.TermsBtn);
//	ToggleButton privacybtn = (ToggleButton)findViewById(R.id.PrivacyBtn);
////	termbtn.setOnCheckedChangeListener((OnCheckedChangeListener) this);
////	privacybtn.setOnCheckedChangeListener((OnCheckedChangeListener) this);
//
//	termbtn.setChecked(false);
////	if(termbtn.setChecked(true) && privacybtn.setChecked(checked);){
////		
////	}
//    
// 
//termbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { 
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            Log.d("tglbtn", "This checkbox is: checked");
//        } else {
//            Log.d("tglbtn", "This checkbox is: unchecked");
//        }
//    }
//    });
//
// }
//}
