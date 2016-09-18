package com.example.ben.metronome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<Piece> listAdapter;
    private ArrayList<Piece> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newPiece);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SelectionActivity.this, EditActivity.class);
                    Piece piece = new Piece ("My Piece", new Default(4, 4, 100), new ArrayList<Exception>(), 3);
                    listItems.add(piece);
                    intent.putExtra("piece", piece);
                    startActivity(intent);
                }
            });
        }

        ListView listView = (ListView) findViewById(R.id.mainListView);

        listItems = new ArrayList<Piece>();
        Exception ex = new Exception (3, 8, 100, 2, 3);
        ArrayList<Exception>arr = new ArrayList<Exception>();
        arr.add(ex);
        Piece piece = new Piece ("My Piece", new Default(4, 4, 100), arr, 1000);
        listItems.add(piece);

        listAdapter = new ArrayAdapter<Piece>(this, R.layout.simplerow, listItems);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Piece piece = listAdapter.getItem(position);
                Intent intent = new Intent(SelectionActivity.this, PlayActivity.class);
                intent.putExtra("Piece", piece);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
