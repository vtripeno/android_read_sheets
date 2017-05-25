package com.example.vrto.read_sheet;

/**
 * Created by vrto on 25/05/17.
 */

public class Validations {

    public static boolean verificaString(String texto) {
        return !texto.isEmpty() && !"".contains(texto);
    }
}
