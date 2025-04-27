/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author necam
 */
public interface Operacije {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int DODAJ_SASTOJAK = 2;
    public static final int OBRISI_SASTOJAK = 3;
    public static final int IZMENI_SASTOJAK = 4;
    public static final int VRATI_SVE_SASTOJAK = 5;

    public static final int DODAJ_SUPLEMENT = 6;
    public static final int OBRISI_SUPLEMENT = 7;
    public static final int IZMENI_SUPLEMENT = 8;
    public static final int VRATI_SVE_SUPLEMENT = 9;
    
    public static final int VRATI_SVE_STAVKA_SUPLEMENTA = 10;

    public static final int DODAJ_RACUN = 11;
    public static final int VRATI_SVE_RACUN = 12;

    public static final int VRATI_SVE_STAVKA_RACUNA = 13;
    
    public static final int VRATI_SVE_KATEGORIJA = 14;

    public static final int VRATI_SVE_JEDINICA_MERE = 15;
    
}
