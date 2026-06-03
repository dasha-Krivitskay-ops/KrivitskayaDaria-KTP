package lab7;
public class ArraySumThread extends Thread {
    private final int[] array;
    private final int start;
    private final int end;
    private long partialSum;

    public ArraySumThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.partialSum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
    }

    public long getPartialSum() {
        return partialSum;
    }

    public static void main(String[] args) {
        // Инициализация тестового массива
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; 
        }

        int mid = array.length / 2;

        // Создание двух потоков для обработки половинок массива
        ArraySumThread thread1 = new ArraySumThread(array, 0, mid);
        ArraySumThread thread2 = new ArraySumThread(array, mid, array.length);

        // Запуск потоков
        thread1.start();
        thread2.start();

        // Ожидание завершения работы обоих потоков в главном потоке
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Поток был прерван: " + e.getMessage());
        }

        // Сложение результатов в главном потоке
        long totalSum = thread1.getPartialSum() + thread2.getPartialSum();
        System.out.println("Общая сумма элементов массива: " + totalSum);
    }
}