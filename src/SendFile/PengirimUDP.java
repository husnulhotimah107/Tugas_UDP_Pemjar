package SendFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author asus
 */
public class PengirimUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        File file = new File("F:\\coba.txt");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream os = new ByteArrayOutputStream((int) file.length());
        byte[] b = new byte[1024];
        int a;
        
        while((a = fis.read(b)) != -1) {
            os.write(b, 0, a);
        }
        byte[] bs = os.toByteArray();
        DatagramPacket dp = new DatagramPacket(bs, bs.length, InetAddress.getByName("10.10.10.3"), 1345);
        ds.send(dp);
        fis.close();
        ds.close();
    }
}