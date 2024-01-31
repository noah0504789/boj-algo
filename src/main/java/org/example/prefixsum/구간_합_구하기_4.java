package org.example.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-01-31
 * @link https://www.acmicpc.net/problem/11659
 * @keyword_solution
    * Requirement : 주어진 숫자에서, 요청된 구간의 합을 구하라.
 * @input
    - N: 수의 개수 (1~100,000), M: 구간합 요청 횟수 (1~100,000)
    - 전체 숫자 (N개)(DELIMITER:" ")(수:1~1000)
    - [i(구간 시작 idx), j(구간 끝 idx)] * M
 * @output
    - 구간 합 * M
 * @time_complex O(N)
 * @perf 90740kb / 1036ms
 */
public class 구간_합_구하기_4 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final String DELIMITER = " ";
    static final StringBuffer sb = new StringBuffer();
    static int N, M;
    static int[] nums, sums, temp;
    static int[][] periods;
    public static void main(String[] args) throws IOException {
        temp = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
        N = temp[0];
        M = temp[1];

        nums = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
        sums = nums.clone();

        for (int i = 1; i < N; i++) {
            sums[i] += sums[i-1];
        }

        periods = new int[M][2];
        for (int m = 0; m < M; m++) {
            periods[m] = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
        }

        for (int[] period : periods) {
            int srcIdx = period[0]-1, destIdx = period[1]-1;

            sb.append(prefixSum(srcIdx, destIdx) + "\n");
        }

        System.out.println(sb);
    }

    private static int prefixSum(int srcIdx, int destIdx) {
        if (srcIdx == 0)        return sums[destIdx];
        if (srcIdx == destIdx)  return nums[destIdx];

        return sums[destIdx] - sums[srcIdx-1];
    }
}
