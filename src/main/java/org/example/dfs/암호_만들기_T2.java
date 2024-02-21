package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-02-21
 * @link https://www.acmicpc.net/problem/1759
 * @requirement 주어진 소문자 알파벳들을 가지고 주어진 조건을 만족하는 모든 암호문들을 사전순으로 출력하라.
 * @summary
    [암호문]
    - 서로 다른 알파벳
    - 길이: L
    - 구성
        - 자음: 2개 이상
        - 모음: 1개 이상
    [출력]
    - 사전순: 조합
 * @input
    - L: 암호문 길이, C: 구성할 수 있는 소문자 알파벳 개수(3<=L<=C<=15)
 * @output
 * @time_complex
 * @perf
 */
public class 암호_만들기_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final StringBuffer password = new StringBuffer();
    private static final int GATHERS_BIT = (1<<'a'-'a') | (1<<'e'-'a') | (1<<'i'-'a') | (1<<'o'-'a') | (1<<'u'-'a');
    private static StringTokenizer st;
    private static char[] alpha;
    private static int L, C;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alpha = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpha);

        dfs(0, 0, 0, 0);

        System.out.println(sb.toString().trim());

        br.close();
    }

    private static void dfs(int start, int depth, int gCnt, int cCnt) {
        if (depth == L) {
            if (gCnt < 1) return;
            if (cCnt < 2) return;

            sb.append(password)
              .append("\n");

            return;
        }

        for (int i = start; i < alpha.length; i++) {
            password.append(alpha[i]);
            if ((GATHERS_BIT & 1<<alpha[i]-'a') > 0) dfs(i+1, depth+1, gCnt+1, cCnt);
            else dfs(i+1, depth+1, gCnt, cCnt+1);
            password.setLength(password.length()-1);
        }
    }
}
