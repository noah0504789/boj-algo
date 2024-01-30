package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author 김노아
 * @date 2024-01-30
 * @link https://www.acmicpc.net/problem/1244
 * @keyword_solution
    [스위치]
    - 번호 (1~100)
    - toggle 가능

    [학생]
    - 성별
    - 스위치
    - 할당 받은 수 (~스위치 개수)

    [스위치 조작]
    - 남학생
        - 스위치 번호가 자신의 수의 배수이면 -> toggle
    - 여학생
        - 자신의 수와 같은 수의 스위치를 기준으로 조건을 만족하는 구간의 스위치들 토글
        - 구간 조건
            1. 좌우 대칭
            2. 가능한 많은 스위치
 * @input
    - 스위치 개수 (100 이하 자연수)
    - 스위치 상태 (1-on, 0-off) (delimiter: " ")
    - 학생 수    (100 이하 자연수)
    - (성별 / 받은 수) * 학생 수
 * @output
    - 토글된 스위치 상태
       - 각 스위치별 빈칸 추가
        - 줄바꿈 : 20개 단위

    [예: 여학생(3)]
    - 스위치 번호 12345678
    - 스위치 상태 01110101 -> 1 0 0 0 1 1 0 1 (1~5)
 * @time_complex O(학생 수 * 스위치 개수)
 * @perf 16616kb / 172ms
 */

public class 스위치_켜고_끄기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int GENDER_IDX = 0, ASSIGNED_NUM_IDX = 1, MALE_CODE = 1, FEMALE_CODE = 2, LINE_BREAK_CNT = 20;
    private static int switchCnt, studentCnt;
    private static int[] curHistory;
    private static Boolean[] lastState;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        switchCnt = Integer.parseInt(br.readLine());
        lastState = Arrays.stream(br.readLine().split(" ")).map(s -> s.equals("1")).toArray(Boolean[]::new);
        studentCnt = Integer.parseInt(br.readLine());

        for (int num = 0; num < studentCnt; num++) {
            curHistory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            changeState(curHistory[GENDER_IDX], curHistory[ASSIGNED_NUM_IDX]);
        }

        IntStream.range(0, switchCnt).forEach(i -> {
            boolean isLastOfLine = i != 0 && i % (LINE_BREAK_CNT) == LINE_BREAK_CNT-1;
            char state = lastState[i] ? '1' : '0';

            sb.append(state + (isLastOfLine ? "\n" : " "));
        });

        System.out.println(sb.toString().trim());

        br.close();
    }

    private static void changeState(int genderCode, int num) {
        if (genderCode == MALE_CODE) {
            for (int i = num-1; i < switchCnt; i+=num) {
                lastState[i] = !lastState[i];
            }
        } else if (genderCode == FEMALE_CODE) {
            num--;
            lastState[num] = !lastState[num];

            for (int i = 1; i < switchCnt / 2; i++) {
                int left = num - i, right = num + i;

                if (left < 0 || right >= lastState.length) break;
                if (lastState[left] != lastState[right]) break;

                lastState[left] = !lastState[left];
                lastState[right] = !lastState[right];
            }
        }
    }
}

