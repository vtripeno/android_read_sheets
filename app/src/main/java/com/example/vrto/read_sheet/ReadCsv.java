package com.example.vrto.read_sheet;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrto on 24/05/17.
 */

public class ReadCsv {

    public static List<String> retornoPlanilhaCsv() throws IOException {
        List<String[]> questionList = new ArrayList();
        List<String> lista = new ArrayList<String>();
        int contador = 0;

        try {
            File inputWorkbook = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.csv");
            Log.d("CAMINHO", inputWorkbook.toString());
            InputStream csvStream = new FileInputStream(inputWorkbook);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            //csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                line[contador] = line[contador].replace(";", " ").trim();
                questionList.add(line);
            }

            for(int i = 0; i < questionList.size(); i++) {
                Log.d("Minha Lista CSV" + i, String.valueOf(questionList.get(i)[0]));

                if(!String.valueOf(questionList.get(i)[0]).isEmpty() &&
                        !" ".contains(String.valueOf(questionList.get(i)[0])) &&
                        !"".contains(String.valueOf(questionList.get(i)[0]))){
                    lista.add(String.valueOf(questionList.get(i)[0]));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
