package Networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionWriterEx {
    public static void main(String[] args) throws Exception {
        // URL 생성
        URL url = new URL("http://httpbin.org/post");

        // URLConnection 객체 생성
        URLConnection conn = url.openConnection();

        // doOutput field 값을 true로 설정
        conn.setDoOutput(true);

        // 서버에 데이터 보내기
        OutputStreamWriter ow = new OutputStreamWriter(conn.getOutputStream());

        ow.write("Name=Hong Gil Dong"); // 'Name=Hong Gil Dong&Number=34' 으로 표현하는 것과 같은 결과
        ow.write("&Number=34");
        ow.close();

        // 서버에 보냈던 데이터 가져오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str = "";
        while((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
    }
}
