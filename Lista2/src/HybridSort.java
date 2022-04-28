import java.io.IOException;
import java.util.Arrays;

public class HybridSort {
    static int timesCompared = 0;
    static int timesSwapped = 0;

    public static void hybridSort(int[] array, int p, int q, int swap) {
        if (p < q) {
            if (q - p < swap) {
                insertionSort(array, p, q);
            } else {
                int pivot = partition(array, p, q);
                if (array.length < 50) {
                    //System.out.println("Pivot: " + pivot);
                    System.out.println("Array before recursion: " + Arrays.toString(array));
                }
                hybridSort(array, p, pivot, swap);
                hybridSort(array, pivot + 1, q, swap);
            }
        }
    }

    public static int partition(int[] array, int p, int q) {
        int pivot = array[p];
        int i = p - 1;
        int j = q + 1;

        while (true) {
            do {
                i++;
                timesCompared++;
            } while (array[i] < pivot);

            do {
                j--;
                timesCompared++;
            } while (array[j] > pivot);

            if (i >= j) {
                return j;
            }
            int x = array[i];
            array[i] = array[j];
            array[j] = x;
            timesSwapped++;
        }
    }

    static void insertionSort(int[] array, int p, int q) {
        for (int j = p + 1; j <= q; j++) {
            int key = array[j];
            int i = j - 1;
            timesCompared++;
            while (i > -1 && array[i] > key) {
                timesCompared++;
                array[i + 1] = array[i];
                timesSwapped++;
                i--;
            }
            array[i + 1] = key;
            timesSwapped++;
            if (array.length < 50) {
                System.out.println(Arrays.toString(array));
            }
        }
    }

    public static void main(String[] args) throws IOException {
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
            System.out.println(Arrays.toString(array));
        }

        hybridSort(array, 0, amountOfNumbers - 1, 10);

        if (amountOfNumbers < 50) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println("Times Compared: " + timesCompared);
        System.out.println("Times Swapped: " + timesSwapped);

        int lastNumber = array[0];
        for (int i = 1; i < amountOfNumbers; i++) {
            if (lastNumber > array[i]) {
                System.out.println("Not sorted!");
                return;
            }
            lastNumber = array[i];
        }
        System.out.println("Sorted!");
    }
}