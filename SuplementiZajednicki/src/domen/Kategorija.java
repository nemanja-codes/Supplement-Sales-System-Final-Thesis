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
public class Kategorija extends ApstraktniDomenskiObjekat {
    
    private Long kategorijaID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Kategorija(Long kategorijaID, String naziv) {
        this.kategorijaID = kategorijaID;
        this.naziv = naziv;
    }

    public Kategorija() {
    }
    
    @Override
    public String nazivTabele() {
        return " Kategorija ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("k.Naziv"));

            lista.add(k);
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

    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
