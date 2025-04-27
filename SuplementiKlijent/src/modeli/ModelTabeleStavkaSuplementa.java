/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.StavkaSuplementa;
import domen.Suplement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleStavkaSuplementa extends AbstractTableModel {

    private ArrayList<StavkaSuplementa> lista;
    private String[] kolone = {"Rb", "Sastojak", "Kolicina"};
    private int rb = 0;

    public ModelTabeleStavkaSuplementa() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkaSuplementa(Suplement suplement) {
        try {
            lista = KlijentKontroler.getInstance().vratiSveStavkaSuplementa(suplement);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavkaSuplementa.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        StavkaSuplementa ss = lista.get(row);

        switch (column) {
            case 0:
                return ss.getRb();
            case 1:
                return ss.getSastojak();
            case 2:
                return ss.getKolicina();

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaSuplementa ss) {
        rb = lista.size();
        ss.setRb(++rb);
        lista.add(ss);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaSuplementa stavkaSuplementa : lista) {
            stavkaSuplementa.setRb(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<StavkaSuplementa> getLista() {
        return lista;
    }

}
