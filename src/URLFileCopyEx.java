import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// URL연결을 통해 웹 상의 이미지 URL을 통해 해당 이미지를 로컬 경로에 존재하는 빈 이미지 파일에 내용 write하기
// 파일 크기를 알지 못하는 문제 발생(HTTPS인 것이 이유로 생각됨) -> HttpURLConnection Class를 사용해 일부 이미지에 연결 성공!!
// 이미지 파일에 연결은 성공했으나 파일 내용은 작성되지 않는 문제 발생 -> 프로젝트 최상위 경로에 생성됐던걸 엉뚱한 경로만 보고있었던 것(src부터 전부 작성해야 함)
public class URLFileCopyEx {
    public static void main(String[] args) throws Exception {
        String str = "https://s.pstatic.net/static/www/img/uit/sp_main_dba1af.png";
//        String str = "https://img.theqoo.net/img/pBKgl.jpg";

        URL url = new URL(str);
//        InputStream ins = url.openStream();
//        URLConnection conn = url.openConnection();
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        InputStream ins = conn.getInputStream();
        BufferedInputStream bi = new BufferedInputStream(ins); // 이미지이기 때문에 문자열 스트림(BufferedReader 사용 불가)

        FileOutputStream fos = new FileOutputStream("src/images/img.jpg");
        byte buff[] = new byte[1024];
        int imgData = 0;
        int cnt = 0;

        int size = conn.getContentLength();

        System.out.println("size : " + size);
        while ((imgData = bi.read(buff)) != -1) {
            fos.write(buff, 0, imgData);
            fos.flush();
            cnt += imgData;
            System.out.println((cnt * 100) / size + "%");
        }
        ins.close();
        bi.close();
        fos.close();
    }
}
