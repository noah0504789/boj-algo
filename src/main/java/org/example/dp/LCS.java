package org.example.dp;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class LCS {

    static int ASIZE, BSIZE;
    static String A, B;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= ASIZE; i++) {
            for (int j = 1; j <= BSIZE; j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[ASIZE][BSIZE]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        B = br.readLine();

        ASIZE = A.length();
        BSIZE = B.length();

        dp = new int[ASIZE+1][BSIZE+1];
    }
}
