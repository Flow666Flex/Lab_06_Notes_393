package com.example.Lab_06_Notes_393;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lab_06_notes_illarionov_393.R;

public class MainActivity2 extends AppCompatActivity {

    EditText txt_title; //text boxes
    EditText txt_content;

    int pos; //note array index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_title = findViewById(R.id.txt_title); // aquire text boxes
        txt_content = findViewById(R.id.txt_content);

        Intent i = getIntent(); //get note data
        pos = i.getIntExtra("my-note-index", -1);
        txt_title.setText(i.getStringExtra("my-note-title"));
        txt_content.setText(i.getStringExtra("my-note-content"));
    }

    public void on_cancel_click(View v)
    {
        finish();
    }

    public void on_save_click(View v)
    {
        Intent i = new Intent();
        i.putExtra("my-note-index", pos); //prepare updated note data back to first activity
        i.putExtra("my-note-title", txt_title.getText().toString());
        i.putExtra("my-note-content", txt_content.getText().toString());

        setResult(RESULT_OK, i); //return to first activity
        finish();
    }
}
