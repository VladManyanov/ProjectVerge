package com.pverge.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.pverge.core.be.util.MiscUtils;

/**
 * Sync node logic - controls online racing & freeroam sessions
 * Not ready to use and unfinished
 * @author Hypernucle
 */
public class SyncNodeInit extends Thread {

    private static DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[96];

    public SyncNodeInit() {
    	int port = 23456;
        try {socket = new DatagramSocket(port);} catch (SocketException e) {e.printStackTrace();}
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {socket.receive(packet);} catch (IOException e) {e.printStackTrace();}
            
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            
            try {String received = new String(packet.getData(), "UTF-8");
            } catch (UnsupportedEncodingException e) {e.printStackTrace();}
            
            String readMessage = MiscUtils.bytesToHex(packet.getData());
            System.out.println("### [Sync] TEST: " + readMessage);
            
            if (readMessage.contains("800000D02FAF08")) {
            	sendPacket(address, port, "A00000F9820D20F0", 1);
            }
            if (readMessage.contains("E000010C99DDBD1A74092761A8174AE0E4DB5EC7929CF37D")) {
            	sendPacket(address, port, "80000016BA1EDC3A", 2);
            }
            if (readMessage.contains("BA1EDC3002D00267B64306232663939622D646137392D31316")) {
            	sendPacket(address, port, "8000009E", 3);
            	sendPacket(address, port, "8000004e198a14a00010240c05e8ce45c0971f1a5645f8144411d7c697e857b7e2e114fbc1cc789a6f2dd3f79090fb8784dba9ed55956528c3c845fa3eeea6afe161615126781943b33df2d8", 3);
            }
            if (readMessage.contains("800010E0F00004")) {
            	sendPacket(address, port, "80001060856f6e194288f7de08", 4);
            	sendPacket(address, port, "18001140040000000000230b936e7275df3104b34fc6582e3688", 4);
            	sendPacket(address, port, "b4001140080000000000f984ec3b742ec4207e9150debfcd29ec", 4);
            	sendPacket(address, port, "ec0011400c0000000000cec90acbf7651a414f1dffaeb786c5441d35b3d6d94bf0e7af75d8ab547aa222", 4);
            }
            if (readMessage.equals("bye"))
                break;
        }
        socket.close();
    }
    
    public static void sendPacket(InetAddress address, int port, String message, int step) {
    	try {
    		String bytes = message;
    		byte[] answer = MiscUtils.fromHexString(bytes);
    		DatagramPacket packet2 = new DatagramPacket(answer, answer.length, address, port);
			socket.send(packet2);
			System.out.println("### [Sync] ANSWER SENT - STEP " + step + ", " + MiscUtils.bytesToHex(answer));
		} catch (IOException e) {e.printStackTrace();}
    }
 
}