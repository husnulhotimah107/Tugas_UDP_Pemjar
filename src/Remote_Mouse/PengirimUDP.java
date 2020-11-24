package Remote_Mouse;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Husnul
 */
public class PengirimUDP extends Frame implements MouseListener{
    static JLabel label1, label2, label3, label4;
    static boolean clicked = false;
    
    PengirimUDP() {
        
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("MouseListener");
        f.setSize(1000, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        label1 = new JLabel("no event ");
        label2 = new JLabel("no event ");
        label3 = new JLabel("no event ");
        label4 = new JLabel("no event ");
        PengirimUDP udp  = new PengirimUDP();
        f.addMouseListener(udp);
        
        p.add(label1);
        p.add(label2);
        p.add(label3);
        p.add(label4);
        
        f.add(p);
        f.show();
        
        try {
            InetAddress ia = InetAddress.getByName("10.10.10.3"); // ip tujuan
            int port = 1345;
            // Mengganti dengan posisi kursor
            PointerInfo a;
            Point b; 
            int x, y;
            String pesan = "null";
              
            while(true) {
                a = MouseInfo.getPointerInfo();
                b = a.getLocation();
                x = (int) b.getX();
                y = (int) b.getY();
                
                if(clicked) {
                    pesan = String.valueOf(x) + "|" + String.valueOf(y) + "|clicked";
                    clicked = false;
                }else {
                    pesan = String.valueOf(x) + "|" + String.valueOf(y) + "|not";
                }
                System.out.println(pesan);
                byte[] data = pesan.getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
                DatagramSocket ds = new DatagramSocket();
                Thread.sleep(100);
                ds.send(dp);
                System.out.println(pesan);
                label4.setText("Lokasi Mouse : X = " + x + " | Y = " + y);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void mouseClicked(MouseEvent e) {
        label3.setText("Mouse clicked at point:"
                + e.getX() + " "
                + e.getY() + "mouse clicked :" + e.getClickCount());
    }

    public void mousePressed(MouseEvent e) {
        label1.setText("Mouse pressed at point:"
                + e.getX() + " " + e.getY());
        clicked = true;
    }

    public void mouseReleased(MouseEvent e) {
        label1.setText("Mouse released at point:"
                + e.getX() + " " + e.getY());
    }

    public void mouseEntered(MouseEvent e) {
        label2.setText("Mouse entered at point:"
                + e.getX() + " " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
        label2.setText("Mouse exited through point:"
                + e.getX() + " " + e.getY());
    }
}