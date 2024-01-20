package org.example.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.function.Predicate;

// <variable naming convention>

// java (camelCase)
// - the first word : lowercase
// - next words : capitalize

// c++ (snake_case)
// - all words : lowercase
// - delimiter : _

// Requirements

// 입력받은 변수의 네이밍 컨벤션을 토글방식으로 변환해서 리턴한다
// - 다루는 네이밍 컨벤션 : java, c++ (다른 컨벤션 입력 시, 에러 발생)

public class Java_vs_Cpp {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String errorMsg = "Error!";

    public static void main(String[] args) throws IOException {
        String inputStr = br.readLine();

        System.out.println(convertWord(inputStr));
    }

    private static String convertWord(final String s) {
        for (ProgrammingLanguage language : ProgrammingLanguage.values()) {
            // 문자열 네이밍 컨벤션 확인
            if (language.isMine(s)) {
                // 토글된 네이밍 컨벤션으로 컨버팅
                return language.getConvertedStr(s);
            }
        }

        return errorMsg;
    }

    enum ProgrammingLanguage {
        JAVA(s -> s.matches("^[a-z]+(?:[A-Z][a-z0-9]*)*$"), camel -> {
            StringBuilder snake = new StringBuilder();

            for (char c : camel.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    snake.append("_");
                    snake.append(Character.toUpperCase(c));
                } else {
                    snake.append(Character.toLowerCase(c));
                }
            }

            return snake.toString().toLowerCase();
        })
        , CPP(s -> s.matches("^[a-z]+(_[a-z]+)*$"), snake -> {
            String[] splitUnderscoreStrs = snake.split("_");
            StringBuilder camel = new StringBuilder(splitUnderscoreStrs[0]);

            for (int i = 1; i < splitUnderscoreStrs.length; i++) {
                String curS = splitUnderscoreStrs[i];
                camel.append(curS.substring(0, 1).toUpperCase());
                camel.append(curS.substring(1).toLowerCase());
            }

            return camel.toString();
        });

        private final Predicate<String> isSelf;
        private final Function<String, String> convert;

        ProgrammingLanguage(Predicate<String> isSelf, Function<String, String> convert) {
            this.isSelf = isSelf;
            this.convert = convert;
        }

        public String getConvertedStr(String s) {
            return convert.apply(s);
        }

        public boolean isMine(String s) {
            return isSelf.test(s);
        }
    };
}
