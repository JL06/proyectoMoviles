package com.example.jl.projectmobile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JL on 21/03/16.
 */
public class ListEvents extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] texto;
    private final String[] texto2;
    //private final Integer[] imageId1;

    public ListEvents(Activity context, String[] text, String[] text2) {
        super(context, R.layout.list_events, text);
        this.context = context;
        this.texto = text;
        this.texto2 = text2;
        //this.imageId1 = imageId1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.list_events, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtBig_list_events);
        txtTitle.setText(texto[position]);

        TextView txtSubTitle = (TextView) rowView.findViewById(R.id.txtMed_list_events);
        txtSubTitle.setText(texto2[position]);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img_list_events);
        //imageView.setImageResource();

        return rowView;
    }
}
