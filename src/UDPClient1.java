import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient1 {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buffer = new byte[100];

        InetAddress ip = InetAddress.getByName("localhost"); // '127.0.0.1'의미

        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ip, 6060);
        socket.send(dp);

        dp = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp);

        String woonse = new String(dp.getData());
        System.out.println("오늘의 운세 : " + woonse);
    }
}
