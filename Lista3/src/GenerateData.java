import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleBinaryOperator;
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
        boolean doOnlyBinarySearch = false;

        List<String[]> dataOutput = new ArrayList<>();
        //NOT FOR BINARY SEARCH
        if (!doOnlyBinarySearch) {
            dataOutput.add(new String[]
                    {"Algorithm", "N: 100", "N: 200", "N: 300", "N: 400", "N: 500", "N: 600", "N: 700", "N: 800", "N: 900", "N: 1000",
                            "N: 1100", "N: 1200", "N: 1300", "N: 1400", "N: 1500", "N: 1600", "N: 1700", "N: 1800", "N: 1900",
                            "N: 2000", "N: 2100", "N: 2200", "N: 2300", "N: 2400", "N: 2500", "N: 2600", "N: 2700", "N: 2800", "N: 2900",
                            "N: 3000", "N: 3100", "N: 3200", "N: 3300", "N: 3400", "N: 3500", "N: 3600", "N: 3700", "N: 3800", "N: 3900",
                            "N: 4000", "N: 4100", "N: 4200", "N: 4300", "N: 4400", "N: 4500", "N: 4600", "N: 4700", "N: 4800", "N: 4900",
                            "N: 5000", "N: 5100", "N: 5200", "N: 5300", "N: 5400", "N: 5500", "N: 5600", "N: 5700", "N: 5800", "N: 5900",
                            "N: 6000", "N: 6100", "N: 6200", "N: 6300", "N: 6400", "N: 6500", "N: 6600", "N: 6700", "N: 6800", "N: 6900",
                            "N: 7000", "N: 7100", "N: 7200", "N: 7300", "N: 7400", "N: 7500", "N: 7600", "N: 7700", "N: 7800", "N: 7900",
                            "N: 8000", "N: 8100", "N: 8200", "N: 8300", "N: 8400", "N: 8500", "N: 8600", "N: 8700", "N: 8800", "N: 8900",
                            "N: 9000", "N: 9100", "N: 9200", "N: 9300", "N: 9400", "N: 9500", "N: 9600", "N: 9700", "N: 9800", "N: 9900", "N: 10000"
                    });
        }
        else {
        dataOutput.add(new String[]
                {"Algorithm", "N: 1000", "N: 2000", "N: 3000", "N: 4000", "N: 5000", "N: 6000", "N: 7000", "N: 8000", "N: 9000", "N: 10000",
                        "N: 11000", "N: 12000", "N: 13000", "N: 14000", "N: 15000", "N: 16000", "N: 17000", "N: 18000", "N: 19000",
                        "N: 20000", "N: 21000", "N: 22000", "N: 23000", "N: 24000", "N: 25000", "N: 26000", "N: 27000", "N: 28000", "N: 29000",
                        "N: 30000", "N: 31000", "N: 32000", "N: 33000", "N: 34000", "N: 35000", "N: 36000", "N: 37000", "N: 38000", "N: 39000",
                        "N: 40000", "N: 41000", "N: 42000", "N: 43000", "N: 44000", "N: 45000", "N: 46000", "N: 47000", "N: 48000", "N: 49000",
                        "N: 50000", "N: 51000", "N: 52000", "N: 53000", "N: 54000", "N: 55000", "N: 56000", "N: 57000", "N: 58000", "N: 59000",
                        "N: 60000", "N: 61000", "N: 62000", "N: 63000", "N: 64000", "N: 65000", "N: 66000", "N: 67000", "N: 68000", "N: 69000",
                        "N: 70000", "N: 71000", "N: 72000", "N: 73000", "N: 74000", "N: 75000", "N: 76000", "N: 77000", "N: 78000", "N: 79000",
                        "N: 80000", "N: 81000", "N: 82000", "N: 83000", "N: 84000", "N: 85000", "N: 86000", "N: 87000", "N: 88000", "N: 89000",
                        "N: 90000", "N: 91000", "N: 92000", "N: 93000", "N: 94000", "N: 95000", "N: 96000", "N: 97000", "N: 98000", "N: 99000", "N: 100000"
                });
        }
        List<String[]> meanTimes = new ArrayList<>();
        if (!doOnlyBinarySearch) {
            String[] timesSwappedRandomSelect = new String[101];
            String[] timesSwappedSelect3 = new String[101];
            String[] timesSwappedSelect5 = new String[101];
            String[] timesSwappedSelect7 = new String[101];
            String[] timesSwappedSelect9 = new String[101];
            String[] timesComparedRandomSelect = new String[101];
            String[] timesComparedSelect3 = new String[101];
            String[] timesComparedSelect5 = new String[101];
            String[] timesComparedSelect7 = new String[101];
            String[] timesComparedSelect9 = new String[101];
            String[] timeSelect3 = new String[101];
            String[] timeSelect5 = new String[101];
            String[] timeSelect7 = new String[101];
            String[] timeSelect9 = new String[101];
            String[] timesSwappedQuick = new String[101];
            String[] timesSwappedDual = new String[101];
            String[] timesComparedQuick = new String[101];
            String[] timesComparedDual = new String[101];
            meanTimes.add(timesSwappedRandomSelect);
            meanTimes.add(timesSwappedSelect3);
            meanTimes.add(timesSwappedSelect5);
            meanTimes.add(timesSwappedSelect7);
            meanTimes.add(timesSwappedSelect9);
            meanTimes.add(timesComparedRandomSelect);
            meanTimes.add(timesComparedSelect3);
            meanTimes.add(timesComparedSelect5);
            meanTimes.add(timesComparedSelect7);
            meanTimes.add(timesComparedSelect9);
            meanTimes.add(timeSelect3);
            meanTimes.add(timeSelect5);
            meanTimes.add(timeSelect7);
            meanTimes.add(timeSelect9);
            meanTimes.add(timesSwappedQuick);
            meanTimes.add(timesSwappedDual);
            meanTimes.add(timesComparedQuick);
            meanTimes.add(timesComparedDual);


            for (int test = 0; test < k; test++) {
                //Generating arrays
                List<Integer[]> arrays = new ArrayList<>();
                for (int i = 100; i <= 10000; i += 100) {
                    int[] arrayTemp = RandomNumbersGenerator.numberGenerator(i);
                    Integer[] array = new Integer[i];
                    for (int j = 0; j < arrayTemp.length; j++) {
                        array[j] = arrayTemp[j];
                    }
                    arrays.add(array);
                }

                //Random Select
                timesSwappedRandomSelect[0] = "Mean RandomSelect Swaps";
                timesComparedRandomSelect[0] = "Mean RandomSelect Comparisons";
                for (int i = 100; i <= 10000; i += 100) {
                    RandomSelect.timesSwapped = 0;
                    RandomSelect.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    int kth = arrayTemp.length / 2;
                    RandomSelect.randomSelect(arrayTemp, 0, arrayTemp.length - 1, kth);
                    int timeTemp;
                    if (timesSwappedRandomSelect[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedRandomSelect[i / 100]);
                    timeTemp += RandomSelect.timesSwapped;
                    timesSwappedRandomSelect[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedRandomSelect[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedRandomSelect[i / 100]);
                    timeTemp += RandomSelect.timesCompared;
                    timesComparedRandomSelect[i / 100] = String.valueOf(timeTemp);
                }


                //Select 3
                timesSwappedSelect3[0] = "Mean Select3 Swaps";
                timesComparedSelect3[0] = "Mean Select3 Comparisons";
                timeSelect3[0] = "Mean Select3 Time";
                for (int i = 100; i <= 10000; i += 100) {
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    int kth = arrayTemp.length / 2;
                    long start = System.nanoTime();
                    Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 3);
                    long end = System.nanoTime();
                    long timeElapsed = end - start;
                    long fullTime;
                    if (timeSelect3[i / 100] == null) fullTime = 0;
                    else fullTime = Long.parseLong(timeSelect3[i / 100]);
                    fullTime += timeElapsed;
                    timeSelect3[i/100] = String.valueOf(fullTime);
                    int timeTemp;
                    if (timesSwappedSelect3[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedSelect3[i / 100]);
                    timeTemp += Select.timesSwapped;
                    timesSwappedSelect3[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedSelect3[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedSelect3[i / 100]);
                    timeTemp += Select.timesCompared;
                    timesComparedSelect3[i / 100] = String.valueOf(timeTemp);
                }

                //Select 5
                timesSwappedSelect5[0] = "Mean Select5 Swaps";
                timesComparedSelect5[0] = "Mean Select5 Comparisons";
                timeSelect5[0] = "Mean Select5 Time";
                for (int i = 100; i <= 10000; i += 100) {
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    int kth = arrayTemp.length / 2;
                    long start = System.nanoTime();
                    Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 5);
                    long end = System.nanoTime();
                    long timeElapsed = end - start;
                    long fullTime;
                    if (timeSelect5[i / 100] == null) fullTime = 0;
                    else fullTime = Long.parseLong(timeSelect5[i / 100]);
                    fullTime += timeElapsed;
                    timeSelect5[i/100] = String.valueOf(fullTime);
                    int timeTemp;
                    if (timesSwappedSelect5[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedSelect5[i / 100]);
                    timeTemp += Select.timesSwapped;
                    timesSwappedSelect5[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedSelect5[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedSelect5[i / 100]);
                    timeTemp += Select.timesCompared;
                    timesComparedSelect5[i / 100] = String.valueOf(timeTemp);
                }

                //Select 7
                timesSwappedSelect7[0] = "Mean Select7 Swaps";
                timesComparedSelect7[0] = "Mean Select7 Comparisons";
                timeSelect7[0] = "Mean Select7 Time";
                for (int i = 100; i <= 10000; i += 100) {
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    int kth = arrayTemp.length / 2;
                    long start = System.nanoTime();
                    Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 7);
                    long end = System.nanoTime();
                    long timeElapsed = end - start;
                    long fullTime;
                    if (timeSelect7[i / 100] == null) fullTime = 0;
                    else fullTime = Long.parseLong(timeSelect7[i / 100]);
                    fullTime += timeElapsed;
                    timeSelect7[i/100] = String.valueOf(fullTime);
                    int timeTemp;
                    if (timesSwappedSelect7[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedSelect7[i / 100]);
                    timeTemp += Select.timesSwapped;
                    timesSwappedSelect7[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedSelect7[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedSelect7[i / 100]);
                    timeTemp += Select.timesCompared;
                    timesComparedSelect7[i / 100] = String.valueOf(timeTemp);
                }

                //Select 9
                timesSwappedSelect9[0] = "Mean Select9 Swaps";
                timesComparedSelect9[0] = "Mean Select9 Comparisons";
                timeSelect9[0] = "Mean Select9 Time";
                for (int i = 100; i <= 10000; i += 100) {
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    int kth = arrayTemp.length / 2;
                    long start = System.nanoTime();
                    Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 9);
                    long end = System.nanoTime();
                    long timeElapsed = end - start;
                    long fullTime;
                    if (timeSelect9[i / 100] == null) fullTime = 0;
                    else fullTime = Long.parseLong(timeSelect9[i / 100]);
                    fullTime += timeElapsed;
                    timeSelect9[i/100] = String.valueOf(fullTime);
                    int timeTemp;
                    if (timesSwappedSelect9[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedSelect9[i / 100]);
                    timeTemp += Select.timesSwapped;
                    timesSwappedSelect9[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedSelect9[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedSelect9[i / 100]);
                    timeTemp += Select.timesCompared;
                    timesComparedSelect9[i / 100] = String.valueOf(timeTemp);
                }

                //QuickSort
                timesSwappedQuick[0] = "Mean Lista2.QuickSort Swaps";
                timesComparedQuick[0] = "Mean Lista2.QuickSort Comparisons";
                for (int i = 100; i <= 10000; i += 100) {
                    int[] array = RandomNumbersGenerator.numberGenerator(i);
                    QuickSort.timesSwapped = 0;
                    QuickSort.timesCompared = 0;
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    QuickSort.quickSort(arrayTemp, 0, arrayTemp.length - 1);
                    int timeTemp;
                    if (timesSwappedQuick[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedQuick[i / 100]);
                    timeTemp += QuickSort.timesSwapped;
                    timeTemp += Select.timesSwapped;
                    timesSwappedQuick[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedQuick[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedQuick[i / 100]);
                    timeTemp += QuickSort.timesCompared;
                    timeTemp += Select.timesCompared;
                    timesComparedQuick[i / 100] = String.valueOf(timeTemp);
                }

                //DualPivotQuickSort
                timesSwappedDual[0] = "Mean DualPivotQuickSort Swaps";
                timesComparedDual[0] = "Mean DualPivotQuickSort Comparisons";
                for (int i = 100; i <= 10000; i += 100) {
                    int[] array = RandomNumbersGenerator.numberGenerator(i);
                    DualPivotQuickSort.timesSwapped = 0;
                    DualPivotQuickSort.timesCompared = 0;
                    Select.timesSwapped = 0;
                    Select.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                    }
                    DualPivotQuickSort.dualPivotQuickSort(arrayTemp, 0, arrayTemp.length - 1);
                    int timeTemp;
                    if (timesSwappedDual[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesSwappedDual[i / 100]);
                    timeTemp += DualPivotQuickSort.timesSwapped;
                    timeTemp += Select.timesSwapped;
                    timesSwappedDual[i / 100] = String.valueOf(timeTemp);
                    if (timesComparedDual[i / 100] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedDual[i / 100]);
                    timeTemp += DualPivotQuickSort.timesCompared;
                    timeTemp += Select.timesCompared;
                    timesComparedDual[i / 100] = String.valueOf(timeTemp);
                }
            }
            for (String[] oneLine : meanTimes) {
                for (int i = 1; i < oneLine.length; i++) {
                    oneLine[i] = String.valueOf(Float.parseFloat(oneLine[i]) / (float) k);
                }
                dataOutput.add(oneLine);
            }

            String[] cnRandomSelect = new String[101];
            String[] cnSelect3 = new String[101];
            String[] cnSelect5 = new String[101];
            String[] cnSelect7 = new String[101];
            String[] cnSelect9 = new String[101];
            String[] cnQuick = new String[101];
            String[] cnDual = new String[101];
            String[] snRandomSelect = new String[101];
            String[] snSelect3 = new String[101];
            String[] snSelect5 = new String[101];
            String[] snSelect7 = new String[101];
            String[] snSelect9 = new String[101];
            String[] snQuick = new String[101];
            String[] snDual = new String[101];
            for (int j = 0; j < 14; j++) {
                for (int i = 100; i <= 10000; i += 100) {
                    switch (j) {
                        case 0:
                            snRandomSelect[0] = "Mean RandomSelect Swaps / N";
                            snRandomSelect[i / 100] = String.valueOf(Float.parseFloat(timesSwappedRandomSelect[i / 100]) / (float) i);
                            break;
                        case 1:
                            cnRandomSelect[0] = "Mean RandomSelect Comparisons / N";
                            cnRandomSelect[i / 100] = String.valueOf(Float.parseFloat(timesComparedRandomSelect[i / 100]) / (float) i);
                            break;
                        case 2:
                            snSelect3[0] = "Mean Select3 Swaps / N";
                            snSelect3[i / 100] = String.valueOf(Float.parseFloat(timesSwappedSelect3[i / 100]) / (float) i);
                            break;
                        case 3:
                            cnSelect3[0] = "Mean Select3 Comparisons / N";
                            cnSelect3[i / 100] = String.valueOf(Float.parseFloat(timesComparedSelect3[i / 100]) / (float) i);
                            break;
                        case 4:
                            snSelect5[0] = "Mean Select5 Swaps / N";
                            snSelect5[i / 100] = String.valueOf(Float.parseFloat(timesSwappedSelect5[i / 100]) / (float) i);
                            break;
                        case 5:
                            cnSelect5[0] = "Mean Select5 Comparisons / N";
                            cnSelect5[i / 100] = String.valueOf(Float.parseFloat(timesComparedSelect5[i / 100]) / (float) i);
                            break;
                        case 6:
                            snSelect7[0] = "Mean Select7 Swaps / N";
                            snSelect7[i / 100] = String.valueOf(Float.parseFloat(timesSwappedSelect7[i / 100]) / (float) i);
                            break;
                        case 7:
                            cnSelect7[0] = "Mean Select7 Comparisons / N";
                            cnSelect7[i / 100] = String.valueOf(Float.parseFloat(timesComparedSelect7[i / 100]) / (float) i);
                            break;
                        case 8:
                            snSelect9[0] = "Mean Select9 Swaps / N";
                            snSelect9[i / 100] = String.valueOf(Float.parseFloat(timesSwappedSelect9[i / 100]) / (float) i);
                            break;
                        case 9:
                            cnSelect9[0] = "Mean Select9 Comparisons / N";
                            cnSelect9[i / 100] = String.valueOf(Float.parseFloat(timesComparedSelect9[i / 100]) / (float) i);
                            break;
                        case 10:
                            snQuick[0] = "Mean QuickSort Swaps / N";
                            snQuick[i / 100] = String.valueOf(Float.parseFloat(timesSwappedQuick[i / 100]) / (float) i);
                            break;
                        case 11:
                            cnQuick[0] = "Mean QuickSort Comparisons / N";
                            cnQuick[i / 100] = String.valueOf(Float.parseFloat(timesComparedQuick[i / 100]) / (float) i);
                            break;
                        case 12:
                            snDual[0] = "Mean DualPivot QuickSort Swaps / N";
                            snDual[i / 100] = String.valueOf(Float.parseFloat(timesSwappedDual[i / 100]) / (float) i);
                            break;
                        case 13:
                            cnDual[0] = "Mean DualPivot QuickSort Comparisons / N";
                            cnDual[i / 100] = String.valueOf(Float.parseFloat(timesComparedDual[i / 100]) / (float) i);
                            break;
                    }
                }
            }
            dataOutput.add(snRandomSelect);
            dataOutput.add(snSelect3);
            dataOutput.add(snSelect5);
            dataOutput.add(snSelect7);
            dataOutput.add(snSelect9);
            dataOutput.add(snQuick);
            dataOutput.add(snDual);
            dataOutput.add(cnRandomSelect);
            dataOutput.add(cnSelect3);
            dataOutput.add(cnSelect5);
            dataOutput.add(cnSelect7);
            dataOutput.add(cnSelect9);
            dataOutput.add(cnQuick);
            dataOutput.add(cnDual);


            givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "Algorithms for k=", k);


        }
        else {
            String[] timesComparedBS = new String[101];
            String[] timeBS = new String[101];
            meanTimes.add(timesComparedBS);
            meanTimes.add(timeBS);

            for (int test = 0; test < k; test++) {
                //Generating arrays
                List<Integer[]> arrays = new ArrayList<>();
                for (int i = 1000; i <= 100000; i += 1000) {
                    int[] arrayTemp = RandomNumbersGenerator.numberGenerator(i);
                    Arrays.sort(arrayTemp);
                    Integer[] array = new Integer[i];
                    for (int j = 0; j < arrayTemp.length; j++) {
                        array[j] = arrayTemp[j];
                    }
                    arrays.add(array);
                }

                //Binary Search
                timesComparedBS[0] = "Mean BinarySearch Comparisons";
                for (int i = 1000; i <= 100000; i += 1000) {
                    BinarySearch.timesCompared = 0;
                    int[] arrayTemp = new int[i];
                    for (int j = 0; j < arrays.get(i / 1000 - 1).length; j++) {
                        arrayTemp[j] = arrays.get(i / 1000 - 1)[j];
                    }                    long start = System.nanoTime();
                    BinarySearch.binarySearch(arrayTemp, 0, arrayTemp.length-1, arrayTemp[i/1000]);
                    long end = System.nanoTime();
                    long timeElapsed = end - start;
                    long fullTime;
                    if (timeBS[i / 1000] == null) fullTime = 0;
                    else fullTime = Long.parseLong(timeBS[i / 1000]);
                    fullTime += timeElapsed;
                    timeBS[i/1000] = String.valueOf(fullTime);
                    int timeTemp;
                    if (timesComparedBS[i / 1000] == null) timeTemp = 0;
                    else timeTemp = Integer.parseInt(timesComparedBS[i / 1000]);
                    timeTemp += BinarySearch.timesCompared;
                    timesComparedBS[i / 1000] = String.valueOf(timeTemp);
                }
            }
            for (String[] oneLine : meanTimes) {
                for (int i = 1; i < oneLine.length; i++) {
                    oneLine[i] = String.valueOf(Float.parseFloat(oneLine[i]) / (float) k);
                }
                dataOutput.add(oneLine);
            }

            String[] cnBS = new String[101];

            cnBS[0] = "Mean Binary Search Compares / N";
            for (int i = 1000; i <= 100000; i += 1000) {
                cnBS[i / 1000] = String.valueOf(Float.parseFloat(timesComparedBS[i / 1000]) / (float) i);
            }
            dataOutput.add(cnBS);
            givenDataArray_whenConvertToCSV_thenOutputCreated(dataOutput, "BSONLY: Algorithms for k=", k);

        }



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
