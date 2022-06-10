import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateDataLCS {
    static int numberOfTests = 100;
    static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        List<String[]> dataOutput = new ArrayList<>();
        dataOutput.add(new String[]
                {"Algorithm", "N: 1000", "N: 2000", "N: 3000", "N: 4000",
                        "N: 5000"});
        List<String[]> meanValues = new ArrayList<>();
        String[] timesCompared = new String[6];
        String[] timesSwapped = new String[6];
        meanValues.add(timesCompared);
        meanValues.add(timesSwapped);

        timesSwapped[0] = "Mean Swaps";
        timesCompared[0] = "Mean Comparisons";

        long swaps = 0;
        long comparisons = 0;
        long temp = 0;

        for (int test = 0; test < numberOfTests; test++) {
            for (int n = 1000; n <= 5000; n += 1000) {
                StringBuilder X = new StringBuilder();
                StringBuilder Y = new StringBuilder();
                LongestCommonSubsequence.timesCompared = 0;
                LongestCommonSubsequence.timesSwapped = 0;
                for (int i = 0; i < n; i++) {
                    X.append((char) rand.nextInt(256));
                    Y.append((char) rand.nextInt(256));
                }
                LongestCommonSubsequence.lcs(X.toString(), Y.toString(), n, n);
                swaps = LongestCommonSubsequence.timesSwapped;
                comparisons = LongestCommonSubsequence.timesCompared;


                // Insert data
                if (timesSwapped[n / 1000] == null) temp = 0;
                else temp = Long.parseLong(timesSwapped[n / 1000]);
                temp += swaps;
                timesSwapped[n / 1000] = String.valueOf(temp);
                if (timesCompared[n / 1000] == null) temp = 0;
                else temp = Long.parseLong(timesCompared[n / 1000]);
                temp += comparisons;
                timesCompared[n / 1000] = String.valueOf(temp);

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

        String[] sn = new String[6];
        String[] snn = new String[6];
        String[] snnn = new String[6];
        String[] s2n = new String[6];
        String[] cn = new String[6];
        String[] cnn = new String[6];
        String[] cnnn = new String[6];
        String[] c2n = new String[6];
        sn[0] = "Swaps / N";
        snn[0] = "Swaps / N^2";
        snnn[0] = "Swaps / N^3";
        s2n[0] = "Swaps / 2^N";
        cn[0] = "Comparisons / N";
        cnn[0] = "Comparisons / N^2";
        cnnn[0] = "Comparisons / N^3";
        c2n[0] = "Comparisons / 2^N";

        for (int i = 1000; i <= 5000; i += 1000) {
            float swapTemp = Float.parseFloat(timesSwapped[i / 1000]);
            float comparisonsTemp = Float.parseFloat(timesCompared[i / 1000]);
            sn[i / 1000] = String.valueOf(swapTemp / (float) i);
            snn[i / 1000] = String.valueOf(swapTemp / (float) (i*i));
            snnn[i / 1000] = String.valueOf(swapTemp / (float) (i*i*i));
            s2n[i / 1000] = String.valueOf(swapTemp / (float) Math.pow(2,i));
            cn[i / 1000] = String.valueOf(comparisonsTemp / (float) i);
            cnn[i / 1000] = String.valueOf(comparisonsTemp / (float) (i*i));
            cnnn[i / 1000] = String.valueOf(comparisonsTemp / (float) (i*i*i));
            c2n[i / 1000] = String.valueOf(comparisonsTemp / (float) Math.pow(2,i));
        }
        dataOutput.add(sn);
        dataOutput.add(cn);
        dataOutput.add(snn);
        dataOutput.add(cnn);
        dataOutput.add(snnn);
        dataOutput.add(cnnn);
        dataOutput.add(s2n);
        dataOutput.add(c2n);

    givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "Algorithms for k = ",numberOfTests);
}

    static public void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines, String fileName, int k) throws IOException {
        File csvOutputFile = new File(fileName + k + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(GenerateDataLCS::convertToCSV)
                    .forEach(pw::println);
        }
    }

    static public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(GenerateDataLCS::escapeSpecialCharacters)
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
