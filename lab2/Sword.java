package lab2;

public class Sword extends Weapon {

  // Дополнительное поле для меча
  private double bladeLength; // длина клинка в см

  // Конструктор по умолчанию
  public Sword() {
      // Неявно вызывается super()
      this.bladeLength = 70.0; // значение по умолчанию
      System.out.println("  Инициализация меча (конструктор по умолчанию).");
  }

  // Конструктор с параметрами 
  public Sword(String name, double weight, int damage, double bladeLength) {
      super(name, weight, damage); // Вызов конструктора родителя
      this.bladeLength = bladeLength;
      System.out.println("  Инициализация меча '" + name + "' с параметрами.");
  }

  //для нового поля геттр и сеттер
  public double getBladeLength() {
      return bladeLength;
  }
  public void setBladeLength(double bladeLength) {
      this.bladeLength = bladeLength;
  }

  // Переопределение абстрактного метода (@Override - аннотация)
  @Override
  public String getAttackSound() {
      return "ВЖЖИК! (звук рассекаемого воздуха)";
  }

  // Переопределение метода родителя 
  @Override
  public void showInfo() {
      super.showInfo(); // Вызов метода родительского класса (super)
      System.out.println("  Длина клинка: " + bladeLength + " см");
  }
}