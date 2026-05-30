package lab6;
import java.util.EmptyStackException;

public class Stack<T> {
    private final T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    // Положить элемент в стек
    public void push(T element) {
        if (size == data.length) {
            throw new StackOverflowError("Стек переполнен!");
        }
        data[size] = element;
        size++;
    }

    // Забрать элемент с вершины
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        size--;//индекс верхнего
        T element = data[size];
        data[size] = null; // Помогаем сборщику мусора
        return element;
    }

    // Посмотреть верхний элемент без удаления
    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }

    public static void main(String[] args) {
        System.out.println("=== ТЕСТ СТРУКТУРЫ STACK ===");
        Stack<String> myStack = new Stack<>(5);
        myStack.push("Элемент 1");
        myStack.push("Элемент 2");
        myStack.push("Элемент 3");

        System.out.println("Верхний элемент через peek(): " + myStack.peek());
        System.out.println("Забрали элемент через pop(): " + myStack.pop());
        System.out.println("Новый верхний элемент: " + myStack.peek());
    }
}