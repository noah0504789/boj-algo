package org.example.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-02-05
 * @link https://www.acmicpc.net/problem/2493
 * @reference https://moonsbeen.tistory.com/204
 * @keyword_solution
    Requirements: 각각의 탑에서 발사한 신호의 수신자 인덱스를 출력하라.

    [탑]
    - 송신방법: 최대 높이에서 왼쪽으로 수평 발사
    - 수신자: 가장 먼저 수신한 탑
 * @input
    - N(탑 개수.1<=N<=500_000)
    - 모든 탑들의 높이(서로 다름.1<=T<=100_000_000)
 * @output
    - 신호를 수신한 탑의 인덱스들
 * @time_complex O(N)
 * @perf
 */
public class 탑 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final Stack<int[]> stack = new Stack<>();
    private static final int NO_RECEIVER_SIGNAL = 0;
    private static final int HEIGHT_IDX = 0;
    private static final int ORDER_IDX = 1;
    private static StringTokenizer st;
    private static int length;

    public static void main(String[] args) throws IOException {
        length = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int idx = 0; idx < length; idx++) {
            int[] curTower = {Integer.parseInt(st.nextToken()), idx+1};

            while (!stack.isEmpty() && stack.peek()[HEIGHT_IDX] < curTower[HEIGHT_IDX]) stack.pop();

            int receiverIdx = stack.isEmpty() ? NO_RECEIVER_SIGNAL : stack.peek()[ORDER_IDX];

            sb.append(receiverIdx + " ");

            stack.push(curTower);
        }

        System.out.println(sb.toString().trim());

        br.close();
    }
}
