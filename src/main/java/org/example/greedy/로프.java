package org.example.greedy;

// 로프를 이용하여 들어올릴 수 있는 물체의 최대 중량

// 로프
// - 여러개의 로프를 병렬로 연결하면 각각의 로프에 중량을 고르게 나눌 수 있음

import java.io.*;
import java.util.*;
public class 로프 {

    static int tot, ans;
    static Integer[] ropes;
    public static void main(String[] args) throws IOException {
        init();

        Arrays.sort(ropes, (a, b) -> b - a);

        ans = 0;

        for (int i = 0; i < tot; i++) {
            ans = Math.max(ans, ropes[i]*(i+1));
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 첫째줄 : 로프 수
        tot = Integer.parseInt(br.readLine());
        ropes = new Integer[tot];

        // 2. 각 로프 무게
        for (int i = 0; i < tot; i++) {
            int rope = Integer.parseInt(br.readLine());
            ropes[i] = rope;
        }
    }
}
