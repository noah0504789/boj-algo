package org.example;

import java.io.IOException;
import java.util.Arrays;

// reference @qlenfrl0577

public class 공주님의_정원2 {
    static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public static void main(String[] args) throws Exception {
        for (int i=1; i < days.length; i++) days[i]+=days[i-1];

        int n = readInt();
        int[] memo = new int[366];

        for (int i=1; i<=n; i++) {
            int sM = readInt(), sD = readInt();
            int eM = readInt(), eD = readInt();

            int start = days[sM-1]+sD;
            int end = days[eM-1]+eD;

            for (int d = start; d < end; d++) memo[d] = Math.max(memo[d], end);
        }

        int curD=60, curCnt=0;

        while (curD < 335) {
            curD = memo[curD];
            curCnt++;

            if (curD == 0) {
                curCnt = 0;
                break;
            }
        }

        System.out.println(curCnt);
    }

    static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
