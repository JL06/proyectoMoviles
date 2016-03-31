package com.example.jl.projectmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    ListView list;
    String[] eventos = {
            "Event 1",
            "Event 2",
            "Event 3",
            "Event 4",
            "Event 5",
    };

    String[] descripcion = {
            "Description 1",
            "Description 2",
            "Description 3",
            "Description 4",
            "Description 5",
    };

    Integer[] imageId1 = {
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
    };

    String[] categorias = {
            "Category 1",
            "Category 2",
            "Category 3",
    };

    String[] vacio = {
            " ",
            " ",
            " ",
            " ",
            " ",
    };


    String[] eventosFav = {
            "Event 1",
            "Event 3",
            "Event 4",
    };

    String[] descripcionFav = {
            "Description 1",
            "Description 3",
            "Description 4",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ListEvents adapter = new ListEvents(MenuActivity.this, eventos, imageId1, descripcion);
        list = (ListView)findViewById(R.id.listMenu);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                intent.putExtra("img", imageId1[+position]);
                intent.putExtra("event", eventos[+position]);
                intent.putExtra("des", descripcion[+position]);
                startActivity(intent);
            }
        });



        Button btnEvents;
        btnEvents = (Button) findViewById(R.id.btn_events);

        btnEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListEvents adapter = new ListEvents(MenuActivity.this, eventos, imageId1, descripcion);
                list = (ListView) findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                        intent.putExtra("img", imageId1[+position]);
                        intent.putExtra("event", eventos[+position]);
                        intent.putExtra("des", descripcion[+position]);
                        startActivity(intent);
                    }
                });
            }
        });


        Button btnCategory;
        btnCategory = (Button) findViewById(R.id.btn_cat);

        btnCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListEvents adapter = new ListEvents(MenuActivity.this, categorias, imageId1, vacio);
                list = (ListView)findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("Click Cat!!!");
                    }
                });
            }
        });

        Button btnFavorite;
        btnFavorite = (Button) findViewById(R.id.btn_fav);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListEvents adapter = new ListEvents(MenuActivity.this, eventosFav, imageId1, descripcionFav);
                list = (ListView) findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                        intent.putExtra("img", imageId1[+position]);
                        intent.putExtra("event", eventosFav[+position]);
                        intent.putExtra("des", descripcionFav[+position]);
                        intent.putExtra("fav", true);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}
