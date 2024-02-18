package org.example.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author noah kim
 * @date 2024/02/18
 * @link https://www.acmicpc.net/problem/1715
 * @requirement 카드 정렬 시, 최소 횟수를 구하라
 * @summary
 * @input
 * @output
 * @time_complex 24992kb / 372ms
 * @perf
 */
public class 카드_정렬하기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static int[] cards;
    private static int N, ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (!pq.isEmpty()) {
            if (N == 1) {
                pq.clear();
            } else if (N < 3) {
                ans += pq.poll();
            } else {
                int tmp = pq.poll() + pq.poll();
                ans+=tmp;
                pq.offer(tmp);

                N--;
            }
        }

        System.out.println(ans);

        br.close();
    }
}
