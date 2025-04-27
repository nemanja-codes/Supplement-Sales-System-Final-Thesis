/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import domen.Administrator;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author necam
 */
public class Sesija {

    private static Sesija instance;
    private Socket soket;
    private Administrator ulogovani;

    private Sesija() {
        try {
            soket = new Socket("localhost", 9999);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSoket() {
        return soket;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

}
