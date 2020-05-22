package com.myapplicationdev.android.p05problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.DateValueSanitizer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    Button btUpdate, btDelete, btCancel;
    TextView tvID;
    EditText etSingers, etYears, etSong;
    Song s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);

        tvID = findViewById(R.id.tvID);

        etSingers = findViewById(R.id.etSingers);
        etYears = findViewById(R.id.etYear);
        etSong = findViewById(R.id.etSong);
        btCancel = findViewById(R.id.btCancel);
        Intent i = getIntent();
        s = (Song) i.getSerializableExtra("data");
        tvID.setText(Integer.toString(s.get_id()));
        etSingers.setText(s.getSingers());
        etSong.setText(s.getTitle());
        etYears.setText(Integer.toString(s.getYear()));
        int star = s.getStars();

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg2);
        // Get the Id of the selected radio button in the RadioGroup
        int selectedButtonId = rg.getCheckedRadioButtonId();
        // Get the radio button object from the Id we had gotten above
        if(star == 5){
            RadioButton rb = (RadioButton) findViewById(R.id.radioButton5);
            rb.setChecked(true);
        }else if (star == 4){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton4);
                rb.setChecked(true);
        }else if (star == 3){
            RadioButton rb = (RadioButton) findViewById(R.id.radioButton3);
            rb.setChecked(true);
        }else if (star == 2){
            RadioButton rb = (RadioButton) findViewById(R.id.radioButton2);
            rb.setChecked(true);
        }else if (star == 1){
            RadioButton rb = (RadioButton) findViewById(R.id.radioButton);
            rb.setChecked(true);
        }
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String song = etSong.getText().toString();
                String singer = etSingers.getText().toString();
                int Year = Integer.parseInt(etYears.getText().toString());

                RadioGroup rg = (RadioGroup) findViewById(R.id.rg2);
                // Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                int stars = Integer.parseInt(rb.getText().toString());
                s.setYear(Year);
                s.setTitle(song);
                s.setSingers(singer);
                s.setStars(stars);
                DBHelper db = new DBHelper(EditActivity.this);
                db.updateSong(s);
                Intent i = getIntent();
                i.putExtra("data", s);
                setResult(RESULT_OK, i);
                finish();

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(s.get_id());
                dbh.close();
                Intent i = getIntent();
                i.putExtra("data", s);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
