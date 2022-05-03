import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static final int port = 6060;

    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        BufferedReader bufReader = null;
        InputStreamReader isReader = new InputStreamReader(System.in);

        bufReader = new BufferedReader(isReader);

        String sendMsg = "";

        System.out.println("전송할 내용을 입력하세요.");
        DatagramSocket ds = new DatagramSocket();
        DatagramPacket dp = null;

        while ((sendMsg = bufReader.readLine()) != null) {
            if (sendMsg.equalsIgnoreCase("x")) break;
            byte[] data = sendMsg.getBytes();
            dp = new DatagramPacket(data, data.length, ip, port);
            ds.send(dp);
        }
    }
}
