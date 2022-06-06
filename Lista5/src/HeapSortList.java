import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HeapSortList {

    public void heapSort(ArrayList<Integer> array) {
        int n = array.size();
        createMaxHeap(array, n);
        for (int i = n - 1; i > 0; i--) {
            int temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i,temp);
            heapify(array, i, 0);
        }
    }

    private void createMaxHeap(ArrayList<Integer> array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
    }

    private void heapify(ArrayList<Integer> array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array.get(l) > array.get(largest))
            largest = l;
        if (r < n && array.get(r) > array.get(largest))
            largest = r;

        if (largest != i) {
            int temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            heapify(array, n, largest);
        }
    }

    public void insert(ArrayList<Integer> array, int key){
        array.add(key);
        int i = array.size() - 1;
        int parent = (i-1)/2;
        while (i > 0 && array.get(parent) < key){
            array.set(i, array.get(parent));
            i = parent;
            parent = (i-1)/2;
        }
        array.set(i, key);
    }

    public int extractMax(ArrayList<Integer> array){
        int n = array.size();
        if (n < 1){
            System.out.println("Empty Heap!");
            return -1;
        }
        int max = array.get(0);
        array.set(0, array.get(n-1));
        array.remove(n-1);
        heapify(array, n-1, 0);
        return max;
    }

    public static void main(String[] args) {
        Integer[] array = {4,2,4,6,7,12,43,5,123,542,2};
//        Integer[] array = {3,5,6};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
//        HeapSortList hs = new HeapSortList();
//        hs.createMaxHeap(list, list.size());
////        hs.heapSort(list);
//        System.out.println(list);
////        hs.insert(list, 13);
////        System.out.println(list);
//        hs.extractMax(list);
//        System.out.println(list);




        printHeap(list);


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