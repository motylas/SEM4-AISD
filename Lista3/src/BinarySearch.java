import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearch {
    public static int timesCompared = 0;
    public static boolean binarySearch(int[] array, int p, int q, int numberToFind){
        int middleIndex = (q-p)/2+p;
        int middleNumber = array[middleIndex];
        timesCompared++;
        if (numberToFind == middleNumber) return true;
        if (p==q) return false;
        timesCompared++;
        if (numberToFind < middleNumber) return binarySearch(array,p,middleIndex, numberToFind);
        return binarySearch(array,middleIndex+1,q,numberToFind);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        int v;
        try{
            v = Integer.parseInt(args[0]);
        } catch (Exception e){
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
        Arrays.sort(array);

        if (amountOfNumbers < 50) {
            //System.out.println(Arrays.toString(array));
        }

        if(binarySearch(array, 0, amountOfNumbers - 1, v)) System.out.println("FOUND");
        else System.out.println("NOT FOUND");

        if (amountOfNumbers < 50) {
            //System.out.println(Arrays.toString(array));
        }
        //System.out.println("Times Compared: " + timesCompared);

        int lastNumber = array[0];
        for (int i = 1; i < amountOfNumbers; i++) {
            if (lastNumber > array[i]) {
                //System.out.println("Not sorted!");
                return;
            }
            lastNumber = array[i];
        }
        //System.out.println("Sorted!");
    }
}