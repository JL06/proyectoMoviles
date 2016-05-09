package com.example.jl.projectmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
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


    List<Double> eventosidList = new ArrayList<Double>();
    List<String> eventosList = new ArrayList<String>();
    List<String> descripcionList = new ArrayList<String>();
    List<Integer> idcatEventList = new ArrayList<Integer>();
    List<String> fechaList = new ArrayList<String>();
    List<String> lugarList = new ArrayList<String>();
    List<Integer> imageIdList = new ArrayList<Integer>();

    Integer imgCultural = R.drawable.formacioncultural;
    Integer imgDeportes = R.drawable.deportes;

    //info de todos los eventos
    private EditText editText;
    ListView list;
        //su propio id
    Double[] ids = {
            1.0,
            2.0,
            3.0,
            4.0,
            5.0,
    };

        //id de la categoria a la que perteneces
    Integer[] idCatEvent = {

    };

        //Titulo del evento
    String[] eventos = {

    };

        //descripcion del evento
    String[] descripcion = {

    };

    String[] fecha = {

    };

    String[] lugar = {

    };

    Integer[] imagesId = {
            imgCultural,
            imgCultural,
            imgCultural,
            imgCultural,
            imgCultural
    };



    //Info por categoria
    String[] categorias = {
            "Formación Cultural",
            "Deportes"
    };

        //id de la categoria
    Integer[] idCat = {
            1,
            2,
    };

        //descripcion de la categoria
    String[] descripcionCat = {
            "Eventos Culturales",
            "Eventos Deportivos",
    };

    JSONObject jsonDifusionArTec;
    JSONObject jsonSportId;

    //Arreglos para categorias
    String[] titlesCat;
    String[] descCat;
    String[] dateCat;
    String[] placeCat;
    double[] idsCat;

    //Arreglos para favoritos
    String[] titlesStr;
    String[] descStr;
    Integer[] idsInt;
    String[] dateStr;
    String[] placeStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_menu);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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

        ListEvents adapter = new ListEvents(MenuActivity.this, eventos //,imageId1
                , descripcion, imagesId);
        list = (ListView)findViewById(R.id.listMenu);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                intent.putExtra("id", ids[+position]);
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
                ListEvents adapter = new ListEvents(MenuActivity.this, eventos //, imageId1
                         , descripcion, imagesId);
                list = (ListView) findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                        intent.putExtra("id", ids[+position]);
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
                ListEvents adapter = new ListEvents(MenuActivity.this, categorias //, imageId1
                        , descripcionCat, imagesId);
                list = (ListView)findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("Click Cat!!!");

                        ArrayList<Double> catids = new ArrayList<Double>();
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
                                catimgs.add(imagesId[+pos]);
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

                        Double[] idsCatO = new Double[catids.size()];
                        idsCatO = catids.toArray(idsCatO);

                        idsCat = new double[idsCatO.length];
                        int posi = 0;
                        for (Double it : idsCatO) {
                            idsCat[posi] = it.doubleValue();
                            posi++;
                        }

                        Intent intent = new Intent(MenuActivity.this, CategoryEvents.class);
                        intent.putExtra("ids", idsCat);
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

                ArrayList<Double> favsids = new ArrayList<Double>();
                ArrayList<String> favstitles = new ArrayList<String>();
                ArrayList<String> favsdescriptions = new ArrayList<String>();
                ArrayList<String> favsDates = new ArrayList<String>();
                ArrayList<String> favsPlaces = new ArrayList<String>();

                Iterator<Event> itr = eventos.iterator();
                while (itr.hasNext()) {
                    Event actual = itr.next();
                    favsids.add(actual.getID());
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

                //idsInt = new Integer[favsids.size()];
                //idsInt = favsids.toArray(idsInt);


                Double[] idsO = new Double[favsids.size()];
                idsO = favsids.toArray(idsO);

                idsCat = new double[idsO.length];
                int posi = 0;
                for (Double it : idsO) {
                    idsCat[posi] = it.doubleValue();
                    posi++;
                }

                ListEvents adapter = new ListEvents(MenuActivity.this, titlesStr, descStr, imagesId);
                list = (ListView) findViewById(R.id.listMenu);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                        intent.putExtra("id", idsCat[+position]);
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



                            for (int i = 0; i < data.length(); i++){
                                JSONObject evento = data.getJSONObject(i);

                                String idStrEve = evento.getString("id");
                                String name = evento.getString("name");
                                String descripcion = evento.getString("description");
                                String fecha = evento.getString("start_time");

                                JSONObject lugarObj = evento.getJSONObject("place");
                                String lugar = lugarObj.getString("name");

                                //ImageView icon=(ImageView)findViewById(R.id.icon);

                                //icon.setImageResource(R.drawable.formacionCultural);


                                System.out.println("Name = " + name);

                                //System.out.println("Descripcion = " + descripcion);


                                eventosidList.add(Double.parseDouble(idStrEve));
                                eventosList.add(name);
                                descripcionList.add(descripcion);
                                idcatEventList.add(1);
                                fechaList.add(fecha);
                                lugarList.add(lugar);
                                imageIdList.add(imgCultural);

                            }

                            //IMAGEN?????

                            // mandar llamar en events 2 porque es lo ultimoq ue se ejecuta

                            /*
                            eventos = eventosList.toArray(new String[eventosList.size()]);
                            descripcion = descripcionList.toArray(new String[descripcionList.size()]);

                            ListEvents adapter = new ListEvents(MenuActivity.this, eventos, descripcion);
                            list = (ListView) findViewById(R.id.listMenu);
                            list.setAdapter(adapter);

*/
                            //lamar events2


                        }
                        catch (JSONException e) {
                            e.toString();
                            e.getMessage();
                        }
                        getEvents2();
                    }
                }
        ).executeAsync();
    }

    public void getEvents2() {

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/182874473110/events",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        jsonSportId = response.getJSONObject();
                        System.out.println("Hola " + jsonSportId);
                        try {
                            JSONArray data = jsonSportId.getJSONArray("data");



                            for (int i = 0; i < data.length(); i++){
                                JSONObject evento = data.getJSONObject(i);

                                String idStrEve = evento.getString("id");
                                String name = evento.getString("name");
                                String descripcion; // = evento.getString("description");
                                try {
                                    descripcion = evento.getString("description");
                                } catch (JSONException e) {
                                    descripcion = "";
                                }
                                String fecha = evento.getString("start_time");
                                String lugar;
                                try {
                                    JSONObject lugarObj = evento.getJSONObject("place");
                                    lugar  = lugarObj.getString("name");
                                } catch (JSONException e) {
                                    lugar = "";
                                }




                                System.out.println("Name = " + name);

                                //System.out.println("Descripcion = " + descripcion);
                                eventosidList.add(Double.parseDouble(idStrEve));
                                eventosList.add(name);
                                descripcionList.add(descripcion);
                                idcatEventList.add(2);
                                fechaList.add(fecha);
                                lugarList.add(lugar);
                                imageIdList.add(imgDeportes);
                            }



                            // mandar llamar en events 2 porque es lo ultimoq ue se ejecuta


                        }
                        catch (JSONException e) {
                            e.getMessage();
                        }

                        eventos = eventosList.toArray(new String[eventosList.size()]);
                        descripcion = descripcionList.toArray(new String[descripcionList.size()]);
                        idCatEvent = idcatEventList.toArray(new Integer[idcatEventList.size()]);
                        fecha = fechaList.toArray(new String[fechaList.size()]);
                        lugar = lugarList.toArray(new String[lugarList.size()]);
                        ids = eventosidList.toArray(new Double[eventosidList.size()]);
                        imagesId = imageIdList.toArray(new Integer[imageIdList.size()]);

                        ListEvents adapter = new ListEvents(MenuActivity.this, eventos, descripcion, imagesId);
                        MenuActivity.this.list = (ListView) findViewById(R.id.listMenu);
                        MenuActivity.this.list.setAdapter(adapter);
                    }
                }
        ).executeAsync();
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
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

}
