package lab5;
import java.util.regex.*;

public class WordsStartingWithLetter {
    public static void main(String[] args) {
        String text = "apple banana apricot cherry avocado orange apple pie";
        char letter = 'a';//какую ищем

        Pattern pattern = Pattern.compile("\\b" + letter + "[a-zA-Z]*\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Слова, начинающиеся с '" + letter + "':");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}