package lab7;
public class MatrixMaxFinder {

    private static class RowMaxWorker extends Thread {
        private final int[] row;
        private int maxInRow;

        public RowMaxWorker(int[] row) {
            this.row = row;
            this.maxInRow = Integer.MIN_VALUE;
        }

        @Override
        public void run() {
            for (int num : row) {
                if (num > maxInRow) {
                    maxInRow = num;
                }
            }
        }

        public int getMaxInRow() {
            return maxInRow;
        }
    }

    public static void main(String[] args) {
        // Тестовая матрица
        int[][] matrix = {
            {3, 5, 1, 9},
            {12, 4, 7, 2},
            {6, 8, 22, 5},
            {1, 15, 3, 0}
        };

        RowMaxWorker[] workers = new RowMaxWorker[matrix.length];

        // Инициализация и запуск потока для каждой строки
        for (int i = 0; i < matrix.length; i++) {
            workers[i] = new RowMaxWorker(matrix[i]);
            workers[i].start();
        }

        int globalMax = Integer.MIN_VALUE;

        // Ожидание завершения потоков и поиск наибольшего элемента
        for (int i = 0; i < matrix.length; i++) {
            try {
                workers[i].join();
                if (workers[i].getMaxInRow() > globalMax) {
                    globalMax = workers[i].getMaxInRow();
                }
            } catch (InterruptedException e) {
                System.err.println("Ошибка ожидания потока: " + e.getMessage());
            }
        }

        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }
}
