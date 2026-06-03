package lab7;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Класс товара, содержащий его вес
class Product {
    private final String name;
    private final int weight;

    public Product(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (" + weight + " кг)";
    }
}

// Класс исходного склада
class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public synchronized void addProduct(Product product) {
        products.add(product);
    }

    public synchronized Product takeProduct() {
        if (products.isEmpty()) {
            return null;
        }
        return products.remove(0);
    }
}

// Система транспортировки с использованием Lock и Condition
class TransportSystem {
    private static final int MAX_WEIGHT = 150;
    
    private final Lock lock = new ReentrantLock();
    private final Condition batchReadyToLoad = lock.newCondition();
    
    private int currentWeight = 0;
    private boolean isTransporting = false;

    public void loadProduct(Product product, String loaderName) throws InterruptedException {
        lock.lock();
        try {
            // Если в данный момент выполняется доставка и разгрузка, поток ждет
            while (isTransporting) {
                batchReadyToLoad.await();
            }

            // Проверяем, помещается ли товар в текущую сессию погрузки до 150 кг
            if (currentWeight + product.getWeight() > MAX_WEIGHT) {
                System.out.println("[" + loaderName + "] Товар " + product + " превысит лимит. Текущий вес: " + currentWeight + " кг. Отправка машины!");
                isTransporting = true;
                
                // Симуляция транспортировки и разгрузки на другом складе
                Thread.sleep(1000); 
                System.out.println(">>> Партия весом " + currentWeight + " кг успешно доставлена и разгружена. <<<");
                
                currentWeight = 0;
                isTransporting = false;
                
                // Уведомляем остальные потоки, что можно продолжить погрузку
                batchReadyToLoad.signalAll();
            }

            // Добавление веса товара к текущей партии
            currentWeight += product.getWeight();
            System.out.println("[" + loaderName + "] Добавил " + product + ". Текущий вес партии: " + currentWeight + " кг.");

        } finally {
            lock.unlock();
        }
    }

    // Метод для выгрузки оставшихся товаров, не добравших до 150 кг в конце работы
    public void flushRemaining() {
        lock.lock();
        try {
            if (currentWeight > 0) {
                System.out.println(">>> Финальная разгрузка остатков: доставлено " + currentWeight + " кг товаров. <<<");
                currentWeight = 0;
            }
        } finally {
            lock.unlock();
        }
    }
}

// Класс грузчика, работающего в отдельном потоке
class Loader extends Thread {
    private final String loaderName;
    private final Warehouse sourceWarehouse;
    private final TransportSystem transportSystem;

    public Loader(String loaderName, Warehouse sourceWarehouse, TransportSystem transportSystem) {
        this.loaderName = loaderName;
        this.sourceWarehouse = sourceWarehouse;
        this.transportSystem = transportSystem;
    }

    @Override
    public void run() {
        while (true) {
            Product product = sourceWarehouse.takeProduct();
            if (product == null) {
                break; 
            }
            try {
                transportSystem.loadProduct(product, loaderName);
                Thread.sleep(200); // Симуляция времени на подбор и перенос товара
            } catch (InterruptedException e) {
                System.out.println(loaderName + " был прерван.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[" + loaderName + "] Закончил работу. На складе пусто.");
    }
}

// Демонстрация работы 
public class MainWarehouseApp {
    public static void main(String[] args) throws InterruptedException {
        Warehouse sourceWarehouse = new Warehouse();
        TransportSystem transportSystem = new TransportSystem();

        // Наполняем склад товарами разного веса
        for (int i = 1; i <= 12; i++) {
            sourceWarehouse.addProduct(new Product("Товар-" + i, 30 + (i % 3) * 15)); 
        }

        // Создание трех грузчиков
        Loader loader1 = new Loader("Грузчик-Алексей", sourceWarehouse, transportSystem);
        Loader loader2 = new Loader("Грузчик-Дмитрий", sourceWarehouse, transportSystem);
        Loader loader3 = new Loader("Грузчик-Иван", sourceWarehouse, transportSystem);

        // Запуск одновременной работы
        loader1.start();
        loader2.start();
        loader3.start();

        // Ожидание завершения погрузки
        loader1.join();
        loader2.join();
        loader3.join();

        // Отправка последней оставшейся недогруженной партии
        transportSystem.flushRemaining();
        System.out.println("Процесс переноса всех товаров успешно завершен!");
    }
}