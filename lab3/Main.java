package lab3;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) { //создаем экземпляр базы данных
        OrderDatabase db = new OrderDatabase();

        // Создание объектов заказов
        Order order1 = new Order("ORD001", "2024-10-15", 
                Arrays.asList("Ноутбук", "Мышь"), "Оформлен");
        Order order2 = new Order("ORD002", "2024-10-16", 
                Arrays.asList("Телефон", "Чехол", "Стекло"), "Оплачен");
        Order order3 = new Order("ORD003", "2024-10-17", 
                Arrays.asList("Наушники"), "Доставлен");

        // 1. Добавление заказов
        System.out.println("=== ДОБАВЛЕНИЕ ЗАКАЗОВ ===");
        db.addOrder("ORD001", order1);
        db.addOrder("ORD002", order2);
        db.addOrder("ORD003", order3);

        // 2. Поиск заказа
        System.out.println("\n=== ПОИСК ЗАКАЗА ===");
        db.findOrder("ORD002");
        db.findOrder("ORD999"); // несуществующий

        // 3. Изменение статуса
        System.out.println("\n=== ИЗМЕНЕНИЕ СТАТУСА ===");
        db.updateOrderStatus("ORD001", "Отправлен");
        db.updateOrderStatus("ORD999", "Отменён"); // несуществующий

        // 4. Вывод всех заказов
        db.displayAllOrders();

        // 5. Удаление заказа
        System.out.println("\n=== УДАЛЕНИЕ ЗАКАЗА ===");
        db.removeOrder("ORD002");
        db.removeOrder("ORD999"); // несуществующий

        // 6. Финальное состояние
        db.displayAllOrders();
        System.out.println("\nКоличество заказов в базе: " + db.size());
        System.out.println("База пуста? " + db.isEmpty());
    }
}

