package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class N과_M_6 {

    static StringBuffer sb = new StringBuffer();
    static int N, M;
    static int[] input, arr, nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, 0);

        System.out.println(sb.toString());
    }

    private static void dfs(int start, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = nums[i];
                dfs(i, depth+1);
                visited[i] = false;
            }
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        visited = new boolean[N];
        arr = new int[M];
    }
}
