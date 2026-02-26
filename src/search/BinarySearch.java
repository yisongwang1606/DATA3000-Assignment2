package search;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(T[] arr, int size, T target) {
        return searchRecursive(arr, target, 0, size - 1);
    }

    private static <T extends Comparable<T>> int searchRecursive(T[] arr,
                                                                 T target,
                                                                 int low,
                                                                 int high) {

        if (low > high)
            return -1;

        int mid = (low + high) / 2;

        int cmp = arr[mid].compareTo(target);

        if (cmp == 0) {

            while (mid > 0 && arr[mid - 1].compareTo(target) == 0) {
                mid--;
            }

            return mid;
        }

        if (cmp > 0)
            return searchRecursive(arr, target, low, mid - 1);
        else
            return searchRecursive(arr, target, mid + 1, high);
    }
}
