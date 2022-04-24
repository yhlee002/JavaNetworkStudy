import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChattingServer implements Runnable {
    Socket sk;

    public ChattingServer() {
        System.out.println("Server Start");
        try {
            ServerSocket ssk = new ServerSocket(6060);
            sk = ssk.accept();
            System.out.println("Connected with client...");

            Thread thr = new Thread(this);
            thr.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(sk.getOutputStream(), true);
            String msg = "";
            while ((msg = br.readLine()) != null) {
                pw.println(msg);
                System.out.println("Me > " + msg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String clientMsg = "";
        try {
            InputStream is = sk.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true) { // 데이터를 읽을 때까지 무한 루프
                clientMsg = br.readLine();
                System.out.println("Someone > " + clientMsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ChattingServer();
    }
}
