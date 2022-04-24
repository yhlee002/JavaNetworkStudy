import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class EchoServerTest {

    public static void main(String[] args) {
        int port = 7070;
        ServerSocket ssk = null;
        Socket socket = null;

        try {
            ssk = new ServerSocket(port);
            System.out.println("접속 대기중");

            while (true) {
                socket = ssk.accept();
                System.out.println(socket.getInetAddress() + ":" + socket.getLocalPort() + "에서 접속되었습니다.");
                /* 원격 서버로 연결할 때는 getPort()를 사용해도 되나, 로컬 서버에 연결할 때는 루프백 주소를 사용하기 때문에
                   getPort() 사용 시 계속해서 포트 번호가 바뀌어서 나옴 */

                // 클라이언트와 통신하기 위한 스트림 생성
                OutputStream os = socket.getOutputStream(); // OutputStream은 브릿지 역할을 하는 스트림
                PrintWriter pw = new PrintWriter(os, true); // 두 번째 매개변수로 true를 전달하면 자동으로 flush 기능을 제공
                pw.println("hi!");

                // Read & Replay Client Message
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String clientMsg = "";
                while ((clientMsg = br.readLine()) != null) {
                    if (clientMsg.startsWith("hi") || clientMsg.startsWith("hello")) {
                        pw.println("nice to meet you, " + socket.getInetAddress() + "..^^");
                    } else if (clientMsg.startsWith("what date is it today")) {
                        LocalDateTime date = LocalDateTime.now();
                        pw.println(date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth());
                    } else {
                        pw.println("bye, " + socket.getInetAddress());
                    }
                }

                os.close();
                pw.close();
                is.close();
                isr.close();
                br.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
