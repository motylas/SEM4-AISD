import java.io.IOException;
import java.util.Arrays;

public class MergeSort {
    static int amountOfNumbers = 0;
    public static int timesCompared = 0;
    public static int timesSwapped = 0;

    public static void mergeSort(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        } else {
            int mid = n / 2;
            int[] leftArray = new int[mid];
            int[] rightArray = new int[n - mid];

            System.arraycopy(array, 0, leftArray, 0, mid);
            for (int i = mid; i < n; i++) {
                rightArray[i - mid] = array[i];
            }
            mergeSort(leftArray);
            mergeSort(rightArray);
            merge(array, leftArray, rightArray);
        }

    }

    static void merge(int[] array, int[] leftArray, int[] rightArray) {
        int l = 0, r = 0;
        for (int i = 0; i < array.length; i++) {
            timesCompared++;
            //timesSwapped++; not a swap exactly
            if (l < leftArray.length && (r == rightArray.length || leftArray[l] < rightArray[r])) {
                array[i] = leftArray[l];
                l++;
            } else {
                array[i] = rightArray[r];
                r++;
            }
        }

        if (amountOfNumbers < 50 && amountOfNumbers != 0) {
//            //System.out.println("Left Array: " + Arrays.toString(leftArray));
//            //System.out.println("Right Array: " + Arrays.toString(rightArray));
            //System.out.println("Merge:" + Arrays.toString(array));
        }
    }

//    public static void main(String[] args) throws IOException {
//        ////System.out.println(Arrays.toString(array));
//        mergeSort(array);
//        mergeSort(array2);
//        ////System.out.println(Arrays.toString(array));
//
//        String[] strArray = new String[array.length];
//        String[] strArray2 = new String[array.length];
//
//        for (int i = 0; i < array.length; i++) {
//            strArray[i] = String.valueOf(array[i]);
//            strArray2[i] = String.valueOf(array2[i]);
//        }
//
//        //System.out.println(Arrays.toString(strArray));
//        //System.out.println(Arrays.toString(strArray2));
//
//
//        List<String[]> dataLines = new ArrayList<>();
//        dataLines.add(strArray);
//        dataLines.add(strArray2);
//
//
//    }
    public static void main(String[] args) throws IOException {
        String response="";
        int digit;
        while(true){
            if ((digit = System.in.read()) != 120){
                response+=Integer.toString(Character.getNumericValue(digit));
            }
            else{
                break;
            }
        }
        amountOfNumbers = Integer.parseInt(response);
        int[] array = new int[amountOfNumbers];
        for(int i=0; i<amountOfNumbers;i++){
            response="";
            while(true){
                if ((digit = System.in.read()) != 120){
                    response+=Integer.toString(Character.getNumericValue(digit));
                }
                else{
                    break;
                }
            }
            array[i] = Integer.parseInt(response);
        }

        if (amountOfNumbers < 50){
            //System.out.println(Arrays.toString(array));
        }

        mergeSort(array);

        if (amountOfNumbers < 50){
            //System.out.println(Arrays.toString(array));
        }
        //System.out.println("Times Compared: " + timesCompared);
        //System.out.println("Times Swapped: " + timesSwapped);

        int lastNumber = array[0];
        for (int i = 1; i<amountOfNumbers;i++){
            if (lastNumber > array[i]){
                //System.out.println("Not sorted!");
                return;
            }
            lastNumber = array[i];
        }
        //System.out.println("Sorted!");
    }
}
