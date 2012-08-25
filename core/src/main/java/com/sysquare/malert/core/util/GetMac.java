package com.sysquare.malert.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetMac {
    
    public static final String LOCAL_MAC_ADDRESS = "44-37-E6-2E-C1-FA";

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isValidMachine());
    }

    public static String getMacAddress() {
        String macAdd = null;
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
//            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

//            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            macAdd = sb.toString();
//            System.out.println(sb.toString());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
        return macAdd;
    }
    
    public static boolean isValidMachine() {
        return GetMac.LOCAL_MAC_ADDRESS.equalsIgnoreCase(getMacAddress());
    }
}
