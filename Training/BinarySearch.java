
public class BinarySearch {
	
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int binarySearch(int[] a, int num) {
        int l = 0;
        int e = a.length - 1;

        while (l <= e) {
            int m = l + (e - l) / 2;

            if (num > a[m]) {
                l = m + 1;
            } else if (num < a[m]) {
                e = m - 1;
            } else {
                return m;  
            }
        }
        return -1;  
    }

    public static void main(String[] args) {
        int[] a = {42, 7, 19, 3, 25, 14, 9, 33, 2};
        insertionSort(a);
        int num = 19;
        int result = binarySearch(a, num);
        if (result != -1)
            System.out.println("Element " + num + " found at index: " + result);
        else
            System.out.println("Element " + num + " not found.");
    }
}
