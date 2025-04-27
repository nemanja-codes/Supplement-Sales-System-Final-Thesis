/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.suplement;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Suplement;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOObrisiSuplement extends ApstraktnaSO {

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Suplement)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Suplement!");
        }
    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
