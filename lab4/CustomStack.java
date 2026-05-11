import java.io.FileWriter;//запись в файл
import java.io.IOException;//ошибки ввода и вывода
import java.io.PrintWriter;//запись текста в файл
import java.util.ArrayList;//динамический массив
import java.util.Date;

// Свой класс для обработки исключений 
class CustomEmptyStackException extends Exception {
    public CustomEmptyStackException(String message) {//вызов конструктора при создании ошибки, передать ей текст
        super(message);//текст ошибки в родительский класс, чтоы знать что не так
    }
}

// Класс-обработчик, который логирует ошибки в текстовый файл
class ExceptionLogger {//журнал ошибок
    private static final String LOG_FILE = "exception_log.txt";//константа с именем файла
    public static void log(Exception e) {//принятие ошибки
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {//открытие файла, новые строки в конец
            writer.println("--- " + new Date() + " ---");
            writer.println("Тип исключения: " + e.getClass().getSimpleName());
            writer.println("Сообщение: " + e.getMessage());
            writer.println("------------------------------------");
            System.out.println("Ошибка записана в файл " + LOG_FILE);
        } catch (IOException ioException) {
            System.err.println("Не удалось записать лог: " + ioException.getMessage());
        }
    }
}

// Основной класс задания
public class CustomStack {//последний пришел - первый ушел
    private ArrayList<Integer> elements = new ArrayList<>();//хранение чисел

    // Метод для добавления в стек
    public void push(int value) {
        elements.add(value);
    }

    // Метод для извлечения из стека (может выбросить наше исключение)
    public int pop() throws CustomEmptyStackException {
        if (elements.isEmpty()) {
            throw new CustomEmptyStackException("Ошибка: Стек пуст. Нечего извлекать.");
        } 
        return elements.remove(elements.size() - 1);
    }

    public static void main(String[] args) {
        CustomStack stack = new CustomStack();//создание стека

        try {
            System.out.println("Добавляем число 10...");
            stack.push(10);
            
            System.out.println("Извлекаем: " + stack.pop());
            
            // Здесь произойдет ошибка, так как стек станет пустым
            System.out.println("Попытка извлечь еще раз...");
            stack.pop();

        } catch (CustomEmptyStackException e) {
            // Обработка нашего исключения
            System.err.println("Перехвачено кастомное исключение: " + e.getMessage());
            
            // Логируем информацию в файл (требование задания)
            ExceptionLogger.log(e);
        }
    }
}
