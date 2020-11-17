/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remote_Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Husnul
 */
public class Client {

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("10.10.10.3");
            int port = 1345;
            PointerInfo pi;
            Point p;
            int x;
            int y;

            while (true) {
                pi = MouseInfo.getPointerInfo();
                p = pi.getLocation();
                x = (int) p.getX();
                y = (int) p.getY();
                String locationX = String.valueOf(x);
                String locationY = String.valueOf(y);
                String location = locationX + "|" + locationY + "|";
                byte[] data = location.getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
                DatagramSocket ds = new DatagramSocket();
                ds.send(dp);
                System.out.println(location);
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
