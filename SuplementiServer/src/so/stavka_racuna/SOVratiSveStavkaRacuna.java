/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavka_racuna;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaRacuna;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOVratiSveStavkaRacuna extends ApstraktnaSO {

    private ArrayList<StavkaRacuna> lista;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaRacuna!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> stavkeRacuna = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaRacuna>) (ArrayList<?>) stavkeRacuna;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

}
