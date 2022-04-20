import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = null;
        ia = InetAddress.getByName("www.naver.com");
        System.out.println("Host Name : " + ia.getHostName());
        System.out.println("Host IP Address : " + ia.getHostAddress());
        System.out.println("Domain + IP Address : " + ia.toString());
        System.out.println();

        InetAddress[] iaArr = InetAddress.getAllByName(ia.getHostName());
        for (int i = 0; i < iaArr.length; i++) {
            System.out.println(iaArr[i].toString());
        }

        ia = InetAddress.getLocalHost();
        System.out.println("My PC Host + IP Address : " + ia.toString());
    }
}
