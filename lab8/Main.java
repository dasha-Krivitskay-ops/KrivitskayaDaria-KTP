import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// --- 1. АННОТАЦИЯ ---
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface DataProcessor {
    String description() default "Data Processing Method";
}

// --- 2. ОБРАБОТЧИКИ ДАННЫХ ---
class TextProcessors {
    
    // Используем parallelStream() для выполнения требований многопоточности при обработке коллекций
    @DataProcessor(description = "Фильтрация ненужных данных")
    public List<String> filterData(List<String> input) {
        return input.parallelStream() // Многопоточная обработка элементов стрима
                .filter(s -> s != null && !s.trim().isEmpty())
                .filter(s -> !s.contains("skip"))
                .collect(Collectors.toList());
    }

    @DataProcessor(description = "Приведение к верхнему регистру")
    public List<String> transformToUpperCase(List<String> input) {
        return input.parallelStream() // Распараллеливание трансформации строк
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    @DataProcessor(description = "Сортировка данных")
    public List<String> sortData(List<String> input) {
        return input.parallelStream() // Распараллеливание сортировки
                .sorted()
                .collect(Collectors.toList());
    }
}

// --- 3. МЕНЕДЖЕР ДАННЫХ ---
class DataManager {
    private List<String> sharedData = new ArrayList<>();
    private final List<Object> processors = new ArrayList<>();
    private List<String> processedResult = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
        System.out.println("Зарегистрирован обработчик: " + processor.getClass().getSimpleName());
    }

    public void loadData(String source) {
        try {
            sharedData = Files.readAllLines(Paths.get(source));
            System.out.println("Данные успешно загружены. Количество строк: " + sharedData.size());
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    // Последовательно вызываем процессоры, которые внутри себя работают многопоточно
    public void processData() {
        // Инициализируем итоговый список начальными данными
        processedResult = new ArrayList<>(sharedData);

        for (Object processor : processors) {
            Method[] methods = processor.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    DataProcessor annotation = method.getAnnotation(DataProcessor.class);
                    
                    try {
                        System.out.println("[Конвейер] Запуск этапа: " + annotation.description());
                        
                        // Передаем результат предыдущего шага на вход следующему
                        @SuppressWarnings("unchecked")
                        List<String> output = (List<String>) method.invoke(processor, processedResult);
                        
                        // Обновляем данные для следующего обработчика
                        processedResult = output;
                        
                        System.out.println("[Конвейер] Этап завершен: " + annotation.description());
                    } catch (Exception e) {
                        System.err.println("Ошибка выполнения метода: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void saveData(String destination) {
        try {
            Files.write(Paths.get(destination), processedResult);
            System.out.println("Обработанные данные успешно сохранены в: " + destination);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }
}

// --- 4. ГЛАВНЫЙ КЛАСС ---
public class Main {
    public static void main(String[] args) {
        String sourceFile = "input_data.txt";
        String destinationFile = "output_data.txt";

        List<String> testLines = Arrays.asList(
                "java programming",
                "skip this line",
                "stream api is powerful",
                "   ", 
                "annotations in java",
                "multithreading core",
                "skip word again"
        );

        try {
            Files.write(Paths.get(sourceFile), testLines);
        } catch (IOException e) {
            System.err.println("Не удалось создать тестовый файл: " + e.getMessage());
            return;
        }

        DataManager dataManager = new DataManager();
        TextProcessors textProcessors = new TextProcessors();
        
        dataManager.registerDataProcessor(textProcessors);
        dataManager.loadData(sourceFile);

        System.out.println("\n--- Старт многопоточной обработки ---");
        dataManager.processData();
        System.out.println("--- Обработка завершена ---\n");

        dataManager.saveData(destinationFile);

        try {
            System.out.println("\nСодержимое итогового файла после конвейера фильтров:");
            Files.readAllLines(Paths.get(destinationFile)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
