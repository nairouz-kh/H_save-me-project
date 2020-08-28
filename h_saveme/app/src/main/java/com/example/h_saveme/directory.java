package com.example.h_saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class directory extends AppCompatActivity {
  Context context;
  Button button;
  RadioGroup radioGroup;
  RadioButton radioButton;
  int rad;
  int searchTypeValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
    context = this;
    button =findViewById(R.id.clinic);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showSelectSearchType();
        }
    });
    }
    void showSelectSearchType() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View picker_layout = LayoutInflater.from(context).inflate(R.layout.directory_type,null);
        builder.setView(picker_layout);


        radioButton =picker_layout.findViewById(R.id.az);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(directory.this,by_alphabet.class));
            }
        });
        radioButton =picker_layout.findViewById(R.id.town);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(directory.this,by_town.class));
            }
        });
        radioGroup = picker_layout.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rad = radioGroup.getCheckedRadioButtonId();
            }
        });



        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
