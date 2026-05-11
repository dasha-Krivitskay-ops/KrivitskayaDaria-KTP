package lab3;
import java.util.List;
import java.util.ArrayList;

public class Order { //поля класса(данные о заказе)
    private String orderId; //номер
    private String date;
    private List<String> items; //список товаров
    private String status;

    // Конструктор для создпния нового заказа
    public Order(String orderId, String date, List<String> items, String status) {
        this.orderId = orderId;
        this.date = date;
        this.items = new ArrayList<>(items); //создаем копию списка товаров
        this.status = status;
    }

    // Геттеры(методы для получения данных)
    public String getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }

    public List<String> getItems() {
        return new ArrayList<>(items); //возвращаем копию списка 
    }

    public String getStatus() {
        return status;
    }

    // Сеттеры(методы для изменения данных)
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItems(List<String> items) {
        this.items = new ArrayList<>(items); //обновляем список через копию
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Метод для отображения информации о заказе
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", items=" + items +
                ", status='" + status + '\'' +
                '}';
    }
}