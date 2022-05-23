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
    static private final int scenario = 1;
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
        String[] BSTAverageCost = new String[11];
        String[] RBBSTAverageCost = new String[11];
        String[] SBSTAverageCost = new String[11];
        String[] BSTMaxCost = new String[11];
        String[] RBBSTMaxCost = new String[11];
        String[] SBSTMaxCost = new String[11];
        meanValues.add(BSTValueComparisons);
        meanValues.add(RBBSTValueComparisons);
        meanValues.add(SBSTValueComparisons);
        meanValues.add(BSTNodesComparisons);
        meanValues.add(RBBSTNodesComparisons);
        meanValues.add(SBSTNodesComparisons);
        meanValues.add(BSTAverageHeight);
        meanValues.add(RBBSTAverageHeight);
        meanValues.add(SBSTAverageHeight);
        meanValues.add(BSTAverageCost);
        meanValues.add(RBBSTAverageCost);
        meanValues.add(SBSTAverageCost);
        meanValues.add(BSTMaxCost);
        meanValues.add(RBBSTMaxCost);
        meanValues.add(SBSTMaxCost);

        BSTValueComparisons[0] = "BST Keys Comparisons";
        RBBSTValueComparisons[0] = "RBBST Keys Comparisons";
        SBSTValueComparisons[0] = "SBST Keys Comparisons";
        BSTNodesComparisons[0] = "BST Read and Swap Nodes";
        RBBSTNodesComparisons[0] = "RBBST Read and Swap Nodes";
        SBSTNodesComparisons[0] = "SBST Read and Swap Nodes";
        BSTAverageHeight[0] = "BST Average Height";
        RBBSTAverageHeight[0] = "RBBST Average Height";
        SBSTAverageHeight[0] = "SBST Average Height";
        BSTAverageCost[0] = "BST Average Cost For Single Operation";
        RBBSTAverageCost[0] = "RBBST Average Cost For Single Operation";
        SBSTAverageCost[0] = "SBST Average Cost For Single Operation";
        BSTMaxCost[0] = "BST Maximum Cost For Single Operation";
        RBBSTMaxCost[0] = "RBBST Maximum Cost For Single Operation";
        SBSTMaxCost[0] = "SBST Maximum Cost For Single Operation";

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

                // Insertion sorted array
                if (scenario == 1) {
                    // Sorted array generator
                    int[] sortedNumbers = new int[n];
                    for (int i = 0; i < n; i++) sortedNumbers[i] = random.nextInt(bound);
                    Arrays.sort(sortedNumbers);

                    // Insertion
                    for (int i = 0; i < n; i++) {
                        bstTree.insert(sortedNumbers[i]);
                        rbbstTree.insert(sortedNumbers[i]);
                        sbstTree.insert(sortedNumbers[i]);

                        bstValueComparisons += bstTree.getComparisonBetweenValues();
                        rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                        sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                        bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                        rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                        sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();
                        bstAverageHeight += bstTree.treeHeight();
                        rbbstAverageHeight += rbbstTree.treeHeight();
                        sbstAverageHeight += sbstTree.treeHeight();
                    }
                }
                // Insertion random values
                else {
                    for (int i = 0; i < n; i++) {
                        int value = random.nextInt(bound);
                        bstTree.insert(value);
                        rbbstTree.insert(value);
                        sbstTree.insert(value);

                        bstValueComparisons += bstTree.getComparisonBetweenValues();
                        rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                        sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                        bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                        rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                        sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();
                        bstAverageHeight += bstTree.treeHeight();
                        rbbstAverageHeight += rbbstTree.treeHeight();
                        sbstAverageHeight += sbstTree.treeHeight();
                    }
                }

                // Deletion
                for (int i = 0; i < n; i++) {
                    int value = random.nextInt(bound);
                    bstTree.delete(value);
                    rbbstTree.delete(value);
                    sbstTree.delete(value);

                    bstValueComparisons += bstTree.getComparisonBetweenValues();
                    rbbstValueComparisons += rbbstTree.getComparisonBetweenValues();
                    sbstValueComparisons += sbstTree.getComparisonBetweenValues();
                    bstReadAndSwapsNodes += bstTree.getReadsAndSwapsOnNodes();
                    rbbstReadAndSwapsNodes += rbbstTree.getReadsAndSwapsOnNodes();
                    sbstReadAndSwapsNodes += sbstTree.getReadsAndSwapsOnNodes();
                    bstAverageHeight += bstTree.treeHeight();
                    rbbstAverageHeight += rbbstTree.treeHeight();
                    sbstAverageHeight += sbstTree.treeHeight();
                }

                // Store data in arrays
                long temp;

                // Keys
                if (BSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(BSTValueComparisons[n / 10000]);
                temp += bstValueComparisons;
                BSTValueComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(RBBSTValueComparisons[n / 10000]);
                temp += rbbstValueComparisons;
                RBBSTValueComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTValueComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(SBSTValueComparisons[n / 10000]);
                temp += sbstValueComparisons;
                SBSTValueComparisons[n / 10000] = String.valueOf(temp);

                // Read and Swap Nodes
                if (BSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(BSTNodesComparisons[n / 10000]);
                temp += bstReadAndSwapsNodes;
                BSTNodesComparisons[n / 10000] = String.valueOf(temp);
                if (RBBSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(RBBSTNodesComparisons[n / 10000]);
                temp += rbbstReadAndSwapsNodes;
                RBBSTNodesComparisons[n / 10000] = String.valueOf(temp);
                if (SBSTNodesComparisons[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(SBSTNodesComparisons[n / 10000]);
                temp += sbstReadAndSwapsNodes;
                SBSTNodesComparisons[n / 10000] = String.valueOf(temp);

                // Average Height
                if (BSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(BSTAverageHeight[n / 10000]);
                temp += (bstAverageHeight / n);
                BSTAverageHeight[n / 10000] = String.valueOf(temp);
                if (RBBSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(RBBSTAverageHeight[n / 10000]);
                temp += (rbbstAverageHeight / n);
                RBBSTAverageHeight[n / 10000] = String.valueOf(temp);
                if (SBSTAverageHeight[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(SBSTAverageHeight[n / 10000]);
                temp += (sbstAverageHeight / n);
                SBSTAverageHeight[n / 10000] = String.valueOf(temp);

                // Average Cost Of Single Operations
                if (BSTAverageCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(BSTAverageCost[n / 10000]);
                temp += (bstTree.getAllOperations() / n);
                BSTAverageCost[n / 10000] = String.valueOf(temp);
                if (RBBSTAverageCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(RBBSTAverageCost[n / 10000]);
                temp += (rbbstTree.getAllOperations() / n);
                RBBSTAverageCost[n / 10000] = String.valueOf(temp);
                if (SBSTAverageCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(SBSTAverageCost[n / 10000]);
                temp += (sbstTree.getAllOperations() / n);
                SBSTAverageCost[n / 10000] = String.valueOf(temp);

                // Max Cost Of Single Operation
                if (BSTMaxCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(BSTMaxCost[n / 10000]);
                temp += bstTree.getMaxOperations();
                BSTMaxCost[n / 10000] = String.valueOf(temp);
                if (RBBSTMaxCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(RBBSTMaxCost[n / 10000]);
                temp += rbbstTree.getMaxOperations();
                RBBSTMaxCost[n / 10000] = String.valueOf(temp);
                if (SBSTMaxCost[n / 10000] == null) temp = 0;
                else temp = Integer.parseInt(SBSTMaxCost[n / 10000]);
                temp += sbstTree.getMaxOperations();
                SBSTMaxCost[n / 10000] = String.valueOf(temp);

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
