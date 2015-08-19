package MyUdpServer;

import java.io.IOException;
import java.net.*;


public class UdpServer {

    private DatagramSocket mSocket;
    private DatagramPacket sendPacket, receivePacket;
    private InetAddress receiverIP, sendersIP;
    private int receiverPort, sendersPort;
    private long sendTimestamp, receiveTimestamp;
    private int socketTimeOut = 5000;   // default value

    public UdpServer() {
        try {
            mSocket = new DatagramSocket();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UdpServer(int port) {
        try {
            mSocket = new DatagramSocket(port);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendPacket(byte[] data) {
        sendPacket = new DatagramPacket(data, data.length, receiverIP, receiverPort);

        try {
            mSocket.send(sendPacket);
            sendTimestamp = System.currentTimeMillis() / 1000L;

            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean sendPacket(InetAddress receiverIP, int receiverPort, byte[] data) {
        sendPacket = new DatagramPacket(data, data.length, receiverIP, receiverPort);

        try {
            mSocket.send(sendPacket);
            sendTimestamp = System.currentTimeMillis() / 1000L;

            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ReceivedPacket receivePacket() {
        byte[] data = new byte[64000];

        receivePacket = new DatagramPacket(data, data.length);

        try {
            mSocket.setSoTimeout(socketTimeOut);
            mSocket.receive(receivePacket);
            receiveTimestamp = System.currentTimeMillis() / 1000L;

            sendersIP = receivePacket.getAddress();
            sendersPort = receivePacket.getPort();

            return new ReceivedPacket(data, receivePacket.getAddress(), receivePacket.getPort());
        }
        catch (SocketTimeoutException e) {
            //
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setReceiverIP(InetAddress receiverIP) {
        this.receiverIP = receiverIP;
    }

    public void setReceiverPort(int receiverPort) {
        this.receiverPort = receiverPort;
    }

    public void setSocketTimeOut(int socketTimeOut) { this.socketTimeOut = socketTimeOut; }

    public long getLatency() {
        return receiveTimestamp - sendTimestamp;
    }

    public int getSocketPortNumber() { return mSocket.getLocalPort(); }
}
