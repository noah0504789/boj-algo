package org.example.hash;

import java.io.*;
import java.util.*;

// 온라인 모임 출석 관리 (채팅 확인)
// 스트리밍 시작 -> 모임 시작 -> 모임 종료 -> 스트리밍 종료
//   00:00

// 입장
// - 모임 시작 전
// - 모임 시작

// 퇴장
// - 모임 종료
// - 스트리밍 종료
public class 싸이버개강총회 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static String line;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        Set<String> entered = new HashSet<>(), exited = new HashSet<>();

        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);

            String time = st.nextToken();
            String nickname = st.nextToken();

            if (time.compareTo(S) <= 0) entered.add(nickname);
            else if (time.compareTo(E) >= 0 && time.compareTo(Q) <= 0) exited.add(nickname);
        }

        int cnt = 0;
        for (String nickname : entered) {
            if (exited.contains(nickname)) cnt++;
        }

        System.out.println(cnt);
    }
}
