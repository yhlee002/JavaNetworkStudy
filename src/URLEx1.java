import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLEx1 {
    public static void main(String[] args) throws Exception {
        URL url = new URL(args[0]);
        System.out.println("Protocol : " + url.getProtocol());
        System.out.println("Host : " + url.getHost());
        System.out.println("Port : " + url.getPort());
        System.out.println("File : " + url.getFile());

        InputStream ins = url.openStream(); // openStream() : 해당 URL로 접속. 접속 결과값을 InputStream으로 반환하는 메소드

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(ins)); // openStream()으로 가져온 내용을 InputStreamReader로 읽어와 이를 BufferedReader에 저장

        String str = "";
        while ((str = br.readLine()) != null) { // 한 라인씩 read
            System.out.println(str);
        }
        br.close();
        ins.close();
    }
    /**
     * [openStream()의 내부 로직(간략히)]
     * 1. openConnection()을 통해 해당 URL에 연결 cf.openConnection()은 URLConnection 인스턴스 반환
     * 2. getInputStream()을 사용해 InputStream을 얻음 cf. getInputStream()은 java.net.URLConnection 클래스의 메소드
     * 즉, openStream() == openConnection().getInputStream()
     *
     *  [URLConnection]
     *  URL의 openConnection()을 통해 반환되는 인스턴스의 클래스.
     *  내부에는 boolean타입의 'doInput' field와 'doOutput' field 존재
     *  -> 이 둘은 URLConnection 인스턴스의 setDoInput() 혹은 setDoOutput()함수 호출로 값 변경이 가능하다.
     *  URL의 openConnection()와 같은 역할을 하는 것이 URLConnection 에서는 connect();
     **/
}
