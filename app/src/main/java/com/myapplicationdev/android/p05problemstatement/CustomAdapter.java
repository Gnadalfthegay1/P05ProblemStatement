package com.myapplicationdev.android.p05problemstatement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView tvYear, tvSong, tvSinger;

    public CustomAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        iv1 = rowView.findViewById(R.id.iv1);
        iv2 = rowView.findViewById(R.id.iv2);
        iv3 = rowView.findViewById(R.id.iv3);
        iv4 = rowView.findViewById(R.id.iv4);
        iv5 = rowView.findViewById(R.id.iv5);

        tvYear = rowView.findViewById(R.id.tvYear);
        tvSinger = rowView.findViewById(R.id.tvSingers);
        tvSong = rowView.findViewById(R.id.tvSong);


        Song song = songs.get(position);
        int stars = song.getStars();
        int Year = song.getYear();
        String Singer = song.getSingers();
        String Song = song.getTitle();

        tvYear.setText(Integer.toString(Year));
        tvSinger.setText(Singer);
        tvSong.setText(Song);
        //Check if the property for starts >= 5, if so, "light" up the stars
        if (stars >= 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 4){
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 3){
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 2){
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }



}
