package lab3;
import java.util.HashMap;
import java.util.Map;

public class OrderDatabase { //основное хранилище(связка номера заказа и объекта)
    private HashMap<String, Order> orders;

    public OrderDatabase() { //пустая карта
        orders = new HashMap<>();
    }

    // Вставка заказа
    public void addOrder(String orderNumber, Order order) {
        if (orders.containsKey(orderNumber)) { //проверка существования ключа
            System.out.println("Заказ с номером " + orderNumber + " уже существует. Обновление...");
        }
        orders.put(orderNumber, order); //сохраняем в карту
        System.out.println("Заказ " + orderNumber + " добавлен/обновлён.");
    }

    // Поиск заказа по номеру
    public Order findOrder(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (order == null) {
            System.out.println("Заказ с номером " + orderNumber + " не найден.");
        } else {
            System.out.println("Заказ найден: " + order);
        }
        return order;
    }

    // Удаление заказа по номеру
    public Order removeOrder(String orderNumber) {
        Order removed = orders.remove(orderNumber);
        if (removed == null) {
            System.out.println("Заказ с номером " + orderNumber + " не найден. Удаление невозможно.");
        } else {
            System.out.println("Заказ " + orderNumber + " удалён.");
        }
        return removed;
    }

    // Изменение статуса заказа
    public boolean updateOrderStatus(String orderNumber, String newStatus) {
        Order order = orders.get(orderNumber); //сначала ищем заказ
        if (order == null) {
            System.out.println("Заказ с номером " + orderNumber + " не найден. Статус не изменён.");
            return false;
        }
        order.setStatus(newStatus);  //меняем внутри найденого объекта
        System.out.println("Статус заказа " + orderNumber + " изменён на '" + newStatus + "'.");
        return true;
    }

    // Вывод всех заказов
    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Нет заказов в базе.");
            return;
        }
        System.out.println("\n=== Все заказы ===");
        for (Map.Entry<String, Order> entry : orders.entrySet()) { //проходим про всем парам ключ и значение
            System.out.println("Номер: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Получение количества заказов
    public int size() {
        return orders.size();
    }

    // Проверка, пуста ли база
    public boolean isEmpty() {
        return orders.isEmpty();
    }
}