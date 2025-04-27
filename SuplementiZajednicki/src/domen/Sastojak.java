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
public class Sastojak extends ApstraktniDomenskiObjekat {

    private Long sastojakID;
    private String naziv;
    private JedinicaMere jm;

    @Override
    public String toString() {
        return naziv + " (" + jm + ")";
    }

    public Sastojak(Long sastojakID, String naziv, JedinicaMere jm) {
        this.sastojakID = sastojakID;
        this.naziv = naziv;
        this.jm = jm;
    }

    public Sastojak() {
    }

    @Override
    public String nazivTabele() {
        return " Sastojak ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return " JOIN JEDINICAMERE JMS ON (JMS.JEDINICAMEREID = S.JEDINICAMEREID) ";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            JedinicaMere jms = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jms.Naziv"));

            Sastojak s = new Sastojak(rs.getLong("SastojakID"),
                    rs.getString("s.Naziv"), jms);

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, jedinicaMereID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', " + jm.getJMID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " SastojakID = " + sastojakID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getSastojakID() {
        return sastojakID;
    }

    public void setSastojakID(Long sastojakID) {
        this.sastojakID = sastojakID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

}
