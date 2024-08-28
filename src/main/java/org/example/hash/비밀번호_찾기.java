package org.example.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 비밀번호_찾기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int siteCnt, pwdCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        siteCnt = Integer.parseInt(st.nextToken());
        pwdCnt = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < siteCnt; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken(), pwd = st.nextToken();

            map.put(site, pwd);
        }

        for (int i = 0; i < pwdCnt; i++) {
            sb.append(map.get(br.readLine().trim()));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
