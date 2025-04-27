/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.StatusOdgovora;

/**
 *
 * @author necam
 */
public class ServerskiOdgovor implements Serializable {

    private Object podaci;
    private Exception exc;
    private StatusOdgovora statusOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object data, Exception exc, StatusOdgovora responseStatus) {
        this.podaci = data;
        this.exc = exc;
        this.statusOdgovora = responseStatus;
    }

    public Object getPodaci() {
        return podaci;
    }

    public void setPodaci(Object podaci) {
        this.podaci = podaci;
    }

    public Exception getException() {
        return exc;
    }

    public void setException(Exception exc) {
        this.exc = exc;
    }

    public StatusOdgovora getStatusOdgovora() {
        return statusOdgovora;
    }

    public void setStatusOdgovora(StatusOdgovora statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }

}
