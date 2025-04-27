/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Suplement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleSuplementi extends AbstractTableModel implements Runnable {

    private ArrayList<Suplement> lista;
    private String[] kolone = {"ID", "Kategorija", "Naziv", "Kolicina", "Cena"};
    private String parametar = "";

    public ModelTabeleSuplementi() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveSuplement();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleSuplementi.class.getName()).log(Level.SEVERE, null, ex);
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
        Suplement s = lista.get(row);
//    private String[] kolone = {"ID", "Kategorija", "Naziv", "Kolicina", "Cena"};

        switch (column) {
            case 0:
                return s.getSuplementID();
            case 1:
                return s.getKategorija();
            case 2:
                return s.getNaziv();
            case 3:
                return s.getKolicina() + "" + s.getJedinicaMere();
            case 4:
                return s.getCena() + "din";

            default:
                return null;
        }
    }

    public Suplement getSelectedSuplement(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleSuplementi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveSuplement();
            if (!parametar.equals("")) {
                ArrayList<Suplement> novaLista = new ArrayList<>();
                for (Suplement s : lista) {
                    if (s.getNaziv().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(s);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
