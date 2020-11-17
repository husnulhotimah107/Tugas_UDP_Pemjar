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
public class Client {
    public static void main(String[] args) throws SocketException, IOException {
        
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      InetAddress ia = InetAddress.getByName("127.1.1.1");
      DatagramSocket ds = new DatagramSocket();
      
      while(true) {
      byte[] sendbuffer = new byte[1024]; 
      byte[] receivebuffer = new byte[1024]; 
      
      System.out.print("\nClient: "); 
      String msgclient = br.readLine(); 
      sendbuffer = msgclient.getBytes(); 
      DatagramPacket dp = new DatagramPacket(sendbuffer, sendbuffer.length, ia, 1345);
      ds.send(dp);
      
      if(msgclient.equalsIgnoreCase("bye")) {
          System.out.println("Connection ended by client");
          break;
      }
      
      DatagramPacket rp = new DatagramPacket(receivebuffer, receivebuffer.length);
      ds.receive(rp);
      String msgserver = new String(rp.getData()); 
      System.out.print("\nServer: " + msgserver); 
      
      }
      ds.close();
    }
}
