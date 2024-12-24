
import java.util.Scanner;

public class SubarrayFloorMeanCalculator {

    public static void main(String[] args) {
        SubarrayFloorMeanCalculator calculator = new SubarrayFloorMeanCalculator();
        calculator.execute();
    }

    // Main method to handle the flow of the program
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        int[] sizeAndQueryInfo = readIntegerArray(scanner, 2);
        int arraySize = sizeAndQueryInfo[0];
        int queryCount = sizeAndQueryInfo[1];

        long[] elements = readLongArray(scanner, arraySize);
        long[] prefixSums = computePrefixSums(elements);
        handleQueries(scanner, queryCount, prefixSums);

        scanner.close();
    }


    private int[] readIntegerArray(Scanner scanner, int size) {
        int[] array = new int[size];
        String[] input = scanner.nextLine().split(" ");
        for (int index = 0; index < size; index++) {
            array[index] = Integer.parseInt(input[index]);
        }
        return array;
    }

    private long[] readLongArray(Scanner scanner, int size) {
        long[] array = new long[size];
        String[] input = scanner.nextLine().split(" ");
        for (int index = 0; index < size; index++) {
            array[index] = Long.parseLong(input[index]);
        }
        return array;
    }

    private long[] computePrefixSums(long[] array) {
        long[] prefixSums = new long[array.length + 1];
        for (int currentIndex = 1; currentIndex <= array.length; currentIndex++) {
            prefixSums[currentIndex] = prefixSums[currentIndex - 1] + array[currentIndex - 1];
        }
        return prefixSums;
    }


    private void handleQueries(Scanner scanner, int queryCount, long[] prefixSums) {
        for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
            int[] queryBounds = readIntegerArray(scanner, 2);
            int startIndex = queryBounds[0];
            int endIndex = queryBounds[1];

            long floorMean = calculateFloorMean(prefixSums, startIndex, endIndex);
            System.out.println(floorMean);
        }
    }


    private long calculateFloorMean(long[] prefixSums, int startIndex, int endIndex) {
        long rangeSum = prefixSums[endIndex] - prefixSums[startIndex - 1];
        int rangeLength = endIndex - startIndex + 1;
        return rangeSum / rangeLength; 
    }
}

