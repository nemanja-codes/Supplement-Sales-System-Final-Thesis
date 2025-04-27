/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleStavkeRacunaDetalji extends AbstractTableModel {
    
    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Kolicina", "Cena", "Suplement"};
    private int rb = 0;

    public ModelTabeleStavkeRacunaDetalji() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkeRacunaDetalji(Racun racun) {
        try {
            lista = KlijentKontroler.getInstance().vratiSveStavkaRacuna(racun);
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
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getKolicina();
            case 2:
                return sr.getCena();
            case 3:
                return sr.getSuplement().toString();

            default:
                return null;
        }
    }


    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }
}
