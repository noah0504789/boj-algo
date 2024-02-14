package org.example.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-14
 * @link https://www.acmicpc.net/problem/2805
 * @requirement 최소한의 벌목으로 필요한 만큼의 나무를 절단할 때, 절단기의 높이를 출력하라.
 * @keyword
    [나무]
    - 길이: M미터

    [절단기]
    - 높이설정 가능
    - 벌목: 절단된 부분(설정된 높이 윗 부분)
 * @input N: 나무 수(1<=N<=1_000_000), M: 나무의 길이(1<=M<=2_000_000_000)
 * @output
 * @time_complex
 * @perf
 */
public class 나무_자르기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] trees;
    private static long ans;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        binarySearch(0, Arrays.stream(trees).max().getAsInt());

        System.out.println(ans);
    }

    private static void binarySearch(int low, int high) {
        if (low > high) return;

        int mid = (low + high) / 2;
        long cnt = getTreeCnt(mid);

        if (cnt >= M) {
            ans = Math.max(ans, mid);
            binarySearch(mid + 1, high);
        } else {
            binarySearch(low, mid - 1);
        }
    }

    private static long getTreeCnt(int limit) {
        long cnt = 0;

        for (int tree : trees) if (tree > limit) cnt += tree - limit;

        return cnt;
    }
}
