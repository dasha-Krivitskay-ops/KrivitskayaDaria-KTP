package lab5;
import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The price is 19.99, but you can buy it for 9.99 or 100.0 or 42.";
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");//объект шаблона
        Matcher matcher = pattern.matcher(text);//поисковый движок

        System.out.println("Найденные числа:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
