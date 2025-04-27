/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author necam
 */
public abstract class ApstraktnaSO {
    
    protected abstract void validacija(ApstraktniDomenskiObjekat ado) throws Exception;
    protected abstract void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception;

    public void izvrsiSablon(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            validacija(ado);
            izvrsi(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
