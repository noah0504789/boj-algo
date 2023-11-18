package org.exam.monitorlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 숫자 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, MAX_DIGIT = 11;
    static int[] digitLength;

    public static void main(String[] args) throws IOException {
        init();

        digitLength[1] = 9;
        int cnt = 9;
        for (int i = 2; i <= MAX_DIGIT; i++) {
            digitLength[i] += digitLength[i-1];
            digitLength[i] += i * Math.pow(10, i-1) * cnt;
        }

        int digit = 0;
        for (int i = 1; i <= MAX_DIGIT; i++) {
            if (N <= digitLength[i]) {
                digit = i;
                break;
            }
        }

        int offset = N-digitLength[digit-1]-1;
        int start = (int) Math.pow(10, digit-1);
        int number = start + offset / digit;
        int numberIdx = offset % digit;

        int ans = String.valueOf(number).charAt(numberIdx) - '0';

        System.out.println(ans);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        digitLength = new int[MAX_DIGIT+1];
    }
}
