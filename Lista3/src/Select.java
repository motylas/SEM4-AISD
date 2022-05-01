import java.io.IOException;
import java.util.Arrays;

public class Select {
    static int timesCompared = 0;
    static int timesSwapped = 0;

    public static int select(int[] array,int p,int q,int i){
        int index=4;
        do{
            if (index > q) index = q;
            insertionSort(array, p, index);
        } while(index < q);
        return -1;
    }

    static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }

    static void insertionSort(int[] array, int p, int q){
        for (int j = p+1; j < q+1; j++) {
            int key = array[j];
            int i = j - 1;
            while (i > p-1 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {20,10,60,30,25,37,22,14};
    }

//    public static void main(String[] args) throws IOException {
//        String response = "";
//        int digit;
//        while (true) {
//            if ((digit = System.in.read()) != 120) {
//                response += Integer.toString(Character.getNumericValue(digit));
//            } else {
//                break;
//            }
//        }
//        int amountOfNumbers = Integer.parseInt(response);
//        System.out.println(amountOfNumbers);
//        int[] array = new int[amountOfNumbers];
//        for (int i = 0; i < amountOfNumbers; i++) {
//            response = "";
//            while (true) {
//                if ((digit = System.in.read()) != 120) {
//                    response += Integer.toString(Character.getNumericValue(digit));
//                } else {
//                    break;
//                }
//            }
//            array[i] = Integer.parseInt(response);
//        }
//
//        if (amountOfNumbers < 50) {
//            System.out.println(Arrays.toString(array));
//        }
//
//        if (amountOfNumbers < 50) {
//            System.out.println(Arrays.toString(array));
//        }
////        System.out.println("Times Compared: " + timesCompared);
////        System.out.println("Times Swapped: " + timesSwapped);
//
//        int lastNumber = array[0];
//        for (int i = 1; i < amountOfNumbers; i++) {
//            if (lastNumber > array[i]) {
//                System.out.println("Not sorted!");
//                return;
//            }
//            lastNumber = array[i];
//        }
//        System.out.println("Sorted!");
//    }
}
