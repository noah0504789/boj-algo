package org.example;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/02
 * @link https://www.acmicpc.net/problem/2023
 * @keyword_solution
Requirements : 주어진 정수 N에서, 신기한 소수를 모두 출력하라.

신기한 소수
- 첫째자리부터 0~N자리까지의 숫자로 만들어진 부분집합이 모두 소수인 수
 * @input
N (자릿수. 1<=N<=8)
 * @output
N자리 수 중에서 모든 신기한 소수를 출력
- 정렬: 오름차순
- 한줄에 하나씩
 * @time_complex
 * @perf
 */

public class 신기한_소수_에라토스테네스의_체_메모리_초과 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer(), sb2 = new StringBuffer();
    private static final int startPrimeNum = 2;
    private static List<Integer> primes = new ArrayList<>();
    private static int N;
    private static boolean isPrime;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        int startNum = (int) Math.pow(10, N-1);
        int endNum = (int) Math.pow(10, N)-1;

        sieve(startPrimeNum, endNum);

        for (int num = startNum; num <= endNum; num++) {
            isPrime = true;

            sb.append(num);

            for (int i = 1; i <= N; i++) {
                CharSequence substring = sb.subSequence(0, i);
                int subsetOfNum = Integer.parseInt(substring.toString());

                if (i == 1) {
                    if (subsetOfNum != 2 || subsetOfNum != 3 || subsetOfNum != 5 || subsetOfNum != 7) {
                        isPrime = false;
                        break;
                    }
                }

                if (subsetOfNum < startPrimeNum || !primes.contains(subsetOfNum)) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) sb2.append(num + "\n");

            sb.setLength(0);
        }

        System.out.println(sb2);
    }

    private static List<Integer> sieve(int startNum, int endNum) {
        boolean[] visited = new boolean[endNum-startNum+1];
        Arrays.fill(visited, true);

        for (int num=startNum; num*num<=endNum; num++) {
            int numIdx = num - startNum;
            if (!visited[numIdx]) continue;

            for (int compo=num*num; compo<=endNum; compo+=num) {
                int compIdx = compo - startNum;
                visited[compIdx] = false;
            }
        }

        for (int num=startNum; num<=endNum; num++) {
            int numIdx = num - startNum;
            if (visited[numIdx]) primes.add(num);
        }

        return primes;
    }
}
