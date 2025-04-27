/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.racun;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SODodajRacun extends ApstraktnaSO {

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }

        Racun racun = (Racun) ado;

        if (racun.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long racunID = tableKeys.getLong(1);

        Racun r = (Racun) ado;
        r.setRacunID(racunID);

        for (StavkaRacuna stavkaRacuna : r.getStavkeRacuna()) {
            stavkaRacuna.setRacun(r);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }

}
