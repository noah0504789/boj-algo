package org.example.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class 보물 {

    static int N;
    static Integer[] A, B;

    public static void main(String[] args) throws IOException {
        init();

        Arrays.sort(A);
        Arrays.sort(B, Comparator.reverseOrder());

        int ans = 0;

        for (int i = 0; i < N; i++) ans += A[i]*B[i];

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new Integer[N];
        B = new Integer[N];

        String[] AInput = br.readLine().split(" ");
        String[] BInput = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(AInput[i]);
            B[i] = Integer.parseInt(BInput[i]);
        }
    }
}
