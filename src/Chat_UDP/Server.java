/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 *
 * @author Husnul
 */
public class Server {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket ds = new DatagramSocket(1345);
        boolean bye = false;
        
        while (true) {
            byte[] receivebuffer = new byte[1024]; 
            byte[] sendbuffer = new byte[1024]; 
            
            DatagramPacket rb = new DatagramPacket(receivebuffer, receivebuffer.length);
            ds.receive(rb);
            InetAddress ia = rb.getAddress(); 
            int port = rb.getPort(); 
            String msgclient = new String(rb.getData()); 
            System.out.println("\nClient : " + msgclient);
            System.out.print("\nServer : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgserver = br.readLine();

            sendbuffer = msgserver.getBytes(); 
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, ia, port);
            ds.send(sendPacket);
            
            if (msgserver.equalsIgnoreCase("bye")) {
                System.out.println("connection ended by server");
                break;
            }
        }
        ds.close();
    }
}