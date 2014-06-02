package com.duck.dart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.android.gms.ads.AdView;
 
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

public class MainActivity extends Activity {
 
		// Declare Variables
		ListView listview;
		List<ParseObject> ob;
		ProgressDialog mProgressDialog;
		ArrayAdapter<String> adapter;
		ArrayList<String>  RestaurantRank = new ArrayList<String>();
		
		  private AdView adView;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Get the view from listview_main.xml
			setContentView(R.layout.listview_main);
		    Boolean b;
		    b=isNetworkAvailable();  //true if connection,  false if not

			 if(!b){
			       //do accordingly to no-connection
				     Log.d("no network","no network");
			    	 Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
//			    	 System.exit(0);
			    	   new Thread(new Runnable() {
			               @Override
			               public void run() {
			                   try {
			                       Thread.sleep(5000);
			                   }
			                   catch (Exception e) { }
			                   System.exit(0);
			               }
			           }).start();
			 
			   }else{

			
			// Save the current Installation to Parse.
			ParseInstallation.getCurrentInstallation().saveInBackground();
			// When users indicate they are Giants fans, we subscribe them to that channel.
			PushService.setDefaultPushCallback(this, MainActivity.class);
//			subscribe Channel
//			PushService.subscribe(this, "AVDbRank", MainActivity.class);
			ParseAnalytics.trackAppOpened(getIntent());
//			Sent Push notification to channel
//			ParsePush push = new ParsePush();
//			push.setChannel("AVDbRank");
//			push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
//			push.sendInBackground();

//			// ?????? adView???
//		    adView = (AdView)findViewById(R.id.adView);
//	 
//		    //get Ad Request
//		    AdRequest request = new AdRequest.Builder()
//		    .setGender(AdRequest.GENDER_MALE)
////		    .setBirthday(new GregorianCalendar(1995, 1, 1).getTime())
//		    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//		     .addTestDevice("2DA6E0791384A56BFA49EC62B3880F42") // ?????? HTC  ????????????
//		    .build();
//		    // Load adView Request
//		    adView.loadAd(request);

	       // Execute RemoteDataTask AsyncTask
			new RemoteDataTask().execute();
		 }
		}

		// RemoteDataTask AsyncTask
		private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(MainActivity.this);
				// Set progressdialog title
				mProgressDialog.setTitle("????????????");
				// Set progressdialog message
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.show();
			 
			}

			@Override
			protected Void doInBackground(Void... params) {
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"AVDb");
				//add new 
