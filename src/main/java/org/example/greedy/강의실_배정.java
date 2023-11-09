package org.example.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 모든 수업을 마치기 위한 최소 강의실 수를 리턴하라
// - 한 강의실은 동시에 사용 불가
// - 수업이 끝난 직후 바로 사용 가능

// 문제링크 : https://www.acmicpc.net/problem/11000
public class 강의실_배정 {

    static int N, length;
    static List<Course> courses;

    public static void main(String[] args) throws IOException {
        init();
        length = courses.size();

        Collections.sort(courses);

        PriorityQueue<Integer> roomEndTime = new PriorityQueue<>();

        for (Course course : courses) {
            if (!roomEndTime.isEmpty() && roomEndTime.peek() <= course.start) {
                roomEndTime.poll();
            }
            roomEndTime.offer(course.end);
        }

        System.out.println(roomEndTime.size());
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        courses = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            String[] Input = br.readLine().split(" ");
            Course c = new Course(Integer.parseInt(Input[0]), Integer.parseInt(Input[1]));
            courses.add(c);
        }
    }

    static class Course implements Comparable<Course> {
        int start, end;

        public Course(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Course o) {
            return this.start == o.start ? this.end - o.end : this.start - o.start;
        }
    }
}

//        while (!courses.isEmpty()) {
//            ans++;
//            startIdx = courses.indexOf(pq.peek());
//            courses.remove(startIdx);
//            dfs(pq.poll(), startIdx);
//        }

//    private static void dfs(Course course, int startIdx) {
//        if (pq.isEmpty()) return;
//
//        int i = startIdx + 1;
//        while (i < courses.size()) {
//            Course curC = courses.get(i);
//            if (curC.start >= course.end) {
//                pq.remove(curC);
//                courses.remove(curC);
//                dfs(curC, i);
//            } else {
//                i++;
//            }
//        }
//    }
