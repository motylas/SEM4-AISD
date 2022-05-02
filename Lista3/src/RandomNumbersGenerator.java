import java.io.IOException;
import java.util.Random;

public class RandomNumbersGenerator {

    public static int[] numberGenerator(int amount){
        int[] array = new int[amount];
        Random r = new Random();
        int high = 2*amount;
        for(int i = 0; i<amount; i++){
            array[i] = r.nextInt(high);
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1 ){
            //System.out.println("Please provide amount of numbers to generate!");
            return;
        }
        int amountOfNumbers;
        try{
            amountOfNumbers = Integer.parseInt(args[0]);
        } catch (Exception e ){
            //System.out.println("Wrong argument!");
            return;
        }
        int[] generatedNumbers = numberGenerator(amountOfNumbers);
        System.out.print(amountOfNumbers);
        System.out.print("x");
        for(int i = 0; i<amountOfNumbers;i++){
            System.out.print(generatedNumbers[i]);
            System.out.print("x");
        }
    }

}
