import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ChatServer {
    Vector<ClientHandler> clThrs; // Vector가 아닌 ArrayList를 사용해도 무관(배열의 크기를 유동적으로 늘릴 수 있기 때문에 사용)

    public ChatServer(int port) throws IOException {
        ServerSocket ssk = new ServerSocket(port);
        clThrs = new Vector<ClientHandler>(4, 2); // 처음에는 capcity가 4인 배열 생성. 필요할 경우 2씩 증가
        while (true) {
            System.out.println("클라이언트 연결 대기중...");
            Socket sk = ssk.accept();

            // 접속한 클라이언트와 통신을 담당하는 Thread 생성(해당 소켓을 다루는 스레드 생성)
            ClientHandler cl = new ClientHandler(this, sk);
            clThrs.add(cl);
            cl.start();
            System.out.println(sk.getInetAddress() + "님이 접속 하였습니다.");

        }
    }

    public static void main(String[] args) {
        try {
            new ChatServer(7070);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    class ClientHandler extends Thread {
        ChatServer server;
        Socket socket;

        DataInputStream dis;
        DataOutputStream dos;
        boolean flag = false;

        public ClientHandler(ChatServer server, Socket socket) {
            this.server = server;
            this.socket = socket;

            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() {
            String nickName = null;
            try {
                nickName = dis.readUTF(); // 로그인 시 클라이언트의 닉네임 얻어오기
                output(nickName + "님이 접속하였습니다.");
                while (!flag) {
                    String clientMsg = dis.readUTF(); // 클라이언트의 대화내용 읽어오기
                    output(nickName + " > " + clientMsg); // 클라이언트의 메세지를 모든 클라이언트들에 출력
                }

            } catch (IOException e) {
//                throw new RuntimeException(e);
                output(nickName + "님이 나가셨습니다.");
                e.printStackTrace();
            }

        }

        // 모든 클라이언트에게 메세지를 출력해주는 기능
        synchronized public void output(String msg) { // 임계영역으로 삼음
            Enumeration<ClientHandler> enu = server.clThrs.elements();// Iterator와 같은 역할. 배열 내부의 요소들을 모두 조회할 때 사용(반복자 역할)
            // Iterator i = server.clThrs.iterator();
            while (enu.hasMoreElements()) { // it.hasNext()
                ClientHandler cTh = enu.nextElement(); // 스레드를 하나씩 조회 // it.next();
                try {
                    cTh.dos.writeUTF(msg);
                    cTh.dos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
