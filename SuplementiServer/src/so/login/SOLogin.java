/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import kontroler.ServerKontroler;
import db.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Administrator;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author necam
 */
public class SOLogin extends ApstraktnaSO {

    Administrator ulogovani;

    @Override
    protected void validacija(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }

        Administrator a = (Administrator) ado;

        for (Administrator administrator : ServerKontroler.getInstance().getUlogovaniAdministratori()) {
            if (administrator.getUsername().equals(a.getUsername())) {
                throw new Exception("Ovaj administrator je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                ServerKontroler.getInstance().getUlogovaniAdministratori().add(administrator);
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");

    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

}
