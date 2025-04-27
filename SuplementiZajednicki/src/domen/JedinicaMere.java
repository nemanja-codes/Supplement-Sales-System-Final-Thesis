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
public class JedinicaMere extends ApstraktniDomenskiObjekat {
    
    private Long JMID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public JedinicaMere(Long JMID, String naziv) {
        this.JMID = JMID;
        this.naziv = naziv;
    }

    public JedinicaMere() {
    }
    
    @Override
    public String nazivTabele() {
        return " JedinicaMere ";
    }

    @Override
    public String alijas() {
        return " jm ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            JedinicaMere jm = new JedinicaMere(rs.getLong("JedinicaMereID"),
                    rs.getString("jm.Naziv"));

            lista.add(jm);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    
    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getJMID() {
        return JMID;
    }

    public void setJMID(Long JMID) {
        this.JMID = JMID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
