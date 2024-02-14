package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-14
 * @link https://www.acmicpc.net/problem/6987
 * @requirement 주어진 네개의 결과가 가능한지 여부를 출력하라.
 * @keyword
    [조별예선]
    - 구성: 6개국
    - 경기방식: 조에 소속된 국가들과 한번씩 각각 경기
 * @input TC: 4
    승 무 패 * 6개국
 * @output
 * @time_complex
 * @perf
 */
public class 월드컵_실패 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final int T = 4;
    private static final int countryCnt = 6;
    private static final int wdlCnt = 3;
    private static final int MINE_SIGNAL = -1;
    private static final int WIN_SIGNAL = 100;
    private static final int DRAW_SIGNAL = 200;
    private static final int LOSE_SIGNAL = 300;

    private static StringTokenizer st;
    private static int[][] table, check;
    private static boolean isCheckCompleted;

    public static void main(String[] args) throws IOException {
        for (int t = 1; t <= T; t++) {
            table = new int[countryCnt][3];
            check = new int[countryCnt][countryCnt];
            isCheckCompleted = false;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < countryCnt; i++) {
                for (int j = 0; j < wdlCnt; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, new int[] {0, 0, 0, 0, 0, 0});

            if (isCheckCompleted) sb.append(1+" ");
            else sb.append(0+" ");
        }

        System.out.println(sb);

        br.close();
    }

    private static void dfs(int depth, int[] prev) {
        if (depth == countryCnt) {
            isCheckCompleted = true;
            return;
        }

        // TODO: NP로 생성된 배열 dfs (백트래킹)
        // 1. prev
        // 2. perm 생성 (table 정보)
        // 3. 최종 순열 생성
        // 4. 전달할 prev 구성 및 전달 (0~depth = 이전 상대의 경기 결과, depth+1 = 자신)

        if (isCheckCompleted) return;

        int[] perm = prev.clone();

        for (int i = 0; i < depth; i++) {
            int counter = check[i][depth];

            perm[i] = counter == WIN_SIGNAL ? LOSE_SIGNAL : WIN_SIGNAL;
        }

        perm[depth] = MINE_SIGNAL;

        int wCnt = table[depth][0], dCnt = table[depth][1], lCnt = table[depth][2];

        for (int i = 0; i < depth; i++) {
            if (perm[i] == WIN_SIGNAL) wCnt--;
            else if (perm[i] == DRAW_SIGNAL) dCnt--;
            else if (perm[i] == LOSE_SIGNAL) lCnt--;
        }

        if (wCnt < 0 || dCnt < 0 || lCnt < 0) return;

        int permIdx = depth+1;
        while (wCnt > 0) {
            perm[permIdx++] = WIN_SIGNAL;
            wCnt--;
        }

        while (dCnt > 0) {
            perm[permIdx++] = DRAW_SIGNAL;
            dCnt--;
        }

        while (lCnt > 0) {
            perm[permIdx++] = LOSE_SIGNAL;
            lCnt--;
        }

        do {
            if (!isPossible(depth, perm)) continue;
            marking(depth, perm);
            dfs(depth+1, perm);
        } while(np(perm, depth+1));
    }

    private static void marking(int depth, int[] perm) {
        for (int i = 0; i < perm.length; i++) {
            check[depth][i] = perm[i];
        }
    }

    private static boolean np(int[] perm, int srcIdx) {
        int inflectIdx = perm.length-1;
        while (inflectIdx > srcIdx && perm[inflectIdx-1] >= perm[inflectIdx]) inflectIdx--;

        if (inflectIdx == srcIdx) return false;

        int targetIdx = perm.length-1;
        while (inflectIdx > srcIdx && perm[inflectIdx-1] >= perm[targetIdx]) targetIdx--;

        swap(perm, inflectIdx-1, targetIdx);

        reverse(perm, inflectIdx, perm.length-1);

        return true;
    }

    private static void swap(int[] perm, int srcIdx, int destIdx) {
        int tmp = perm[srcIdx];
        perm[srcIdx] = perm[destIdx];
        perm[destIdx] = tmp;
    }

    private static void reverse(int[] perm, int left, int right) {
        while (left < right) {
            swap(perm, left++, right--);
        }
    }

    private static boolean isPossible(int depth, int[] perm) {
        int[] gameInfo = table[depth];
        int needW = gameInfo[0], needD = gameInfo[1], needL = gameInfo[2];

        for (int i = 0; i < perm.length; i++) {
            if (perm[i] == WIN_SIGNAL) needW--;
            else if (perm[i] == DRAW_SIGNAL) needD--;
            else if (perm[i] == LOSE_SIGNAL) needL--;
        }

        return needW == 0 && needD == 0 && needL == 0;
    }
}
