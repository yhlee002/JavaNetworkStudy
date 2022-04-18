package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    public static void main(String[] args) throws IOException {
        int port = 5050;

        // 서버 소켓(리스너 역할 뿐) 생성
        ServerSocket ssk = new ServerSocket(port); // 소켓과 연결할 포트 번호를 인자로 지님
        System.out.println("접속 대기중");

        while (true) {
            Socket socket = ssk.accept(); // Client와 연결되면 새로운 소켓 생성(accept)
            System.out.println("클라이언트가 접속했습니다.");
            System.out.println("클라이언트 IP : " + socket.getInetAddress().getHostAddress());
        }
    }
}
