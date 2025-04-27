/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.racun;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveRacun extends ApstraktnaSO {

    private ArrayList<Racun> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> racuni = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Racun>) (ArrayList<?>) racuni;
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }

}
