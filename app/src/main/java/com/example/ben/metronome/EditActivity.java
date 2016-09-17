package com.example.ben.metronome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
//    private EditText result = (EditText) findViewById(R.id.editTextResult);
    public String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addException);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
//                builder.setMessage("A different message").setTitle("The title")
//                        .setPositiveButton("Do it", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // FIRE ZE MISSILES!
//                            }
//                        })
//                        .setNegativeButton("Don't do it", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
//                builder.show();
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(EditActivity.this);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        EditActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                NumberPicker np = (NumberPicker) promptsView.findViewById(R.id.tempoPicker);
                np.setMinValue(30);
                np.setMaxValue(252);
                np.setWrapSelectorWheel(false);

                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //Display the newly selected number from picker
//                        result.setText("Selected Number : " + newVal);
                        TextView text = (TextView)findViewById(R.id.resultText);
                        text.setText(newVal + "");
                    }
                });

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
//                                        result.setText(userInput.getText());
//                                        result = userInput.getText().toString();
//                                        TextView text = (TextView)findViewById(R.id.resultText);
//                                        text.setText(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }

        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
