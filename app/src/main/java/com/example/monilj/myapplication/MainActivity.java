package com.example.monilj.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog.Builder alertDialog;
    private Button showDialog;
    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView totalResultTextView;
    private TextView textViewSeekbar;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalBillTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog =(Button) findViewById(R.id.showDialogId);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show the actual alert dialog
                alertDialog = new AlertDialog.Builder(MainActivity.this);
                //Setup title
                //alertDialog.setTitle(R.string.title);
                // Alternate way
                alertDialog.setTitle(getResources().getString(R.string.title));
                //set message
                alertDialog.setMessage(getResources().getString(R.string.message));
                //set cancelable
                alertDialog.setCancelable(false);
                //set positive button
                alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                    }
                });

                alertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    }
                });
                //Create actual alert dialog
                AlertDialog dialog = alertDialog.create();
                //show the dialog
                dialog.show();
            }
        });
        enteredAmount = (EditText) findViewById(R.id.billAmountId);
        seekBar = (SeekBar) findViewById(R.id.percentageSeekBar);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        totalResultTextView = (TextView) findViewById(R.id.resultId);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekBar);
        totalBillTv = (TextView) findViewById(R.id.totalBillTextView);
        calculateButton.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekbar.setText(String.valueOf(seekBar.getProgress()) +"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();

            }
        });
    }


    @Override
    public void onClick(View v) {
        calculate();
    }
    public void calculate()
    {
        float result = 0.0f;
        if(!enteredAmount.getText().toString().equals(""))
        {
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            result = enteredBillFloat * seekBarPercentage/100;
            totalResultTextView.setText("Your tip will be $ "+String.valueOf(result));
            totalBillTv.setText("Total bill $ "+String.valueOf(enteredBillFloat + result));
        }
        else
        {
            Toast.makeText(MainActivity.this,"Please enter bill amount",Toast.LENGTH_LONG).show();
        }
    }
}
//Future scope : O/P format is dollar and cents.Cent should be in 2 digits