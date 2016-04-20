package com.example.jl.projectmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    //info de todos los eventos
    private EditText editText;
    ListView list;
        //su propio id
    Integer[] ids = {
            1,
            2,
            3,
            4,
            5,
    };

        //id de la categoria a la que perteneces
    Integer[] idCatEvent = {
            1,
            2,
            3,
            1,
            2,
    };

        //Titulo del evento
    String[] eventos = {
            "Event 1",
            "Event 2",
            "Event 3",
            "Event 4",
            "Event 5",
    };

        //descripcion del evento
    String[] descripcion = {
            "Description 1",
            "Description 2",
            "Description 3",
            "Description 4",
            "Description 5",
    };

        //id de imagen del evento
    Integer[] imageId1 = {
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
            R.drawable.img,
    };

    //Info por categoria
    String[] categorias = {
            "Category 1",
            "Category 2",
            "Category 3",
    };

        //id de la categoria
    Integer[] idCat = {
            1,
            2,
            3,
    };

        //descripcion de la categoria
    String[] vacio = {
            " ",
            " ",
            " ",
            " ",
            " ",
    };

    //Arreglos para categorias
    String[] titlesCat;
    String[] descCat;

    //Arreglos para favoritos
    String[] titlesStr;
    String[] descStr;
    Integer[] imgsInt;
    Integer[] idsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        editText = (EditText)findViewById(R.id.et_menu);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ListEvents adapter = new ListEvents(MenuActivity.this, eventos, imageId1, descripcion);
        list = (ListView)findViewById(R.id.listMenu);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                intent.putExtra("id", ids[+position]);
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
                        intent.putExtra("id", ids[+position]);
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

                        ArrayList<Integer> catids = new ArrayList<Integer>();
                        ArrayList<Integer> catimgs = new ArrayList<Integer>();
                        ArrayList<String> cattitles = new ArrayList<String>();
                        ArrayList<String> catdescriptions = new ArrayList<String>();

                        Integer idAct = idCat[+position];
                        //Obtener todos los eventos de esta categoria
                        int pos = 0;
                        for (int item : idCatEvent) {
                            if (item == idAct) {
                                catids.add(ids[+pos]);
                                catimgs.add(imageId1[+pos]);
                                cattitles.add(eventos[+pos]);
                                catdescriptions.add(descripcion[+pos]);
                            }
                            pos++;
                        }

                        titlesCat = new String[cattitles.size()];
                        titlesCat = cattitles.toArray(titlesCat);

                        descCat = new String[catdescriptions.size()];
                        descCat = catdescriptions.toArray(descCat);

                        Intent intent = new Intent(MenuActivity.this, CategoryEvents.class);
                        intent.putExtra("ids", catids);
                        intent.putExtra("imgs", catimgs);
                        intent.putExtra("events", titlesCat);
                        intent.putExtra("des", descCat);
                        startActivity(intent);
                    }
                });
            }
        });


        Button btnFavorite;
        btnFavorite = (Button) findViewById(R.id.btn_fav);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FavoritesDB MDB = new FavoritesDB(getApplicationContext());
                List<Event> eventos = MDB.retrieveEvent();

                ArrayList<Integer> favsids = new ArrayList<Integer>();
                ArrayList<Integer> favsimgs = new ArrayList<Integer>();
                ArrayList<String> favstitles = new ArrayList<String>();
                ArrayList<String> favsdescriptions = new ArrayList<String>();

                Iterator<Event> itr = eventos.iterator();
                while (itr.hasNext()) {
                    Event actual = itr.next();
                    favsids.add(actual.getID());
                    favsimgs.add(actual.getImage());
                    favstitles.add(actual.getTitle());
                    favsdescriptions.add(actual.getDescription());
                }

                titlesStr = new String[favstitles.size()];
                titlesStr = favstitles.toArray(titlesStr);

                descStr = new String[favsdescriptions.size()];
                descStr = favsdescriptions.toArray(descStr);

                imgsInt = new Integer[favsimgs.size()];
                imgsInt = favsimgs.toArray(imgsInt);

                idsInt = new Integer[favsids.size()];
                idsInt = favsids.toArray(idsInt);

                ListEvents adapter = new ListEvents(MenuActivity.this, titlesStr, imgsInt, descStr);
                list = (ListView) findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                        intent.putExtra("id", idsInt[+position]);
                        intent.putExtra("img", imgsInt[+position]);
                        intent.putExtra("event", titlesStr[+position]);
                        intent.putExtra("des", descStr[+position]);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void buscar(String s){
        // Aquí estará la función de buscar, y desplegar, cuando los eventos se jalen correctamnete.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            default:
                break;
            case R.id.settings:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
