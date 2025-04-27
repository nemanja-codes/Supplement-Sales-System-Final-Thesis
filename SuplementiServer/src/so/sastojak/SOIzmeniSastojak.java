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
public class SOIzmeniSastojak extends ApstraktnaSO {

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Sastojak)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sastojak!");
        }

        Sastojak s = (Sastojak) ado;

        ArrayList<Sastojak> sastojci = (ArrayList<Sastojak>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Sastojak sastojak : sastojci) {
            if (!sastojak.getSastojakID().equals(s.getSastojakID())) {
                if (sastojak.getNaziv().equals(s.getNaziv())) {
                    throw new Exception("Sastojak sa tim nazivom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
