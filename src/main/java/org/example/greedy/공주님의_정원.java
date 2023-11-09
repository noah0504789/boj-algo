package org.example.greedy;

import java.io.*;
import java.util.*;

// 문제링크 : https://www.acmicpc.net/problem/2457
public class 공주님의_정원 {
    static int tot;
    static Flower[] flowers;

    // 정원에 있는 최소의 꽃 갯수를 구하라

    // 3/1 ~ 11/30 까지 정원에 꽃이 있어야 한다
    // - 펴있는 꽃만 있어야 한다
    public static void main(String[] args) throws IOException {
        init();

        // 1. 정렬
        // - 꽃이 피는 날짜 -> 오름차순
        // - 꽃이 지는 날짜 -> 내림차순
        Arrays.sort(flowers);

        // 2. 꽃 고르기
        // - 꽃 조건
        //   - 피는 날짜 : 이전 꽃의 지는날짜보다 작거나 같아야 한다
        //      - 지는 날짜 : 가장 늦게 져야 한다
        int length = flowers.length, startDay = 301, endDay = 1201, max = 0, cnt = 0;
        boolean isFind;
        while (startDay < endDay) {
            isFind = false;
            for (int i = 0; i < length; i++) {
                Flower flower = flowers[i];
                if (flower.start > startDay) break;
                if (flower.end > max) {
                    max = flower.end;
                    isFind = true;
                }
            }

            if (isFind) {
                cnt++;
                startDay = max;
            } else {
                break;
            }
        }

        // 3. 출력
        // - 해당 경우가 없는 경우 0을 출력한다
        // - 최소개의 꽃을 출력한다.
        if (endDay > max) System.out.println(0);
        else System.out.println(cnt);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 첫째줄 : 꽃들의 총 개수
        tot = Integer.parseInt(br.readLine());
        flowers = new Flower[tot];

        // 2. 꽃이 피는 날짜 / 지는 날짜
        for (int i = 0; i < tot; i++) {
            String[] tmp = br.readLine().split(" ");
            int startDay = Integer.parseInt(tmp[0]) * 100 + Integer.parseInt(tmp[1]);
            int endDay = Integer.parseInt(tmp[2]) * 100 + Integer.parseInt(tmp[3]);
            flowers[i] = new Flower(startDay, endDay);
        }
    }

    // 꽃
    // - 피고 지는 날짜가 있음
    static class Flower implements Comparable<Flower> {

        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            return this.start == o.start ? o.end - this.end : this.start - o.start;
        }
    }
}

/**
import java.io.*;
import java.util.*;

public class Main {
    static int tot, prev = 301, max = 1130, cnt = 0, ans = 1;
    static int[][] flowers;
    static Map<Integer, Integer> map;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();

        Arrays.sort(flowers, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        map = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.reverseOrder());

        int start = flowers[0][0];
        int end = flowers[0][1];
        while (cnt < flowers.length && end < max) {
            if (start > prev) {
                cnt++;
                continue;
            }

            cnt++;
            start = flowers[cnt][0];

            while (start > end) cnt++;

            if (cnt >= flowers.length) break;

            pq.offer(flowers[cnt][1]);

            while (cnt+1 < flowers.length && flowers[cnt+1][0] <= end) {
                cnt++;
                pq.offer(flowers[cnt][1]);
            }

            prev = end;
            end = pq.poll();
            start = map.get(end);
            ans++;
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 첫째줄 : 꽃들의 총 개수
        tot = Integer.parseInt(br.readLine());
        flowers = new int[tot][2];

        // 2. 꽃이 피는 날짜 / 지는 날짜
        for (int i = 0; i < tot; i++) {
            String[] tmp = br.readLine().split(" ");
            flowers[i][0] = Integer.parseInt(tmp[0]) * 100 + Integer.parseInt(tmp[1]);
            flowers[i][1] = Integer.parseInt(tmp[2]) * 100 + Integer.parseInt(tmp[3]);
            map.put(flowers[i][1], flowers[i][0]);
        }
    }
}
 */
