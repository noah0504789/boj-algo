package org.example.greedy;

import java.io.*;
import java.util.*;

// 문제 링크 : https://www.acmicpc.net/problem/15903
public class 카드합체놀이 {
    // 카드 합체 놀이를 했을 때, 나올 수 있는 가장 작은 값을 리턴하라.

    // 카드
    // - 자연수
    // - n장을 가지고 있음 (a0 ~ ai)

    // 카드 합체
    // 1. sum = ax + ay
    // 2. ax = sum, ay = sum

    // 카드 합체 놀이
    // 1. m번 카드 합체 하기
    // 2. 모든 카드의 합을 구한다

    static int n, m;
    static PriorityQueue<Long> cards;

    public static void main(String[] args) throws IOException {
        init();

        // 이전 합체값이 전체 합체값에 영향을 미침
        // - 작은 합체값을 얻어야 하므로 가장 작은 값끼리 합체시킨다.

        // 최소값 구하기
        // - PriorityQueue
        for (int i = 0; i < m; i++) {
            long x = cards.poll();
            long y = cards.poll();
            long tmp = x + y;

            cards.offer(tmp);
            cards.offer(tmp);
        }

        System.out.println(cards.stream().mapToLong(Long::longValue).sum());
    }

    private static void init() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // - 첫번째 줄 : n m
        String[] t = br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);
        cards = new PriorityQueue<>();

        // - 두번째 줄 : 모든 카드
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards.offer(Long.parseLong(st.nextToken()));
        }
    }
}

