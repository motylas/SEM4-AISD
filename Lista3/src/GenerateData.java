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
        List<String[]> meanTimes = new ArrayList<>();
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
            for (int i = 100; i <= 10000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                Select.timesSwapped = 0;
                Select.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                int kth = arrayTemp.length / 2;
                Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 3);
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
            for (int i = 100; i <= 10000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                Select.timesSwapped = 0;
                Select.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                int kth = arrayTemp.length / 2;
                Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 5);
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
            for (int i = 100; i <= 10000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                Select.timesSwapped = 0;
                Select.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                int kth = arrayTemp.length / 2;
                Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 7);
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
            for (int i = 100; i <= 10000; i += 100) {
                int[] array = RandomNumbersGenerator.numberGenerator(i);
                Select.timesSwapped = 0;
                Select.timesCompared = 0;
                int[] arrayTemp = new int[i];
                for (int j = 0; j < arrays.get(i / 100 - 1).length; j++) {
                    arrayTemp[j] = arrays.get(i / 100 - 1)[j];
                }
                int kth = arrayTemp.length / 2;
                Select.select(arrayTemp, 0, arrayTemp.length - 1, kth, 9);
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
        }
        for (String[] oneLine : meanTimes) {
            for (int i = 1; i < oneLine.length; i++) {
                System.out.println(Arrays.toString(oneLine));
                oneLine[i] = String.valueOf(Float.parseFloat(oneLine[i]) / (float) k);
            }
            dataOutput.add(oneLine);
        }

        String[] cnRandomSelect = new String[11];
        String[] cnSelect3 = new String[11];
        String[] cnSelect5 = new String[11];
        String[] cnSelect7 = new String[11];
        String[] cnSelect9 = new String[11];
        String[] snRandomSelect = new String[11];
        String[] snSelect3 = new String[11];
        String[] snSelect5 = new String[11];
        String[] snSelect7 = new String[11];
        String[] snSelect9 = new String[11];
        for (int j = 0; j < 10; j++) {
            for (int i = 100; i <= 1000; i += 100) {
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
                }
            }
        }
        dataOutput.add(snRandomSelect);
        dataOutput.add(snSelect3);
        dataOutput.add(snSelect5);
        dataOutput.add(snSelect7);
        dataOutput.add(snSelect9);
        dataOutput.add(cnRandomSelect);
        dataOutput.add(cnSelect3);
        dataOutput.add(cnSelect5);
        dataOutput.add(cnSelect7);
        dataOutput.add(cnSelect9);


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
