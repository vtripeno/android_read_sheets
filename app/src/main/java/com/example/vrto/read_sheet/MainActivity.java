package com.example.vrto.read_sheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnCsv;
    Button btnXsl;
    Button btnPdf;
    TextView txtBtnNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listaValores);
        btnCsv = (Button) findViewById(R.id.csv);
        btnXsl = (Button) findViewById(R.id.xls);
        btnPdf = (Button) findViewById(R.id.pdf);
        txtBtnNome = (TextView) findViewById(R.id.txtBnt);

        btnXsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    popularLista(ReadXls.retornoPlanilhaXls(), listView);
                    txtBtnNome.setText("XSL");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    popularLista(ReadCsv.retornoPlanilhaCsv(), listView);
                    txtBtnNome.setText("CSV");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularLista(ReadPdf.retornoPlanilhaPdf(), listView);
                txtBtnNome.setText("PDF");
            }
        });

    }

    public void popularLista(List<String> lista, ListView listView){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                lista );
        listView.setAdapter(arrayAdapter);
    }

}
