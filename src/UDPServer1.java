import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer1 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6060);

        while(true) {
            System.out.println("클라이언트 연결 대기중");
            byte buffer[] = new byte[100];

            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            socket.receive(dp); // 패킷 내부에는 클라이언트의 정보가 들어있음

            String woonse = TodayWoonse.selWoonse(); // 운세를 가져옴
            buffer = woonse.getBytes();

            InetAddress ip = dp.getAddress();
            int port = dp.getPort();
            System.out.println("연결된 포트번호 : " + port);

            dp = new DatagramPacket(buffer, buffer.length, ip, port);
            socket.send(dp);
        }
    }
}
