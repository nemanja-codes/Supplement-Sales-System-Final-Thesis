/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author necam
 */
public class KlijentskiZahtev implements Serializable {

    private int operacija;
    private Object podaci;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int operation, Object data) {
        this.operacija = operation;
        this.podaci = data;
    }

    public Object getPodaci() {
        return podaci;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setPodaci(Object podaci) {
        this.podaci = podaci;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

}
