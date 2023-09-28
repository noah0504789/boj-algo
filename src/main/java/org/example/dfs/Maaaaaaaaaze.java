package org.example.dfs;

import java.util.*;
import java.io.*;
public class Maaaaaaaaaze {

    static int answer = Integer.MAX_VALUE, heights = 5, rows = 5, cols = 5;
    static int[][][] maze = new int[5][5][5];
    static boolean[][][] visited = new boolean[5][5][5];
    static int[][] dirs = {
        {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, 0}, {1, 0, 0}
    };

    static class Node {
        int z, y, x, turn;
        Node(int z, int y, int x, int turn) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.turn = turn;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        boolean[] check = new boolean[heights];
        dfs(0, check, new int[5][5][5]);
        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < heights; i++) {
            int[][] table = maze[i];
            for (int j = 0; j < rows; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < cols; k++) {
                    table[j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    private static void dfs(int depth, boolean[] check, int[][][] currentMaze) {
        if (depth == heights) {
            if (currentMaze[0][0][0] == 1 && currentMaze[4][4][4] == 1) {
                int turn = bfs(currentMaze);
                if (turn == 12) {
                    answer = 12;
                    return;
                }
                if (turn > -1) {
                    answer = Math.min(answer, turn);
                }
            }
            return;
        }

        for (int i = 0; i < heights; i++) {
            if (!check[i]) {
                check[i] = true;
                for (int j = 0; j < 4; j++) {
                    currentMaze[depth] = rotate90(maze[i], j);
                    dfs(depth+1, check, currentMaze);
                    currentMaze[depth] = maze[i];
                }
                check[i] = false;
            }
        }
    }

    private static int[][] rotate90(int[][] table, int count) {
        if (count == 0) return table;

        int rows = table.length, cols = table[0].length;
        int[][] rotatedTable = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedTable[j][rows-1-i] = table[i][j];
            }
        }

        return rotate90(rotatedTable, count-1);
    }

    private static void initVisited(boolean[][][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                Arrays.fill(visited[i][j], false);
            }
        }
    }

    private static int bfs(int[][][] maze) {
        initVisited(visited);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node cp = queue.poll();
            int cz = cp.z, cy = cp.y, cx = cp.x, ct = cp.turn;
            if (cz == heights-1 && cy == rows-1 && cx == cols-1) {
                return ct;
            }

            for (int[] dir : dirs) {
                int nz = cz + dir[0], ny = cy + dir[1], nx = cx + dir[2], nt = ct + 1;
                if (validate(nz, ny, nx, maze)) {
                    visited[nz][ny][nx] = true;
                    queue.offer(new Node(nz, ny, nx, nt));
                }
            }
        }

        return -1;
    }

    private static boolean validate(int z, int y, int x, int[][][] maze) {
        if (z < 0 || z >= heights || y < 0 || y >= rows || x < 0 || x >= cols) return false;
        if (maze[z][y][x] == 0) return false;
        if (visited[z][y][x]) return false;
        return true;
    }
}
