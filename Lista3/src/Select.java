public class Select {
    static int timesCompared = 0;
    static int timesSwapped = 0;
//
//    static void swap(int[] array, int p, int q){
//        int temp = array[p];
//        array[p] = array[q];
//        array[q] = temp;
//    }
//
//    public static int select(int[] array,int p,int q,int i, int subarraysCapacity){
//        if (array.length == 1 && i == 0) return array[0];
//        int right = Math.min(subarraysCapacity - 1, q);
//        int left = p;
//        int[] medians = new int[(q-p+1 + subarraysCapacity - 1) / subarraysCapacity];
//        int index=0;
//        do{
//            insertionSort(array, left, right);
//            medians[index] = array[(right-left)/2 + left];
//            index++;
//            left+=subarraysCapacity;
//            right+=subarraysCapacity;
//            if (right > q && left <= q) right = q;
//        } while(right <= q);
//        System.out.println("Array before recurension: " + Arrays.toString(medians));
//        int medianOfMedians = select(medians, 0, medians.length-1,medians.length/2,subarraysCapacity);
//        System.out.println("Returned median of medians: " + medianOfMedians);
//        //int r = partition(array,p,q, medianOfMedians);
//        for (int j = p; j <= q; j++){
//            if (array[j] == medianOfMedians){
//                System.out.println("IN PARTITION MEDIAN: " + medianOfMedians + "     J: " + j);
//                swap(array, j, p);
//                break;
//            }
//        }
//        int store = 0;
//        for (int j = p; j < q; j++){
//            if (array[j] < medianOfMedians) swap(array, j, store++);
//        }
//        swap(array, store, q);
//
////        int k = r-p+1;
//        if (i==store) return medianOfMedians;
//        if (store > i) return select(array,p,store,i,subarraysCapacity);
//        else return select(array,store + 1 ,q,i-store-1,subarraysCapacity);
//    }
//
    static int partition(int[] arr, int low, int high, int medianOfMedians) {
        for (int i = low; i < high; i++) {
            if (arr[i] == medianOfMedians) {
                swap(arr, i, high);
                break;
            }
        }
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }

    static void insertionSort(int[] array, int p, int q) {
        if (array.length <= q) q = array.length - 1;
        for (int j = p + 1; j < q + 1; j++) {
            int key = array[j];
            int i = j - 1;
            while (i > p - 1 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
    }


    //    public static void main(String[] args) {
//        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
//        //int[] array = Lista2.SortedNumbersGenerator.sortedNumberGenerator(100);
//        System.out.println("Array generated: " + Arrays.toString(array));
//        System.out.println(select(array, 0, array.length - 1, 4,5));
//    }
    static int findMedian(int arr[], int i, int n) {
        for (int y = i; y <= n; y++) {
//            System.out.println("MEDIANS: ");
        }
        insertionSort(arr, i, n);
//        System.out.println(arr[i+(n-i)/2]);
        return arr[i + (n - i) / 2];                    // sort the array and return middle element
    }


    // Returns k'th smallest element
// in arr[l..r] in worst case
// linear time. ASSUMPTION: ALL
// ELEMENTS IN ARR[] ARE DISTINCT
    static int kthSmallest(int arr[], int l, int r, int k, int subArrayCapacity) {
        // If k is smaller than
        // number of elements in array
        if (k > 0 && k <= r - l + 1) {
            int n = r - l + 1; // Number of elements in arr[l..r]

            // Divide arr[] in groups of size 5,
            // calculate median of every group
            //  and store it in median[] array.
            int i;

            // There will be floor((n+4)/5) groups;
            int[] median = new int[(n + subArrayCapacity - 1) / subArrayCapacity];
            for (i = 0; i < n / 5; i++)
                median[i] = findMedian(arr, l + i * subArrayCapacity, l + i * subArrayCapacity + subArrayCapacity);

            // For last group with less than 5 elements
            if (i * 5 < n) {
                median[i] = findMedian(arr, l + i * 5, l + i * 5 + n % 5);
                i++;
            }

            // Find median of all medians using recursive call.
            // If median[] has only one element, then no need
            // of recursive call
            int medOfMed = (i == 1) ? median[i - 1] :
                    kthSmallest(median, 0, i - 1, i / 2, subArrayCapacity);

            // Partition the array around a random element and
            // get position of pivot element in sorted array
            int pos = partition(arr, l, r, medOfMed);

            // If position is same as k
            if (pos - l == k - 1)
                return arr[pos];
            if (pos - l > k - 1) // If position is more, recur for left
                return kthSmallest(arr, l, pos - 1, k, subArrayCapacity);

            // Else recur for right subarray
            return kthSmallest(arr, pos + 1, r, k - pos + l - 1, subArrayCapacity);
        }

        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Driver code
    public static void main(String[] args) {
        int arr[] = RandomNumbersGenerator.numberGenerator(100);
        int n = arr.length, k = 7;
        System.out.println("K'th smallest element is "
                + kthSmallest(arr, 0, n - 1, k, 5));
        MergeSort.mergeSort(arr);
        System.out.println(arr[k-1]);
    }
}