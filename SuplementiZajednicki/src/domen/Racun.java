/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author necam
 */
public class Racun extends ApstraktniDomenskiObjekat {

    private Long racunID;
    private Date datumVreme;
    private double ukupanIznos;
    private double poreskaStopa;
    private double konacanIznos;
    private Administrator administrator;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun(Long racunID, Date datumVreme, double ukupanIznos, double poreskaStopa, double konacanIznos, Administrator administrator, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.ukupanIznos = ukupanIznos;
        this.poreskaStopa = poreskaStopa;
        this.konacanIznos = konacanIznos;
        this.administrator = administrator;
        this.stavkeRacuna = stavkeRacuna;
    }

    public Racun(Long racunID, Date datumVreme, double ukupanIznos, double poreskaStopa, double konacanIznos, Administrator administrator) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.ukupanIznos = ukupanIznos;
        this.poreskaStopa = poreskaStopa;
        this.konacanIznos = konacanIznos;
        this.administrator = administrator;
    }

    public Racun() {
    }

    @Override
    public String nazivTabele() {
        return " Racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ";
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

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, ukupanIznos, poreskaStopa, konacanIznos, AdministratorID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', " + ukupanIznos + ", "
                + " " + poreskaStopa + ", " + konacanIznos + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " racunID = " + racunID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public double getPoreskaStopa() {
        return poreskaStopa;
    }

    public void setPoreskaStopa(double poreskaStopa) {
        this.poreskaStopa = poreskaStopa;
    }

    public double getKonacanIznos() {
        return konacanIznos;
    }

    public void setKonacanIznos(double konacanIznos) {
        this.konacanIznos = konacanIznos;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

}
