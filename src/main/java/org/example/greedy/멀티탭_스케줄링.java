package org.example.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.*;

// 플러그를 빼는 최소의 횟수를 리턴하라
// - 효율적인 멀티탭 사용을 위해 전기용품의 사용순서를 구성해야 한다.

// item
// - k개 이하

public class 멀티탭_스케줄링 {

    static int N, K; // 멀태탭 구멍 | 전기용품의 총 사용횟수
    static List<Integer> order;

    public static void main(String[] args) throws IOException {
        init();

        int ans = 0;
        List<Integer> multitap = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int curItem = order.get(i);

            if (multitap.contains(curItem)) continue;
            if (multitap.size() < N) {
                multitap.add(curItem);
                continue;
            }

            int indexToUnplug = -1;
            int maxDistance = -1;
            for (int j = 0; j < multitap.size(); j++) {
                int item = multitap.get(j);
                int nextUse = -1;
                for (int k = i+1; k < K; k++) {
                    if (order.get(k).equals(item)) {
                        nextUse = k;
                        break;
                    }
                }

                if (nextUse == -1) {
                    indexToUnplug = j;
                    break;
                } else if (nextUse > maxDistance) {
                    maxDistance = nextUse;
                    indexToUnplug = j;
                }
            }

            multitap.remove(indexToUnplug);
            multitap.add(curItem);
            ans++;
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        order = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
}
