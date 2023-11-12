package org.example.backtracking;

import java.io.*;
import java.util.*;

public class N과_M_4 {

    static String[] input = null;
    static StringBuffer sb = new StringBuffer();
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1, 0);

        System.out.println(sb.toString());
    }

    private static void dfs(int at, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = at; i <= N; i++) {
            if (depth == 0) {
                arr[depth] = i;
            } else {
                if (i >= arr[depth-1]) {
                    arr[depth] = i;
                }
            }

            dfs(i, depth+1);
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
