package lab5;
import java.util.regex.*;

public class PasswordValidator {
    public static void main(String[] args) {
        String[] passwords = {"Pass1234", "pass", "PASSWORD1", "Pass1", "ValidPass9", "noDigit", "NoDigitAtAll"};

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$");

        for (String pwd : passwords) {//для кадого элемента выполни след код
            Matcher matcher = pattern.matcher(pwd);//проверяльщик текущего
            System.out.println(pwd + " : " + (matcher.matches() ? "✅ Корректен" : "❌ Некорректен"));
        }
    }
}
