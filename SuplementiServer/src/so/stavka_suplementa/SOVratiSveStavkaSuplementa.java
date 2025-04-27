/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavka_suplementa;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaSuplementa;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveStavkaSuplementa extends ApstraktnaSO {

    private ArrayList<StavkaSuplementa> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaSuplementa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaSuplementa!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> stavkeSuplementa = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaSuplementa>) (ArrayList<?>) stavkeSuplementa;
    }

    public ArrayList<StavkaSuplementa> getLista() {
        return lista;
    }

}
