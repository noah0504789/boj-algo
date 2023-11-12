package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N과_M_12 {

    static StringBuffer sb = new StringBuffer();
    static int N, M;
    static int[] input, arr, nums;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0);

        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        int before = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == before) continue;
            if (depth > 0 && nums[i] < arr[depth-1]) continue;
            arr[depth] = nums[i];
            before = nums[i];
            dfs(depth+1);
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
