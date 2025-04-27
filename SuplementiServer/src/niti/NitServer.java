/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author necam
 */
public class NitServer extends Thread {

    private ServerSocket serverskiSoket;

    public NitServer() {
        try {
            serverskiSoket = new ServerSocket(9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!serverskiSoket.isClosed()) {
                Socket socket = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                NitKlijent th = new NitKlijent(socket);
                th.start();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public ServerSocket getServerskiSoket() {
        return serverskiSoket;
    }

    public void setServerskiSoket(ServerSocket serverskiSoket) {
        this.serverskiSoket = serverskiSoket;
    }

}
