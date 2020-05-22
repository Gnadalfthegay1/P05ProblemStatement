package com.myapplicationdev.android.p05problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lv;
    Button btSort;
    ArrayList<Song> songs;
    ArrayAdapter aa;
    Spinner s;
    String year[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = findViewById(R.id.lv);
        btSort = findViewById(R.id.btFilter);
        s = findViewById(R.id.spinner);

        songs = new ArrayList<Song>();
        DBHelper db = new DBHelper(ListActivity.this);
        songs = db.getAllSongs();
        aa = new CustomAdapter(this, R.layout.row, songs);
        lv.setAdapter(aa);
        String year[] = new String[songs.size()];
        for(int i = 0; i< songs.size(); i++){
            Song s = songs.get(i);
            year[i] = Integer.toString(s.getYear());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
        s.setAdapter(a);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper db = new DBHelper(ListActivity.this);
                int date = Integer.parseInt((String) parent.getItemAtPosition(position));
                songs = db.getDateOfSong(date);
                aa = new CustomAdapter(ListActivity.this, R.layout.row, songs);
                lv.setAdapter(aa);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songs.clear();
                DBHelper db = new DBHelper(ListActivity.this);
                songs = db.getRatingOfSong(5);
                aa = new CustomAdapter(ListActivity.this, R.layout.row, songs);
                lv.setAdapter(aa);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song s = songs.get(position);
                Intent i = new Intent(ListActivity.this, EditActivity.class);
                i.putExtra("data", s);
                startActivityForResult(i, 9);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            songs = new ArrayList<Song>();
            songs.clear();
            DBHelper db = new DBHelper(ListActivity.this);
            songs = db.getAllSongs();
            aa = new CustomAdapter(this, R.layout.row, songs);
            lv.setAdapter(aa);
        }
    }
}
