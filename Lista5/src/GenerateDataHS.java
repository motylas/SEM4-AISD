import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateDataHS {
    static int numberOfTests = 100;
    static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        List<String[]> dataOutput = new ArrayList<>();
        dataOutput.add(new String[]
                {"Algorithm", "N: 10 000", "N: 20 000", "N: 30 000", "N: 40 000",
                        "N: 50 000", "N: 60 000", "N: 70 000", "N: 80 000", "N: 90 000", "N: 100 000",});
        List<String[]> meanValues = new ArrayList<>();
        String[] timesSwappedInsert = new String[11];
        String[] timesSwappedExtractMax = new String[11];
        String[] timesComparedInsert = new String[11];
        String[] timesComparedExtractMax = new String[11];
        meanValues.add(timesSwappedInsert);
        meanValues.add(timesSwappedExtractMax);
        meanValues.add(timesComparedInsert);
        meanValues.add(timesComparedExtractMax);

        timesSwappedInsert[0] = "Insert Mean Swaps";
        timesSwappedExtractMax[0] = "ExtractMax Mean Swaps";
        timesComparedInsert[0] = "Insert Mean Comparisons";
        timesComparedExtractMax[0] = "ExtractMax Mean Comparisons";

        long insertSwaps = 0;
        long insertComparisons = 0;
        long extractSwaps = 0;
        long extractComparisons = 0;
        long temp = 0;

        for (int test = 0; test < numberOfTests; test++) {
            for (int n = 10000; n <= 100000; n += 10000) {
                ArrayList<Integer> heap = new ArrayList<>();
                HeapSortList.timesSwapped = 0;
                HeapSortList.timesCompared = 0;
                for (int i = 0; i < n; i++)
                    HeapSortList.insert(heap, rand.nextInt());
                insertSwaps = HeapSortList.timesSwapped;
                insertComparisons = HeapSortList.timesCompared;
                HeapSortList.timesSwapped = 0;
                HeapSortList.timesCompared = 0;
                for (int i = 0; i < n; i++) {
                    HeapSortList.extractMax(heap);
                }
                extractSwaps = HeapSortList.timesSwapped;
                extractComparisons = HeapSortList.timesCompared;

                // Insert data

                // Insertion
                if (timesSwappedInsert[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(timesSwappedInsert[n / 10000]);
                temp += insertSwaps;
                timesSwappedInsert[n / 10000] = String.valueOf(temp);
                if (timesComparedInsert[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(timesComparedInsert[n / 10000]);
                temp += insertComparisons;
                timesComparedInsert[n / 10000] = String.valueOf(temp);

                // Extraction
                if (timesSwappedExtractMax[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(timesSwappedExtractMax[n / 10000]);
                temp += extractSwaps;
                timesSwappedExtractMax[n / 10000] = String.valueOf(temp);
                if (timesComparedExtractMax[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(timesComparedExtractMax[n / 10000]);
                temp += extractComparisons;
                timesComparedExtractMax[n / 10000] = String.valueOf(temp);
            }
        }

        // Get average values from all tests
        for (String[] oneLine : meanValues) {
            for (int i = 1; i < oneLine.length; i++) {
                System.out.println(Arrays.toString(oneLine));
                oneLine[i] = String.valueOf(Float.parseFloat(oneLine[i]) / (float) numberOfTests);
            }
            dataOutput.add(oneLine);
        }

        givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "Algorithms for k = ", numberOfTests);
    }

    static public void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines, String fileName, int k) throws IOException {
        File csvOutputFile = new File(fileName + k + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(GenerateDataHS::convertToCSV)
                    .forEach(pw::println);
        }
    }

    static public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(GenerateDataHS::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    static public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
