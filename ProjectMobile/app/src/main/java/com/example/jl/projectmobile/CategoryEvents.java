package com.example.jl.projectmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryEvents extends AppCompatActivity {

    double[] ids;
    Integer[] imgIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_events);

        Intent intent = getIntent();
        final double[] idsAr = intent.getDoubleArrayExtra("ids");
        final String[] titles = intent.getStringArrayExtra("events");
        final String[] descriptions = intent.getStringArrayExtra("des");
        final ArrayList<Integer> imageIdsAr = intent.getIntegerArrayListExtra("imgs");
        final String[] dates = intent.getStringArrayExtra("dates");
        final String[] places = intent.getStringArrayExtra("places");

        //ids = new Integer[idsAr.size()];
        ids = idsAr;

       // imgIds = new Integer[imageIdsAr.size()];
        //imgIds = imageIdsAr.toArray(imgIds);

        ListEvents adapter = new ListEvents(CategoryEvents.this, titles, descriptions);
        ListView list = (ListView) findViewById(R.id.listCategoryEvents);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoryEvents.this, EventActivity.class);
                intent.putExtra("id", ids[+position]);
                //intent.putExtra("img", imgIds[+position]);
                intent.putExtra("event", titles[+position]);
                intent.putExtra("des", descriptions[+position]);
                intent.putExtra("fecha", dates[+position]);
                intent.putExtra("lugar", places[+position]);
                startActivity(intent);
            }
        });
    }
}
