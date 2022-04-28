import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {
    public static int timesCompared = 0;
    public static int timesSwapped = 0;

    public static void insertionSort(int[] array) {
        for (int j = 1; j < array.length; j++) {
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
            if (array.length < 50){
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
        System.out.println(amountOfNumbers);
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

        insertionSort(array);

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
