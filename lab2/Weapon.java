package lab2;
public abstract class Weapon {
  // Поля класса 
  private String name;
  private double weight; // в кг
  private int damage;    // урон
  // Статическая переменная-счетчик (принадлежит классу, а не объекту)
  private static int totalWeaponsCreated = 0;

  // Конструктор по умолчанию создание объекта с базовыми настройками
  public Weapon() {
      this.name = "Unknown Weapon";
      this.weight = 1.0;
      this.damage = 1;
      totalWeaponsCreated++; // Увеличиваем счетчик при создании объекта
      System.out.println("Создано оружие (конструктор по умолчанию). Всего оружия: " + totalWeaponsCreated);
  }

  // Конструктор с параметрами 
  public Weapon(String name, double weight, int damage) {
      this.name = name;
      this.weight = weight;
      this.damage = damage;
      totalWeaponsCreated++;
      System.out.println("Создано оружие '" + name + "'. Всего оружия: " + totalWeaponsCreated);
  }
  //доступ к private полям
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public double getWeight() {
      return weight;
  }
  public void setWeight(double weight) {
      this.weight = weight;
  }
  public int getDamage() {
      return damage;
  }
  public void setDamage(int damage) {
      this.damage = damage;
  }
  //для получения значения счетчика
  public static int getTotalWeaponsCreated() {
      return totalWeaponsCreated;
  }

  // Абстрактный метод обязателен для переопределения 
  // Демонстрирует разное поведение 
  public abstract String getAttackSound();

  // Обычный метод, который будет унаследован 
  public void showInfo() {
      System.out.println("  Тип: " + this.getClass().getSimpleName() +
                         ", Название: " + name +
                         ", Вес: " + weight + " кг" +
                         ", Урон: " + damage);
  }
}