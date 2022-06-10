import java.io.IOException;
import java.util.Arrays;

public class HeapSort {
    public static int timesCompared = 0;
    public static int timesSwapped = 0;

    public static void heapSort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            timesSwapped++;
            heapify(array, i, 0);
        }
    }

    static private void heapify(int[] array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array[l] > array[largest])
            largest = l;
        if (r < n && array[r] > array[largest])
            largest = r;
        timesCompared+=2;

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            timesSwapped++;
            heapify(array, n, largest);
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

        heapSort(array);

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