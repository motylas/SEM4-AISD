import javax.naming.PartialResultException;
import java.util.ArrayList;

public class LongestSubstring {
//    function LCSubstr(S[1..r], T[1..n])
//    L := array(1..r, 1..n)
//    z := 0
//    ret := {}
//
//    for i := 1..r
//        for j := 1..n
//            if S[i] = T[j]
//            if i = 1 or j = 1
//    L[i, j] := 1
//            else
//    L[i, j] := L[i − 1, j − 1] + 1
//            if L[i, j] > z
//    z := L[i, j]
//    ret := {S[i − z + 1..i]}
//                else if L[i, j] = z
//    ret := ret ∪ {S[i − z + 1..i]}
//            else
//    L[i, j] := 0
//            return ret
    static ArrayList<Character> findSubstring(String x, String y){
        char[] S = x.toCharArray();
        char[] T = y.toCharArray();
        int r = S.length;
        int n = T.length;

        int[][] L = new int[r][n];
        int z = 0;
        ArrayList<Character> ret = new ArrayList<>();
        for (int i =0; i < r; i++){
            for (int j = 0; j < n; j++){
                if (S[i] == T[i]){
                    if (i == 0 || j == 0){
                        L[i][j] = 1;
                    }
                    else{
                        L[i][j] = L[i-1][j-1] + 1;
                    }
                    if (L[i][j] > z){
                        z = L[i][j];
                        ret.clear();
                        int sum = 0;
                        for(int k = 0; k < i; k++)
                            sum+=k;
                        ret.add(S[i-z + sum + 1]);
                    }
                    else if(L[i][j] == z){
                        int sum = 0;
                        for(int k = 0; k < i; k++)
                            sum+=k;
                        ret.add(S[i-z+sum]);
                    }
                }
                else{
                    L[i][j] = 0;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("abcdef", "abcdef"));
    }
}
