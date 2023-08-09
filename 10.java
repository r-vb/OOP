import java.io.*;
import java.util.*;

public class PrimeNumberChecker {

    public static void main(String[] args) {
        String fileName = "numbers.txt";
        int countPrimes = 0;

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < 1000; i++) {
                int number = getRandomNumber();
                boolean isPrime = isPrimeNumber(number);

                bufferedWriter.write(number + " " + isPrime);
                bufferedWriter.newLine();

                if (isPrime) {
                    countPrimes++;
                }
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        System.out.println("File generation and prime check completed.");

        System.out.println("Number of prime numbers in the file: " + countPrimes);
    }

    private static int getRandomNumber() {
        return new Random().nextInt(1000000);
    }

    private static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
