/*AVDB Copyright*/
package com.duck.dart;

//FACEBOOK SIGN In
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphLocation;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

 
	   public class Splash extends Activity {
		 
			private static final List<String> PERMISSIONS = Arrays.asList("publish_actions","user_birthday","basic_info","user_location");
			private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
			private boolean pendingPublishReauthorization = false;

			private Session.StatusCallback callback = new Session.StatusCallback() {
			    @Override
			    public void call(Session session, SessionState state, Exception exception) {
			        onSessionStateChange(session, state, exception);
			    }
			};
			
			private Button publishStory;

			private UiLifecycleHelper uiHelper;

			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.splash);
				 
				try {
				    PackageInfo info = getPackageManager().getPackageInfo(
				          "com.movie.o2", PackageManager.GET_SIGNATURES);
				    for (Signature signature : info.signatures){
				           MessageDigest md = MessageDigest.getInstance("SHA");
				           md.update(signature.toByteArray());
				           Log.d("ymowkey:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				    }
				} catch (NameNotFoundException e) {
				} catch (NoSuchAlgorithmException e) {
				}
			
				uiHelper = new UiLifecycleHelper(this, callback);
			    uiHelper.onCreate(savedInstanceState);
//			    LoginButton authButton = (LoginButton) findViewById(R.id.authButton);
//			    authButton.clearPermissions();
//			    authButton.setReadPermissions(Arrays.asList("email","user_birthday"));
			    publishStory = (Button) findViewById(R.id.publishStory);
			    publishStory.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View v) {
			            publishStory();        
			        }
			    });
			}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.activity_main, menu);
				return true;
			}
			
			@Override
			public void onResume() {
			    super.onResume();
			    
			    Session session = Session.getActiveSession();
			    if (session != null &&
			           (session.isOpened() || session.isClosed()) ) {
			        onSessionStateChange(session, session.getState(), null);
			    }
			    
			    uiHelper.onResume();
			}

			@Override
			public void onActivityResult(int requestCode, int resultCode, Intent data) {
			    super.onActivityResult(requestCode, resultCode, data);
			    uiHelper.onActivityResult(requestCode, resultCode, data);
			}

			@Override
			public void onPause() {
			    super.onPause();
			    uiHelper.onPause();
			}

			@Override
			public void onDestroy() {
			    super.onDestroy();
			    uiHelper.onDestroy();
			}

			@Override
			public void onSaveInstanceState(Bundle outState) {
			    super.onSaveInstanceState(outState);
			    outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
			    uiHelper.onSaveInstanceState(outState);
			}
			
			private void onSessionStateChange(Session session, SessionState state, Exception exception) {
				  if (state.isOpened()) {
				        Log.i("TAG", "Logged in...");
				        Log.e("sessionopened", "true");
				        Bundle postParams = new Bundle();
				        Request.newMeRequest(session, new Request.GraphUserCallback() {
			                @Override
			                public void onCompleted(GraphUser user, Response response) {
			                    if (user != null) {
			                    	  StringBuilder userInfo = new StringBuilder("");
			                        String firstName = user.getFirstName();
			                        String lastName = user.getLastName();
			                        String username = user.getUsername();
			                        userInfo.append(String.format("Birthday: %s\n\n", 
			                                user.getBirthday()));
			                        String birthday = user.getBirthday();
			                        String mLink = user.getLink();
			                        String email =   user.getProperty("email").toString();
			                        String gender = (String) user.getProperty("gender");

			                        double MyLatitude = user.getLocation().getLatitude();
			                        double MyLongitude = user.getLocation().getLongitude();
			                        
			                        
			                        String id = user.getId();

			                        Log.e("facebookid", id);
			                        Log.e("firstName", firstName);
			                        Log.e("lastName", lastName);
			                        Log.e("username", username);
			                        Log.e("mLink", mLink);
			                        Log.e("mLink", gender);    
			                        Log.e("birthday",birthday );
//			                        Log.e("GraphLocation",GraphLocation.toString() );
			                        
			                        Log.e("email", email);
			                    }
			                }
			            }).executeAsync();
				        postParams.putString("name", "Facebook??????");
				        postParams.putString("caption", "????????????story");
				        postParams.putString("description", "");
				        postParams.putString("link", "http://taipei.ntua.edu.tw/elite-general.html");
				        postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
				        Log.i("TAG", "Intent...");
				        Intent myProfile = new Intent();
				        myProfile.setClass( Splash.this, target.class );
				        myProfile.putExtras(postParams);
			            startActivity(myProfile);
			            Splash.this.finish();
				    } else if (state.isClosed()) {
				        Log.i("TAG", "Logged out...");
				    }
				    else
				        Log.i("TAG", session.toString());
				
			}
			
			private void publishStory() {
			    Session session = Session.getActiveSession();

			    if (session != null){

			        // Check for publish permissions    
			        List<String> permissions = session.getPermissions();
			        if (!isSubsetOf(PERMISSIONS, permissions)) {
			            pendingPublishReauthorization = true;
			            Session.NewPermissionsRequest newPermissionsRequest = new Session
			                    .NewPermissionsRequest(this, PERMISSIONS);
			        session.requestNewPublishPermissions(newPermissionsRequest);
			            return;
			        }

			        Bundle postParams = new Bundle();
			        postParams.putString("name", "Facebook??????");
			        postParams.putString("caption", "????????????story");
			        postParams.putString("description", "");
			        postParams.putString("link", "http://taipei.ntua.edu.tw/elite-general.html");
			        postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

			        Request.Callback callback= new Request.Callback() {
			            public void onCompleted(Response response) {
			                JSONObject graphResponse = response
			                                           .getGraphObject()
			                                           .getInnerJSONObject();
			                String postId = null;
			                try {
			                    postId = graphResponse.getString("id");
			                } catch (JSONException e) {
			                    System.out.println("JSON error "+ e.getMessage());
			                }
			                FacebookRequestError error = response.getError();
			                if (error != null) {
			                    Toast.makeText(getApplicationContext(),
			                         error.getErrorMessage(),
			                         Toast.LENGTH_SHORT).show();
			                    } else {
			                        Toast.makeText(getApplicationContext(), 
			                             postId,
			                             Toast.LENGTH_LONG).show();
			                }
			            }
			        };

			        Request request = new Request(session, "me/feed", postParams, 
			                              HttpMethod.POST, callback);

			        RequestAsyncTask task = new RequestAsyncTask(request);
			        task.execute();
			    }

			}
			
			private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
			    for (String string : subset) {
			        if (!superset.contains(string)) {
			            return false;
			        }
			    }
			    return true;
			}

		}
