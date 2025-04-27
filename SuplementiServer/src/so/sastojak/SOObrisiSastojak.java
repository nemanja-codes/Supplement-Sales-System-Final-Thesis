/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sastojak;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Sastojak;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOObrisiSastojak extends ApstraktnaSO {

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Sastojak)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sastojak!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
