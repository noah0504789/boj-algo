package org.example.greedy;

import java.io.*;
import java.util.*;

public class ATM {
    static int N, prev;
    static Integer[] P;

    public static void main(String[] args) throws IOException {
        init();

        Arrays.sort(P);

        int ans = 0;

        for(int i = 0; i < N; i++) {
            ans += P[i] * (N-i);
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new Integer[N];

        String[] PInput = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(PInput[i]);
        }
    }
}
