package org.example.backtracking;


import java.io.*;
import java.util.*;

public class N과_M_3 {

    static String[] input = null;
    static int N, M;
    static int[] arr;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        init();

        dfs(0);

        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[depth] = i;
            dfs(depth+1);
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = new int[M];
    }
}
