package org.example.greedy;

import java.io.*;
import java.util.*;

public class 주식 {

    static int tc, N;
    static long ans;
    static int[] nums;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            init();

            int max = 0;
            for (int i = N-1; i >= 0; i--) {
                if (nums[i] > max) max = nums[i];
                else ans += max - nums[i];
            }

            System.out.println(ans);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ans = 0;
    }
}
