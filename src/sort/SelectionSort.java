package sort;

public class SelectionSort {

    public static <T extends Comparable<T>> void sort(T[] arr, int size) {

        for (int i = 0; i < size - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}