import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientTest {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 7070;
        Socket socket = null;

        try {
            socket = new Socket(ip, port);
            System.out.println("서버에 접속했습니다.");

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
