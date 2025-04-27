/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author necam
 */
public class StavkaRacuna extends ApstraktniDomenskiObjekat {

    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private Suplement suplement;

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, Suplement suplement) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.suplement = suplement;
    }

    public StavkaRacuna() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN RACUN R ON (R.RACUNID = SR.RACUNID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) "
                + "JOIN SUPLEMENT SUP ON (SR.SUPLEMENTID = SUP.SUPLEMENTID) "
                + "JOIN KATEGORIJA K ON (K.KATEGORIJAID = SUP.KATEGORIJAID) "
                + "JOIN JEDINICAMERE JMSUP ON (JMSUP.JEDINICAMEREID = SUP.JEDINICAMEREID)";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Racun r = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"), 
                    rs.getDouble("ukupanIznos"), rs.getDouble("poreskaStopa"), 
                    rs.getDouble("konacanIznos"), a);
            
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("k.Naziv"));
            
            JedinicaMere jmsup = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jmsup.Naziv"));
            
            Suplement sup = new Suplement(rs.getLong("suplementID"), rs.getString("sup.naziv"), 
                    rs.getDouble("sup.kolicina"), rs.getDouble("sup.cena"), rs.getString("opis"), 
                    k, jmsup);
            
            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"), rs.getInt("sr.kolicina"), 
                    rs.getDouble("sr.cena"), sup);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (racunID, rb, kolicina, cena, suplementID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getRacunID() + ", " + rb + ", "
                + " " + kolicina + ", " + cena + ", " + suplement.getSuplementID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " racunID = " + racun.getRacunID();
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE R.RACUNID = " + racun.getRacunID();
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Suplement getSuplement() {
        return suplement;
    }

    public void setSuplement(Suplement suplement) {
        this.suplement = suplement;
    }

}
