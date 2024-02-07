package org.example.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author noah kim
 * @date 2024/02/07
 * @link https://www.acmicpc.net/problem/11286
 * @keyword_solution
    Requirements: 절댓값 힙으로부터 출력명령으로 받은 수들을 출력하라.

    [입력]
    - 0: 절댓값이 가장 작은 값 출력 및 제거
        - 여러개일 경우: 가장 작은 수 선택
        - 비어있는 경우: 0
    - 0 아님: 추가 (-2^31<num<2^31)
 * @input
    - N: 연산 개수(1<=N<=100_000)
 * @output
    - 출력명령 수들 모두 출력
 * @time_complex O(NlogN)
 * @perf
 */
public class 절댓값_힙 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static int N, input;

    public static void main(String[] args) throws IOException {
        PriorityQueue<Num> pq = new PriorityQueue<>();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            if (input != 0) {
                pq.offer(new Num(input));
                continue;
            }

            int output = 0;
            if (!pq.isEmpty()) output = pq.poll().num;

            sb.append(output).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    static class Num implements Comparable<Num> {
        final int num;

        public Num(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Num o) {
            int curAbs = Math.abs(this.num);
            int otherAbs = Math.abs(o.num);

            if (curAbs == otherAbs) return this.num - o.num;

            return curAbs - otherAbs;
        }
    }
}
