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
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return (i + 1);
    }

    static void swap(int[] array, int p, int q){
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }

//    public static void main(String[] args) {
////        int[] array = {10,20,30,40,50,60,70,80};
//        int[] array = Lista2.RandomNumbersGenerator.numberGenerator(100);
//        System.out.println(randomSelect(array, 0, array.length-1, 30));
//        Lista2.MergeSort.mergeSort(array);
//        System.out.println(array[30-1]);
//    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1 ){
            System.out.println("Please provide amount of numbers to generate!");
            return;
        }
        int kth;
        try{
            kth = Integer.parseInt(args[0]);
        } catch (Exception e ){
            //System.out.println("Wrong argument!");
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
//        System.out.println(amountOfNumbers);
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

        int result = randomSelect(array,0,amountOfNumbers-1,kth);
        System.out.println("Kth element is: " + result);

        if (amountOfNumbers < 50) {
            System.out.println(Arrays.toString(array));
        }
//        System.out.println("Times Compared: " + timesCompared);
//        System.out.println("Times Swapped: " + timesSwapped);

        MergeSort.mergeSort(array);
        if (array[kth-1] == result) System.out.println("WORKED");
        else System.out.println("DIDNT WORKED");
    }
}
