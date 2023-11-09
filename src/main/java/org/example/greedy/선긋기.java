package org.example.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class 선긋기 {

    static int N;
    static List<Line> lines;

    public static void main(String[] args) throws IOException {
        init();

        lines = lines.stream().sorted().collect(Collectors.toList());

        int ans = 0, max = Integer.MIN_VALUE;

        // 한줄씩 순회하면서 이어질 수 있는지 확인하기
        for (Line line : lines) {
            if (line.start > max) {
                ans += line.length;
            } else if (line.end > max){
                ans += line.end-max;
            }

            max = Math.max(max, line.end);
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            lines.add(new Line(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
    }

    static class Line implements Comparable<Line> {
        int start, end, length;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end - start;
        }

        @Override
        public int compareTo(Line o) {
            if (this.start == o.start) {
                return o.end - this.end;
            } else {
                return this.start - o.start;
            }
        }
    }
}
