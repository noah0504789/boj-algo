package org.example.dp;

import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024/03/01
 * reference @kky156
 */
public class 외판원_순회_2_DP {
    private static final int INF = 20000000;
    private static final int INIT = -1;
    private static final int NO_WAY = 0;
    private static int[][] adjMatrix;
    private static int[][] dp; // i도시를 시작으로 j의 방문상태일 때, 남은 도시들을 모두 방문하고 i도시로 돌아가는데 드는 비용의 최소합
    private static int N, start, end;

    public static void main(String[] args) throws Exception {
        N = read();
        adjMatrix = new int[N][N];
        dp = new int[N][1<<N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = read();
            }

            Arrays.fill(dp[i], INIT);
        }

        start = end = 0;

        System.out.println(dfs(start, 1<<start));
    }

    private static int dfs(int src, int visitBit) {
        if (visitBit == (1<<N)-1) return adjMatrix[src][end] == NO_WAY ? INF : adjMatrix[src][end];

        if (dp[src][visitBit] != INIT) return dp[src][visitBit];

        dp[src][visitBit] = INF;

        for (int dest = 0; dest < N; dest++) {
            if ((visitBit&(1<<dest)) > 0) continue;

            int toDestD = adjMatrix[src][dest];
            if (toDestD == NO_WAY) continue;

            dp[src][visitBit] = Math.min(dp[src][visitBit], dfs(dest, visitBit|(1<<dest)) + toDestD);
        }

        return dp[src][visitBit];
    }

    private static int read() throws Exception {
        int c, n = System.in.read()&(1<<4)-1;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & (1<<4)-1);
        return n;
    }
}
