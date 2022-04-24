import java.io.*;
import java.net.Socket;

public class ChattingClient implements Runnable {
    String ip = "127.0.0.1"; // IP 주소의 구조 : 앞의 세 자리는 네트워크 주소, 마지막 자리는 컴퓨터 주소
    int port = 6060;
    Socket sk;

    public ChattingClient() {
        try {
            sk = new Socket(ip, port);
            System.out.println("Connected with chatting server");

            Thread thr = new Thread(this);
            thr.start();

            // Input message would sended to server
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(sk.getOutputStream(), true);
            String sendMsg = "";
            while ((sendMsg = br.readLine()) != null) {
                pw.println(sendMsg);
                System.out.println("Me > " + sendMsg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ChattingClient();
    }

    @Override
    public void run() {
        String serverMsg = "";
        try {
            // Read message come from server
            InputStream is = sk.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true) {
                serverMsg = br.readLine();
                System.out.println("Someone > " + serverMsg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
