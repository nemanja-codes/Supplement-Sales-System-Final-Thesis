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
public class SOIzmeniSuplement extends ApstraktnaSO {

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Suplement)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Suplement!");
        }
        
        Suplement s = (Suplement) ado;

        if (s.getKolicina() < 0 || s.getKolicina() > 10000) {
            throw new Exception("Kolicina mora biti izmedju 1 i 10000!");
        }

        if (s.getCena() < 100 || s.getCena() > 50000) {
            throw new Exception("Cena mora biti izmedju 100 i 50000!");
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
