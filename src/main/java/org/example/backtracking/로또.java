package org.example.backtracking;

import java.io.*;
import java.util.*;

public class 로또 {

    static BufferedReader br;
    static StringBuffer sb;
    static int K, MAX_DEPTH = 6;
    static int[] input, S, arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();

        while (true) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0) return;

            K = input[0];
            S = Arrays.copyOfRange(input, 1, input.length);

            sb = new StringBuffer();
            arr = new int[MAX_DEPTH];
            visited = new boolean[K];

            dfs(0);

            System.out.println(sb.toString());
            //System.out.println();
        }
    }

    private static void dfs(int depth) {
        if (depth == MAX_DEPTH) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            if (depth > 0 && S[i] < arr[depth-1]) continue;
            visited[i] = true;
            arr[depth] = S[i];
            dfs(depth+1);
            visited[i] = false;
        }
    }

    private static void init() {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
    }
}
