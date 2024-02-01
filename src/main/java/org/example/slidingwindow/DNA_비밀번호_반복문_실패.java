package org.example.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-02-01
 * @link
 * @keyword_solution
    Requirements : 만들 수 있는 비밀번호의 갯수를 출력하라.

    - dnaChars = {‘A’, ‘C’, ‘G’, ’T’}
    - DNA 문자열
      - dnaChars의 요소 만으로 구성된 문자열
    - 비밀번호
      - DNA 문자열의 부분문자열
      - dnaChars 요소별 주어진 개수 이상으로 구성됨
      - 순서 의미 있음
 * @input
    - S(DNA 문자열 길이), P(비밀번호 길이)
    - DNA 문자열
    - dnaChars 요소별 등장 필요 횟수 (배열의 인덱스 순으로)
 * @output
    - 만들 수 있는 비밀번호 수
 * @time_complex O(N^2)
 * @perf
 */

public class DNA_비밀번호_반복문_실패 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final String INPUT_DELIMITER = " ";
    private static final char[] dnaChars = {'A', 'C', 'G', 'T'};
    private static final int A_IDX = 0, C_IDX = 1, G_IDX = 2, T_IDX = 3;
    private static String dnaStr;
    private static int[] tmp, needCntArr, nonRequiredCntArr = new int[dnaChars.length], dnaStrCntArr = new int[dnaChars.length];
    private static int S, P, needTotCnt, permCntUsingNeeds, permCntWithoutNeed;

    public static void main(String[] args) throws IOException {
        tmp = Arrays.stream(br.readLine().split(INPUT_DELIMITER)).mapToInt(Integer::parseInt).toArray();
        S = tmp[0];
        P = tmp[1];

        dnaStr = br.readLine();
        dnaStr.chars().forEach(c -> {
            if (c == dnaChars[A_IDX])      dnaStrCntArr[A_IDX]++;
            else if (c == dnaChars[C_IDX]) dnaStrCntArr[C_IDX]++;
            else if (c == dnaChars[G_IDX]) dnaStrCntArr[G_IDX]++;
            else if (c == dnaChars[T_IDX]) dnaStrCntArr[T_IDX]++;
        });

        needCntArr = Arrays.stream(br.readLine().split(INPUT_DELIMITER)).mapToInt(Integer::parseInt).toArray();
        needTotCnt = Arrays.stream(needCntArr).sum();

        nonRequiredCntArr[A_IDX] = dnaStrCntArr[A_IDX] - needCntArr[A_IDX];
        nonRequiredCntArr[C_IDX] = dnaStrCntArr[C_IDX] - needCntArr[C_IDX];
        nonRequiredCntArr[G_IDX] = dnaStrCntArr[G_IDX] - needCntArr[G_IDX];
        nonRequiredCntArr[T_IDX] = dnaStrCntArr[T_IDX] - needCntArr[T_IDX];

        if (nonRequiredCntArr[A_IDX] < 0 || nonRequiredCntArr[C_IDX] < 0 || nonRequiredCntArr[G_IDX] < 0 || nonRequiredCntArr[T_IDX] < 0) {
            System.out.println(0);
            return;
        }

        countPermUsingNeeds(0);

        if (needTotCnt == P) {
            System.out.println(permCntUsingNeeds);
            return;
        }

        countPermWithoutNeeds(0);

        System.out.println(permCntUsingNeeds * permCntWithoutNeed);
    }

    private static void countPermUsingNeeds(final int depth) {
        if (depth == needTotCnt) {
            permCntUsingNeeds++;
            return;
        }

        for (int idx = 0; idx < needCntArr.length; idx++) {
            if (needCntArr[idx] > 0) {
                needCntArr[idx]--;
                countPermUsingNeeds(depth+1);
                needCntArr[idx]++;
            }
        }
    }

    private static void countPermWithoutNeeds(final int depth) {
        if (depth == P - needTotCnt) {
            permCntWithoutNeed++;
            return;
        }

        for (int idx = 0; idx < nonRequiredCntArr.length; idx++) {
            if (nonRequiredCntArr[idx] > 0) {
                nonRequiredCntArr[idx]--;
                countPermWithoutNeeds(depth+1);
                nonRequiredCntArr[idx]++;
            }
        }
    }
}
