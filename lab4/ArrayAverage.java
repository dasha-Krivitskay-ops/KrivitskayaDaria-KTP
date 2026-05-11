public class ArrayAverage {
  public static void main(String[] args) {
      // Пример с разными типами данных (представлены как Object для демонстрации ошибок данных)
      Object[] arr = {10, 20, "30", 40, "abc"};
      int sum = 0;
      int count = 0; 
      try { //зона ожидания ошибок
          // Специально выходим за границы для демонстрации catch
          for (int i = 0; i <= arr.length; i++) {
              sum += Integer.parseInt(arr[i].toString());//сначала эдемент становится строкой, потом числом
              count++;
          }
      } catch (ArrayIndexOutOfBoundsException e) { //ловушка для ошибки
          System.err.println("Ошибка: Выход за границы массива.");
      } catch (NumberFormatException e) {
          System.err.println("Ошибка: Неверный формат данных (элемент не является числом).");
      } finally { //блок выполняется всегда
          if (count > 0) {
              System.out.println("Среднее арифметическое: " + (double) sum / count);//с плавающей точкой ср.арифм
          } else {
              System.out.println("Массив не был обработан.");
          }
      }
  }
} 
