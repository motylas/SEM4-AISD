import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateData {

    static private final int numberOfTests = 20;
    static private final int scenario = 2;
    static private final Random random = new Random();


    public static void main(String[] args) throws IOException {
        List<String[]> dataOutput = new ArrayList<>();
        dataOutput.add(new String[]
                {"Algorithm", "N: 10 000", "N: 20 000", "N: 30 000", "N: 40 000",
                        "N: 50 000", "N: 60 000", "N: 70 000", "N: 80 000", "N: 90 000", "N: 100 000",});
        List<String[]> meanValues = new ArrayList<>();
        String[] BSTValueComparisons = new String[11];
        String[] RBBSTValueComparisons = new String[11];
        String[] SBSTValueComparisons = new String[11];
        String[] BSTNodesComparisons = new String[11];
        String[] RBBSTNodesComparisons = new String[11];
        String[] SBSTNodesComparisons = new String[11];
        String[] BSTAverageHeight = new String[11];
        String[] RBBSTAverageHeight = new String[11];
        String[] SBSTAverageHeight = new String[11];
        String[] BSTMAXValueComparisons = new String[11];
        String[] RBBSTMAXValueComparisons = new String[11];
        String[] SBSTMAXValueComparisons = new String[11];
        String[] BSTMAXNodesComparisons = new String[11];
        String[] RBBSTMAXNodesComparisons = new String[11];
        String[] SBSTMAXNodesComparisons = new String[11];
        String[] BSTMAXHeight = new String[11];
        String[] RBBSTMAXHeight = new String[11];
        String[] SBSTMAXHeight = new String[11];
        meanValues.add(BSTValueComparisons);
        meanValues.add(RBBSTValueComparisons);
        meanValues.add(SBSTValueComparisons);
        meanValues.add(BSTNodesComparisons);
        meanValues.add(RBBSTNodesComparisons);
        meanValues.add(SBSTNodesComparisons);
        meanValues.add(BSTAverageHeight);
        meanValues.add(RBBSTAverageHeight);
        meanValues.add(SBSTAverageHeight);
        meanValues.add(BSTMAXValueComparisons);
        meanValues.add(RBBSTMAXValueComparisons);
        meanValues.add(SBSTMAXValueComparisons);
        meanValues.add(BSTMAXNodesComparisons);
        meanValues.add(RBBSTMAXNodesComparisons);
        meanValues.add(SBSTMAXNodesComparisons);
        meanValues.add(BSTMAXHeight);
        meanValues.add(RBBSTMAXHeight);
        meanValues.add(SBSTMAXHeight);
        BSTValueComparisons[0] = "BST Keys Comparisons";
        RBBSTValueComparisons[0] = "RBBST Keys Comparisons";
        SBSTValueComparisons[0] = "SBST Keys Comparisons";
        BSTNodesComparisons[0] = "BST Read and Swap Nodes";
        RBBSTNodesComparisons[0] = "RBBST Read and Swap Nodes";
        SBSTNodesComparisons[0] = "SBST Read and Swap Nodes";
        BSTAverageHeight[0] = "BST Average Height";
        RBBSTAverageHeight[0] = "RBBST Average Height";
        SBSTAverageHeight[0] = "SBST Average Height";
        BSTMAXValueComparisons[0] = "BST MAX Keys Comparison";
        RBBSTMAXValueComparisons[0] = "RBBST MAX Keys Comparison";
        SBSTMAXValueComparisons[0] = "SBST MAX Keys Comparison";
        BSTMAXNodesComparisons[0] = "BST MAX Read and Swap Nodes";
        RBBSTMAXNodesComparisons[0] = "RBBST MAX Read and Swap Nodes";
        SBSTMAXNodesComparisons[0] = "SBST MAX Read and Swap Nodes";
        BSTMAXHeight[0] = "BST MAX Height";
        RBBSTMAXHeight[0] = "RBBST MAX Height";
        SBSTMAXHeight[0] = "SBST MAX Height";

        for (int test = 0; test < numberOfTests; test++) {
            for (int n = 10000; n <= 100000; n+=10000) {
                // Calculate Bounds
                int bound = n * 2;

                // Generate Trees
                BST bstTree = new BST();
                RBBST rbbstTree = new RBBST();
                SBST sbstTree = new SBST();

                // Variables for storing data
                long bstValueComparisons = 0;
                long rbbstValueComparisons = 0;
                long sbstValueComparisons = 0;
                long bstReadAndSwapsNodes = 0;
                long rbbstReadAndSwapsNodes = 0;
                long sbstReadAndSwapsNodes = 0;
                long bstAverageHeight = 0;
                long rbbstAverageHeight = 0;
                long sbstAverageHeight = 0;
                int operationsSuccessful = 0;

                // Insertion sorted array
                if (scenario == 1) {
                    // Sorted array generator
                    int[] sortedNumbers = new int[n];
                    for (int i = 0; i < n; i++) sortedNumbers[i] = random.nextInt(bound);
                    Arrays.sort(sortedNumbers);

                    // Insertion
                    for (int i = 0; i < n; i++) {
                        rbbstTree.insert(sortedNumbers[i]);
                        sbstTree.insert(sortedNumbers[i]);
                        if (null != bstTree.insert(sortedNumbers[i])){
                            bstAverageHeight += bstTree.treeHeight();
                            rbbstAverageHeight += rbbstTree.treeHeight();
                            sbstAverageHeight += sbstTree.treeHeight();
                            operationsSuccessful++;
                        }

                        bstValueComparisons += bstTree.getComparisonBetweenValues();
                        rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                        sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                        bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                        rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                        sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();
                    }
                }
                // Insertion random values
                else {
                    for (int i = 0; i < n; i++) {
                        int value = random.nextInt(bound);
                        rbbstTree.insert(value);
                        sbstTree.insert(value);
                        if (null != bstTree.insert(value)){
                            bstAverageHeight += bstTree.treeHeight();
                            rbbstAverageHeight += rbbstTree.treeHeight();
                            sbstAverageHeight += sbstTree.treeHeight();
                            operationsSuccessful++;
                        }

                        bstValueComparisons += bstTree.getComparisonBetweenValues();
                        rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                        sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                        bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                        rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                        sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();

                    }
                }
                // Maximum heights
                long bstMAXHeight = bstTree.treeHeight();
                long rbbstMAXHeight = rbbstTree.treeHeight();
                long sbstMAXHeight = sbstTree.treeHeight();

                // Deletion
                for (int i = 0; i < n; i++) {
                    int value = random.nextInt(bound);
                    bstTree.delete(value);
                    rbbstTree.delete(value);
                    sbstTree.delete(value);
                    if (null != bstTree.delete(value)){
                        bstAverageHeight += bstTree.treeHeight();
                        rbbstAverageHeight += rbbstTree.treeHeight();
                        sbstAverageHeight += sbstTree.treeHeight();
                        operationsSuccessful++;
                    }

                    bstValueComparisons += bstTree.getComparisonBetweenValues();
                    rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                    sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                    bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                    rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                    sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();

                }

                // Store data in arrays
                long temp;

                // Average Keys
                if (BSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTValueComparisons[n / 10000]);
                temp += bstValueComparisons;
                BSTValueComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTValueComparisons[n / 10000]);
                temp += rbbstValueComparisons;
                RBBSTValueComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTValueComparisons[n / 10000]);
                temp += sbstValueComparisons;
                SBSTValueComparisons[n / 10000] = String.valueOf(temp);

                // Average Read and Swap Nodes
                if (BSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTNodesComparisons[n / 10000]);
                temp += bstReadAndSwapsNodes;
                BSTNodesComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTNodesComparisons[n / 10000]);
                temp += rbbstReadAndSwapsNodes;
                RBBSTNodesComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTNodesComparisons[n / 10000]);
                temp += sbstReadAndSwapsNodes;
                SBSTNodesComparisons[n / 10000] = String.valueOf(temp);

                // Average Height
                if (BSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTAverageHeight[n / 10000]);
                temp += (bstAverageHeight / operationsSuccessful);
                BSTAverageHeight[n / 10000] = String.valueOf(temp);
                if (RBBSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTAverageHeight[n / 10000]);
                temp += (rbbstAverageHeight / operationsSuccessful);
                RBBSTAverageHeight[n / 10000] = String.valueOf(temp);
                if (SBSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTAverageHeight[n / 10000]);
                temp += (sbstAverageHeight / operationsSuccessful);
                SBSTAverageHeight[n / 10000] = String.valueOf(temp);

                // Max keys
                if (BSTMAXValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTMAXValueComparisons[n / 10000]);
                temp += bstTree.getMAXcomparisonBetweenValues();
                BSTMAXValueComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTMAXValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTMAXValueComparisons[n / 10000]);
                temp += rbbstTree.getMAXcomparisonBetweenValues();
                RBBSTMAXValueComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTMAXValueComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTMAXValueComparisons[n / 10000]);
                temp += sbstTree.getMAXcomparisonBetweenValues();
                SBSTMAXValueComparisons[n / 10000] = String.valueOf(temp);

                // Max Read and Swaps Nodes
                if (BSTMAXNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTMAXNodesComparisons[n / 10000]);
                temp += bstTree.getMAXreadsAndSwapsOnNodes();
                BSTMAXNodesComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTMAXNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTMAXNodesComparisons[n / 10000]);
                temp += rbbstTree.getMAXreadsAndSwapsOnNodes();
                RBBSTMAXNodesComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTMAXNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTMAXNodesComparisons[n / 10000]);
                temp += sbstTree.getMAXreadsAndSwapsOnNodes();
                SBSTMAXNodesComparisons[n / 10000] = String.valueOf(temp);

                // Max Height
                if (BSTMAXHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(BSTMAXHeight[n / 10000]);
                temp += bstMAXHeight;
                BSTMAXHeight[n / 10000] = String.valueOf(temp);
                if (RBBSTMAXHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(RBBSTMAXHeight[n / 10000]);
                temp += rbbstMAXHeight;
                RBBSTMAXHeight[n / 10000] = String.valueOf(temp);
                if (SBSTMAXHeight[n / 10000] == null) temp = 0;
                else temp = Long.parseLong(SBSTMAXHeight[n / 10000]);
                temp += sbstMAXHeight;
                SBSTMAXHeight[n / 10000] = String.valueOf(temp);

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
        givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "Algorithms for k = ", 20);
    }


    static public void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines, String fileName, int k) throws IOException {
        File csvOutputFile = new File(fileName + k + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(GenerateData::convertToCSV)
                    .forEach(pw::println);
        }
    }

    static public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(GenerateData::escapeSpecialCharacters)
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
