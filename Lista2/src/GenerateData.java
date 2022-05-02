import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateData {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return;
        }

        List<String[]> dataOutput = new ArrayList<>();
        dataOutput.add(new String[]
                {"Algorithm", "N: 100", "N: 200", "N: 300", "N: 400",
                        "N: 500", "N: 600", "N: 700", "N: 800", "N: 900", "N: 1000",});
        List<String[]> meanTimes = new ArrayList<>();
        String[] timesSwappedMerge = new String[11];
        String[] timesComparedMerge = new String[11];
        String[] timesSwappedInsertion = new String[11];
        String[] timesComparedInsertion = new String[11];
        String[] timesSwappedQuick = new String[11];
        String[] timesComparedQuick = new String[11];
        String[] timesSwappedDual = new String[11];
        String[] timesComparedDual = new String[11];
        String[] timesSwappedHybrid = new String[11];
        String[] timesComparedHybrid = new String[11];
        meanTimes.add(timesSwappedMerge);
        meanTimes.add(timesComparedMerge);
        meanTimes.add(timesSwappedInsertion);
        meanTimes.add(timesComparedInsertion);
        meanTimes.add(timesSwappedQuick);
        meanTimes.add(timesComparedQuick);
        meanTimes.add(timesSwappedDual);
        meanTimes.add(timesComparedDual);
        meanTimes.add(timesSwappedHybrid);
        meanTimes.add(timesComparedHybrid);


        for (int test = 0; test < k; test++) {
            //Generating arrays
            List<Integer[]> arrays = new ArrayList<>();
            for (int i = 100; i <= 1000; i += 100) {
                int[] arrayTemp = RandomNumbersGenerator.numberGenerator(i);
                Integer[] array = new Integer[i];
                for (int j = 0; j < arrayTemp.length; j++) {
                    array[j] = arrayTemp[j];
                }
                arrays.add(array);
            }

            //Merge Sort
            timesSwappedMerge[0] = "Mean Lista2.MergeSort Swaps";
            timesComparedMerge[0] = "Mean Lista2.MergeSort Comparisons";
            for (int i = 100; i <= 1000; i += 100) {
                MergeSort.timesSwapped = 0;
                MergeSort.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                MergeSort.mergeSort(arrayTemp);
                int timeTemp;
                if (timesSwappedMerge[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesSwappedMerge[i / 100]);
                timeTemp += MergeSort.timesSwapped;
                timesSwappedMerge[i / 100] = String.valueOf(timeTemp);
                if (timesComparedMerge[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesComparedMerge[i / 100]);
                timeTemp += MergeSort.timesCompared;
                timesComparedMerge[i / 100] = String.valueOf(timeTemp);
            }


            //Lista2.InsertionSort
            timesSwappedInsertion[0] = "Mean Lista2.InsertionSort Swaps";
            timesComparedInsertion[0] = "Mean Lista2.InsertionSort Comparisons";
            for (int i = 100; i <= 1000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                InsertionSort.timesSwapped = 0;
                InsertionSort.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                InsertionSort.insertionSort(arrayTemp);
                int timeTemp;
                if (timesSwappedInsertion[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesSwappedInsertion[i / 100]);
                timeTemp += InsertionSort.timesSwapped;
                timesSwappedInsertion[i / 100] = String.valueOf(timeTemp);
                if (timesComparedInsertion[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesComparedInsertion[i / 100]);
                timeTemp += InsertionSort.timesCompared;
                timesComparedInsertion[i / 100] = String.valueOf(timeTemp);
            }


            //Lista2.QuickSort
            timesSwappedQuick[0] = "Mean Lista2.QuickSort Swaps";
            timesComparedQuick[0] = "Mean Lista2.QuickSort Comparisons";
            for (int i = 100; i <= 1000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                QuickSort.timesSwapped = 0;
                QuickSort.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                QuickSort.quickSort(arrayTemp, 0, arrayTemp.length - 1);
                int timeTemp;
                if (timesSwappedQuick[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesSwappedQuick[i / 100]);
                timeTemp += QuickSort.timesSwapped;
                timesSwappedQuick[i / 100] = String.valueOf(timeTemp);
                if (timesComparedQuick[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesComparedQuick[i / 100]);
                timeTemp += QuickSort.timesCompared;
                timesComparedQuick[i / 100] = String.valueOf(timeTemp);
            }

            //Lista2.DualPivotQuickSort
            timesSwappedDual[0] = "Mean Lista2.DualPivotQuickSort Swaps";
            timesComparedDual[0] = "Mean Lista2.DualPivotQuickSort Comparisons";
            for (int i = 100; i <= 1000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                DualPivotQuickSort.timesSwapped = 0;
                DualPivotQuickSort.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                DualPivotQuickSort.dualPivotQuickSort(arrayTemp, 0, arrayTemp.length - 1);
                int timeTemp;
                if (timesSwappedDual[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesSwappedDual[i / 100]);
                timeTemp += DualPivotQuickSort.timesSwapped;
                timesSwappedDual[i / 100] = String.valueOf(timeTemp);
                if (timesComparedDual[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesComparedDual[i / 100]);
                timeTemp += DualPivotQuickSort.timesCompared;
                timesComparedDual[i / 100] = String.valueOf(timeTemp);
            }

            //Lista2.HybridSort
            timesSwappedHybrid[0] = "Mean Lista2.HybridSort Swaps";
            timesComparedHybrid[0] = "Mean Lista2.HybridSort Comparisons";
            for (int i = 100; i <= 1000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                HybridSort.timesSwapped = 0;
                HybridSort.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                HybridSort.hybridSort(arrayTemp, 0, arrayTemp.length - 1,10);
                int timeTemp;
                if (timesSwappedHybrid[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesSwappedHybrid[i / 100]);
                timeTemp += HybridSort.timesSwapped;
                timesSwappedHybrid[i / 100] = String.valueOf(timeTemp);
                if (timesComparedHybrid[i / 100] == null) timeTemp = 0;
                else timeTemp = Integer.parseInt(timesComparedHybrid[i / 100]);
                timeTemp += HybridSort.timesCompared;
                timesComparedHybrid[i / 100] = String.valueOf(timeTemp);
            }

        }
        for (String[] oneLine : meanTimes) {
            for (int i = 1; i < oneLine.length; i++) {
                System.out.println(Arrays.toString(oneLine));
                oneLine[i] = String.valueOf(Float.parseFloat(oneLine[i]) / (float) k);
            }
            dataOutput.add(oneLine);
        }

        String[] cnMerge = new String[11];
        String[] cnInsertion = new String[11];
        String[] cnQuick = new String[11];
        String[] cnDual = new String[11];
        String[] cnHybrid = new String[11];
        String[] snMerge = new String[11];
        String[] snInsertion = new String[11];
        String[] snQuick = new String[11];
        String[] snDual = new String[11];
        String[] snHybrid = new String[11];
        for (int j = 0; j < 10; j++) {
            for (int i = 100; i <= 1000; i += 100) {
                switch (j) {
                    case 0:
                        snMerge[0] = "Mean Lista2.MergeSort Swaps / N";
                        snMerge[i/100] = String.valueOf(Float.parseFloat(timesSwappedMerge[i/100]) / (float) i);
                        break;
                    case 1:
                        cnMerge[0] = "Mean Lista2.MergeSort Comparisons / N";
                        cnMerge[i/100] = String.valueOf(Float.parseFloat(timesComparedMerge[i/100]) / (float) i);
                        break;
                    case 2:
                        snInsertion[0] = "Mean Lista2.InsertionSort Swaps / N";
                        snInsertion[i/100] = String.valueOf(Float.parseFloat(timesSwappedInsertion[i/100]) / (float) i);;
                        break;
                    case 3:
                        cnInsertion[0] = "Mean Lista2.InsertionSort Comparisons / N";
                        cnInsertion[i/100] = String.valueOf(Float.parseFloat(timesComparedInsertion[i/100]) / (float) i);
                        break;
                    case 4:
                        snQuick[0] = "Mean Lista2.QuickSort Swaps / N";
                        snQuick[i/100] = String.valueOf(Float.parseFloat(timesSwappedQuick[i/100]) / (float) i);
                        break;
                    case 5:
                        cnQuick[0] = "Mean Lista2.QuickSort Comparisons / N";
                        cnQuick[i/100] = String.valueOf(Float.parseFloat(timesComparedQuick[i/100]) / (float) i);
                        break;
                    case 6:
                        snDual[0] = "Mean Lista2.DualPivotQuickSort Comparisons / N";
                        snDual[i/100] = String.valueOf(Float.parseFloat(timesSwappedDual[i/100]) / (float) i);
                        break;
                    case 7:
                        cnDual[0] = "Mean Lista2.DualPivotQuickSort Comparisons / N";
                        cnDual[i/100] = String.valueOf(Float.parseFloat(timesComparedDual[i/100]) / (float) i);
                        break;
                    case 8:
                        snHybrid[0] = "Mean Lista2.QuickSort Comparisons / N";
                        snHybrid[i/100] = String.valueOf(Float.parseFloat(timesSwappedHybrid[i/100]) / (float) i);
                        break;
                    case 9:
                        cnHybrid[0] = "Mean Lista2.QuickSort Comparisons / N";
                        cnHybrid[i/100] = String.valueOf(Float.parseFloat(timesComparedHybrid[i/100]) / (float) i);
                        break;
                }
            }
        }
        dataOutput.add(snMerge);
        dataOutput.add(cnMerge);
        dataOutput.add(snInsertion);
        dataOutput.add(cnInsertion);
        dataOutput.add(snQuick);
        dataOutput.add(cnQuick);
        dataOutput.add(snDual);
        dataOutput.add(cnDual);
        dataOutput.add(snHybrid);
        dataOutput.add(cnHybrid);

        givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "Algorithms for k=", k);
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
