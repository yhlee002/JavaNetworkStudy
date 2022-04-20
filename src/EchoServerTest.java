import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTest {

    public static void main(String[] args) {
        int port = 7070;
        ServerSocket ssk = null;
        Socket socket = null;

        try {
            while(true) {
                ssk = new ServerSocket(port);
                System.out.println("접속 대기중");
                socket = ssk.accept();
                System.out.println(socket.getInetAddress() + ":" + socket.getLocalPort() + "에서 접속되었습니다.");
                // Todo. 원격 서버로 연결할 때는 getPort()를 사용해도 되나, 로컬 서버에 연결할 때는 루프백 주소를 사용하기 때문에 getPort() 사용 시 계속해서 포트 번호가 바뀌어서 나옴

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
