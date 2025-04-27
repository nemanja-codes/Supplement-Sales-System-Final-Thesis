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
import java.util.ArrayList;
import so.jedinica_mere.SOVratiSveJedinicaMere;
import so.kategorija.SOVratiSveKategorija;
import so.login.SOLogin;
import so.racun.SODodajRacun;
import so.racun.SOVratiSveRacun;
import so.sastojak.SODodajSastojak;
import so.sastojak.SOObrisiSastojak;
import so.sastojak.SOVratiSveSastojak;
import so.sastojak.SOIzmeniSastojak;
import so.stavka_racuna.SOVratiSveStavkaRacuna;
import so.stavka_suplementa.SOVratiSveStavkaSuplementa;
import so.suplement.SODodajSuplement;
import so.suplement.SOObrisiSuplement;
import so.suplement.SOVratiSveSuplement;
import so.suplement.SOIzmeniSuplement;

/**
 *
 * @author necam
 */
public class ServerKontroler {

    private static ServerKontroler instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();

    private ServerKontroler() {
    }

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }

    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.izvrsiSablon(administrator);
        return so.getUlogovani();
    }

    public void dodajSastojak(Sastojak sastojak) throws Exception {
        (new SODodajSastojak()).izvrsiSablon(sastojak);
    }

    public void dodajSuplement(Suplement suplement) throws Exception {
        (new SODodajSuplement()).izvrsiSablon(suplement);
    }

    public void dodajRacun(Racun racun) throws Exception {
        (new SODodajRacun()).izvrsiSablon(racun);
    }

    public void obrisiSastojak(Sastojak sastojak) throws Exception {
        (new SOObrisiSastojak()).izvrsiSablon(sastojak);
    }

    public void obrisiSuplement(Suplement suplement) throws Exception {
        (new SOObrisiSuplement()).izvrsiSablon(suplement);
    }

    public void izmeniSuplement(Sastojak sastojak) throws Exception {
        (new SOIzmeniSastojak()).izvrsiSablon(sastojak);
    }

    public void izmeniSuplement(Suplement suplement) throws Exception {
        (new SOIzmeniSuplement()).izvrsiSablon(suplement);
    }

    public ArrayList<Sastojak> vratiSveSastojak() throws Exception {
        SOVratiSveSastojak so = new SOVratiSveSastojak();
        so.izvrsiSablon(new Sastojak());
        return so.getLista();
    }

    public ArrayList<Suplement> vratiSveSuplement() throws Exception {
        SOVratiSveSuplement so = new SOVratiSveSuplement();
        so.izvrsiSablon(new Suplement());
        return so.getLista();
    }

    public ArrayList<JedinicaMere> vratiSveJedinicaMere() throws Exception {
        SOVratiSveJedinicaMere so = new SOVratiSveJedinicaMere();
        so.izvrsiSablon(new JedinicaMere());
        return so.getLista();
    }

    public ArrayList<Kategorija> vratiSveKategorija() throws Exception {
        SOVratiSveKategorija so = new SOVratiSveKategorija();
        so.izvrsiSablon(new Kategorija());
        return so.getLista();
    }

    public ArrayList<Racun> vratiSveRacun() throws Exception {
        SOVratiSveRacun so = new SOVratiSveRacun();
        so.izvrsiSablon(new Racun());
        return so.getLista();
    }

    public ArrayList<StavkaRacuna> vratiSveStavkaRacuna(Racun racun) throws Exception {
        SOVratiSveStavkaRacuna so = new SOVratiSveStavkaRacuna();
        
        StavkaRacuna sr = new StavkaRacuna();
        sr.setRacun(racun);
        
        so.izvrsiSablon(sr);
        return so.getLista();
    }

    public ArrayList<StavkaSuplementa> vratiSveStavkaSuplementa(Suplement suplement) throws Exception {
        SOVratiSveStavkaSuplementa so = new SOVratiSveStavkaSuplementa();
        
        StavkaSuplementa ss = new StavkaSuplementa();
        ss.setSuplement(suplement);
        
        so.izvrsiSablon(ss);
        return so.getLista();
    }

    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }

}
