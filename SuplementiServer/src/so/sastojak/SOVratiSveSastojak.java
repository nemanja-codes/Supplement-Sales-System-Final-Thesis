/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sastojak;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Sastojak;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveSastojak extends ApstraktnaSO {

    private ArrayList<Sastojak> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Sastojak)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sastojak!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> sastojci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sastojak>) (ArrayList<?>) sastojci;
    }

    public ArrayList<Sastojak> getLista() {
        return lista;
    }

}
