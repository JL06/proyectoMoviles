package com.example.jl.projectmobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;

public class SplashActivity extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                // Cambie MenuActivity.class por LoginActivity.class

                if (isLoggedIn()){
                    Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                    SplashActivity.this.startActivity(i);
                    SplashActivity.this.finish();
                }
                else {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }


        }, SPLASH_DISPLAY_LENGTH);
            }

        /*
    public void onSuccess(LoginResult loginResult) {
            String userLoginId = loginResult.getAccessToken().getUserId();
            Intent facebookIntent = new Intent(SplashActivity.this, MenuActivity.class);
        }
    }
    */

    public boolean isLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("logged", false);
    }

}
