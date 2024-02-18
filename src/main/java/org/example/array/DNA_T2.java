package org.example.array;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/18
 * @link https://www.acmicpc.net/problem/1969
 * @requirement 주어진 DNA 문자열들 중에서 Hamming Distance의 최소합의 DNA 문자열과 그 최소합을 출력하라. (여러 개 있을 경우, 사전순으로)
 * @summary
    [DNA]
    - 구성문자: A, T, G, C
    [Hamming Distance]
    - 같은 길이의 두 DNA 문자열 중, 각 위치의 문자가 다른 것의 개수
 * @input
    - N: DNA 수(1<=N<=1_000), M: DNA 문자열 길이(1<=M<50)
    - DNA 문자열
 * @output
 * @time_complex
 * @perf
 */
public class DNA_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static Map<Character, Integer> map = new HashMap<>();
    private static StringTokenizer st;
    private static String dna;
    private static char[][] dnas;
    private static int N, M, ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dnas = new char[M][N];

        for (int c = 0; c < N; c++) {
            dna = br.readLine();
            for (int r = 0; r < M; r++) {
                dnas[r][c] = dna.charAt(r);
            }
        }

        for (int i = 0; i < M; i++) {
            for (char c : dnas[i]) {
                map.put(c, map.getOrDefault(c, 0)+1);
            }

            Character c = map.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer> comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                    .findFirst()
                    .get()
                    .getKey();

            sb.append(c);
            ans += N - map.get(c);

            map.clear();
        }

        System.out.println(sb);
        System.out.println(ans);

        br.close();
    }
}
