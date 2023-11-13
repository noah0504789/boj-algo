package org.example.backtracking;

// 왼쪽부터 계란 깨기를 하여, 가장 많은 계란이 깨지는 갯수를 리턴하라

// 계란 깨기
// - 내구도가 0 이하가 될 경우 깨짐
//   - 각 계란의 내구도 : 상대 계란의 무게만큼 깎임
// - 계란 깨는 법
//   -  가장 왼쪽 계란으로 깨지지 않은 다른 계란을 친다

// 계란
// - 내구도, 무게

import java.io.*;
import java.util.*;

public class 계란으로_계란치기 {

    static int N;
    static long ans = 0;
    static int[] input;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0);

        System.out.println(ans);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            ans = Math.max(ans, Arrays.stream(eggs).filter(Egg::isCracked).count());
            return;
        }

        if (eggs[depth].isCracked()) {
            dfs(depth+1);
            return;
        }

        boolean hasUnbrokenEgg = false;
        for (int i = 0; i < N; i++) {
            if (i == depth || eggs[i].isCracked()) continue;

            hasUnbrokenEgg = true;

            Egg holdingEgg = eggs[depth];
            Egg beatenEgg = eggs[i];

            int hs = holdingEgg.cs;
            int bs = beatenEgg.cs;

            holdingEgg.crack(beatenEgg);
            dfs(depth+1);
            holdingEgg.restore(hs);
            beatenEgg.restore(bs);
        }

        if (!hasUnbrokenEgg) dfs(depth+1);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            eggs[i] = new Egg(input[0], input[1]);
        }
    }

    static class Egg {
        int cs, w;

        public Egg(int s, int w) {
            this.cs = s;
            this.w = w;
        }

        public void crack(Egg o) {
            this.cs -= o.w;
            o.cs -= this.w;
        }

        public boolean isCracked() {
            if (this.cs <= 0) return true;

            return false;
        }

        public void restore(int ps) {
            this.cs = ps;
        }
    }
}
