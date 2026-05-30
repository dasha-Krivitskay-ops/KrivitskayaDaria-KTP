package lab6;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        // Задаем имя файла БЕЗ сложных путей
        String filePath = "text.txt"; 
        File file = new File(filePath);

        //АВТОМАТИЧЕСКАЯ ПРОВЕРКА И СОЗДАНИЕ ФАЙЛА
        if (!file.exists()) {
            try {
                System.out.println("Файл не был найден. Создаю новый файл по пути:");
                System.out.println(file.getAbsolutePath());//путь к файлу
                
                FileWriter writer = new FileWriter(file);
                // Запишем туда тестовый текст, чтобы программа не была пустой
                writer.write("apple banana apple orange banana apple grape apple");
                writer.close();
                
                System.out.println("--> ФАЙЛ УСПЕШНО СОЗДАН! Открой его и вставь свой текст.");
                System.out.println("--> Затем запусти программу ЕЩЁ РАЗ.\n");
            } catch (Exception e) {
                System.out.println("Не удалось автоматически создать файл: " + e.getMessage());
            }
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл всё ещё не найден.");
            return;
        }

        Map<String, Integer> wordMap = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Zа-яА-Я0-9]", "");
            if (!word.isEmpty()) {//пустая строка(был просто знак препинания в строке), не чит пуст стр
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }
        scanner.close();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println("=== ТОП-10 САМЫХ ЧАСТЫХ СЛОВ ===");
        int limit = Math.min(10, list.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i + 1) + ". [" + entry.getKey() + "] — " + entry.getValue() + " раз(а)");
        }
    }
}