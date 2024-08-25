package org.example.hashtable;

import java.io.*;
import java.util.*;

public class 수강신청 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int K, L;

    // 수강신청
    // - 선착순
    // - 배치되었는데 또 누르면 마지막 것으로
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Map<String, Integer> hashtable = new HashMap<>();

        for (int idx = 0; idx < L; idx++) {
            String studentId = br.readLine();

            hashtable.put(studentId, idx);
        }

        String[] ans = new String[L];

        for (Map.Entry<String, Integer> e : hashtable.entrySet()) ans[e.getValue()] = e.getKey();

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for (String s : ans) {
            if (s == null) continue;
            if (cnt++ >= K) break;
            sb.append(s);
            sb.append("\n");
        }

        System.out.println(sb);

//        hashtable.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(K).forEach(e -> System.out.println(e.getKey()));
    }
}
