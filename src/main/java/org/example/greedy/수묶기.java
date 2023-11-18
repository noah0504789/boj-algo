package org.example.greedy;

// 수를 묶어 수열의 합이 최대가 되는 값을 리턴하라.

// 수열
// - 길이 : N

// 수 묶기
// - 한번만 묶일 수 있음

import java.io.*;
import java.util.*;

public class 수묶기 {

    static int N;
    static long ans;
    static int[] input, nums;

    public static void main(String[] args) throws IOException {
        init();

        int left = 0, right = N-1;
        for (; left < N-1; left+= 2) {
            if (nums[left] < 1 && nums[left+1] < 1) {
                ans += nums[left] * nums[left+1];
            } else {
                break;
            }
        }

        for (; right > 0; right-=2) {
            if (nums[right] > 1 && nums[right-1] > 1) {
                ans += nums[right] * nums[right-1];
            } else {
                break;
            }
        }

        for (int i = left; i <= right; i++) ans += nums[i];

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        ans = 0;
    }
}

/**
 int idx = 0;
 int ascSum = 0;
 while (idx < N) {
 if (nums[idx] == 0) {
 idx++;
 } else if (idx+1 < N && nums[idx+1] == 0 || idx-1 >= 0 && nums[idx-1] == 0) {
 ascSum += nums[idx];
 idx++;
 } else if (nums[idx] < 0 && idx+1 < N && nums[idx+1] < 0 || nums[idx] > 0 && idx+1 < N && nums[idx+1] > 0) {
 ascSum += nums[idx] * nums[idx+1];
 idx += 2;
 }
 }

 idx = N-1;
 int descSum = 0;
 while (idx > 0) {
 if (nums[idx] == 0) {
 idx--;
 } else if (idx-1 >= 0 && nums[idx-1] == 0 || idx+1 < N && nums[idx+1] == 0) {
 descSum += nums[idx];
 idx--;
 } else if (nums[idx] < 0 && idx-1 >= 0 && nums[idx-1] < 0 || nums[idx] > 0 && idx-1 >= 0 && nums[idx-1] > 0) {
 descSum += nums[idx] * nums[idx-1];
 idx -= 2;
 }
 }

 System.out.println(Math.max(ascSum, descSum));
 */
