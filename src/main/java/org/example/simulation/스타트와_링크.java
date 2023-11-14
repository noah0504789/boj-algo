package org.example.simulation;

import java.io.*;
        import java.util.*;

public class 스타트와_링크 {

    static int N, myPwr = 0, counterPwr = 0, ans = 200;
    static int[] input, aTeam, bTeam;
    static int[][] nums;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1, 0);

        System.out.println(ans);
    }

    private static void dfs(int start, int depth) {
        if (depth == N/2) {
            bTeam();
            myPwr = power(aTeam);
            counterPwr = power(bTeam);

            ans = Math.min(ans, Math.abs(myPwr-counterPwr));
            if (ans == 0) {
                System.out.println(ans);
                System.exit(0);
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            aTeam[depth] = i;
            dfs(i+1, depth+1);
        }
    }

    private static void bTeam() {
        boolean[] visited = new boolean[N+1];
        for (int a : aTeam) {
            visited[a] = true;
        }

        int idx = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            bTeam[idx++] = i;
        }
    }

    private static int power(int[] team) {
        int power = 0;
        int lastIdx = team.length-1;
        for (int i = 0; i < lastIdx; i++) {
            for (int j = i+1; j <= lastIdx; j++) {
                int firstIdx = team[i]-1;
                int secondIdx = team[j]-1;
                power += nums[firstIdx][secondIdx];
                power += nums[secondIdx][firstIdx];
            }
        }

        return power;
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        nums = new int[N][N];
        aTeam = new int[N/2];
        bTeam = new int[N/2];

        for (int i = 0; i < N; i++) {
            nums[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
