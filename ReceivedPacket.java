package MyUdpServer;

import java.net.InetAddress;

/**
 * Contains data and remote ip + port
 */
public class ReceivedPacket {

    private byte[] data;
    private InetAddress ip;
    private int port;

    public ReceivedPacket(byte[] data, InetAddress ip, int port) {
        this.data = data;
        this.ip = ip;
        this.port = port;
    }

    public byte[] getData() {
        return data;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
