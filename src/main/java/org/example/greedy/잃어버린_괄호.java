package org.example.greedy;

import java.io.*;
import java.util.*;

public class 잃어버린_괄호 {

    static int ans;
    static String s;

    public static void main(String[] args) throws IOException {
        init();

        String[] sMinusLst = s.split("-");
        for (int i = 0; i < sMinusLst.length; i++) {
            int tmp = mySum(sMinusLst[i]);
            if (i == 0) ans += tmp;
            else ans -= tmp;
        }

        System.out.println(ans);
    }

    private static int mySum(String s) {
        return Arrays.stream(s.split("\\+")).mapToInt(Integer::parseInt).sum();
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();

        ans = 0;
    }
}
