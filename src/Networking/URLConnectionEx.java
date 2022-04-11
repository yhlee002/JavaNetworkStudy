package Networking;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionEx {

    public static void main(String[] args) throws Exception {
        String str = "https://skbp.learningmate.co.kr/usrs/lms/classrm/classrmPrgrsItm.do"; // File까지 있는 URL을 부여
        URL url = new URL(str);

        URLConnection conn = url.openConnection();
        int size = conn.getContentLength(); // 가끔 불러오지 못하는 경우 존재
        String fileType = conn.getContentType();
        long time = conn.getDate(); // 1970.01.01 00:00부터 시작되어 천분의 일초로 계산된 값
        System.out.println("conn : " + conn.toString());
        System.out.println("doc size : " + size);
        System.out.println("file type : " + fileType);
        System.out.println("time : " + time);
    }
}
