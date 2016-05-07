package com.example.jl.projectmobile;

import android.content.Intent;
import android.graphics.PorterDuff;
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

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            "Lorem ipsum dolor sit amet, consectetur",
            "Event 2",
            "Event 3",
            "Event 4",
            "Event 5",
    };

        //descripcion del evento
    String[] descripcion = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Description 2",
            "Description 3",
            "Description 4",
            "Description 5",
    };

    String[] fecha = {
            "11/06/2016",
            "21/07/2016",
            "21/07/2016",
            "21/07/2016",
            "21/07/2016",
    };

    String[] lugar = {
            "Gimnasio ITESM",
            "Auditorio ITESM",
            "Auditorio ITESM",
            "Auditorio ITESM",
            "Auditorio ITESM",
    };

        //id de imagen del evento
    Integer[] imageId1 = {
            R.drawable.fondo,
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

    JSONObject jsonDifusionArTec;

    //Arreglos para categorias
    String[] titlesCat;
    String[] descCat;
    String[] dateCat;
    String[] placeCat;

    //Arreglos para favoritos
    String[] titlesStr;
    String[] descStr;
    Integer[] imgsInt;
    Integer[] idsInt;
    String[] dateStr;
    String[] placeStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getEvents();

        editText = (EditText)findViewById(R.id.et_menu);
        editText.getBackground().setColorFilter(getResources().getColor(R.color.com_facebook_blue), PorterDuff.Mode.SRC_ATOP);
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

        ListEvents adapter = new ListEvents(MenuActivity.this, eventos, descripcion);
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
                intent.putExtra("fecha", fecha[+position]);
                intent.putExtra("lugar", lugar[+position]);
                startActivity(intent);
            }
        });


        Button btnEvents;
        btnEvents = (Button) findViewById(R.id.btn_events);

        btnEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListEvents adapter = new ListEvents(MenuActivity.this, eventos, descripcion);
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
                        intent.putExtra("fecha", fecha[+position]);
                        intent.putExtra("lugar", lugar[+position]);
                        startActivity(intent);
                    }
                });
            }
        });


        Button btnCategory;
        btnCategory = (Button) findViewById(R.id.btn_cat);

        btnCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListEvents adapter = new ListEvents(MenuActivity.this, categorias, vacio);
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
                        ArrayList<String> catDates = new ArrayList<String>();
                        ArrayList<String> catPlaces = new ArrayList<String>();

                        //categoria Seleccionada
                        Integer idAct = idCat[+position];
                        //Obtener todos los eventos de esta categoria
                        int pos = 0;
                        for (int item : idCatEvent) {
                            if (item == idAct) {
                                catids.add(ids[+pos]);
                                catimgs.add(imageId1[+pos]);
                                cattitles.add(eventos[+pos]);
                                catdescriptions.add(descripcion[+pos]);
                                catDates.add(fecha[+pos]);
                                catPlaces.add(lugar[+pos]);
                            }
                            pos++;
                        }

                        titlesCat = new String[cattitles.size()];
                        titlesCat = cattitles.toArray(titlesCat);

                        descCat = new String[catdescriptions.size()];
                        descCat = catdescriptions.toArray(descCat);

                        dateCat = new String[catDates.size()];
                        dateCat = catDates.toArray(dateCat);

                        placeCat = new String[catPlaces.size()];
                        placeCat = catPlaces.toArray(placeCat);

                        Intent intent = new Intent(MenuActivity.this, CategoryEvents.class);
                        intent.putExtra("ids", catids);
                        intent.putExtra("imgs", catimgs);
                        intent.putExtra("events", titlesCat);
                        intent.putExtra("des", descCat);
                        intent.putExtra("dates", dateCat);
                        intent.putExtra("places", placeCat);
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
                ArrayList<String> favsDates = new ArrayList<String>();
                ArrayList<String> favsPlaces = new ArrayList<String>();

                Iterator<Event> itr = eventos.iterator();
                while (itr.hasNext()) {
                    Event actual = itr.next();
                    favsids.add(actual.getID());
                    favsimgs.add(actual.getImage());
                    favstitles.add(actual.getTitle());
                    favsdescriptions.add(actual.getDescription());
                    favsDates.add(actual.getDate());
                    favsPlaces.add(actual.getPlace());
                }

                titlesStr = new String[favstitles.size()];
                titlesStr = favstitles.toArray(titlesStr);

                descStr = new String[favsdescriptions.size()];
                descStr = favsdescriptions.toArray(descStr);

                dateStr = new String[favsDates.size()];
                dateStr = favsDates.toArray(dateStr);

                placeStr = new String[favsPlaces.size()];
                placeStr = favsPlaces.toArray(placeStr);

                imgsInt = new Integer[favsimgs.size()];
                imgsInt = favsimgs.toArray(imgsInt);

                idsInt = new Integer[favsids.size()];
                idsInt = favsids.toArray(idsInt);

                ListEvents adapter = new ListEvents(MenuActivity.this, titlesStr, descStr);
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
                        intent.putExtra("fecha", dateStr[+position]);
                        intent.putExtra("lugar", placeStr[+position]);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void getEvents() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/327390470624805/events",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        jsonDifusionArTec = response.getJSONObject();
                        System.out.println("Hola " + jsonDifusionArTec);
                        try {
                            JSONArray data = jsonDifusionArTec.getJSONArray("data");

                            //hacerlas globales
                            List<String> eventosList = new ArrayList<String>();
                            List<String> descripcionList = new ArrayList<String>();
                            for (int i = 0; i < data.length(); i++){
                                JSONObject evento = data.getJSONObject(i);
                                String name = evento.getString("name");
                                String descripcion = evento.getString("description");

                                System.out.println("Name = " + name);

                                //System.out.println("Descripcion = " + descripcion);
                                eventosList.add(name);
                                descripcionList.add(descripcion);
                            }



                            // mandar llamar en events 2 porque es lo ultimoq ue se ejecuta

                            eventos = eventosList.toArray(new String[eventosList.size()]);
                            descripcion = descripcionList.toArray(new String[descripcionList.size()]);

                            ListEvents adapter = new ListEvents(MenuActivity.this, eventos, descripcion);
                            list = (ListView) findViewById(R.id.listMenu);
                            list.setAdapter(adapter);
                            //lamar events2
                        }
                        catch (JSONException e) {

                        }
                    }
                }
        ).executeAsync();
    }

    // Events 2

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
