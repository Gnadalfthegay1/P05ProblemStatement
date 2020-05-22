package com.myapplicationdev.android.p05problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSinger, etYear;
    Button btAdd, btShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btAdd = findViewById(R.id.btAdd);
        btShow = findViewById(R.id.btShow);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = (RadioGroup) findViewById(R.id.rg1);
                // Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId = rg.getCheckedRadioButtonId();
                // Get the radio button object from the Id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                String title = etTitle.getText().toString();
                String Singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = Integer.parseInt(rb.getText().toString());

                DBHelper db = new DBHelper(MainActivity.this);
                // Insert a task
                db.insertSong(title, Singer, year, stars);
                db.close();
                Toast.makeText(MainActivity.this, "Inserted",
                        Toast.LENGTH_LONG).show();
                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
            }
        });
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
    }
}
