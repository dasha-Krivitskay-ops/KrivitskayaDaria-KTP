package lab3;

import java.util.LinkedList;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16; //размер массива
    private LinkedList<Entry<K, V>>[] table; //массив связанных списков
    private int size; //кол-во элементов

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        table = new LinkedList[capacity]; //создание массива списков
        size = 0;
    }

    public HashMap() {
        this(DEFAULT_CAPACITY); //вызов конструктора с размером 16
    }

    private int hash(K key) { //вычисление индекса ячейки
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) { //номер корзины
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>(); 
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] == null) {
            return null;
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K key) { 
        int index = hash(key);
        if (table[index] == null) {
            return null;
        }

        var iterator = table[index].iterator(); //итератор для безопасного удаления из списка
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                iterator.remove();
                size--;
                if (table[index].isEmpty()) {
                    table[index] = null; //если корзина пустая занулить
                }
                return value;
            }
        }
        return null;
    }

    public int size() {
        return size; //текущий размер
    }

    public boolean isEmpty() {
        return size == 0; //проверка на пустоту
    }

    private static class Entry<K, V> { //помогает хранить ключ и значение
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // Демонстрация работы
    public static void main(String[] args) { 
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println("isEmpty: " + map.isEmpty());

        map.put("apple", 5);
        map.put("banana", 3);
        map.put("orange", 7);
        map.put("pear", 2);

        System.out.println("size: " + map.size());
        System.out.println("apple -> " + map.get("apple"));
        System.out.println("banana -> " + map.get("banana"));

        map.remove("banana");
        System.out.println("After remove banana, size: " + map.size());
        System.out.println("banana -> " + map.get("banana"));

        System.out.println("isEmpty: " + map.isEmpty());
    }
}
