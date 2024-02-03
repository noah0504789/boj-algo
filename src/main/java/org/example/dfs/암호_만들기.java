package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/03
 * @link https://www.acmicpc.net/problem/1759
 * @keyword_solution
    Requirements: 암호가 될 수 있는 문자열을 사전순으로 모두 출력하라.

    [암호]
    - 개수: L개
    - 구성
        1. 알파벳 소문자
        2. 중복 X
        3. 순서 상관 X
        4. 최소 한 개의 모음 (a,e,i,o,u)
        5. 최소 두 개의 자음
 * @input
    - L(암호 문자열 길이), C(암호에 쓰일 수 있는 알파벳 개수) (3<=L<=C<=15)
    - 암호에 쓰일 수 있는 알파벳들
 * @output
    - 암호가 될 수 있는 문자열 출력 (각 줄에 하나씩)(사전순으로)
 * @time_complex O()
 * @perf
 */
public class 암호_만들기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final String GATHERS = "aeiou";
    private static char[] secretChars, code;
    private static int L, C;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        secretChars = new char[C];
        code = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) secretChars[i] = st.nextToken().charAt(0);

        Arrays.sort(secretChars);

        dfs(0, 0, 0, 0);

        System.out.println(sb);

        br.close();
    }

    private static void dfs(int depth, int start, int gatherCnt, int consonantCnt) {
        if (depth == L) {
            if (gatherCnt >= 1 && consonantCnt >= 2) sb.append(String.valueOf(code) + "\n");

            return;
        }

        for (int i = start; i < secretChars.length; i++) {
            char secretChar = secretChars[i];
            code[depth] = secretChar;

            if (GATHERS.contains(String.valueOf(secretChar))) dfs(depth+1, i+1, gatherCnt+1, consonantCnt);
            else dfs(depth+1,  i+1, gatherCnt, consonantCnt+1);
        }
    }
}
