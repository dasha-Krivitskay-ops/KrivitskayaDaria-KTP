package lab6;
import java.util.*;

// Класс для хранения данных об одной продаже
class ProductSale {
    private final String productName;
    private final double price;
    private final int quantity;

    public ProductSale(String productName, double price, int quantity) { 
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Товар: '" + productName + "' | Цена: " + price + " руб. | Куплено: " + quantity + " шт.";
    }
}

// Главный класс трекера продаж
public class SalesTracker {
    private final List<ProductSale> salesList = new ArrayList<>();

    // Добавление новой продажи
    public void addSale(String productName, double price, int quantity) {
        if (quantity <= 0 || price < 0) {
            System.out.println("Ошибка: неверные данные!");
            return;
        }
        salesList.add(new ProductSale(productName, price, quantity));
        System.out.println("Зафиксирована продажа: " + productName + " в количестве " + quantity + " шт.");
    }

    // 1. Вывод всех совершенных продаж
    public void printAllSales() {
        System.out.println("\n=== ПОЛНЫЙ СПИСОК ПРОДАЖ ===");
        if (salesList.isEmpty()) {
            System.out.println("Продаж еще нет.");
            return;
        }
        for (ProductSale sale : salesList) {
            System.out.println(sale);
        }
    }

    // 2. Подсчет общей выручки магазина
    public double calculateTotalRevenue() {
        double total = 0;
        for (ProductSale sale : salesList) {
            total += sale.getPrice() * sale.getQuantity();
        }
        return total;
    }

    // 3. Поиск самого популярного товара (по суммарному количеству)
    public String getMostPopularProduct() {
        if (salesList.isEmpty()) return "Данные отсутствуют";

        Map<String, Integer> productCountMap = new HashMap<>();
        for (ProductSale sale : salesList) {
            String name = sale.getProductName();
            productCountMap.put(name, productCountMap.getOrDefault(name, 0) + sale.getQuantity());
        }

        String popularProduct = "";
        int maxQuantity = -1;

        for (Map.Entry<String, Integer> entry : productCountMap.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                maxQuantity = entry.getValue();
                popularProduct = entry.getKey();
            }
        }
        return popularProduct + " (Общее количество: " + maxQuantity + " шт.)";
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        System.out.println("=== РЕГИСТРАЦИЯ ОПЕРАЦИЙ ===");
        tracker.addSale("Мышка", 1200.0, 2);
        tracker.addSale("Клавиатура", 3500.0, 1);
        tracker.addSale("Наушники", 4000.0, 3);
        tracker.addSale("Мышка", 1200.0, 4); // Повторная продажа той же позиции

        // Вызов требуемых в задании методов
        tracker.printAllSales();

        System.out.println("\n=== ИТОГОВАЯ АНАЛИТИКА ===");
        System.out.println("Общая выручка: " + tracker.calculateTotalRevenue() + " руб.");
        System.out.println("Самый популярный товар: " + tracker.getMostPopularProduct());
    }
}