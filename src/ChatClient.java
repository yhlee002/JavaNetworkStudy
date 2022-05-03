import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatClient extends JApplet implements ActionListener, Runnable {
    int port = 7070;
    DataInputStream dis;
    DataOutputStream dos;
    JTextField tfNickName, tfInput;
    JTextArea ta;
    CardLayout card;
    Thread th;
    boolean flag = false;
    int lineCnt; // 줄 수가 일정 수준을 넘어서면 지우기 위해
    String lastMsg; // 지울 경우 마지막 메세지만 다시 보여주기 위해

    Container cont;
    Socket socket;

    public void init() {
        cont = getContentPane();
        card = new CardLayout();
        cont.setLayout(card);

        JPanel nameP = new JPanel();
        JPanel chatP = new JPanel(new BorderLayout());

        cont.add(nameP, "login");
        cont.add(chatP, "chat");
        card.show(cont, "login");

        nameP.add(new JLabel("로그인"));
        nameP.add(tfNickName = new JTextField(10));

        chatP.add(new JScrollPane(ta = new JTextArea()));
        chatP.add(tfInput = new JTextField(), "South");
        ta.setEditable(false);
        ta.setBackground(Color.ORANGE);
        ta.setForeground(Color.BLACK);

        tfNickName.addActionListener(this);
        tfInput.addActionListener(this);
    }

    public void start() {
        // 스레드를 이 때 동작시킴
        th = new Thread(this);
        th.start();
    }

    public void stop() {
        // 스레드 정지
        if (th != null) {
            flag = true;
        }
        th = null;
    }

    public void destroy() {
        try {
            if (dis != null) dis.close();
            if (dos != null) dos.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        if (obj == tfNickName) {
            String nickName = tfNickName.getText();
            if (nickName == null || nickName.trim().equals("")) {
                showStatus("닉네임을 입력하세요.");
                return;
            }

            try {
                dos.writeUTF(nickName);
                dos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            card.show(cont, "chat");
            tfInput.setEditable(true);
        } else if (obj == tfInput) {
            String sendMsg = tfInput.getText();
            try {
                dos.writeUTF(sendMsg);
                dos.flush();
                tfInput.setText("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void run() {
        // 서버에 접속하기 위해 IP 얻어오기
        String ip = getCodeBase().getHost();
        System.out.println(ip);
        ta.append("서버와 연결중..\n");
        try {
            socket = new Socket(ip, port);
            ta.append("연결되었습니다.\n");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);

            readFunc();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 서버에서 전달된 메세지를 읽어옴
    public void readFunc() {
        String serverMsg = "";
        while (!flag) { // 스레드가 stop되지 않는 한 계속해서 메세지 읽어오기
            try {
                serverMsg = dis.readUTF();
                if (lineCnt > 20) {
                    ta.setText("");
                    ta.append(serverMsg);
                    lineCnt = 0;
                }

                ta.append(serverMsg + "\n");
                lastMsg = serverMsg;
                lineCnt++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
