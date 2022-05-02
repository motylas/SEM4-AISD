import java.util.Arrays;

public class BinarySearch {
    public static boolean binarySearch(int[] array, int p, int q, int numberToFind){
        int middleIndex = (q-p)/2+p;
        int middleNumber = array[middleIndex];
        if (numberToFind == middleNumber) return true;
        if (p==q) return false;
        if (numberToFind < middleNumber) return binarySearch(array,p,middleIndex, numberToFind);
        return binarySearch(array,middleIndex+1,q,numberToFind);
    }

    public static void main(String[] args) {
        int[] array = RandomNumbersGenerator.numberGenerator(1000);
        MergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));
        int x = 3000;
        if(binarySearch(array,0,array.length-1,x)) System.out.println("JEST");
        else System.out.println("NIE JEST");
    }
}