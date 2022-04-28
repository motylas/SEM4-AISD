import java.io.IOException;
import java.util.Arrays;

public class RandomSelect {
    static int timesCompared = 0;
    static int timesSwapped = 0;

    public static int randomSelect(int[] array,int p,int q,int i){
        if (p==q) return array[p];
//        System.out.println("Array before partition " + Arrays.toString(array));
        int r = partition(array, p, q);
//        System.out.println("R: " + r);
//        System.out.println("Array[r] " + array[r]);
//        System.out.println("Array after partition " + Arrays.toString(array));
        int k = r - p+1;
        if (k==i) return array[r];
        if (i<k) return randomSelect(array,p,r-1,i);
        else return randomSelect(array,r+1,q,i-k);
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

    public static void main(String[] args) {
//        int[] array = {10,20,30,40,50,60,70,80};
        int[] array = {40,40,60,40,70,20,80,10,4,6,8,2,4,1,6,32,12,52};
        for (int i = 1; i<=array.length; i++){
            System.out.println("START for i: " + i);
            System.out.println(randomSelect(array,0,array.length-1,i));
            System.out.println("END for i: " + i);
        }
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
