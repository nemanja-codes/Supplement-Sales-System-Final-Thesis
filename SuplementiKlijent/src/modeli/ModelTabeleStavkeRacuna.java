/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Suplement", "Kolicina", "Cena"};
    private int rb = 0;

    public ModelTabeleStavkeRacuna() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkeRacuna(Racun racun) {
//        try {
//            lista = ClientController.getInstance().getAllStavkaRacuna(racun);
//        } catch (Exception ex) {
//            Logger.getLogger(TableModelStavkeRacuna.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getSuplement();
            case 2:
                return sr.getKolicina() + "kom";
            case 3:
                return sr.getCena() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {

        for (StavkaRacuna stavkaRacuna : lista) {
            if (stavkaRacuna.getSuplement().getSuplementID().equals(sr.getSuplement().getSuplementID())) {
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setCena(stavkaRacuna.getCena() + sr.getCena());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaRacuna stavkaRacuna : lista) {
            ukupanIznos += stavkaRacuna.getCena();
        }

        return ukupanIznos;
    }

}
