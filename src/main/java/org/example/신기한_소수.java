package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author noah kim
 * @date 2024/02/02
 * @link https://www.acmicpc.net/problem/2023
 * @keyword_solution
    Requirements : 주어진 정수 N의 자리의 수들 중, 신기한 소수를 모두 출력하라.

    신기한 소수
    - 첫째자리부터 0~N자리까지의 숫자로 만들어진 부분집합이 모두 소수인 수
 * @input
    N (자릿수. 1<=N<=8)
 * @output
    N자리 수 중에서 모든 신기한 소수를 출력
    - 정렬: 오름차순
    - 한줄에 하나씩
 * @time_complex O(5^N * sqrt(M)). N: 자릿수, M: 소수인지 판단하려는 숫자
 * @perf
 */
public class 신기한_소수 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        countPrime(2, 1);
        countPrime(3, 1);
        countPrime(5, 1);
        countPrime(7, 1);

        System.out.println(sb);
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    private static void countPrime(int num, int len) {
        if (len == N) {
            sb.append(num + "\n");
            return;
        }

        for (int i = 1; i < 10; i+=2) {
            if (isPrime(num*10+i)) {
                countPrime(num*10+i, len+1);
            }
        }
    }
}
