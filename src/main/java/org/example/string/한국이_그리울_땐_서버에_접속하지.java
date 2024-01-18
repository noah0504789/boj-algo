package org.example.string;

// 디렉토리에 있는 파일을 패턴 문자열로 검색하기

// - 패턴
//   - 영어 소문자 (n개) + '*' (1개)
// - '*'
//   - 다수의 영문자 및 공백으로 대체될 수 있습니다.
//   - 중간에 위치합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 한국이_그리울_땐_서버에_접속하지 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String YES = "DA", NO = "NE";

    public static void main(String[] args) throws IOException {
        int inputCnt = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        for (int i = 0; i < inputCnt; i++) {
            String fileName = br.readLine();

            if (!isConsistent(pattern, fileName)) {
                System.out.println(NO);
            } else {
                System.out.println(YES);
            }
        }
    }

    private static boolean isConsistent(final String pattern, final String fileName) {
        int starIdx = pattern.indexOf('*');
        String lPattern = pattern.substring(0, starIdx), rPattern = pattern.substring(starIdx+1);

        int lPatternIdx = fileName.indexOf(lPattern);
        int rPatternIdx = fileName.lastIndexOf(rPattern);

        return lPatternIdx == 0 & rPatternIdx > 1 & rPatternIdx + rPattern.length() == fileName.length();
    }
}
