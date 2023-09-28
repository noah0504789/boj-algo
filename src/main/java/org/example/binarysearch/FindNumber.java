package org.example.binarysearch;

import java.io.*;
import java.util.*;

// 문제링크 : https://www.acmicpc.net/problem/1920
public class FindNumber {

    static int N, M;
    static int[] A, B;
    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(A);
        for (int i = 0; i < M; i++) {
            binarySearch(i);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken(" "));
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st2.nextToken(" "));
        }
    }

    private static void binarySearch(int idx) {
        int left = 0, right = A.length-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == B[idx]) {
                System.out.println(1);
                return;
            } else if (A[mid] > B[idx]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(0);
    }
}
