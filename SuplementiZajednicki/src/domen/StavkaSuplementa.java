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
public class StavkaSuplementa extends ApstraktniDomenskiObjekat {

    private Suplement suplement;
    private int rb;
    private double kolicina;
    private Sastojak sastojak;

    public StavkaSuplementa(Suplement suplement, int rb, double kolicina, Sastojak sastojak) {
        this.suplement = suplement;
        this.rb = rb;
        this.kolicina = kolicina;
        this.sastojak = sastojak;
    }

    public StavkaSuplementa() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaSuplementa ";
    }

    @Override
    public String alijas() {
        return " ss ";
    }

    @Override
    public String join() {
        return " JOIN SUPLEMENT SUP ON (SS.SUPLEMENTID = SUP.SUPLEMENTID) "
                + "JOIN KATEGORIJA K ON (K.KATEGORIJAID = SUP.KATEGORIJAID) "
                + "JOIN JEDINICAMERE JMSUP ON (JMSUP.JEDINICAMEREID = SUP.JEDINICAMEREID) "
                + "JOIN SASTOJAK S ON (S.SASTOJAKID = SS.SASTOJAKID) "
                + "JOIN JEDINICAMERE JMS ON (JMS.JEDINICAMEREID = S.JEDINICAMEREID) ";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("k.Naziv"));
            
            JedinicaMere jmsup = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jmsup.Naziv"));
            
            Suplement sup = new Suplement(rs.getLong("suplementID"), rs.getString("sup.naziv"), 
                    rs.getDouble("sup.kolicina"), rs.getDouble("sup.cena"), rs.getString("opis"), 
                    k, jmsup);
            
            JedinicaMere jms = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jms.Naziv"));
            
            Sastojak s = new Sastojak(rs.getLong("SastojakID"),
                    rs.getString("s.Naziv"), jms);

            StavkaSuplementa ss = new StavkaSuplementa(sup, rs.getInt("rb"), 
                    rs.getDouble("ss.kolicina"), s);
            
            lista.add(ss);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (suplementID, rb, kolicina, SastojakID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + suplement.getSuplementID() + ", " + rb + ", "
                + " " + kolicina + ", " + sastojak.getSastojakID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " suplementID = " + suplement.getSuplementID();
    }

    @Override
    public String uslovZaSelect() {
        if (suplement != null) {
            return " WHERE SUP.SUPLEMENTID = " + suplement.getSuplementID();
        }
        if (sastojak != null) {
            return " WHERE S.SASTOJAKID = " + sastojak.getSastojakID();
        }
        return "";
    }

    public Suplement getSuplement() {
        return suplement;
    }

    public void setSuplement(Suplement suplement) {
        this.suplement = suplement;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public Sastojak getSastojak() {
        return sastojak;
    }

    public void setSastojak(Sastojak sastojak) {
        this.sastojak = sastojak;
    }

}
