package SendFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Husnul
 */
public class PenerimaUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(1345); // Mendefinisi ds dari datagramsocket yang mana port digunakan 1111
        byte[] b = new byte[1024]; //whitespace
        DatagramPacket dp = new DatagramPacket(b, b.length); // Mendefinisi dp lalu dari b dan panjangnya berapa
        ds.receive(dp);
        byte[] bs = dp.getData();
        int length = dp.getLength();
        FileOutputStream fos = new FileOutputStream("E:\\hasil.txt");
        fos.write(bs, 0, length); 
        fos.close();
        ds.close();
    }
}