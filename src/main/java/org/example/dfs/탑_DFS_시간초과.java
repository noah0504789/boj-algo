package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-02-05
 * @link https://www.acmicpc.net/problem/2493
 * @keyword_solution
    Requirements: 각각의 탑에서 발사한 신호를 어느 탑에서 수신하는지 출력하라.

    [탑]
    - 송신: 최대 높이에서 왼쪽으로 수평 발사
    - 수신: 가장 먼저 만나는 하나의 탑에서 수신
 * @input
    - N(탑 개수.1<=N<=500_000)
    - 모든 탑들의 높이(1<=T<=100_000_000.서로 다름)
 * @output
    - 신호를 수신한 탑의 인덱스들
 * @time_complex O(N^2)
 * @perf
 */
public class 탑_DFS_시간초과 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final String DELIMITER = " ";
    private static int[] towers, ans;
    private static int length;

    public static void main(String[] args) throws IOException {
        length = Integer.parseInt(br.readLine());
        towers = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
        ans = new int[length];

        for (int i = length-1; i >= 0; i--) {
            dfs(i, i-1);
        }

        for (int i = 0; i < length; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb.toString().trim());

        br.close();
    }

    private static void dfs(int start, int search) {
        if (search == -1) {
            ans[start] = 0;
            return;
        } else if (towers[start] < towers[search]) {
            ans[start] = search+1;
            return;
        }

        dfs(start, search-1);
    }
}
