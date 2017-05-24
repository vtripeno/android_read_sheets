package com.example.vrto.read_sheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnCsv;
    Button btnXsl;
    Button btnPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listaValores);
        btnCsv = (Button) findViewById(R.id.csv);
        btnXsl = (Button) findViewById(R.id.xls);
        btnPdf = (Button) findViewById(R.id.pdf);

        btnXsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            ReadXls.retornoPlanilhaXls() );
                    listView.setAdapter(arrayAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    ArrayAdapter<String> arrayAdapter = null;
                    arrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            ReadCsv.retornoPlanilhaCsv() );
                    listView.setAdapter(arrayAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        ReadPdf.retornoPlanilhaPdf() );
                listView.setAdapter(arrayAdapter);
            }
        });

    }

}
