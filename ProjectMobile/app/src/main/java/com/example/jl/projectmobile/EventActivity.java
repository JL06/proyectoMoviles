package com.example.jl.projectmobile;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent = getIntent();
        final double id = intent.getDoubleExtra("id", 0);
        final String title = intent.getStringExtra("event");
        final Integer imageId = intent.getIntExtra("img", 0);
        final String description = intent.getStringExtra("des");
        final String fecha = intent.getStringExtra("fecha");
        final String lugar = intent.getStringExtra("lugar");

        TextView titulo = (TextView) findViewById(R.id.txt_eventAct_lg);
        TextView descripcion = (TextView) findViewById(R.id.txt_eventAct_md);
        TextView TVfecha = (TextView) findViewById(R.id.txt_eventAct_fecha);
        TextView TVlugar = (TextView) findViewById(R.id.txt_eventAct_lugar);
        ImageView imagen = (ImageView) findViewById(R.id.img_eventAct);
        final Button btn = (Button) findViewById(R.id.btn_eventAct);

        final FavoritesDB MDB = new FavoritesDB(getApplicationContext());
        List<Event> eventos = MDB.retrieveEvent();

        Boolean isFav = false;
        ArrayList<String> list = new ArrayList<String>();
        Iterator<Event> itr = eventos.iterator();
        while (itr.hasNext()) {
            if (itr.next().getID() == id) {
                isFav = true;
                break;
            }
        }

        if (isFav) {
            btn.setText("Remove");
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MDB.deleteEvent((int) id);
                    btn.setVisibility(View.GONE);
                }
            });
        } else {
            btn.setText("Add Favorite");
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MDB.insertEvent((int) id, title, description, fecha, lugar, imageId);
                    btn.setVisibility(View.GONE);
                }
            });
        }

        titulo.setText(title);
        descripcion.setText(description);
        imagen.setImageResource(imageId);
        TVfecha.setText(fecha);
        TVlugar.setText(lugar);
    }
}