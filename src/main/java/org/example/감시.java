package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import static org.example.감시.Type.BIDIRECT_RIGHT_ANGLE;
import static org.example.감시.Type.BIDIRECT_STRAIGHT;
import static org.example.감시.Type.DIR.*;
import static org.example.감시.Type.DIR;
import static org.example.감시.Type.QUADIRECT;
import static org.example.감시.Type.TRIDIRECT;

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
    private static long a;
    private static int N, M, ans, cctvCnt;

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
            UP, RIGHT, DOWN, LEFT, LR, UD, UR, RD, DL, LU, URD, RDL, DLU, LUR, URDL;
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

        public long[] rotate(long curBit) {
            // TODO: type이 가진 회전종류를 모두 가져오고, 종류별로 회전한 후의 결과를 pointNum을 기준값으로 비트마스킹 값으로 반환하기
            for (DIR dir : getRotations()) {

            }

            return new long[] {1};
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

                int pointNum = r * N + c;

                if (board[r][c] == UNDIR) cctvs[cctvCnt++] = new CCTV(pointNum, Type.UNDIRECT);
                else if (board[r][c] == BIDIR_STRAIGHT) cctvs[cctvCnt++] = new CCTV(pointNum, BIDIRECT_STRAIGHT);
                else if (board[r][c] == BIDIR_RIGHT_ANGLE) cctvs[cctvCnt++] = new CCTV(pointNum, BIDIRECT_RIGHT_ANGLE);
                else if (board[r][c] == TRIDIR) cctvs[cctvCnt++] = new CCTV(pointNum, TRIDIRECT);
                else if (board[r][c] == QUADIR) cctvs[cctvCnt++] = new CCTV(pointNum, QUADIRECT);
            }
        }

        long init = 0;

        dfs(0, init);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int depth, long curBit) {
        if (depth == cctvCnt) {
            // TODO : 사각지대 수 계수 및 ans 갱신

            return;
        }

        for (long rotatedBit : cctvs[depth].rotate(curBit)) {
            dfs(depth+1, rotatedBit);
        }
    }
}
