package com.example.jl.projectmobile;

import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jl.projectmobile.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.Profile;




public class LoginActivity extends FragmentActivity {

    CallbackManager mCallbackManager;

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

                login();
                startActivity(facebookIntent);
                Log.e("Prueba", " Sí funciona el login");
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }



    public void login() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("logged", true);
        editor.apply();
    }

    public void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("logged", false);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

       // Intent facebookIntent = new Intent(LoginActivity.this, MenuActivity.class);

        //startActivity(facebookIntent);
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

