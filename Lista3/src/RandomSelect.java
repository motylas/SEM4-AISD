import java.io.IOException;
import java.util.Arrays;

public class RandomSelect {
    static int timesCompared = 0;
    static int timesSwapped = 0;

    public static int randomSelect(int[] array,int p,int q,int i){
        if (p==q) return array[p];
        if(array.length <= 50) System.out.println("Array before partition " + Arrays.toString(array));
        int r = partition(array, p, q);
        if (array.length <= 50) System.out.println("Array after partition " + Arrays.toString(array));
        int k = r - p+1;
        if (k==i) return array[r];
        if (i<k) return randomSelect(array,p,r-1,i);
        else return randomSelect(array,r+1,q,i-k);
    }

    static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        if (arr.length <= 50) System.out.println("Pivot: " + pivot);
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            timesCompared++;
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

        int result = randomSelect(array,0,amountOfNumbers-1,kth);
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
