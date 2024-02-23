package org.example;

/**
 * @author noah kim
 * @date 2024-02-23
 * @link https://www.acmicpc.net/problem/15961
 * @requirement 손님이 먹을 수 있는 가능한 최대의 먹을 수 있는 초밥 종류 수 구하라.
 * @summary
    [벨트]
    - 구조: 원형
    - 아이템: 초밥(중복 O)

    [초밥]
    - 종류: 번호별

    [세트]
    - 품목 수: k개
    - 초밥: (벨트 내에서) 연쇄적인 위치 관계를 가짐
    - 쿠폰: 세트 구매 시, 쿠폰에 쓰인 초밥 증정
 * @input
    - N:벨트 길이(2<=N<=3_000_000), d:초밥 종류 수(2<=d<=3_000), k:셋트 품목 개수(2<=k<=3_000), c:쿠폰번호
 * @output
 * @time_complex
 * @perf 26660kb / 256ms
 */
public class 회전_초밥 {
    private static int[] belt, plate;
    private static int N, d, k, c, ans, cnt, prevFirstIdx;
    private static int MAX_CNT;

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        N = reader.nextInt();
        d = reader.nextInt();
        k = reader.nextInt();
        c = reader.nextInt();

        MAX_CNT = k + 1;

        belt = new int[N+k];
        for (int i = 0; i < N; i++) {
            belt[i] = reader.nextInt();
        }

        for (int i = 0; i < k; i++) {
            belt[N+i] = belt[i];
        }

        plate = new int[d+1];
        plate[c] = 10000;
        cnt++;

        storeSet(0, k);

        prevFirstIdx = 0;

        if ((ans = cnt) < MAX_CNT) {
            for (int startIdx = 1; startIdx < N; startIdx++) {
                sliding(startIdx+k-1);

                ans = Math.max(ans, cnt);

                if (ans == MAX_CNT) break;
            }
        }

        System.out.println(ans);
    }

    private static void storeSet(int startIdx, int size) {
        for (int idxToAdd = startIdx; idxToAdd < startIdx+size; idxToAdd++) {
            int foodToAdd = belt[idxToAdd];

            if (plate[foodToAdd]++ == 0) cnt++;
        }
    }

    private static void sliding(int lastIdx) {
        int foodToRemove = belt[prevFirstIdx++];
        if (--plate[foodToRemove] == 0) cnt--;

        int foodToAdd = belt[lastIdx];
        if (plate[foodToAdd]++ == 0) cnt++;
    }
}
