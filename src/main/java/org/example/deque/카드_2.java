package org.example.deque;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/02
 * @link https://www.acmicpc.net/problem/2164
 * @keyword_solution
    Requirements: N장의 카드들 중, 제일 마지막 카드를 출력하라.

    [카드]
    - 갯수: N
    - 방향: Bottom-Up
    - 정렬: Desending
    - 배치: Stacked

    [뽑기]
    - 동작 횟수: 카드가 한장 남을 떄까지
    - 동작 방식
      1. 제일 위에 있는 카드를 버림
      2. 제일 위에 있는 카드를 제일 아래로 옮김
 * @input
    - N (1<=N<=500_000)
 * @output
    - 제일 마지막에 남게되는 카드의 번호
 * @time_complex O(N)
 * @perf 49444kb / 192ms
 */
public class 카드_2 {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<Long> deque = new LinkedList<>();
    private static long N;

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());

        for (long num = N; num > 0; num--) {
            deque.push(num);
        }

        while (deque.size() > 2) {
            deque.pop();
            deque.addLast(deque.pop());
        }

        System.out.println(deque.getLast());
    }
}
