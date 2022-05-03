import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[256];
        DatagramSocket ds = new DatagramSocket(6060);
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length); // send parameter : min 4 param(buffer, bufferLength, InetAddress, port)

        while(true) {
            ds.receive(dp);
            byte[] bufferMsg = dp.getData();
            String clientMsg = new String(bufferMsg, 0, dp.getLength());
            System.out.println(dp.getAddress() + ">>" + clientMsg);
        }

    }
}
