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
public class Suplement extends ApstraktniDomenskiObjekat {

    private Long suplementID;
    private String naziv;
    private double kolicina;
    private double cena;
    private String opis;
    private Kategorija kategorija;
    private JedinicaMere jedinicaMere;
    private ArrayList<StavkaSuplementa> stavkeSuplementa;

    public Suplement(Long suplementID, String naziv, double kolicina, double cena, String opis, Kategorija kategorija, JedinicaMere jedinicaMere, ArrayList<StavkaSuplementa> stavkeSuplementa) {
        this.suplementID = suplementID;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cena = cena;
        this.opis = opis;
        this.kategorija = kategorija;
        this.jedinicaMere = jedinicaMere;
        this.stavkeSuplementa = stavkeSuplementa;
    }

    @Override
    public String toString() {
        return naziv + " (Kolicina: " + kolicina + jedinicaMere + ", Cena: " + cena + "din)";
    }

    public Suplement(Long suplementID, String naziv, double kolicina, double cena, String opis, Kategorija kategorija, JedinicaMere jedinicaMere) {
        this.suplementID = suplementID;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cena = cena;
        this.opis = opis;
        this.kategorija = kategorija;
        this.jedinicaMere = jedinicaMere;
    }

    public Suplement() {
    }

    @Override
    public String nazivTabele() {
        return " Suplement ";
    }

    @Override
    public String alijas() {
        return " sup ";
    }

    @Override
    public String join() {
        return " JOIN KATEGORIJA K ON (K.KATEGORIJAID = SUP.KATEGORIJAID) "
                + "JOIN JEDINICAMERE JM ON (JM.JEDINICAMEREID = SUP.JEDINICAMEREID) ";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("k.Naziv"));
            
            JedinicaMere jmsup = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jm.Naziv"));
            
            Suplement sup = new Suplement(rs.getLong("suplementID"), rs.getString("sup.naziv"), 
                    rs.getDouble("sup.kolicina"), rs.getDouble("sup.cena"), rs.getString("opis"), 
                    k, jmsup);

            lista.add(sup);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, kolicina, cena, opis, kategorijaID, jedinicaMereID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', " + kolicina + ", "
                + " " + cena + ", '" + opis + "', " + kategorija.getKategorijaID()
                + ", " + jedinicaMere.getJMID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', cena = " + cena + ", "
                + "kolicina = " + kolicina + ", opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " suplementID = " + suplementID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getSuplementID() {
        return suplementID;
    }

    public void setSuplementID(Long suplementID) {
        this.suplementID = suplementID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public ArrayList<StavkaSuplementa> getStavkeSuplementa() {
        return stavkeSuplementa;
    }

    public void setStavkeSuplementa(ArrayList<StavkaSuplementa> stavkeSuplementa) {
        this.stavkeSuplementa = stavkeSuplementa;
    }

}
