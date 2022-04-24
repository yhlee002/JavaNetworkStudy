import java.io.*;
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

            String serverMsg = "", sendMsg = "";

            // Read & Print Server Message
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            serverMsg = br.readLine();
            System.out.println("Server Message > " + serverMsg);

            // Input & Send Message
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true); // Send Message (Write to OutputStream)

            InputStreamReader isr2 = new InputStreamReader(System.in); // Input with Keyboard
            BufferedReader br2 = new BufferedReader(isr2);

            while ((sendMsg = br2.readLine()) != null) {
                pw.println(sendMsg); // Send
                serverMsg = br.readLine(); // Read reply message that comes from server
                System.out.println("Server Message > " + serverMsg);
            }

            is.close();
            isr.close();
            isr2.close();
            br.close();
            br2.close();
            pw.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
