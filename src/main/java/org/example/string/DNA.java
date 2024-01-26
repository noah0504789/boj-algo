package org.example.string;

// DNA
// * 뉴클레오티드 (Adenine, Thymine, Guanine, Cytosine)
//   - n개의 뉴클레오티드들로 구성됨
//   - 첫글자를 따서 DNA를 표현

// * Hamming Distance
//   - 길이가 같은 DNA 대상
//   - 각 위치의 뉴클레오티드 문자가 다른 것의 개수

import java.io.*;
import java.util.*;

public class DNA {
    static int cnt, length, ans = 0;
    static int[] inputNums;
    static char[][] dnaArrs;
    static StringBuffer sb = new StringBuffer();
    static Map<Character, Integer> map = new HashMap<>();
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        inputNums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cnt = inputNums[0];
        length = inputNums[1];
        dnaArrs = new char[cnt][];

        for (int i = 0; i < cnt; i++) {
            dnaArrs[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < cnt; j++) {
                char key = dnaArrs[j][i];
                map.put(key, map.getOrDefault(key, 0)+1);
            }
            Character c = map.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .findFirst().get().getKey();

            sb.append(c);
            ans += cnt - map.get(c);

            map.clear();
        }

        System.out.println(sb);
        System.out.println(ans);
    }
}
