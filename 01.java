import java.util.*;

public class SetCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Set Calculator Menu:");
            System.out.println("1. Union");
            System.out.println("2. Intersection");
            System.out.println("3. Power Set");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    performUnion();
                    break;
                case 2:
                    performIntersection();
                    break;
                case 3:
                    performPowerSet();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int[] readSet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the set: ");
        int n = scanner.nextInt();

        int[] set = new int[n];

        System.out.print("Enter the elements separated by spaces: ");
        for (int i = 0; i < n; i++) {
            set[i] = scanner.nextInt();
        }

        return set;
    }

    private static void performUnion() {
        int[] set1 = readSet();
        int[] set2 = readSet();

        int[] union = new int[set1.length + set2.length];
        int index = 0;

        for (int num : set1) {
            union[index++] = num;
        }

        for (int num : set2) {
            if (!contains(union, num)) {
                union[index++] = num;
            }
        }

        System.out.print("Union of the sets: ");
        printSet(union, index);
    }

    private static void performIntersection() {
        int[] set1 = readSet();
        int[] set2 = readSet();

        int[] intersection = new int[Math.min(set1.length, set2.length)];
        int index = 0;

        for (int num : set1) {
            if (contains(set2, num)) {
                intersection[index++] = num;
            }
        }

        System.out.print("Intersection of the sets: ");
        printSet(intersection, index);
    }

    private static void performPowerSet() {
        int[] set = readSet();
        int n = set.length;

        int totalSubsets = 1 << n;
        System.out.println("Power Set of the set:");

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(set[i]);
                }
            }
            System.out.println(subset);
        }
    }

    private static boolean contains(int[] set, int num) {
        for (int i : set) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    private static void printSet(int[] set, int size) {
        System.out.print("{");
        for (int i = 0; i < size; i++) {
            System.out.print(set[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
}
