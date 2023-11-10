package org.example.dp;

import java.io.*;
import java.util.*;

public class 구간합_구하기_4 {

    static BufferedReader br;
    static String[] input = null;
    static int N, M, start, end;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);

            System.out.println(nums[end] - nums[start-1]);
        }
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        nums = new int[100001];
        nums[0] = 0;

        input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            nums[i+1] = nums[i] + Integer.parseInt(input[i]);
        }
    }
}
