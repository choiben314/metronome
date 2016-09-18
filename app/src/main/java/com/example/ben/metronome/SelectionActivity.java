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
                    intent.putExtra("Piece", piece);
                    startActivity(intent);
                }
            });
        }

        ListView listView = (ListView) findViewById(R.id.mainListView);

        listItems = new ArrayList<Piece>();
        Exception ex = new Exception (5, 8, 52, 2, 2);
        Exception ex1 = new Exception (5, 8, 52, 4, 4);
        Exception ex2 = new Exception (7, 8, 52, 5, 5);
        Exception ex3 = new Exception (5, 8, 52, 6, 6);
        Exception ex4 = new Exception (3, 8, 52, 10, 10);



        ArrayList<Exception>arr = new ArrayList<Exception>();
        arr.add(ex);
        arr.add(ex2);
        arr.add(ex3);
        arr.add(ex4);

        ArrayList<Exception>arr1 = new ArrayList<Exception>();
        Exception exc1 = new Exception (7, 8, 180, 4, 8);
        Exception exc2 = new Exception (5, 8, 120, 10, 12);

        arr1.add(exc1);
        arr1.add(exc2);

        Piece piece = new Piece ("Stravinsky 3 Pieces for Clarinet", new Default(2, 4, 52), arr, 1000);
        Piece piece1 = new Piece ("Mozart", new Default(3, 4, 180), arr, 1000);
        Piece piece2 = new Piece ("Beethoven Symphony No.8", new Default(5, 4, 90), arr, 1000);
        Piece piece3 = new Piece ("Bartok Concerto", new Default(6, 4, 120), arr, 1000);
        Piece piece4 = new Piece ("String Quartet", new Default(3, 2, 67), arr, 1000);
        Piece piece5 = new Piece ("Debussy Clair de Lune", new Default(2, 4, 52), arr, 1000);
        Piece piece9 = new Piece ("My New Piece 1", new Default(4, 4, 100), arr1, 1000);
        listItems.add(piece);
        listItems.add(piece1);
        listItems.add(piece2);
        listItems.add(piece3);
        listItems.add(piece4);
        listItems.add(piece5);
        listItems.add(piece9);

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
