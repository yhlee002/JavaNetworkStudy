import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    public static void main(String[] args) throws IOException {
        int port = 5050;

        int number = Integer.parseInt(args[0]); // Text 형태로 들어오기 때문에 변환
        String str = new String(args[1]);

        // 서버 소켓(리스너 역할 뿐) 생성
        ServerSocket ssk = new ServerSocket(port); // 소켓과 연결할 포트 번호를 인자로 지님
        System.out.println("접속 대기중");

        while (true) {
            Socket socket = ssk.accept(); // Client와 연결되면 새로운 소켓 생성(accept)
            System.out.println("클라이언트가 접속했습니다.");
            System.out.println("클라이언트 IP : " + socket.getInetAddress().getHostAddress());
            
            // Client와의 연결을 위한(Data를 보내기 위해) Stream 생성
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os); // DataOutputStream : 들어오는 데이터의 자료형에 따라 받아올 수 있는 Filter Stream. Node Stream인 OutputStream과 연결해야 함

            dos.writeInt(number);
            dos.writeUTF(str);
            dos.flush();
            dos.close();

            socket.close();
        }
    }
}
