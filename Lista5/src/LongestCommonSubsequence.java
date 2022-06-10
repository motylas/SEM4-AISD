import java.util.Arrays;

public class LongestCommonSubsequence {
    public static long timesCompared = 0;
    public static long timesSwapped = 0;

    static public String lcs(String X, String Y, int m, int n) {
        int[][] L = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)){
                    timesCompared++;
                    L[i][j] = L[i - 1][j - 1] + 1;
                }
                else{
                    timesCompared++;
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
                timesSwapped++;
            }
        }
//        for (int[] ints : L) {
//            System.out.println(Arrays.toString(ints));
//        }


        StringBuilder result = new StringBuilder();

        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                timesCompared++;
                result.insert(0, X.charAt(i - 1));
                i--;
                j--;
            } else if (L[i - 1][j] > L[i][j - 1]){
                timesCompared+=2;
                i--;
            }
            else{
                timesCompared+=2;
                j--;
            }
        }


        return result.toString();
    }

    public static void main(String[] args) {
        String X = "123454321";
        String Y = "244261";
        int m = X.length();
        int n = Y.length();
        System.out.println(lcs(X, Y, m, n));
    }
}