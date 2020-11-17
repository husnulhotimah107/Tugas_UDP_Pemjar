/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remote_Mouse;

import java.awt.Robot;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Husnul
 */
public class Server {

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            byte[] buffer = new byte[65];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            DatagramSocket ds = new DatagramSocket(1345);
            
            while (true) {
                ds.receive(dp);
                byte[] data = dp.getData();
                String msg = new String(data, 0, data.length);
                System.out.println("lokasi pointer : " + msg);
                String[] posisi = msg.split("\\|");
                
                int x = Integer.parseInt(posisi[0]);
                int y = Integer.parseInt(posisi[1]);
                
                Robot r = new Robot();
                r.mouseMove(x, y);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
