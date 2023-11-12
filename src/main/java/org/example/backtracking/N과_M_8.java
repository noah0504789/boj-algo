package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N과_M_8 {

    static StringBuffer sb = new StringBuffer();
    static int[] input, arr, nums;
    static int N, M;

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
            arr[depth] = nums[i];
            dfs(i, depth+1);
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        arr = new int[M];
    }
}
