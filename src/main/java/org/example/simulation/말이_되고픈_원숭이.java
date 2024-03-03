package org.example.simulation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author noah kim
 * @date 2024/03/03
 * @link https://www.acmicpc.net/problem/1600
 * @requirement 맨 왼쪽에서 오른쪽 하단까지 최소 동작으로 이동할 때의 동작 횟수를 구하라. (이동 불가 시, -1)
 * @summary
    [이동]
    - 점프
      - (이동방향으로) 대각선+직각
      - 장애물 뛰어 넘을 수 있음
      - 허용횟수: k번
    - 걷기: 사방
 * @input
 * @output
 * @time_complex
 * @perf 122208kb / 644ms
 */
public class 말이_되고픈_원숭이 {
    private static final Queue<Turn> queue = new LinkedList<>();
    private static final int GROUND = 0;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int JUMP_CNT = 8;
    private static final int[][] DIRS = {{LEFT,LEFT,UP},{LEFT,UP,UP},{LEFT,DOWN,DOWN},{LEFT,LEFT,DOWN},{RIGHT,UP,UP},{RIGHT,RIGHT,UP},{RIGHT,RIGHT,DOWN},{RIGHT,DOWN,DOWN},{UP},{RIGHT},{DOWN},{LEFT}};
    private static final int[] dr = {-1,0,1,0};
    private static final int[] dc = {0,1,0,-1};
    private static int[][] board;
    private static boolean[][][] visited;
    private static int sr, sc, destR, destC;
    private static int K, W, H;

    static class Turn {
        int r, c;
        int turn, jumpCnt;

        public Turn(int r, int c) {
            this.r = r;
            this.c = c;
            this.turn = 0;
            jumpCnt = 0;
        }

        private Turn(Turn origin) {
            this.r = origin.r;
            this.c = origin.c;
            this.turn = origin.turn;
            this.jumpCnt = origin.jumpCnt;
        }

        public Turn nextTurn() {
            return new Turn(this);
        }

        public void visitJump(int r, int c) {
            this.r = r;
            this.c = c;
            visited[r][c][++jumpCnt] = true;
            turn++;
        }

        public void visitWalk(int r, int c) {
            this.r = r;
            this.c = c;
            visited[r][c][jumpCnt] = true;
            turn++;
        }

        public boolean canJump() {
            return jumpCnt < K;
        }

        public boolean isArrived() {
            return r == destR && c == destC;
        }
    }

    public static void main(String[] args) throws IOException {
        K = readInt();
        W = readInt();
        H = readInt();
        sr = sc = 0;
        destR = H-1;
        destC = W-1;

        board = new int[H][W];
        visited = new boolean[H][W][K+1];

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                board[r][c] = readInt();
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        queue.offer(new Turn(sr, sc));
        visited[sr][sc][0] = true;

        while (!queue.isEmpty()) {
            Turn curT = queue.poll();
            int r = curT.r, c = curT.c, jumpCnt = curT.jumpCnt;

            if (curT.isArrived()) return curT.turn;

            for (int d = 0; d < DIRS.length; d++) {
                int[] np = getPoint(r, c, d);
                int nr = np[0], nc = np[1];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;

                if (d < JUMP_CNT && canVisitJump(nr, nc, jumpCnt)) {
                    Turn nt = curT.nextTurn();
                    nt.visitJump(nr, nc);
                    queue.offer(nt);
                } else if (d >= JUMP_CNT && canVisitWalk(nr, nc, jumpCnt)) {
                    Turn nt = curT.nextTurn();
                    nt.visitWalk(nr, nc);
                    queue.offer(nt);
                }
            }
        }

        return -1;
    }

    private static int[] getPoint(int r, int c, int d) {
        int nr = r, nc = c;

        for (int dir : DIRS[d]) {
            nr += dr[dir];
            nc += dc[dir];
        }

        return new int[] {nr, nc};
    }

    private static boolean canVisitJump(int r, int c, int jumpCnt) {
        return board[r][c] == GROUND && jumpCnt < K && !visited[r][c][jumpCnt+1];
    }

    private static boolean canVisitWalk(int r, int c, int jumpCnt) {
        return board[r][c] == GROUND && !visited[r][c][jumpCnt];
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > (1<<5)) {
            n = (n<<1) + (n<<3) + (c&(1<<4)-1);
        }
        return n;
    }
}
