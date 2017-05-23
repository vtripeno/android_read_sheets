package com.example.vrto.read_sheet;

import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnCsv;
    Button btnXsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listaValores);
        btnCsv = (Button) findViewById(R.id.csv);
        btnXsl = (Button) findViewById(R.id.xls);

        btnXsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("Minha Lista XSL", retornoPlanilhaJxl().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String[]>  lista = null;
                try {
                    lista = retornoPlanilhaCsv();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < lista.size(); i++) {
                    Log.d("Minha Lista CSV" + i, String.valueOf(lista.get(i)[0]));
                }
            }
        });

    }

    public List<String> retornoPlanilhaJxl(/*String key*/) throws IOException {
        List<String> resultSet = new ArrayList<String>();

        Log.d("CAMINHO", String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.xls");
        File inputWorkbook = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.xls");
        if(inputWorkbook.exists()){
            Workbook w;
            try {
                w = Workbook.getWorkbook(inputWorkbook);
                // Get the first sheet
                Sheet sheet = w.getSheet(0);
                // Loop over column and lines
                for (int j = 0; j < sheet.getRows(); j++) {
                    Cell cell = sheet.getCell(0, j);
                   // if(cell.getContents().equalsIgnoreCase(key)){
                        for (int i = 0; i < sheet.getColumns(); i++) {
                            Cell cel = sheet.getCell(i, j);
                            resultSet.add(cel.getContents());

                        }
                   // }
                    continue;
                }
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            resultSet.add("File not found..!");
        }
        if(resultSet.size()==0){
            resultSet.add("Data not found..!");
        }
        return resultSet;

    }

    public List<String[]> retornoPlanilhaCsv() throws IOException {
        List<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = getAssets();

        try {
            File inputWorkbook = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.csv");
            Log.d("CAMINHO", inputWorkbook.toString());
            InputStream csvStream = new FileInputStream(inputWorkbook);// assetManager.open(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            //csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
