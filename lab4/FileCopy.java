import java.io.*; //импорт классов вводв-вывода

public class FileCopy {
    public static void main(String[] args) {
        String sourceFile = "source.txt";//имя файла откуда читаем(источник)
        String destFile = "destination.txt";//куда пишем

        try (FileInputStream in = new FileInputStream(sourceFile);//открываем поток для чтения
             FileOutputStream out = new FileOutputStream(destFile)) {//поток для записи
            
            int c;//переменная для хранение одного байта данных
            while ((c = in.read()) != -1) { //читаем байт пока не конц файла
                out.write(c);//запись байта в out
            }
            System.out.println("Файл успешно скопирован.");
            
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении или записи файла: " + e.getMessage());
        }
    }
}
