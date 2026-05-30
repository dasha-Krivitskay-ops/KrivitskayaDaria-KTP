package lab5;
import java.util.regex.*;

public class CapitalAfterLowercase {
    public static void main(String[] args) {
        String text = "aA bB cC dDD eEe fF helloWorld JavaRegex testAbc xYz";
        Pattern pattern = Pattern.compile("([a-z])([A-Z])");
        Matcher matcher = pattern.matcher(text);//привязка к тексту
        String result = matcher.replaceAll("!$1$2!");//склейка совпад и !
        System.out.println("Результат:\n" + result);
    }
}