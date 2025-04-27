/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Sastojak;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleSastojci extends AbstractTableModel implements Runnable {

    private ArrayList<Sastojak> lista;
    private String[] kolone = {"ID", "Naziv", "Jedinica mere"};
    private String parametar = "";

    public ModelTabeleSastojci() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveSastojak();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleSastojci.class.getName()).log(Level.SEVERE, null, ex);
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
        Sastojak s = lista.get(row);

        switch (column) {
            case 0:
                return s.getSastojakID();
            case 1:
                return s.getNaziv();
            case 2:
                return s.getJm();

            default:
                return null;
        }
    }

    public Sastojak getSelectedSastojak(int row) {
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
            Logger.getLogger(ModelTabeleSastojci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveSastojak();
            if (!parametar.equals("")) {
                ArrayList<Sastojak> novaLista = new ArrayList<>();
                for (Sastojak s : lista) {
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
