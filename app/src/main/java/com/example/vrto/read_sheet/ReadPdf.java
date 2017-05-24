package com.example.vrto.read_sheet;

import android.os.Environment;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrto on 24/05/17.
 */

public class ReadPdf {

    public static List<String> retornoPlanilhaPdf() {
        List<String> listaString = new ArrayList<>();
        try {
            String parsedText[];

            PdfReader reader = new PdfReader(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/Planilha_teste.pdf");
            int n = reader.getNumberOfPages();
            for (int i = 0; i <n ; i++) {
                if(!PdfTextExtractor.getTextFromPage(reader, i+1).trim().isEmpty() &&
                        !"".contains(PdfTextExtractor.getTextFromPage(reader, i+1).trim())) {

                    parsedText = PdfTextExtractor.getTextFromPage(reader, i + 1).trim().split("\n");
                    for(int j = 0; j < parsedText.length; j++) {
                        listaString.add(parsedText[j]);
                    }
                    //listaString.add(PdfTextExtractor.getTextFromPage(reader, i + 1).trim()); //Extracting the content from the different pages
                    //parsedText   = parsedText+ PdfTextExtractor.getTextFromPage(reader, i+1).trim()+"\n"; //Extracting the content from the different pages
                }
            }
            System.out.println(listaString.toString());
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaString;
    }

}
