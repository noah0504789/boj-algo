package org.example.backtracking;

// 암호
// - L개의 알파벳 소문자
// - 최소 한개의 모음, 최소 두개의 자음을 구성됨
// - 알파벳 순으로 생성됨

import java.io.*;
import java.util.*;

public class 암호_만들기 {

    static StringBuffer sb = new StringBuffer();
    static String[] alps, arr;
    static int L, C;
    static int[] input, GATHER = {'a'-'a', 'e'-'a', 'i'-'a', 'o'-'a', 'u'-'a'};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, 0);

        System.out.println(sb.toString());
    }

    private static void dfs(int start, int depth) {
        if (depth == L) {
            String pwd = validPwd();
            if (pwd == null) return;

            sb.append(pwd);
            sb.append('\n');
            return;
        }

        for (int i = start; i < C; i++) {
            arr[depth] = alps[i];
            dfs(i+1, depth+1);
        }
    }

    private static String validPwd() {
        StringBuffer sb2 = new StringBuffer();

        visited = new boolean['z'-'a'+1];

        for (String a : arr) {
            sb2.append(a);
            visited[a.charAt(0)-'a'] = true;
        }

        int g_cnt = 0;
        for (int g : GATHER) {
            if (visited[g]) g_cnt++;
        }

        if (g_cnt == 0 || L - g_cnt < 2) return null;
        return sb2.toString();
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        L = input[0];
        C = input[1];
        arr = new String[L];

        alps = br.readLine().split(" ");
        Arrays.sort(alps);
    }
}
