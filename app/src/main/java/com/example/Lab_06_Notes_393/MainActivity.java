package com.example.Lab_06_Notes_393;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab_06_notes_illarionov_393.R;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter <Note> adp; // note array
    int sel; // selected note position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter <Note> (this, android.R.layout.simple_list_item_1); // create note array

        ListView lst = findViewById(R.id.lst_notes); // get list and attach note array to it
        lst.setAdapter(adp);

        lst.setOnItemClickListener( new  AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                sel = position;
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (data != null)
        {
            int pos = data.getIntExtra("my-note-index", -1);
            String title = data.getStringExtra("my-note-title");
            String content = data.getStringExtra("my-note-content");

            Note n = adp.getItem(pos);
            n.title = title;
            n.content = content;

            adp.notifyDataSetChanged(); //update list box

        }
        super.onActivityResult(requestCode,resultCode,data);


    }

    public void on_new_click(View v)
    {
        Note n = new Note(); //create new note
        n.title = "New note";
        n.content = "Some content";

        adp.add(n); //add to list
        int pos = adp.getPosition (n); //get it is position (array element index)

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("my-note-index", pos); //share note data with new activity
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-content", n.content);

        startActivityForResult(i, 12345); //show note editing activity

    }

    public void on_edit_click(View v) {
        Note n = adp.getItem(sel);
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("my-note-index", sel);
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-content", n.content);

        startActivityForResult(i, 12345);

    }
}
