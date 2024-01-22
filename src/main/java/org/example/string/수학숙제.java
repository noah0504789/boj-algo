package org.example.string;

// 글
// - 숫자 + 알파벳 소문자 * N줄

// Requirements
// 글에 있는 숫자들을 오름차순으로 정렬
// - 앞에  0이 있는 경우 생략
// - 숫자는 맨앞 혹은 맨뒤 혹은 문자 사이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class 수학숙제 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Integer lineCnt = Integer.parseInt(br.readLine());
        List<BigInteger> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < lineCnt; i++) {
            String line = br.readLine();
            boolean isNum = false;

            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                    isNum = true;
                    continue;
                }

                if (!isNum || sb.length() == 0) continue;

                list.add(new BigInteger(sb.toString()));
                sb.setLength(0);
                isNum = false;
            }

            if (!isNum || sb.length() == 0) continue;

            list.add(new BigInteger(sb.toString()));
            sb.setLength(0);
        }

        list.stream().sorted().forEach(System.out::println);
    }
}
