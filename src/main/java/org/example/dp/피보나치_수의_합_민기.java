package org.example.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class 피보나치_수의_합_민기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final long MOD = 1_000_000_000;
    private static long[] input;
    private static long a, b;

    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        a = input[0];
        b = input[1];

        System.out.println((fibSum(b) - fibSum(a-1) + MOD)%MOD);
    }

    private static long fib(long n) {
        return power(new long[][] {{1,1},{1,0}}, n)[0][1];
    }

    private static long[][] power(long[][] matrix, long n) {
        if (n == 0) return new long[][] {{1,0},{0,1}}; // 단위 행렬

        long[][] half = power(matrix,n/2);

        return n%2==0 ? mul(half,half) : mul(matrix, mul(half,half));
    }

    private static long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    c[i][j] = (c[i][j] + a[i][k]*b[k][j]) % MOD;
                }
            }
        }

        return c;
    }

    private static long fibSum(long n) {
        return (fib(n+2) - 1 + MOD) % MOD;
    }
}
