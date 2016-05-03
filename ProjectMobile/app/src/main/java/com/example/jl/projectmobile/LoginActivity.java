package com.example.jl.projectmobile;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jl.projectmobile.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.Profile;




public class LoginActivity extends FragmentActivity {

    CallbackManager mCallbackManager;
    public static final String PROFILE_USER_ID = "USER_ID";
    public static final String PROFILE_FIRST_NAME = "PROFILE_FIRST_NAME";
    public static final String PROFILE_LAST_NAME = "PROFILE_LAST_NAME";
    public static final String PROFILE_IMAGE_URL = "PROFILE_IMAGE_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton mLoginButton = (LoginButton)findViewById(R.id.login_button);
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userLoginId = loginResult.getAccessToken().getUserId();
                Intent facebookIntent = new Intent(LoginActivity.this, MenuActivity.class);
                Profile mProfile = Profile.getCurrentProfile();
                String firstName = mProfile.getFirstName();
                String lastName = mProfile.getLastName();
                String userId = mProfile.getId().toString();
                String profileImageUrl = mProfile.getProfilePictureUri(96, 96).toString();
                facebookIntent.putExtra(PROFILE_USER_ID, userId);
                facebookIntent.putExtra(PROFILE_FIRST_NAME, firstName);
                facebookIntent.putExtra(PROFILE_LAST_NAME, lastName);
                facebookIntent.putExtra(PROFILE_IMAGE_URL, profileImageUrl);
                startActivity(facebookIntent);
                Log.e("A huevo", " Perro");
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
}


    /* @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());

            //setContentView(R.layout.activity_login);

            // Callback registration
            callbackManager = CallbackManager.Factory.create();

            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("user_friends");

            // If using in a fragment
            // loginButton.setFragment(this);
            // Other app specific specialization


            // Callback registration

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {

                    Log.e("algo", "algo algoo");
                    // Redirigir a MenuActivity una vez que hace login correctamente
                    Intent mainIntent = new Intent(LoginActivity.this, MenuActivity.class);
                    LoginActivity.this.startActivity(mainIntent);
                    LoginActivity.this.finish();
                }

                @Override
                public void onCancel() {
                    // App code
                }
                @Override
                public void onError(FacebookException exception) {
                    // App code
                }
            });


        }

            @Override
    protected void onResume() {
                super.onResume();

                // Logs 'install' and 'app activate' App Events.
                AppEventsLogger.activateApp(this);
            }

            // Agregar código abajo ↓ en cada actividad para rastrear
            // el tiempo que pasa el usuario en la app.

            @Override
    protected void onPause() {
                super.onPause();

                // Logs 'app deactivate' App Event.
                AppEventsLogger.deactivateApp(this);
            }
*/

