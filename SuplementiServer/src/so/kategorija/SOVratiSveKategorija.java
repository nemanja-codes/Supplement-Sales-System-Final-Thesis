/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kategorija;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Kategorija;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveKategorija extends ApstraktnaSO {

    private ArrayList<Kategorija> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Kategorija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kategorija!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> kategorije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kategorija>) (ArrayList<?>) kategorije;
    }

    public ArrayList<Kategorija> getLista() {
        return lista;
    }

}
