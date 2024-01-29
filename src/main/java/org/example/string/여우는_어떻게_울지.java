package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 여우는_어떻게_울지 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final String endMsg = "what does the fox say?";
    static String[] input;
    static List<String> list = new ArrayList<>();
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        int loopNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < loopNum; i++) {
            input = br.readLine().split(" ");

            while (true) {
                String s = br.readLine();
                if (s.equals(endMsg)) break;

                String[] split = s.split(" ");
                list.add(split[split.length-1]);
            }

            for (int j = 0; j < input.length; j++) {
                String word = input[j];
                if (list.contains(word)) continue;

                sb.append(word + " ");
            }

            System.out.println(sb.toString().trim());
            sb.setLength(0);
            list.clear();
        }
    }
}
