import java.io.IOException;
import java.util.Arrays;

public class QuickSort {
    public static int timesCompared = 0;
    public static int timesSwapped = 0;

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        timesSwapped++;
    }
    public static void quickSort(int[] array, int p, int q) {
        if (p < q) {
            //System.out.println("Array before: " + Arrays.toString(array));
            int pivot = partition(array,p,q);
            //System.out.println("x: " + pivot);
            if (array.length < 50) {
                ////System.out.println("Pivot: " + pivot);
                //System.out.println("Array before recursion: " + Arrays.toString(array));
            }
            quickSort(array, p, pivot);
            quickSort(array, pivot+1, q);
        }
    }

    public static int partition(int[] array, int p, int q) {
        int middleNumber = Select.select(array, p, q, (q-p)/2 + p,5);
        for (int i = p; i < q; i++) {
            timesCompared++;
            if (array[i] == middleNumber) {
                swap(array, i, p);
                break;
            }
        }
        int pivot = array[p];
        //System.out.println("Pivot: " + array[p]);
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
            //System.out.println(Arrays.toString(array));
        }

        quickSort(array, 0, amountOfNumbers - 1);

        if (amountOfNumbers < 50) {
            //System.out.println(Arrays.toString(array));
        }
        //System.out.println("Times Compared: " + timesCompared);
        //System.out.println("Times Swapped: " + timesSwapped);

        int lastNumber = array[0];
        for (int i = 1; i < amountOfNumbers; i++) {
            if (lastNumber > array[i]) {
                //System.out.println("Not sorted!");
                return;
            }
            lastNumber = array[i];
        }
        //System.out.println("Sorted!");
    }
}
