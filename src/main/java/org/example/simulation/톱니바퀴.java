package org.example.simulation;

// 톱니바퀴
// - 8개의 톱니를 가짐
//   - 각 톱니는 N or S 극임
// - 회전
//   - 시계방향 or 반시계방향으로 회전함

import java.io.*;
import java.util.*;

public class 톱니바퀴 {
    static int SIZE = 8, CNT = 4, N = 0, S = 1, Clockwise = 1, CounterClockwise = -1;
    static int twelveIdx = 0, threeIdx = 2, nineIdx = 6;
    static int rightCogIdx = threeIdx, leftCogIdx = nineIdx;
    static int rotateCnt, cogWheelIdx, rotationDir, ans;
    static int[] input;
    static int[][] nums;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < rotateCnt; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            cogWheelIdx = input[0]-1;
            rotationDir = input[1];

            dfs1(cogWheelIdx, -1*rotationDir);
            dfs2(cogWheelIdx, -1*rotationDir);
            rotate(cogWheelIdx, rotationDir);
        }

        calculate();

        System.out.println(ans);
    }

    private static void dfs1(int curIdx, int nextDir) {
        int nextIdx = curIdx-1;
        if (nextIdx < 0) return;
        if (nums[curIdx][leftCogIdx] != nums[nextIdx][rightCogIdx]) {
            dfs1(nextIdx, -1*nextDir);
            rotate(nextIdx, nextDir);
        }
    }

    private static void dfs2(int curIdx, int nextDir) {
        int nextIdx = curIdx+1;
        if (nextIdx > CNT-1) return;
        if (nums[curIdx][rightCogIdx] != nums[nextIdx][leftCogIdx]) {
            dfs2(nextIdx, -1*nextDir);
            rotate(nextIdx, nextDir);
        }
    }

    private static void rotate(int cogWheelIdx, int rotationDir) {
        if (rotationDir == Clockwise) {
            int tmp = nums[cogWheelIdx][SIZE-1];
            System.arraycopy(nums[cogWheelIdx], 0, nums[cogWheelIdx], 1, SIZE-1);
            nums[cogWheelIdx][0] = tmp;
        } else if (rotationDir == CounterClockwise) {
            int tmp = nums[cogWheelIdx][0];
            System.arraycopy(nums[cogWheelIdx], 1, nums[cogWheelIdx], 0, SIZE-1);
            nums[cogWheelIdx][SIZE-1] = tmp;
        }
    }

    private static void calculate() {
        if (nums[0][twelveIdx] == S) ans += 1;
        if (nums[1][twelveIdx] == S) ans += 2;
        if (nums[2][twelveIdx] == S) ans += 4;
        if (nums[3][twelveIdx] == S) ans += 8;
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        nums = new int[CNT][SIZE];

        for (int i = 0; i < CNT; i++) {
            nums[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        rotateCnt = Integer.parseInt(br.readLine());

        ans = 0;
    }
}
