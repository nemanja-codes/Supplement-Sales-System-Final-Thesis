/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import kontroler.KlijentKontroler;
import domen.Racun;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author necam
 */
public class ModelTabeleRacun extends AbstractTableModel implements Runnable{
    
    private ArrayList<Racun> lista;
    private String[] kolone = {"ID", "Datum i vreme", "Ukupan iznos", "Poreska stopa", "Konacan iznos", "Administrator"};
    private Long parametar;

    public ModelTabeleRacun() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveRacun();
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
        Racun r = lista.get(row);
//    private String[] kolone = {"ID", "Datum i vreme", "Ukupan iznos", "Poreska stopa", "Konacan iznos", "Administrator"};

        switch (column) {
            case 0:
                return r.getRacunID();
            case 1:
                return r.getDatumVreme();
            case 2:
                return r.getUkupanIznos();
            case 3:
                return r.getPoreskaStopa();
            case 4:
                return r.getKonacanIznos();
            case 5:
                return r.getAdministrator().toString();

            default:
                return null;
        }
    }

    public Racun getSelectedRacun(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                osveziTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleSuplementi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(Long parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public void osveziTabelu() {
        try {
            lista = KlijentKontroler.getInstance().vratiSveRacun();
            if (parametar != null) {
                ArrayList<Racun> novaLista = new ArrayList<>();
                for (Racun r : lista) {
                    if (r.getRacunID().equals(parametar)) {
                        novaLista.add(r);
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
