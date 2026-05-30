package lab5;
import java.util.regex.*;

public class IPValidator {
    public static void main(String[] args) {
        String[] ips = {
            "192.168.1.1",
            "255.255.255.0",
            "0.0.0.0",
            "256.1.1.1",
            "123.456.78.90",
            "10.0.0",
            "abc.def.ghi.jkl"
        };

        Pattern pattern = Pattern.compile(
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
        );

        for (String ip : ips) {
            Matcher matcher = pattern.matcher(ip);
            System.out.println(ip + " : " + (matcher.matches() ? "Корректен" : "Некорректен"));
        }
    }
}