package Remote_Mouse;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Husnul
 */
public class PenerimaUDP {
    public static void main(String[] args) {
        try {
            byte[] buffer = new byte[60]; //whitespace
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            DatagramSocket ds = new DatagramSocket(1345);
            
            while(true) {       
                ds.receive(dp); //Dari datagramPacket 
                byte[] data = dp.getData(); //Untuk dibaca lalu mengubah receive dari data dp
                String msg = new String(data, 0, data.length); // Dikonversi menjadi String
                System.out.println("Pesan dari pengirim : " + msg); // Mencetak

                //Titik koordinat mouse
                String[] posisi = msg.split("\\|");
                int x = Integer.parseInt(posisi[0]);
                int y = Integer.parseInt(posisi[1]);
                String click = posisi[2];
                Robot rb = new Robot(); //Fungsi robot ini untuk menggerakkan 
                rb.mouseMove(x, y);
                
                if(click.contains("click")) {
                    rb.mousePress(InputEvent.BUTTON1_MASK); 
                    rb.mouseRelease(InputEvent.BUTTON1_MASK);
                    System.out.println("Position : x = " + x + " | y = " + y + " | clicked" );
                }
            }
        } catch (Exception e) {
        }
    }
}