package com.example.ben.metronome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
//    private EditText result = (EditText) findViewById(R.id.editTextResult);
//    public String result = "";

    private int exceptionNumberPickerVal = 120;

    private Default dflt = new Default(4, 4, 120);
    private ArrayList<Exception> excpt = new ArrayList<Exception>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAddDefault = (FloatingActionButton) findViewById(R.id.fabAddDefault);
        fabAddDefault.setOnClickListener(new View.OnClickListener() {
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
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                        new ContextThemeWrapper(EditActivity.this, android.R.style.Theme_Holo_Dialog));

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
                        TextView text = (TextView)findViewById(R.id.textViewDefaultTempo);
                        text.setText(newVal + "");
                        dflt.setTempo(newVal);



                    }
                });

                final EditText meterTop = (EditText) promptsView
                        .findViewById(R.id.meterTop);
                final EditText meterBot = (EditText) promptsView
                        .findViewById(R.id.meterBot);

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
                                        ((TextView)findViewById(R.id.meterTop)).setText(meterTop.getText().toString());
                                        ((TextView)findViewById(R.id.meterBot)).setText(meterBot.getText().toString());
//                                        NumberPicker np = (NumberPicker) promptsView.findViewById(R.id.tempoPicker);
//                                        dflt = new Default(meterTop.getText(), meterBot.getText(), np.getValue());
                                        dflt.setMeterTop(Integer.parseInt(meterTop.getText().toString()));
                                        dflt.setMeterBot(Integer.parseInt(meterBot.getText().toString()));
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

        /*
         *********************************************************************************************************
         */

        FloatingActionButton fabAddException = (FloatingActionButton) findViewById(R.id.fabAddException);
        fabAddException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(EditActivity.this);
                View exceptionPromptsView = li.inflate(R.layout.prompts_exception, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        EditActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(exceptionPromptsView);
                NumberPicker np = (NumberPicker) exceptionPromptsView.findViewById(R.id.tempoPicker);
                np.setMinValue(30);
                np.setMaxValue(252);
                np.setWrapSelectorWheel(false);

                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //Display the newly selected number from picker
//                        result.setText("Selected Number : " + newVal);
//                        TextView text = (TextView)findViewById(R.id.textViewDefaultTempo);
//                        text.setText(newVal + "");
                        exceptionNumberPickerVal = newVal;
                    }
                });

                final EditText meterTop = (EditText) exceptionPromptsView
                        .findViewById(R.id.meterTop);
                final EditText meterBot = (EditText) exceptionPromptsView
                        .findViewById(R.id.meterBot);
                final EditText start = (EditText) exceptionPromptsView
                        .findViewById(R.id.start);
                final EditText end = (EditText) exceptionPromptsView
                        .findViewById(R.id.end);

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

                                        TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);
                                        TableRow row = new TableRow(EditActivity.this);
                                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                        row.setLayoutParams(lp);
                                        TextView qty = new TextView(EditActivity.this);
                                        qty.setText("Meter: " + meterTop.getText().toString() + "|" + meterBot.getText().toString() + " - BPM: " + exceptionNumberPickerVal + " - Measures: " + start.getText().toString() + "-" + end.getText().toString() + ".");
                                        row.addView(qty);
                                        ll.addView(row, ll.getChildCount());

                                        Exception ex = new Exception(Integer.parseInt(meterTop.getText().toString()), Integer.parseInt(meterBot.getText().toString()), exceptionNumberPickerVal,
                                                Integer.parseInt(start.getText().toString()), Integer.parseInt(end.getText().toString()));
                                        excpt.add(ex);
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
