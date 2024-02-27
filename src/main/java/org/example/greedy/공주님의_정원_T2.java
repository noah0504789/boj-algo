package org.example.greedy;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-02-27
 * @link
 * @requirement
 * @keyword
 * @input
 * @output
 * @time_complex
 * @perf
 */

public class 공주님의_정원_T2 {
    // 프리랜서

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, ans, sM, sD, eM, eD;
    static class Day {
        final int month, day;

        public Day(int month, int day) {
            this.month = month;
            this.day = day;
        }
    }

    static class WorkDay {
        final Day startDay, endDay;

        public WorkDay(Day startDay, Day endDay) {
            this.startDay = startDay;
            this.endDay = endDay;
        }

        public boolean isConcentratePeriod() {
            // TODO : 일하는 날이 집중기간(3/1~11/30)에 하는 날인지 체크. (하루라도 겹치는지 체크)

            return true;
        }

        public int getCntOfWorkDay() {
            // TODO: 일한 날짜 리턴하기

            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sM = Integer.parseInt(st.nextToken());
            sD = Integer.parseInt(st.nextToken());
            eM = Integer.parseInt(st.nextToken());
            eD = Integer.parseInt(st.nextToken());

            // TODO: WorkDay 보관하기
        }

        // TODO: 시작날짜 정하기. 시작날짜를 포함하는 일정 for문 돌리기 -> ans min값 찾기
        // TODO: (집중기간 일정들 대상. 정렬 시작일자 ASC then 일할 날 DESC) 그리디 알고리즘
        // TODO: - 다음 일정의 시작일자가 현재 방문한 일정의 종료일자보다 작으면 pass
        // TODO: - 다음 일정의 시작일자가 현재 방문한 일정의 종료일자+1이 아니면 -> 0 출력. 프로그램 종료

        System.out.println(ans);

        br.close();
    }
}
