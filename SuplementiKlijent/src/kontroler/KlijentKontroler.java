/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Administrator;
import domen.JedinicaMere;
import domen.Kategorija;
import domen.Racun;
import domen.Sastojak;
import domen.StavkaRacuna;
import domen.StavkaSuplementa;
import domen.Suplement;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author necam
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    private KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
    }

    public void logout(Administrator ulogovani) throws Exception {
        posaljiZahtev(Operacije.LOGOUT, ulogovani);
    }

    public void dodajRacun(Racun racun) throws Exception {
        posaljiZahtev(Operacije.DODAJ_RACUN, racun);
    }

    public void dodajSastojak(Sastojak sastojak) throws Exception {
        posaljiZahtev(Operacije.DODAJ_SASTOJAK, sastojak);
    }

    public void dodajSuplement(Suplement suplement) throws Exception {
        posaljiZahtev(Operacije.DODAJ_SUPLEMENT, suplement);
    }

    public void obrisiSastojak(Sastojak sastojak) throws Exception {
        posaljiZahtev(Operacije.OBRISI_SASTOJAK, sastojak);
    }

    public void obrisiSuplement(Suplement suplement) throws Exception {
        posaljiZahtev(Operacije.OBRISI_SUPLEMENT, suplement);
    }

    public void izmeniSastojak(Sastojak sastojak) throws Exception {
        posaljiZahtev(Operacije.IZMENI_SASTOJAK, sastojak);
    }

    public void izmeniSuplement(Suplement suplement) throws Exception {
        posaljiZahtev(Operacije.IZMENI_SUPLEMENT, suplement);
    }

    public ArrayList<Sastojak> vratiSveSastojak() throws Exception {
        return (ArrayList<Sastojak>) posaljiZahtev(Operacije.VRATI_SVE_SASTOJAK, null);
    }

    public ArrayList<Suplement> vratiSveSuplement() throws Exception {
        return (ArrayList<Suplement>) posaljiZahtev(Operacije.VRATI_SVE_SUPLEMENT, null);
    }

    public ArrayList<Racun> vratiSveRacun() throws Exception {
        return (ArrayList<Racun>) posaljiZahtev(Operacije.VRATI_SVE_RACUN, null);
    }

    public ArrayList<Kategorija> vratiSveKategorija() throws Exception {
        return (ArrayList<Kategorija>) posaljiZahtev(Operacije.VRATI_SVE_KATEGORIJA, null);
    }

    public ArrayList<JedinicaMere> vratiSveJedinicaMere() throws Exception {
        return (ArrayList<JedinicaMere>) posaljiZahtev(Operacije.VRATI_SVE_JEDINICA_MERE, null);
    }

    public ArrayList<StavkaRacuna> vratiSveStavkaRacuna(Racun racun) throws Exception {
        return (ArrayList<StavkaRacuna>) posaljiZahtev(Operacije.VRATI_SVE_STAVKA_RACUNA, racun);
    }

    public ArrayList<StavkaSuplementa> vratiSveStavkaSuplementa(Suplement suplement) throws Exception {
        return (ArrayList<StavkaSuplementa>) posaljiZahtev(Operacije.VRATI_SVE_STAVKA_SUPLEMENTA, suplement);
    }

    private Object posaljiZahtev(int operacija, Object podaci) throws Exception {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(operacija, podaci);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSoket().getOutputStream());
        out.writeObject(zahtev);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSoket().getInputStream());
        ServerskiOdgovor odgovor = (ServerskiOdgovor) in.readObject();

        if (odgovor.getStatusOdgovora().equals(StatusOdgovora.Greska)) {
            throw odgovor.getException();
        } else {
            return odgovor.getPodaci();
        }

    }

}
