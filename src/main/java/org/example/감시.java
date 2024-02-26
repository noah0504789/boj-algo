package org.example;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static org.example.감시.Type.*;
import static org.example.감시.Type.DIR.*;

/**
 * @author noah kim
 * @date 2024-02-21
 * @link https://www.acmicpc.net/problem/15683
 * @requirement 얻을 수 있는 최소 크기의 사각지대를 출력하라.
 * @summary
    [지도]
    - 크기: N*M
    - 칸: 빈칸(0), CCTV(1~5), 벽(6)

    [CCTV]
    - 개수: K개 (1<=K<=8)
    - 종류: 1(단방향), 2(양방향:수직/수평), 3(양방향:직각), 4(세방향), 5(네방향)
    - 방향: 상하좌우
    - 회전: 직각(양방향)
    - 감시영역: 방향에 있는 줄의 전체 칸 (단, 벽 통과 X - 벽 너머 감시 불가)
 * @input
    - N: 높이, M: 너비 (1<=N,M<=8)
    - 지도 정보
 * @output
 * @time_complex
 * @perf
 */
public class 감시 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final List<Long> rotatedBits = new ArrayList<>();
    private static final CCTV[] cctvs = new CCTV[8];
    private static final int EMPTY = 0;
    private static final int UNDIR = 1;
    private static final int BIDIR_STRAIGHT = 2;
    private static final int BIDIR_RIGHT_ANGLE = 3;
    private static final int TRIDIR = 4;
    private static final int QUADIR = 5;
    private static final int WALL = 6;

    private static StringTokenizer st;
    private static int[][] board;
    private static boolean flag;
    private static int N, M, ans, cctvCnt, wallCnt;

    enum Type {
        UNDIRECT(UP, RIGHT, DOWN, LEFT), BIDIRECT_STRAIGHT(LR, UD), BIDIRECT_RIGHT_ANGLE(UR, RD, DL, LU), TRIDIRECT(URD, RDL, DLU, LUR), QUADIRECT(URDL);

        Type(DIR... rotations) {
            this.rotations = List.of(rotations);
        }

        private final List<DIR> rotations;

        public List<DIR> getRotations() {
            return rotations;
        }

        enum DIR {
            UP(감시::up), RIGHT(감시::right), DOWN(감시::down), LEFT(감시::left),
            LR(감시::left, 감시::right), UD(감시::up, 감시::down), UR(감시::up, 감시::right), RD(감시::right, 감시::down), DL(감시::down, 감시::left), LU(감시::left, 감시::up),
            URD(감시::up, 감시::right, 감시::down), RDL(감시::right, 감시::down, 감시::left), DLU(감시::right, 감시::down, 감시::up), LUR(감시::left, 감시::up, 감시::right),
            URDL(감시::up, 감시::right, 감시::down, 감시::left);
            private final LongUnaryOperator[] rotateFuncs;

            DIR(LongUnaryOperator... rFuncs) {
                this.rotateFuncs = rFuncs;
            }

            public LongUnaryOperator combinedFunc() {
                return Arrays.stream(rotateFuncs).reduce(LongUnaryOperator.identity(), LongUnaryOperator::andThen);
            }
        }
    }

    static class CCTV {
        final int pointNum;
        final Type type;

        public CCTV(int pointNum, Type type) {
            this.pointNum = pointNum;
            this.type = type;
        }

        public List<DIR> getRotations() {
            return type.getRotations();
        }

        public Long[] rotate(long curBit) {
            rotatedBits.clear();

            for (LongUnaryOperator rF : getRotations().stream().map(DIR::combinedFunc).toArray(LongUnaryOperator[]::new)) {
                rotatedBits.add(rF.applyAsLong(curBit));
            }

            return rotatedBits.toArray(Long[]::new);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());

                int pointNum = r * M + c;

                if (board[r][c] == UNDIR) cctvs[cctvCnt++] = new CCTV(pointNum, UNDIRECT);
                else if (board[r][c] == BIDIR_STRAIGHT) cctvs[cctvCnt++] = new CCTV(pointNum, BIDIRECT_STRAIGHT);
                else if (board[r][c] == BIDIR_RIGHT_ANGLE) cctvs[cctvCnt++] = new CCTV(pointNum, BIDIRECT_RIGHT_ANGLE);
                else if (board[r][c] == TRIDIR) cctvs[cctvCnt++] = new CCTV(pointNum, TRIDIRECT);
                else if (board[r][c] == QUADIR) cctvs[cctvCnt++] = new CCTV(pointNum, QUADIRECT);
                else if (board[r][c] == WALL) wallCnt++;
            }
        }

        long init = 0;

        dfs(0, init);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int depth, long curBit) {
        if (depth == cctvCnt) {
            int cnt = Long.bitCount(curBit);
            if (cnt == N*M - (cctvCnt+wallCnt)) flag = true;
            ans = Math.max(ans, cnt);

            return;
        }

        for (long rotatedBit : cctvs[depth].rotate(curBit)) {
            if (flag) break;
            dfs(depth+1, rotatedBit);
        }
    }

    private static long up(long curBit) {
        int r = (int) (curBit/N), c = (int) (curBit%N);

        while (--r >= 0) {
            if (board[r][c] == WALL) break;
            if (board[r][c] != EMPTY) continue;

            curBit |= 1L<<(r*N+c);
        }

        return curBit;
    }

    private static long down(long curBit) {
        int r = (int) (curBit/N), c = (int) (curBit%N);

        while (++r < N) {
            if (board[r][c] == WALL) break;
            if (board[r][c] != EMPTY) continue;

            curBit |= 1L<<(r*N+c);
        }

        return curBit;
    }

    private static long left(long curBit) {
        int r = (int) (curBit/N), c = (int) (curBit%N);

        while (--c >= 0) {
            if (board[r][c] == WALL) break;
            if (board[r][c] != EMPTY) continue;

            curBit |= 1L<<(r*N+c);
        }

        return curBit;
    }

    private static long right(long curBit) {
        int r = (int) (curBit/N), c = (int) (curBit%N);

        while (++c < M) {
            if (board[r][c] == WALL) break;
            if (board[r][c] != EMPTY) continue;

            curBit |= 1L<<(r*N+c);
        }

        return curBit;
    }
}