//				ParseObject Restaurant = new ParseObject("EatSomething");
//				addGirl.put("GirlName", "XXX");
//				addGirl.put("VideoName", "Where should we go for lunch?");
//				addGirl.put("ClickNumber", "0");
//				addGirl.saveInBackground();
//				query.orderByDescending("_created_at");
				//Ranking
				query.orderByDescending("ClickNumber");
				
				try {
					ob = query.find();
				} catch (ParseException e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// Locate the listview in listview_main.xml
				listview = (ListView) findViewById(R.id.listview);
							// Pass the results into an ArrayAdapter
				adapter = new ArrayAdapter<String>(MainActivity.this,
						R.layout.customfont);

				int i=0;
	 
		 
				// Retrieve object "name" from Parse.com database
				for (ParseObject Rank : ob) {
					i++;
					
					adapter.add(i+"."+(String) Rank.get("VideoName"));
				 Log.d("AVDb",(String) Rank.get("VideoName"));
				 
				//GirlList.add((String) AVgirl.get("GirlName"));
				}
			 
				
				// Binds the Adapter to the ListView
				listview.setAdapter(adapter);
				// Close the progressdialog
				mProgressDialog.dismiss();
				ShoutGun();
				// Capture button clicks on ListView items
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
	 		 
						// Send single item click data to SingleItemView Class
						Intent i = new Intent(MainActivity.this,SingleItemView.class);

						// Pass data "name" followed by the position
						Log.d("videoname", ob.get(position).getString("VideoName").toString());
						Log.d("AVGirlName", ob.get(position).getString("AVGirlName").toString());
						i.putExtra("videoname", ob.get(position).getString("VideoName").toString());
						i.putExtra("AVgirlname", ob.get(position).getString("AVGirlName").toString());
					 
		                
						ParseObject Object = ob.get(position);
						String PlusAdd = Object.getObjectId();
	
				        ParseQuery<ParseObject> query = ParseQuery.getQuery("AVDb");
				        query.getInBackground(PlusAdd, new GetCallback<ParseObject>() {
				          public void done(ParseObject object, ParseException e) {
				            if (e == null) {

				             int s = object.getInt("ClickNumber");
				             System.out.println("ClickNumber = " + s + "+1");
							
				             s = s+1;
								adapter. notifyDataSetChanged();
				             object.put("ClickNumber",  s);
				             object.saveInBackground();
				             
				            } else {
				              
				            }
				          }
				        });
				        
						// Open SingleItemView.java Activity

						startActivity(i);
					}
				});
			}

			private void ShoutGun() {
				// TODO Auto-generated method stub
				
				try{
        	        MediaPlayer mp = new MediaPlayer();
        				   Resources resources = getResources();
//        	        int id = resources.getIdentifier("shoutgun.wav", "raw", "com.sex.AVDb005");
        	        AssetFileDescriptor afd = resources.openRawResourceFd(R.raw.shoutgun);
        			 mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        	            afd.close();
        	            mp.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
        	            mp.setLooping(false);
        	            mp.prepare();
        	            mp.start();
        	        } catch (IllegalArgumentException e) {
        	            // TODO Auto-generated catch block 
        	            e.printStackTrace();
        	        } catch (IllegalStateException e) {
        	            // TODO Auto-generated catch block 
        	            e.printStackTrace();
        	        } catch (IOException e) {
        	            // TODO Auto-generated catch block 
        	            e.printStackTrace();
        	        }
			}
		}
		 @Override
		  public void onPause() {
		    adView.pause();
		    super.onPause();

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
		  @Override
		  public void onResume() {
		    super.onResume();
		    adView.resume(); 
		    Boolean b;
		    b=isNetworkAvailable();  //true if connection,  false if not

		   if(!b){
		       //do accordingly to no-connection
			     Log.d("no network","no network");
		    	 Toast.makeText(MainActivity.this, "??????????????????????????????????????????", Toast.LENGTH_LONG).show();
//		    	 System.exit(0);
		    	   new Thread(new Runnable() {
		               @Override
		               public void run() {
		                   try {
		                       Thread.sleep(5000);
		                   }
		                   catch (Exception e) { }
		                   System.exit(0);
		               }
		           }).start();
		 
		   }else{}
		  }

		  @Override
		  public void onDestroy() {
		    adView.destroy();
		    super.onDestroy();
		  }
//			//check internet connetion
		  private boolean isNetworkAvailable() {
			    ConnectivityManager connectivityManager 
			          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
			    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
		        	  addnewone();
						FlurryAgent.logEvent("addnewone");
//		        	  Intent intent = new Intent(this, MainActivity.class);
//		        	  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		        	  startActivity(intent);
//		        	 this.finish();
		        	  return true;
		 //         case R.id.menu_settings:
		            //  openSettings();
		             // return true;
		          default:
		              return super.onOptionsItemSelected(item);
		      }
		  }

		private void addnewone() {
			// TODO Auto-generated method stub
 
				LayoutInflater factory=LayoutInflater.from(MainActivity.this);
				final View v1=factory.inflate(R.layout.addnewone,null);
		                         //R.layout.login???login.xml???????????????,???login?????????View??????
				
				AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
				
				dialog.setTitle("Movie O2");
				dialog.setView(v1);//????????????View
		                          //?????????????????????v1.findViewById ????????????

				final EditText dialogvideoname = (EditText)v1.findViewById(R.id.DialogVideoName);
 
				final EditText dialoggirlname =  (EditText)v1.findViewById(R.id.DialogGirlName);
				    Log.d("d","?????????");
				    
		        dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
	            		((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
		            	ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
								"AVDb");
		            	if("".equals(dialogvideoname.getText().toString()) || "".equals(dialogvideoname.getText().toString())){
		            		Log.d("d","????????????");
		            		Toast.makeText(MainActivity.this, "????????????",Toast.LENGTH_SHORT).show();
		            		((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

		        		}else{
		        			((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
		        		
		            	//		            	Toast.makeText(MainActivity.this, "??????",Toast.LENGTH_SHORT);
		    		    String newvideo = dialogvideoname.getText().toString();
		    		    String newvideogirl = dialoggirlname.getText().toString();
		    		    SharedPreferences settings = getSharedPreferences("Preference", 1);
		    		    String UserID = settings.getString("UserID", "");
		    		    System.out.println(newvideo);
						ParseObject it = new ParseObject("AVDb");
						it.put("AVGirlName", newvideo);
						it.put("VideoName", newvideogirl);
						it.put("UserID", UserID);
						it.put("ClickNumber", 0);
						it.saveInBackground();
						query.orderByDescending("_created_at");
						Toast.makeText(MainActivity.this, "????????????",Toast.LENGTH_SHORT).show();
		            }
		           } 	
		        });
		        
		        dialog.setNegativeButton("??????",new DialogInterface.OnClickListener() {
		 
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		        
		        dialog.show();

        		}
		public boolean onKeyDown(int keyCode, KeyEvent event) {//???????????????
	        if ((keyCode == KeyEvent.KEYCODE_BACK)) {  
//	            startActivity(new Intent(SingleItemView.this,
//	                    MainActivity.class));
	            finish();
	            System.exit(0);
//	            ConfirmExit();//????????????????????????????????????
	            return true;   
	        }   
	        return super.onKeyDown(keyCode, event);   
	    }
//	    public void ConfirmExit(){//????????????
//	        AlertDialog.Builder ad=new AlertDialog.Builder(MyOpenDataActivity.this);
//	        ad.setTitle("??????");
//	        ad.setMessage("????????????????");
//	        ad.setPositiveButton("???", new DialogInterface.OnClickListener() {//????????????
//	            public void onClick(DialogInterface dialog, int i) {
//	                // TODO Auto-generated method stub
//	                MyOpenDataActivity.this.finish();//??????activity
	//  
//	            }
//	        });
//	        ad.setNegativeButton("???",new DialogInterface.OnClickListener() {
//	            public void onClick(DialogInterface dialog, int i) {
//	                //?????????????????????????????????
//	            }
//	        });
//	        ad.show();//????????????
//	    }
	}
