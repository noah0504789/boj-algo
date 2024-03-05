package org.example.simulation;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/03/04
 * @link https://www.acmicpc.net/problem/20056
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf
 */
public class 마법사_상어와_파이어볼 {
    private static final Queue<FireBall> queue = new ArrayDeque<>(), calQueue = new ArrayDeque<>();
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int[][] DIRS = {{UP},{UP,RIGHT},{RIGHT},{DOWN,RIGHT},{DOWN},{DOWN,LEFT},{LEFT},{UP,LEFT}};
    private static final int[] dr = {-1,0,1,0};
    private static final int[] dc = {0,1,0,-1};
    private static FireBall[][] board;
    private static BitSet visitBit = new BitSet();
    private static int N, M, K, ans;
    private static int r, c, m, s, d;
    static class FireBall {
        int r, c, m, s, d;
        FireBall next;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            next = null;
        }

        public FireBall(FireBall cur, FireBall next) {
            this.r = cur.r;
            this.c = cur.c;
            this.m = cur.m;
            this.s = cur.s;
            this.d = cur.d;
            this.next = next;
        }

        public void move() {
            int[] dirs = DIRS[d];
            int nr = r, nc = c;

            for (int i = 0; i < s; i++) {
                for (int dir : dirs) {
                    nr += dr[dir];
                    nc += dc[dir];
                }
            }

            r = (nr+N)%N;
            c = (nc+N)%N;
        }

        public FireBall union() {
            int cnt = 0;
            boolean allEven = true, allOdd = true;
            FireBall moveF = this, unionF = new FireBall(r, c, 0, 0, -100);

            for (; moveF != null; moveF = moveF.next, cnt++) {
                unionF.m += moveF.m;
                unionF.s += moveF.s;

                if (moveF.d % 2 == 0) allOdd = false;
                else allEven = false;
            }

            unionF.m /= 5;
            unionF.s /= cnt;
            unionF.d = (allEven||allOdd) ? -1 : -2;

            return unionF;
        }

        public FireBall[] divide() {
            if (this.m == 0) return null;

            FireBall[] divided = new FireBall[4];

            int d = this.d == -1 ? 0:1;
            for (int i = 0; i < 4; i++) {
                divided[i] = new FireBall(r, c, m, s, d);
                d+=2;
            }

            return divided;
        }

        public void destroy() {
            board[r][c] = null;
        }
    }

    public static void main(String[] args) throws IOException {
        N = readInt();
        board = new FireBall[N][N];

        M = readInt();
        K = readInt();

        for (int i = 0; i < M; i++) {
            r = readInt()-1;
            c = readInt()-1;
            m = readInt();
            s = readInt();
            d = readInt();

            queue.offer(new FireBall(r, c, m, s, d));
        }

        while (K-- > 0) {
            visitBit.clear();
            ans = 0;

            while (!queue.isEmpty()) {
                FireBall curF = queue.poll();
                curF.move();
                int r = curF.r, c = curF.c;
                visitBit.set(r*N+c);
                if (board[r][c] != null) {
                    board[r][c] = new FireBall(curF, board[r][c]);
                } else {
                    board[r][c] = curF;
                }
            }

            for (int vBit = visitBit.nextSetBit(0); vBit>=0; vBit = visitBit.nextSetBit(vBit+1)) {
                int vr = vBit/N, vc = vBit%N;
                calQueue.offer(board[vr][vc]);
            }

            while (!calQueue.isEmpty()) {
                FireBall moveF = calQueue.poll();

                if (moveF.next == null) {
                    ans += moveF.m;
//                    moveF.move();
                    queue.offer(moveF);
                } else {
                    FireBall newF = moveF.union();
                    if (newF.m > 0) {
                        ans += newF.m*4;
                        for (FireBall f : newF.divide()) queue.offer(f);
                    } else {
                        newF.destroy();
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > 1<<5) {
            n = n<<1+n<<3+c&((1<<4)-1);
        }
        return n;
    }
}
