import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class IntegerSortingApp {
    public static void main(String[] args) {
        int n = 100; // Change this value to the desired number of random integers
        int numThreads = 3;
        
        // Step 1: Create a file named "integers.txt" and insert large random integers into it.
        createRandomIntegersFile("integers.txt", n);
        
        // Step 2: Create three threads T1, T2, and T3 to read n/3 integers from the file and sort them into individual arrays.
        List<Integer>[] sortedArrays = new ArrayList[numThreads];
        Thread[] threads = new Thread[numThreads];
        
        try (BufferedReader reader = new BufferedReader(new FileReader("integers.txt"))) {
            for (int i = 0; i < numThreads; i++) {
                sortedArrays[i] = new ArrayList<>();
                threads[i] = new Thread(new SortingTask(reader, sortedArrays[i], n / numThreads));
                threads[i].start();
            }
            
            // Step 3: Wait for all the threads T1, T2, and T3 to complete sorting.
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        // Step 4: Merge the sorted arrays and write the output to "sorted_integers.txt".
        List<Integer> mergedList = mergeSortedArrays(sortedArrays);
        writeSortedIntegers("sorted_integers.txt", mergedList);
        
        System.out.println("Sorting completed and the output is written to 'sorted_integers.txt'.");
    }
    
    // Helper method to create a file and insert large random integers into it.
    private static void createRandomIntegersFile(String filename, int n) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                writer.println(random.nextInt(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to merge sorted arrays.
    private static List<Integer> mergeSortedArrays(List<Integer>[] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        for (int i = 0; i < arrays.length; i++) {
            if (!arrays[i].isEmpty()) {
                minHeap.offer(new Element(i, 0, arrays[i].get(0)));
            }
        }

        while (!minHeap.isEmpty()) {
            Element min = minHeap.poll();
            result.add(min.value);
            
            if (min.index + 1 < arrays[min.arrayIndex].size()) {
                minHeap.offer(new Element(min.arrayIndex, min.index + 1, arrays[min.arrayIndex].get(min.index + 1)));
            }
        }

        return result;
    }
    
    // Helper method to write sorted integers to a file.
    private static void writeSortedIntegers(String filename, List<Integer> sortedList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : sortedList) {
                writer.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Sorting task for individual threads.
    private static class SortingTask implements Runnable {
        private BufferedReader reader;
        private List<Integer> sortedArray;
        private int count;

        public SortingTask(BufferedReader reader, List<Integer> sortedArray, int count) {
            this.reader = reader;
            this.sortedArray = sortedArray;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                String line;
                int numCount = 0;
                while ((line = reader.readLine()) != null && numCount < count) {
                    int num = Integer.parseInt(line);
                    sortedArray.add(num);
                    numCount++;
                }
                Collections.sort(sortedArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Helper class for merging sorted arrays.
    private static class Element {
        int arrayIndex;
        int index;
        int value;

        public Element(int arrayIndex, int index, int value) {
            this.arrayIndex = arrayIndex;
            this.index = index;
            this.value = value;
        }
    }
}
