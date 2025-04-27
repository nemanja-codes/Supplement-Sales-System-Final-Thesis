/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.jedinica_mere;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.JedinicaMere;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveJedinicaMere extends ApstraktnaSO {

    private ArrayList<JedinicaMere> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof JedinicaMere)) {
            throw new Exception("Prosledjeni objekat nije instanca klase JedinicaMere!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> jediniceMera = DBBroker.getInstance().select(ado);
        lista = (ArrayList<JedinicaMere>) (ArrayList<?>) jediniceMera;
    }

    public ArrayList<JedinicaMere> getLista() {
        return lista;
    }

}
