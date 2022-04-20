import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class DemoClient {

    public static void main(String[] args) throws IOException {
        // 연결시 소켓 생성(연결되지 않을 경우 예외 발생)
        Socket sk = new Socket("127.0.0.1", 5050); // Host 주소 + port 필요. 현재 자신의 PC 주소(루프백 주소) 사용. 실제 자신의 IP를 입력해도 무관
        System.out.println("서버와 접속이 되었습니다.");

        InputStream is = sk.getInputStream();
        DataInputStream dis = new DataInputStream(is); // Server에서 Filter Stream으로 DataOutputStream을 사용했기 때문에 Client측에서도 DataInputStream을 사용해야 함

        int number = dis.readInt();
        System.out.println("서버에서 전송된 값 : " + number);
        String str = dis.readUTF();
        System.out.println("서버에서 전송된 값2 : " + str);

        dis.close();
        is.close();
        sk.close();
    }
}
