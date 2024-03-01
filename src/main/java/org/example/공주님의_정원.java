package org.example;

import java.io.*;
import java.util.*;

import static java.util.Comparator.comparing;

public class 공주님의_정원 {
    private static final int curYear = 2024;
    private static Calendar start = Calendar.getInstance(), end = Calendar.getInstance(), s = Calendar.getInstance(), e = Calendar.getInstance(), cur = Calendar.getInstance();
    static {
        start.set(curYear, Calendar.MARCH, 1);
        end.set(curYear, Calendar.NOVEMBER, 30);
        cur.set(curYear, Calendar.MARCH, 1);
        s.set(Calendar.YEAR, curYear);
        e.set(Calendar.YEAR, curYear);
    }

    private static Flower vis, latest;
    private static List<Flower> flowers = new ArrayList<>();
    private static int N, sM, sD, eM, eD, idx = 0, cnt = 0, totCnt;

    static class Flower {
        final Calendar start, end;

        public Flower(int sM, int sD, int eM, int eD) {
            this.start = Calendar.getInstance();
            this.end = Calendar.getInstance();

            this.start.set(curYear, sM-1, sD);
            this.end.set(curYear, eM-1, eD);
        }

        public Calendar getStart() {
            return start;
        }
    }

    public static void main(String[] args) throws IOException {
        N = readInt();

        for (int i = 0; i < N; i++) {
            sM = readInt();
            sD = readInt();
            eM = readInt();
            eD = readInt();

            if (!isPeriodIn(sM, sD, eM, eD)) continue;

            flowers.add(new Flower(sM, sD, eM, eD));
        }

        totCnt = flowers.size();

        flowers.sort(comparing(Flower::getStart));

        while (isRemainToVisit() && isCurBeforeThanEnd()) {
            latest = null;

            vis = flowers.get(idx);
            while (isRemainToVisit() && isBeforeThanCur(vis.start)) {
                vis = flowers.get(idx++);
                if (isBeforeThanCur(vis.end)) continue;
                if (Objects.isNull(latest) || isAfter(vis.end, latest.end)) latest = vis;
            }

            if (Objects.isNull(latest)) break;

            cur = (Calendar) latest.end.clone();
            cur.add(Calendar.DATE, 1);
            cnt++;
        }

        if (isCurAfterThanEnd()) System.out.println(cnt);
        else System.out.println(0);
    }

    private static boolean isRemainToVisit() {
        return idx < totCnt;
    }

    private static boolean isPeriodIn(int sM, int sD, int eM, int eD) {
        s.set(Calendar.MONTH, sM-1);
        s.set(Calendar.DAY_OF_MONTH, sD);

        e.set(Calendar.MONTH, eM-1);
        e.set(Calendar.DAY_OF_MONTH, eD);

        return !(e.before(start) || s.after(end));
    }

    private static boolean isBeforeThanCur(Calendar date) {
        return date.before(cur) || date.equals(cur);
    }

    private static boolean isCurBeforeThanEnd() {
        return cur.before(end) || cur.equals(end);
    }

    private static boolean isAfter(Calendar date, Calendar end) {
        return date.after(end) || date.equals(end);
    }

    private static boolean isCurAfterThanEnd() {
        return cur.after(end) || cur.equals(end);
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read()&(1<<4)-1;
        while ((c=System.in.read()) > (1<<5)) {
            n = (n<<1) + (n<<3) + (c&(1<<4)-1);
        }
        return n;
    }
}
