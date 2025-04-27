/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import kontroler.ServerKontroler;
import domen.Administrator;
import domen.Racun;
import domen.Sastojak;
import domen.Suplement;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author necam
 */
public class NitKlijent extends Thread {

    private Socket soket;

    NitKlijent(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        try {
            while (!soket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
                KlijentskiZahtev zahtev = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor odgovor = obradiZahtev(zahtev);
                ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
                out.writeObject(odgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev zahtev) {
        ServerskiOdgovor odgovor = new ServerskiOdgovor(null, null, StatusOdgovora.Uspesno);
        try {
            switch (zahtev.getOperacija()) {
                case Operacije.DODAJ_RACUN:
                    ServerKontroler.getInstance().dodajRacun((Racun) zahtev.getPodaci());
                    break;
                case Operacije.DODAJ_SASTOJAK:
                    ServerKontroler.getInstance().dodajSastojak((Sastojak) zahtev.getPodaci());
                    break;
                case Operacije.DODAJ_SUPLEMENT:
                    ServerKontroler.getInstance().dodajSuplement((Suplement) zahtev.getPodaci());
                    break;
                case Operacije.OBRISI_SASTOJAK:
                    ServerKontroler.getInstance().obrisiSastojak((Sastojak) zahtev.getPodaci());
                    break;
                case Operacije.OBRISI_SUPLEMENT:
                    ServerKontroler.getInstance().obrisiSuplement((Suplement) zahtev.getPodaci());
                    break;
                case Operacije.IZMENI_SASTOJAK:
                    ServerKontroler.getInstance().izmeniSuplement((Sastojak) zahtev.getPodaci());
                    break;
                case Operacije.IZMENI_SUPLEMENT:
                    ServerKontroler.getInstance().izmeniSuplement((Suplement) zahtev.getPodaci());
                    break;
                case Operacije.VRATI_SVE_JEDINICA_MERE:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveJedinicaMere());
                    break;
                case Operacije.VRATI_SVE_KATEGORIJA:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveKategorija());
                    break;
                case Operacije.VRATI_SVE_RACUN:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveRacun());
                    break;
                case Operacije.VRATI_SVE_SASTOJAK:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveSastojak());
                    break;
                case Operacije.VRATI_SVE_STAVKA_RACUNA:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveStavkaRacuna((Racun) zahtev.getPodaci()));
                    break;
                case Operacije.VRATI_SVE_STAVKA_SUPLEMENTA:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveStavkaSuplementa((Suplement) zahtev.getPodaci()));
                    break;
                case Operacije.VRATI_SVE_SUPLEMENT:
                    odgovor.setPodaci(ServerKontroler.getInstance().vratiSveSuplement());
                    break;
                case Operacije.LOGIN:
                    Administrator administrator = (Administrator) zahtev.getPodaci();
                    Administrator admin = ServerKontroler.getInstance().login(administrator);
                    odgovor.setPodaci(admin);
                    break;
                case Operacije.LOGOUT:
                    Administrator ulogovani = (Administrator) zahtev.getPodaci();
                    ServerKontroler.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            odgovor.setStatusOdgovora(StatusOdgovora.Greska);
            odgovor.setException(ex);
        }
        return odgovor;
    }

}
