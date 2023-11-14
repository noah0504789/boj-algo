package org.example.simulation;

// 될 수 있는 결과 중 최솟값을 리턴하라

// 연산자 끼워넣기
// - 연산자의 순서를 바꿀 수 있다
// - 왼쪽부터 진행 (연산자 우선순위 무시)

// 연산자 : 덧셈, 뺄셈, 곱셈, 나눗셈

import java.io.*;
        import java.util.*;

public class 연산자_끼워넣기 {

    static int N, min = 1000000000, max = -1000000000, PLUS = 0, MINUS = 1, MUL = 2, DIV = 3;
    static int[] input, nums, opsCnt;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int depth, int sum) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        int i = depth;

        for (int j = 0; j < 4; j++) {
            if (opsCnt[j] == 0) continue;

            opsCnt[j] -= 1;

            if (j == PLUS) dfs(i+1, sum+nums[i]);
            else if (j == MINUS) dfs(i+1, sum-nums[i]);
            else if (j == MUL) dfs(i+1, sum*nums[i]);
            else if (j == DIV) dfs(i+1, sum/nums[i]);

            opsCnt[j] += 1;
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        opsCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
