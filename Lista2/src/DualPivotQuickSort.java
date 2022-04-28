import java.io.IOException;
import java.util.Arrays;

public class DualPivotQuickSort {
    public static int timesSwapped = 0;
    public static int timesCompared = 0;

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        timesSwapped++;
    }

    static void dualPivotQuickSort(int[] array, int p, int q) {
        if (p < q) {
            int[] pivots = partition(array, p, q);
            if(array.length < 50){
                System.out.println("Pivots: " + pivots[0] + ", " + pivots[1]);
                System.out.println("Array before recursion: " + Arrays.toString(array));
            }
            dualPivotQuickSort(array, p, pivots[0] - 1);
            dualPivotQuickSort(array, pivots[0] + 1, pivots[1] - 1);
            dualPivotQuickSort(array, pivots[1] + 1, q);
        }
    }

    static int[] partition(int[] array, int p, int q) {
        timesCompared++;
        if (array[p] > array[q]){
            swap(array, p, q);
        }
        int leftPivot = array[p];
        int rightPivot = array[q];
        int i = p + 1;
        int j = q - 1;
        int x = p + 1;

        boolean ifFlag = false;
        while (i <= j) {
            timesCompared++;
            if (array[i] < leftPivot) {
                ifFlag=true;
                swap(array, i, x);
                x++;
            }
            else if (array[i] >= rightPivot) {
                timesCompared++;
                while (array[j] > rightPivot && i < j){
                    j--;
                    timesCompared++;
                }
                swap(array, i, j);
                j--;
                timesCompared++;
                if (array[i] < leftPivot) {
                    swap(array, i, x);
                    x++;
                }
            }
            if (!ifFlag) timesCompared++;
            i++;
        }
        x--;
        j++;

        swap(array, p, x);
        swap(array, q, j);

        return new int[]{x, j};
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

        dualPivotQuickSort(array, 0, amountOfNumbers - 1);

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
