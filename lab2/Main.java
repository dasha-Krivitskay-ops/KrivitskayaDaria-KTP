package lab2;

public class Main {
  public static void main(String[] args) {
      System.out.println("=== Демонстрация работы программы ===");
      // --- Создание объектов с использованием разных конструкторов ---
      System.out.println("\n--- Создание оружия ---");
      Sword sword1 = new Sword(); // Конструктор по умолчанию
      Sword sword2 = new Sword("Экскалибур", 2.5, 150, 95.5); // Конструктор с параметрами
      Bow bow1 = new Bow("Лонгбоу", 1.2, 80, 70);
      MagicWand wand1 = new MagicWand("Бузинная палочка", 0.3, 200, "Смерть");
      MagicWand wand2 = new MagicWand(); // Конструктор по умолчанию
      // --- Демонстрация работы статического счетчика ---
      System.out.println("\n--- Статистика ---");
      System.out.println("Всего создано объектов оружия: " + Weapon.getTotalWeaponsCreated());
      // --- Демонстрация полиморфизма и работы методов ---
      System.out.println("\n--- Информация об оружии (полиморфизм) ---");
      // Создаем массив оружия, чтобы показать, что они все - потомки Weapon
      Weapon[] arsenal = {sword1, sword2, bow1, wand1, wand2};
      for (Weapon w : arsenal) {
          System.out.println("\n" + w.getName() + " издает звук: " + w.getAttackSound());
          w.showInfo(); // Вызов переопределенного метода
      }
      // --- Демонстрация работы геттеров и сеттеров ---
      System.out.println("\n--- Работа с геттерами/сеттерами ---");
      System.out.println("Старое имя лука: " + bow1.getName());
      bow1.setName("Легендарный Лонгбоу");
      bow1.setDamage(100); // увеличили урон
      System.out.println("Новое имя лука: " + bow1.getName());
      System.out.println("Новый урон: " + bow1.getDamage());
      System.out.println("Длина клинка меча sword1 (геттер): " + sword1.getBladeLength() + " см");
      System.out.println("\n=== Программа завершена ===");
  }
}
