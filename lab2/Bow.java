package lab2;

public class Bow extends Weapon {
  // Дополнительное поле
  private int drawWeight; // сила натяжения в фунтах

  public Bow() {
      this.drawWeight = 30;
      System.out.println("  Инициализация лука (конструктор по умолчанию).");
  }

  public Bow(String name, double weight, int damage, int drawWeight) {
      super(name, weight, damage);
      this.drawWeight = drawWeight;
      System.out.println("  Инициализация лука '" + name + "' с параметрами.");
  }

  public int getDrawWeight() {
      return drawWeight;
  }

  public void setDrawWeight(int drawWeight) {
      this.drawWeight = drawWeight;
  }

  @Override
  public String getAttackSound() {
      return "ТЯУ! (звук натянутой тетивы)";
  }

  @Override
  public void showInfo() {
      super.showInfo();
      System.out.println("  Сила натяжения: " + drawWeight + " фунтов");
  }
}