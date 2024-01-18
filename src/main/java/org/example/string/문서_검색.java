package org.example.string;

// 영문 문서내에 검색 함수 만들기

// Requirements
// 어떤 단어가 총 몇 번 등장하는지
// - 이미 센 단어는 다음 검색의 대상에서 제외됩니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문서_검색 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String document = br.readLine(), searchWord = br.readLine();
        int ans = 0, startIdx = 0, searchWordLength = searchWord.length();

        while (startIdx < document.length()) {
            startIdx = document.indexOf(searchWord, startIdx);
            if (startIdx == -1) {
                break;
            }

            startIdx += searchWordLength;
            ans++;
        }

        System.out.println(ans);
    }
}
