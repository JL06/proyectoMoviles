package com.example.jl.projectmobile;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jl.projectmobile.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;



public class LoginActivity extends Activity {
        CallbackManager callbackManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_login);

            LoginButton loginButton;
            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("user_friends");
            // If using in a fragment
            // loginButton.setFragment(this);
            // Other app specific specialization

            // Callback registration
            callbackManager = CallbackManager.Factory.create();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {

                    Log.e("algo", "algo algo");
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
        }
