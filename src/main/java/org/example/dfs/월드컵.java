package org.example.dfs;

import java.io.IOException;

/**
 * @author noah kim
 * @date 2024/03/02
 * @link https://www.acmicpc.net/problem/6987
 * @reference https://velog.io/@hyeon930/BOJ-6987-%EC%9B%94%EB%93%9C%EC%BB%B5-Java
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf
 */
public class 월드컵 {
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] home = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    private static final int[] away = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    private static final int TC = 4;
    private static final int GROUP_MEMBER_CNT = 6;
    private static final int POSSIBLE = 1;
    private static final int IMPOSSIBLE = 0;
    private static int[][] score = new int[GROUP_MEMBER_CNT][3];
    private static int total;

    public static void main(String[] args) throws IOException {
        for (int t = 0; t < TC; t++) {
            total = 0;

            for (int i = 0; i < GROUP_MEMBER_CNT; i++) {
                for (int j = 0; j < 3; j++) {
                    score[i][j] = readInt();
                    total += score[i][j];
                }
            }

            if (total > 30) {
                sb.append(IMPOSSIBLE + " ");
                continue;
            }

            sb.append(dfs(0) ? POSSIBLE : IMPOSSIBLE).append(" ");
        }

        System.out.println(sb);
    }

    private static boolean dfs(int game) {
        if (game == 15) return true;

        if (score[home[game]][0]>0 && score[away[game]][2]>0) {
            score[home[game]][0]--;
            score[away[game]][2]--;
            if(dfs(game+1)) return true;
            score[home[game]][0]++;
            score[away[game]][2]++;
        }

        if (score[home[game]][2]>0 && score[away[game]][0]>0) {
            score[home[game]][2]--;
            score[away[game]][0]--;
            if(dfs(game+1)) return true;
            score[home[game]][2]++;
            score[away[game]][0]++;
        }

        if (score[home[game]][1]>0 && score[away[game]][1]>0) {
            score[home[game]][1]--;
            score[away[game]][1]--;
            if(dfs(game+1)) return true;
            score[home[game]][1]++;
            score[away[game]][1]++;
        }

        return false;
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > 1<<5) {
            n = (n<<1)+(n<<4)+c&((1<<4)-1);
        }
        return n;
    }
}
