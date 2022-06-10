import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HeapSortList {
    public static long timesCompared = 0;
    public static long timesSwapped = 0;

    static public void heapSort(ArrayList<Integer> array) {
        int n = array.size();
        createMaxHeap(array, n);
        for (int i = n - 1; i > 0; i--) {
            int temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i,temp);
            timesSwapped++;
            heapify(array, i, 0);
        }
    }

    static private void createMaxHeap(ArrayList<Integer> array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
    }

    static private void heapify(ArrayList<Integer> array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array.get(l) > array.get(largest))
            largest = l;
        if (r < n && array.get(r) > array.get(largest))
            largest = r;
        timesCompared+=2;

        if (largest != i) {
            int temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            timesSwapped++;
            heapify(array, n, largest);
        }
    }

    static public void insert(ArrayList<Integer> array, int key){
        array.add(key);
        int i = array.size() - 1;
        int parent = (i-1)/2;
        timesCompared++;
        while (i > 0 && array.get(parent) < key){
            array.set(i, array.get(parent));
            timesSwapped++;
            i = parent;
            parent = (i-1)/2;
            timesCompared++;
        }
        array.set(i, key);
        timesSwapped++;
    }

    static public int extractMax(ArrayList<Integer> array){
        int n = array.size();
        if (n < 1){
            System.out.println("Empty Heap!");
            return -1;
        }
        int max = array.get(0);
        array.set(0, array.get(n-1));
        timesSwapped++;
        array.remove(n-1);
        heapify(array, n-1, 0);
        return max;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            insert(list, i);
        System.out.println(timesCompared);
        System.out.println(timesSwapped);

    }

    private static void printHeap(ArrayList<Integer> array) {
        for(int i=0;i<5;i++){
            for(int j=0;j<Math.pow(2,i)&&j+Math.pow(2,i)<10;j++){
                System.out.print(array.get(j+(int)Math.pow(2,i)-1)+" ");

            }
            System.out.println();
        }
    }
}