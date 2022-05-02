import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Select {
    static int timesCompared = 0;
    static int timesSwapped = 0;
    static int subArraysSize = 5;

    static int partition(int[] arr, int low, int high, int medianOfMedians) {
        for (int i = low; i < high; i++) {
            timesCompared++;
            if (arr[i] == medianOfMedians) {
                swap(arr, i, high);
                break;
            }
        }
        int pivot = arr[high];
        if (arr.length <= 50) System.out.println("Pivot: " + pivot);
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            timesCompared++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, j, i);
            }
        }
        swap(arr,i+1,high);
        return (i + 1);
    }

    static void insertionSort(int[] array, int p, int q) {
        if (array.length <= q) q = array.length - 1;
        for (int j = p + 1; j < q + 1; j++) {
            int key = array[j];
            int i = j - 1;
            timesCompared++;
            while (i > p - 1 && array[i] > key) {
                timesCompared++;
                array[i + 1] = array[i];
                timesSwapped++;
                i--;
            }
            array[i + 1] = key;
            timesSwapped++;
        }
    }


    static int findMedian(int[] arr, int i, int n) {
        insertionSort(arr, i, n);
        return arr[i + (n - i) / 2];
    }

    static int select(int arr[], int l, int r, int k, int subArrayCapacity) {
        if (k > 0 && k <= r - l + 1) {
            int n = r - l + 1;
            int i;
            int[] median = new int[(n + subArrayCapacity - 1) / subArrayCapacity];
            for (i = 0; i < n / subArrayCapacity; i++)
                median[i] = findMedian(arr, l + i * subArrayCapacity, l + i * subArrayCapacity + subArrayCapacity);
            if (i * subArrayCapacity < n) {
                median[i] = findMedian(arr, l + i * subArrayCapacity, l + i * subArrayCapacity + n % subArrayCapacity);
                i++;
            }
            int medOfMed = (i == 1) ? median[i - 1] :
                    select(median, 0, i - 1, i / 2, subArrayCapacity);
            if(arr.length <= 50) System.out.println("Array before partition: " + Arrays.toString(arr));
            int pos = partition(arr, l, r, medOfMed);
            if (arr.length <= 50) System.out.println("Array after partition: " + Arrays.toString(arr));
            if (pos - l == k - 1)
                return arr[pos];
            if (pos - l > k - 1)
                return select(arr, l, pos - 1, k, subArrayCapacity);
            return select(arr, pos + 1, r, k - pos + l - 1, subArrayCapacity);
        }
        return -1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        timesSwapped++;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1 ){
            System.out.println("Please provide amount of numbers to generate!");
            return;
        }
        int kth;
        try{
            kth = Integer.parseInt(args[0]);
        } catch (Exception e ){
            System.out.println("Wrong argument!");
            return;
        }
        String response = "";
        int digit;
        while (true) {
            if ((digit = System.in.read()) != 120) {
                response += Integer.toString(Character.getNumericValue(digit));
            } else {
                break;
            }
        }
        int amountOfNumbers = Integer.parseInt(response);
        int[] array = new int[amountOfNumbers];
        for (int i = 0; i < amountOfNumbers; i++) {
            response = "";
            while (true) {
                if ((digit = System.in.read()) != 120) {
                    response += Integer.toString(Character.getNumericValue(digit));
                } else {
                    break;
                }
            }
            array[i] = Integer.parseInt(response);
        }

        if (amountOfNumbers < 50) {
            System.out.println("Array before randomSelect: " + Arrays.toString(array));
        }

        int result = select(array,0,amountOfNumbers-1,kth, subArraysSize);
        System.out.println("Kth element is: " + result);

        if (amountOfNumbers < 50) {
            System.out.println("Array after randomSelect: " + Arrays.toString(array));
        }
        System.out.println("Times Compared: " + timesCompared);
        System.out.println("Times Swapped: " + timesSwapped);

        MergeSort.mergeSort(array);
        if (array[kth-1] == result) System.out.println("WORKED");
        else System.out.println("DIDNT WORKED");
    }
}