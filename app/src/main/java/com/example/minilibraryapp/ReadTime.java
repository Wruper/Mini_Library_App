package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadTime extends AppCompatActivity {

    private static final String FILE_PAGES = "pages.txt";
    TextView readTime;
    private FloatingActionButton floaters;
    private int pageCount = 0;
    private double timeRead = 0; // in hours

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_time);
        setIds();
        getPages();
        generateReadTime();
        setActions();
    }



    public boolean getPages(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_PAGES);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while((text = br.readLine()) != null){
                int newData = Integer.parseInt(text);
                pageCount += newData;
            }
            return true;
        }

        catch (IOException e) {
            Toast.makeText(ReadTime.this, "ERROR: No file exists.", Toast.LENGTH_SHORT);
            e.printStackTrace();
            return false;
        }


    }

    public void setIds(){
        floaters = findViewById(R.id.backBtn);
        readTime = findViewById(R.id.readTime_txt);
    }

    public void setActions(){
        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadTime.this,MainActivity.class);
                startActivity(intent);
            }
        });

        readTime.setText("In total you spend: " + timeRead + " hours reading!");


    }

    public void generateReadTime(){
        timeRead = ((pageCount * 1.5) / 60);
        timeRead = Double.parseDouble(new DecimalFormat("#.##").format(timeRead));

        // multiply time read by average reading time 1.5 mins or 1 min and 30 sec and then geth the hours

    }

}