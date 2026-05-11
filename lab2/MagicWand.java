package lab2;

public class MagicWand extends Weapon {
  // Дополнительное поле
  private String coreMaterial; // материал сердцевины
  public MagicWand() {
      this.coreMaterial = "Феникс";
      System.out.println("  Инициализация волшебной палочки (конструктор по умолчанию).");
  }

  public MagicWand(String name, double weight, int damage, String coreMaterial) {
      super(name, weight, damage);
      this.coreMaterial = coreMaterial;
      System.out.println("  Инициализация волшебной палочки '" + name + "' с параметрами.");
  }

  public String getCoreMaterial() {
      return coreMaterial;
  }

  public void setCoreMaterial(String coreMaterial) {
      this.coreMaterial = coreMaterial;
  }

  @Override
  public String getAttackSound() {
      return "АВАДА КЕДАВРА! (или любое другое заклинание)";
  }

  @Override
  public void showInfo() {
      super.showInfo();
      System.out.println("  Сердцевина: " + coreMaterial);
  }
}
