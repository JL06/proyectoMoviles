package com.example.jl.projectmobile;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent = getIntent();
        String title = intent.getStringExtra("event");
        String description = intent.getStringExtra("des");
        Integer imageId = intent.getIntExtra("img", 0);
        Boolean favorite = intent.getBooleanExtra("fav", false);

        TextView titulo = (TextView) findViewById(R.id.txt_eventAct_lg);
        TextView descripcion = (TextView) findViewById(R.id.txt_eventAct_md);
        ImageView imagen = (ImageView) findViewById(R.id.img_eventAct);
        Button btn = (Button) findViewById(R.id.btn_eventAct);

        if (favorite) {
            btn.setText("Remove");
        } else {
            btn.setText("Add Favorite");
        }

        titulo.setText(title);
        descripcion.setText(description);
        imagen.setImageResource(imageId);
    }
}