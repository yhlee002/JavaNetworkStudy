package Networking;

import java.io.IOException;
import java.net.Socket;

public class DemoClient {

    public static void main(String[] args) throws IOException {
        // 연결시 소켓 생성(연결되지 않을 경우 예외 발생)
        Socket sk = new Socket("127.0.0.1", 5050); // Host 주소 + port 필요. 현재 자신의 PC 주소(루프백 주소) 사용. 실제 자신의 IP를 입력해도 무관
        System.out.println("서버와 접속이 되었습니다.");
    }
}
